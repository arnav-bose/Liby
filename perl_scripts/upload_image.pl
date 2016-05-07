#!C:\Dwimperl\perl\bin\perl.exe
use strict;
use warnings FATAL => 'all';
use DBI;
use CGI ;
use JSON;
use MIME::Base64;


print "Content-type: text/plain\n\n";


my $q = new CGI;
my $name = $q->param('filename');
my $image = $q->param('image');

my $encoded = $image ;
#my $encoded = "adsfasdf";
my $decoded = decode_base64($encoded);

my $path_to_www = "C:\\wamp\\www";
my $picture_folder_name = "pictures";
my $photo_name = "my_photo.jpg";
my $path = "$path_to_www\\$picture_folder_name\\$photo_name";

open my $filehandle, '>', $path or die $!;
binmode $filehandle;
print $filehandle $decoded;
close $filehandle;

my $driver = "mysql";
my $database = "koha_nulibrary";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "";

my $dbh = DBI->connect($dsn, $userid, $password ) or die $DBI::errstr;

my $url = "$picture_folder_name/$photo_name";
my $query = "INSERT INTO notes(path) VALUES('$url')";

my $sth = $dbh->prepare($query);

$sth->execute() or die $DBI::errstr;


print "Done!";
