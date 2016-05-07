#!C:/Dwimperl/perl/bin/perl.exe
use strict;
use warnings FATAL => 'all';
use DBI;
use JSON;
use CGI qw('-no_undef_params' -utf8);
use Data::Dumper;

print "Content-type: text/plain\n\n";

#DATABASE DETAILS
my $driver = "mysql";
my $database = "koha_nulibrary";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "";

my $query = new CGI;
#print $query->header("text/plain");
my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;
$query = "SELECT title, author from biblio WHERE title IS NOT NULL ORDER BY timestamp";
$dbh->do( $query );
my $result = $dbh->prepare( $query );
$result->execute();
my @row = $result->fetchall_arrayref({});
my %a = ('Books' => @row);
my $json = encode_json \%a;
print $json;
$result->finish();