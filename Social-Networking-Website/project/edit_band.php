<?php
session_start();
$error = 0;

$Username = $_SESSION['s'];
 if(isset($_POST['Submit'])){ 
 if ($_POST['pass'] == $_POST['pass1'])
 {
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "db_proj_1";

	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error)
	{
		die("Connection failed: " . $conn->connect_error);
	}
	else
	{
		$sql = "UPDATE band 
		SET Password = '".$_POST['pass']."',
		`Info` = '".$_POST['info']."', city = '".$_POST['city']."',
		`webpage` = '".$_POST['link']."' 
		WHERE `band`.`BandID` = '$Username'";
		$result = $conn->query($sql);
		//echo $conn->error;
		if ($conn->errno != 0)
		{
			echo "im ahere";
			$error = 1;
		}
	}
 }
 else
 {
	// pass word mismatch
	$error = 2;
 }
 }
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
		<form name="form1" method="post" action="edit_band.php">
			<div id="content">
			
				<div class="box">
					<h2>Edit Your Band's Details </h2> 
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
							$sql = "SELECT * FROM band where BandID = '".$Username."'";
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
							$bname = $row["BandName"];
							$info = $row["Info"];
							$pass = $row["Password"];
							$city = $row["city"];
							$link = $row["webpage"];

							
						?>						
						    <div class="textField3">
							<div>Username:</div>
							<input type="text" name="Username" placeholder="Username" value= "<?php echo "$Username";?>" readonly />
						</div>
						
						<div class="textField3">
						</div>
						<div class="textField3">
						</div>
						
						<div class="textField3">
							<div>Password</div>
							<input type="password" name="pass" placeholder="Password" value="<?php echo "$pass";?>" />
						</div>
						
						<div class="textField3">
							<div>Confirm Password:</div>
							<input type="password" name="pass1" placeholder="confirm password" value="$<?php echo "$pass";?>" />
							
							</div>
						
						<div class="textField3">
						<div> </div>
						<?php if(isset($_POST['Submit'])) 
							{
								if ($error == 2) {echo "*Password do not match";}

							}
							?>
						</div>
						
						<div class="textField3">
							<div>Band Name</div>
							<input type="text" name="bName" placeholder="Band Name" value="<?php echo "$bname";?>" readonly />
						</div>
						
						<div class="textField3">
						<div></div>
						</div>
						<div class="textField3">
						</div>
						
						<div class="textField3">	
							<div>City</div>
							<input type="text" name="city" placeholder="City of origin" value="<?php echo "$city";?>" />
						</div>	
					
						<div class="textField3">	
							<div>Band's webpage</div>
							<input type="text" name="link" placeholder="hyperlink to band's webpage" value="<?php echo "$link";?>" />
						</div>	
						<div class="textField3">
						</div>
						
						<div class="textField">
							<div>Information</div>
							<input type="text" name="info" placeholder="Please enter information about band" value="<?php echo "$info";?>" /> 
						</div>
					</div>
				</div>

				<div class="buttons">
					<input type="submit" name="Submit" value="Register">
					<?php if(isset($_POST['Submit'])) 
					{
						if ($error == 0) {echo "Information updated successfully!!";}
						else {echo "Please fill in the correct information";}
					}
					?>
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