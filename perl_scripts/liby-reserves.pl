#!/usr/bin/perl -w
use strict;
use warnings FATAL => 'all';
use DBI;
use JSON;
use Data::Dumper;
use CGI qw('-no_undef_params' -utf8);

print "Content-type: text/plain\n\n";

my $query = new CGI;


my $bn = $query->param('borrowernumber');


my $driver = "mysql";
my $database = "koha_library";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "qwerty";


my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;

$query = "select borrowernumber,biblionumber,title,reservedate,date(reservedate + 3) as 'expires on' from reserves
inner join biblio using (biblionumber)
where borrowernumber = $bn"; 

#$query = "SELECT title, author FROM biblio WHERE title like '%$title%'";

my $sth = $dbh->prepare($query);
$sth->execute() or die $DBI::errstr;


my $json = encode_json $sth->fetchall_hashref('title');
print $json;

$sth->finish();