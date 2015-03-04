<?php

session_start();
?> 
<html>
<body>
<!--form action="login_success.php" method="post"-->
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


$sql = "INSERT INTO `user` (`Username`, `Password`, `fName`, `lName`, `dob`, `city`, `emailID`, `regDate`, `LastLogInTime`) VALUES\n"."(\"".$_POST["Username"]."\",\"".$_POST["Password"]."\",\"". $_POST["fName"]."\",\"". $_POST["lName"]."\",\"".$_POST["year"]."-".$_POST["mon"]."-".$_POST["date"]."\",\"". $_POST["city"]."\",\"".$_POST["emailID"]."\",\"".date("Y-m-d")."\", NULL)";
//echo $sql;
$result = $conn->query($sql);

//$sql = "SELECT * from user where Username = ".$_POST["Username"]."";

$_SESSION["s"] = $_POST['Username'];
 
header("location:login_success.php");


//$result = $conn->query($sql);
?>
    
<!--/form-->
</body>
</html>	