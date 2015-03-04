<?php

session_start();
?>

<?php
error_reporting(0);
//echo "Here is some text";
//echo "Here are is an error";
?>
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
$Username=$_POST['myusername']; 
$password=$_POST['mypassword']; 
$sql = "SELECT BandID FROM band WHERE BandID = '$Username' AND Password = '$password'";
//echo $sql;

$result = $conn->query($sql);
if ($result->num_rows == 0) 
{
    echo "Opps..!! no result found.. \r\nPlease try again !!";
}
else
{
$_SESSION["s"] = $_POST['myusername'];
$_SESSION["isBand"] = 1;
header("location:login_success.php");
}

?>