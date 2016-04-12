#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';
use DBI;
use JSON;
use Data::Dumper;
#use allow_nonref; 


#print "Hello, World!\n";

my $driver = "mysql";
my $database = "koha_nulibrary";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "1234";
my $primary_key = 'biblionumber';

my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;

my $query = "SELECT $primary_key, title, author FROM biblio
                WHERE title like 'Eragon'";

my $sth = $dbh->prepare($query);

$sth->execute() or die $DBI::errstr;

my $json = encode_json $sth->fetchall_hashref($primary_key);

print $json;


$sth->finish();
