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

$Username = $_SESSION["s"]; 
$Band = $_REQUEST["BandID"];
//echo"This is band id:: $Band";

$sql = "INSERT INTO fan (bandid, Username) VALUES ('$Band', '$Username')";
//echo "$sql";

$result = $conn->query($sql);
//echo "$result";

	

header("location:band_profile.php?BandID=$Band");

?>