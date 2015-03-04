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
$username=$_POST['myusername']; 
$password=$_POST['mypassword']; 
$sql = "SELECT Username FROM user WHERE Username = '$username' AND Password = '$password'";
//echo $sql;

$result = $conn->query($sql);

if ($result->num_rows == 0) 
{
    echo "Opps..!! no result found.. \r\nPlease try again !!";
}
else
{
$_SESSION["s"] = $_POST['myusername'];
$_SESSION["isBand"] = 0;

//echo "session var" . $_SESSION["s"] . "local ::" . $_POST['myusername'];
	//$_SESSION["c"] = $_POST["contact"];

//session_register("mypassword"); 
header("location:login_success.php");
}







/*
$host="localhost"; // Host name 
$username="root"; // Mysql username 
$password=""; // Mysql password 
$db_name="db_proj_1"; // Database name 
//$tbl_name="members"; // Table name 

// Connect to server and select databse.
$conn = @mysqli_connect("$host", "$username", "$password","$db_name")
or die("cannot connect"); 

// username and password sent from form 
$myusername=$_POST['myusername']; 
$mypassword=$_POST['mypassword']; 

// To protect MySQL injection (more detail about MySQL injection)
$myusername = stripslashes($myusername);
//echo "1: $myusername";
$mypassword = stripslashes($mypassword);
//echo "$mypassword";
$myusername = mysql_real_escape_string($myusername);
//echo "2: $myusername";
$mypassword = mysql_real_escape_string($mypassword);
//echo "2. $mypassword";
$sql="SELECT * FROM user WHERE Username='$myusername'";
echo "this is sql :: $sql";
$result=mysql_query($sql);

echo "this is result :: $result";
// Mysql_num_row is counting table row
$count=mysql_num_rows($result);
echo "the count is $count" ;
// If result matched $myusername and $mypassword, table row must be 1 row
if($count==1){

// Register $myusername, $mypassword and redirect to file "login_success.php"
//session_register("myusername");
$_SESSION['myusername'] = "$myusername";
$_SESSION['mypassword'] = "$mypassword";
//session_register("mypassword"); 
header("location:login_success.php");
}
else {
echo "Wrong Username or Password";
}
*/
?>