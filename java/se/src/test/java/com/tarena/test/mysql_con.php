<?php
$server="10.117.64.153";
$usename="enginer";
$password="dataisbest";
$con=new mysqli($server, $usename, $password, 'hmcdss2');
if (!$con){
	die("Database connect error: " . mysql_error());
}
$mysqli->query("SET NAMES 'UTF8'");
?>