<?php

session_start();
?>

<?php
error_reporting(0);
?>
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "db_proj_1";
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error)
{
    die("Connection failed: " . $conn->connect_error);
}
$follower= $_SESSION["s"]; 
$following= $_SESSION["following"]; 

$sql = "INSERT INTO `db_proj_1`.`follow` (`follower`, `following`, `dateTime`) VALUES ('$follower', '$following',". now().")";
$result = $conn->query($sql);
header("location:/*TAAK ithe*/.php");
}
?>