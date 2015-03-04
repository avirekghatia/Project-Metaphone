<?php

session_start();
$Username = $_SESSION["s"];
$ConcertID = $_REQUEST["ConcertID"];
echo "concert ID is : $ConcertID";
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

//$ConcertID= $_SESSION["ConcertID"]; 
//$following= $_REQUEST["user"]; 

//echo "follower : $follower .... following : $following";
//echo "1 : $sql";
$sql = "INSERT INTO attend (concertID, username, dateTime) VALUES ('$ConcertID','$Username', '".date("Y-m-d H:i:s", time())."')";
//echo "$sql";
$result = $conn->query($sql);
//echo "ok man done";
header("location:concert_profile.php?ConcertID=$ConcertID");

?>