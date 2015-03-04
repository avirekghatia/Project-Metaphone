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
$username=$_POST['myusername']; 
$password=$_POST['mypassword']; 
$sql = "SELECT Username FROM user WHERE Username = '$username' AND Password = '$password'";
$result = $conn->query($sql);

if ($result->num_rows == 0) 
{
		$sql = "SELECT BandID FROM band WHERE BandID = '$username' AND Password = '$password'";
		$result = $conn->query($sql);
		
		if ($result->num_rows == 0){
			echo "Username or Password is incorrect!!. Please try again later.";
		}
		else
		{
			$_SESSION["s"] = $_POST['myusername'];
			$_SESSION["isBand"] = 1;
			header("location:login_success.php");
		}
}
else
{
$_SESSION["s"] = $_POST['myusername'];
$_SESSION["isBand"] = 0;
header("location:login_success.php");
}
?>