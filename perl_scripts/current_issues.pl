#!/usr/bin/perl -w
use strict;
use warnings FATAL => 'all';
use DBI;
use JSON;
use Data::Dumper;
use CGI qw('-no_undef_params' -utf8);

print "Content-type: text/plain\n\n";

my $query = new CGI;


#my $bn = $query->param('borrowernumber');
my $bn = 1;


my $driver = "mysql";
my $database = "koha_nulibrary";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "";


my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;

$query = "select borrowernumber,cardnumber,biblio.title,author,date_due from borrowers 
inner join issues using (borrowernumber)
inner join items using (itemnumber)
inner join biblio using (biblionumber)
where borrowernumber = $bn"; 

#$query = "SELECT title, author FROM biblio WHERE title like '%$title%'";

my $sth = $dbh->prepare($query);
$sth->execute() or die $DBI::errstr;


my @row = $sth->fetchall_arrayref({});
my %a = ('Books' => @row);
my $json = encode_json \%a;
print $json;

$sth->finish();