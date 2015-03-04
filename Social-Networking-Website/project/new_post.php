<?php

session_start();
$Username = $_SESSION['s'];
$isBand = $_SESSION['isBand'];

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "db_proj_1";

//$isBand = $_SESSION

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error)
{
    die("Connection failed: " . $conn->connect_error);
}
 else{
 //$sql1 = "select * from user where Username = '$Username'";
 //echo $sql1;	
 //$result = $conn->query($sql1);
 //if ($conn->query($sql1) === TRUE)
 //{
 //echo "inside";
  @$mypost = $_POST['mypost'];
  
	if ($mypost)
	 {
		if($isBand == 0){
			$sql2 = "INSERT INTO post (username, dateTimeStamp, Content) VALUES ('$Username', '".date("Y-m-d H:i:s", time())."', '$mypost')"; 
		}else{
			$sql2 = "INSERT INTO post (BandID, dateTimeStamp, Content) VALUES ('$Username', '".date("Y-m-d H:i:s", time())."', '$mypost')"; 
		}
		
		$result = $conn->query($sql2);
		header("location:dashboard.php");
	 }
 //}
 //else
 //{
//	echo "outside";
 //}
 
 }


?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>WebStarter Admin Template - Form</title>

<meta name="description" content="" />
<meta name="keywords" content="" />
<meta http-equiv="Content-Language" content="en" />


<link rel="shortcut icon" type="image/x-icon" href="./favicon.ico" />
<link rel="stylesheet" type="text/css" href="./css/webstarter.css" media="screen" />
<link rel="stylesheet" type="text/css" href="./css/jquery-ui-1.8.13.custom.css" />
<link rel="stylesheet" type="text/css" href="./css/superfish.css" />
<link rel="stylesheet" type="text/css" href="./css/jquery.wysiwyg.css" />
<link rel="stylesheet" type="text/css" href="./css/fullcalendar.css" />

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="./js/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript" src="./js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="./js/jquery.vAlign.js"></script>
<script type="text/javascript" src="./js/jquery.disableSelection.js"></script>
<script type="text/javascript" src="./js/jquery.superfish.js"></script>
<script type="text/javascript" src="./js/fullcalendar.min.js"></script>
<script type="text/javascript" src="./js/gcal.js"></script>
<script type="text/javascript" src="./js/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="./js/ws.init.js"></script>

<!--[if lte IE 6]>

<script type="text/javascript" src="js/jquery.pngFix.js"></script>
<script type="text/javascript" src="js/jquery.pngFix.init.js"></script>

<![endif]-->

<meta charset="UTF-8"></head>

<body>

<div id="bgTop">

	<div id="core">

		<div id="bgBottom">

			<div id="header">

				<div id="logo">
					<a href="./dashboard.html"><img src="./img/ws_logo.png" alt="WebStarter Dashboard" /></a>
				</div>
				
				<div id="controls">
					<img src="./img/ws_icon_user.png" alt="User" /> Logged in: <strong>Your Name</strong>
				</div>
				
				<div id="logOff">
					<a href="#"><img src="./img/ws_logoff.png" alt="Log off" /></a>
				</div>

			</div><!-- END OF #header -->

			<div id="menu">
				<ul id="menuUl" class="sf-menu">
					<li><span class="folder"><img src="./img/key.png" align="left" style="padding-right: 4px;" alt="Login Page" /><a href="./login.html">Login Page</a></span></li>
					<li><span class="folder"><img src="./img/house.png" align="left" style="padding-right: 4px;" alt="Dashboard" /><a href="./dashboard.html">Dashboard</a></span></li>
					<li><span class="folder"><img src="./img/application_form.png" align="left" style="padding-right: 4px;" alt="Form" /><a href="./form.html">Form</a></span></li>
					<li><span class="folder"><img src="./img/application_error.png" align="left" style="padding-right: 4px;" alt="Messages" /><a href="./messages.html">Messages</a></span></li>
					<li><span class="folder"><img src="./img/application_view_columns.png" align="left" style="padding-right: 4px;" alt="Table" /><a href="./datagrid.html">Table</a></span></li>
					<li><span class="folder"><img src="./img/chart_bar.png" align="left" style="padding-right: 4px;" alt="Charts" /><a href="./charts.html">Charts</a></span></li>
					<li><span class="folder"><img src="./img/plugin.png" align="left" style="padding-right: 4px;" alt="Other Widgets" /><a href="./others.html">Other Widgets</a></span></li>
				</ul>
			</div><!-- END OF #menu -->
<form method = "POST" action = "new_post.php">
			<div id="content">
			
				<div class="box">
				
					<h2>What's on your mind??</h2>
					
					<div>
						
						
						
						<div class="richTextArea">

							<textarea name="mypost" rows="6" cols="12">Say what you want!</textarea>
						
						</div>

					</div>
					
				</div>

				<div class="buttons">
					<input type=submit name ="Submit" value = "Post">
					<!--div class="button"><a href="#"><img src="./img/accept.png" alt="Save" /> Save</a></div>
					
					<div class="button"><a href="dashboard.php"><img src="./img/application_view_columns.png" alt="Back" /> Back </a></div-->

				</div>

			</div><!-- END OF #content -->
</form>			

			<div id="footer">
				<strong>WebStarter Content Management System</strong> - 2011 &copy; YourDomainName.com
			</div>

		</div><!-- END OF #bgBottom -->

	</div><!-- END OF #core -->

</div><!-- END OF #bgTop -->

</body>
</html>