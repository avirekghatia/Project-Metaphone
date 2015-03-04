<?php
session_start();
$Username = $_SESSION['s'];
//$Password = $_SESSION['Password'];
//print_r($_SESSION);
//Echo "<h3>Username is :: $Username</h3>";
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>Registration Form</title>

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
					<a href="./dashboard.html"><img src="./img/ws_logo.png" alt="Dashboard" /></a>
				</div>
				
				<div id="controls">
					<img src="./img/ws_icon_user.png" alt="User" /> Logged in: <strong><?php echo "$Username"?></strong>
				</div>
				
				<div id="logOff">
					<a href="logout.php"><img src="./img/ws_logoff.png" alt="Log off" /></a>
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
		<form name="form1" method="post" action="">
			<div id="content">
			
				<div class="box">
				
					<h2>Enter Your Details </h2> 
					
					<div>
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
							$sql = "SELECT * FROM user where username = '".$Username."'";
							//$sql="SELECT musicName FROM music";
							//echo $sql;
							$result = $conn->query($sql);
							if ($result->num_rows == 0) 
							{
								echo "Opps..!! no result found.. \r\nPlease try again !!";
							}
							//echo $result;
							$row=mysqli_fetch_array($result);
//							INSERT INTO `db_proj_1`.`user` (`Username`, `Password`, `fName`, `lName`, `dob`, `city`, `emailID`, `regDate`, `LastLogInTime`) VALUES ('TP1001', 'TP1001', 'TP', '001', '1990-10-30', 'Boston', 'tp001@some.com', '2014-12-01 00:00:00', NULL);
							$Fname = $row["fName"];
							$lName = $row["lName"];
							$dob = $row["dob"];
							$pass = $row["Password"];
							$city = $row["city"];
							$emailID = $row["emailID"];
							$regDate = $row["regDate"];

							
						?>						<div class="textField3">
						
							<div>Username:</div>
							<input type="text" name="Username" value= "<?php echo "$Username";?>" readonly />
							
						</div>
						<div class="textField3">
						</div>
						<div class="textField3">
						</div>
						<?php
						
						?>
						<div class="textField3">
						<div>New Password</div>
						<input type="password" name="Password" value="<?php echo "$pass";?>" />
						</div>
						<div class="textField3">
						<div>Confirm Password:</div>
						<input type="password" name="password1" value="<?php echo "$pass";?>" />
						</div>
						<div class="textField3">
						</div>
						
												
						<div class="textField3">
							<div>Last Name:</div>
							<input type="text" name="lName" value="<?php echo "$lName";?>" />
						</div>
						
						<div class="textField3">
						<div>First Name:</div>
						<input type="text" name="fName" value="<?php echo "$Fname";?>" />
						</div>
						
						<div class="textField3">
						</div>
						
						<div class="textField4">
							<div>Birth Date</div>
							<input type="text" name="date" value="<?php echo "$dob";?>" />
						</div>
						
						<div class="textField4">
							<div>Registration Date </div>
							<input type="text" name="mon" value="<?php echo "$regDate";?>" readonly />
						</div>	

						<div class="textField3">	
							<div>City</div>
							<input type="text" name="city" value="<?php echo "$city";?>" />
						</div>	
						
						
						<div class="textField3">	
							<div>Email ID</div>
							<input type="text" name="emailID" value="<?php echo "$emailID";?>" />
						</div>	
					
					
					
					</div>
				
				</div>
				
				

				<div class="buttons">
				
					<input type="submit" name="Submit" value="Update">
					<!--a href="./index.html"><img src="./img/ws_login_button.png" alt="Login" title="Login" /></a-->
				

					<!--div class="button"><a href="#"><img src="./img/accept.png" alt="Save" /> Save</a></div-->
					<!--div class="button"><a href="#"><img src="./img/add.png" alt="Save and New" /> Save and New Record</a></div-->
					<!--div class="button"><a href="#"><img src="./img/application_view_columns.png" alt="Back" /> Back to List</a></div-->

				</div>

			</div><!-- END OF #content -->

		</form>	
			
			<div id="footer">
				<strong>Mukul Hodarkar</strong>
			</div>

		</div><!-- END OF #bgBottom -->

	</div><!-- END OF #core -->

</div><!-- END OF #bgTop -->

</body>
</html>