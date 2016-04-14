#!C:/Dwimperl/perl/bin/perl.exe

use strict;
use warnings FATAL => 'all';
use DBI;
use JSON;
use Data::Dumper;
# use CGI qw(:standard);
use CGI qw('-no_undef_params' -utf8);

print "Content-type: text/plain\n\n";

my $query = new CGI;
#print $query->header("text/plain");
my $title = $query->param('title');
#my $name = "Eragon";

my $driver = "mysql";
my $database = "koha_nulibrary";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "1234";
#my $primary_key = 'title';

my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;

$query = "SELECT title, author FROM biblio WHERE title like '$title'";
#my $query = "SELECT $primary_key, title, author FROM biblio ";

my $sth = $dbh->prepare($query);

$sth->execute() or die $DBI::errstr;

my $json = encode_json $sth->fetchall_hashref('title');


print $json;

$sth->finish();
