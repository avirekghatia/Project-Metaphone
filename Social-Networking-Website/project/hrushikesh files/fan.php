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
$fanOF= $_SESSION["s"]; 
$fan= $_SESSION["fan"]; 
$sql = "INSERT INTO `db_proj_1`.`fan` (`FanOF`, `Fan`) VALUES ($fanOF, $fan)";
$result = $conn->query($sql);
header("location:/*TAAK ithe*/.php");
}
?>