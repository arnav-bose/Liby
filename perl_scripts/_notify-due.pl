#!/usr/bin/perl

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
my $borrowernumber = $query->param('borrowernumber');
#my $name = "Eragon";

my $driver = "mysql";
my $database = "koha_nulibrary";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "b\@rc3l0n@";
#my $primary_key = 'title';

my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;


$query = "SELECT date_due FROM issues WHERE borrowernumber=$borrowernumber";
#my $query = "SELECT $primary_key, title, author FROM biblio ";

my $sth = $dbh->prepare($query);

$sth->execute() or die $DBI::errstr;

my $json = encode_json $sth->fetchall_hashref('date_due');

print $json;

$sth->finish();
