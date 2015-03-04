<?php
session_start();
//print_r($_SESSION);
//echo "this is username:: " . $_SESSION["s"];
if(!$_SESSION["s"]){
echo "Login Successful he kahitari nava aahe!";
header("location:login.php");
}
?>

<html>
<body>
Login Successful
<?php header("location:dashboard.php");?>
</body>
</html>