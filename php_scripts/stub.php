#!/usr/bin/env php
<?php
$now = new DateTime();
$txt = $now->format('M d h:i A');
$f = fopen("/home/shree/php_logs.txt", "a");
if($f) {
    fwrite($f, $txt . "\n");
    fclose($f);
} else {
    print "File doesn't exists";
}

?>
