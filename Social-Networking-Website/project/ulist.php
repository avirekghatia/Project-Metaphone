<?php

session_start();
$Username = $_SESSION["s"];
$ConcertID = $_REQUEST["ConcertID"];
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

//echo "follower : $follower .... following : $following";
//echo "1 : $sql";
$sql = "INSERT INTO userlist (username, ConcertID) VALUES ('$Username', '$ConcertID')";
//echo "2: $sql";
$result = $conn->query($sql);
//echo "ok man done";
header("location:concert_profile.php?ConcertID=$ConcertID");

?>