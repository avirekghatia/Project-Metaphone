<?php

session_start();
?> 
<html>
<body>
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "db_proj_1";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error)
{
    die("Connection failed: " . $conn->connect_error);
}
else
{
	// echo "connection success";
}

$sql = "INSERT INTO `db_proj_1`.`band` (`BandID`, `Password`, `BandName`, `Info`, `city`, `webpage`) VALUES ('$_POST[Username]', '$_POST[Password]', '$_POST[bName]', '$_POST[info]', '$_POST[city]', '$_POST[link]');";

$result = $conn->query($sql);

$_SESSION["s"] = $_POST['Username'];
$_SESSION["isBand"] = 1;
header("location:login_success.php");
?>
    
</body>
</html>	