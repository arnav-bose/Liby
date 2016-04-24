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
my $dbpassword = "";

my $query = new CGI;
#print $query->header("text/plain");
my $username = $query->param('username');
my $password = $query->param('password');
#my $username = "arnav.bose";
#my $password = 1234;
my $dbh = DBI->connect($dsn, $userid, $dbpassword ) or die $DBI::errstr;
$query = "SELECT borrowernumber, firstname, surname FROM borrowers WHERE email = '$username' and password = '$password'";
$dbh->do( $query );
my $result = $dbh->prepare( $query );
$result->execute();
my @row = $result->fetchall_arrayref({});
my %a = ('Login' => @row);
my $json = encode_json \%a;
print "$json";
$result->finish();