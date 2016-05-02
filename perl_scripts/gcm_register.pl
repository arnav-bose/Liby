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
#my $database = "test";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "";

my $query = new CGI;
#print $query->header("text/plain");
#my $token = $query->param('token');
#my $borrowernumber = $query->param('borrowernumber');
my $token = "fFiV1rbdWUI:APA91bGxCxWTreIV6_6MzTE2dnClgMlRbLAGQAeArqMgrqcXRwupkGbv1mTV4ibFnUqZTbmBRD_A2uR5KIuQ8ajTLCHwhyjrgLysUw-u5yYz1S6jvQU2RJpaFYbztIBOyKd8yAAsKrw9";
my $borrowernumber = 3;
my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;
$query = "INSERT INTO gcm(gcm_token, borrowernumber) values('$token', $borrowernumber)";
#$query = "INSERT INTO barcelona(token, borrowernumber) values($token, $borrowernumber)";

#$dbh->do( $query );
my $result = $dbh->prepare( $query );
$result->execute();


print "Data Sent!";
$result->finish();