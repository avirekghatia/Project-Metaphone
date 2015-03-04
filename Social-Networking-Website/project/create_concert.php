<?php
session_start();
$Username = $_SESSION['s'];
//$Password = $_SESSION['Password'];
//print_r($_SESSION);
//Echo "<h3>Username is :: $Username</h3>";

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
else
{

if(isset($_POST['submit'])){ 
	@$color= $_POST['color'];
	$str = implode(" ",$color);
	
	@$cName= $_POST['cName'];
	@$datetime= $_POST['datetime'];
	@$ticketLink= $_POST['ticketLink'];
	@$TicketPrice= $_POST['ticketPrice'];
	@$seatsAvailable= $_POST['seatsAvailable'];
	
	//if( is_array($color)){
	//while (list ($key, $val) = each ($color)) {
	
	//echo "$val <br>";
	
	$sql = "INSERT INTO concert ( concertName, BandID, venueID, datetime, ticketLink, TicketPrice, seatsAvailable) VALUES ('$cName', '$Username', '$str', '$datetime', '$ticketLink', '$TicketPrice', '$seatsAvailable')";

	//echo $sql;
	$result = $conn->query($sql);

	if ($result === TRUE) {
		echo "New record created successfully";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

	$conn->close();
	//}
	//}//
}	
}
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>New Concert</title>

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
		<form name="form1" method="post" action="">
			<div id="content">
			
				<div class="box">
					<h2>Create a new concert </h2> 
					<div>
						
						
						<div class="textField3">
							<div>Concert Name</div>
							<input type="text" name="cName" placeholder="Concert Name" value="" />
						</div>
						
						<div class="textField3">
							<!--div>Concert's Unique Identification Number</div>
							<input type="text" name="ConcertID" placeholder="Concert ID" value="" /-->
						</div>
						
						<div class="textField3">
						</div>
						
						
						<div class="selectField">
						
						<div>Venue:</div>
						
						<select name='color[]' >
						<?php 
						$sql1 = "SELECT venueID FROM venue";
						
						$result1 = $conn->query($sql1);
						if ($result1->num_rows == 0) 
						{
							echo "Opps..!! no result found.. \r\nPlease try again !!";
						}
						//echo $result;
						while ($row1=mysqli_fetch_array($result1)) {
							$Name=$row1["venueID"];
							echo "<option>$Name</option>";
						
						} 							
						?>
						
						</select> 
						</div>
						
						<div class="textField2">	
							<div>Ticket Link</div>
							<input type="text" name="ticketLink" placeholder="Link to buy tickets" value="" />
						</div>
						
						<div class="textField2">	
							<div>Date & Time (yyyy-mm-dd HH:MM:SS)</div>
							<input type="text" name="datetime" placeholder="Date and time of the concert" value="" />
						</div>
					
						<div class="textField3">	
							<div>Ticket Prices</div>
							<input type="text" name="ticketPrice" placeholder="Price" value="" />
						</div>
						<div class="textField3">	
							<div>Max seats</div>
							<input type="text" name="seatsAvailable" placeholder="maximum seats available" value="" />
						</div>
						<div class="textField3"></div>
						
					</div>
				</div>

				<div class="buttons">
					<input type="submit" name="submit" value="submit">
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