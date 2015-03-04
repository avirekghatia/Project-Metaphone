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
$following= $_REQUEST["user"]; 

//echo "follower : $follower .... following : $following";
//echo "1 : $sql";
$sql = "INSERT INTO follow (follower, following, dateTime) VALUES ('$follower', '$following','".date("Y-m-d H:i:s", time())."')";
//echo "2: $sql";
$result = $conn->query($sql);
//echo "ok man done";
header("location:profile.php?username=$following");

?>