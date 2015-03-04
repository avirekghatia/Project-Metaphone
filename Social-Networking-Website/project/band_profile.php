<?php

session_start();
$Username = $_SESSION['s'];

$Band = $_REQUEST['BandID'];
$fan = 0;
$concert = 0;

//$isBand = $_SESSION['isBand'];


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
else{

	$sql = "SELECT BandName, city, webpage, Info FROM band WHERE BandID = '$Band'";
	//echo $sql;

	$result = $conn->query($sql);

	if ($result->num_rows == 0) 
	{
		echo "Opps..!! no result found.. \r\nPlease try again !!";
	}
	else
	{ 
	
	// output data of each row
	while($row = $result->fetch_assoc()) {
		
		//$BandID = $row["BandID"];
		$BandName = $row["BandName"];
		$webpage = $row["webpage"];
		$city = $row["city"];	
		$Info = $row["Info"];
		//echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
		}
	
	}

	$sql11 = "SELECT bandid, Username FROM fan WHERE bandid = '$Band' AND Username ='$Username' ";
	//echo $sql11;

	$result11 = $conn->query($sql11);

	if ($result11->num_rows == 0) 
	{
		//echo "Opps..!! no result found.. \r\nPlease try again !!";
		$show = 1;
		//echo "YES show";
	}
	else
	{	
		$show = 0;
		//echo "No show";
//echo"$fan";
	}
	
	
	$sql1 = "SELECT bandid, Username FROM fan WHERE bandid = '$Band'";
	//echo $sql1;

	$result1 = $conn->query($sql1);

	if ($result1->num_rows == 0) 
	{
		echo "Opps..!! no result found.. \r\nPlease try again !!";
	}
	else
	{	
		while($row1 = $result1->fetch_assoc()) {	
		$bandid = $row1["bandid"];
		$Username1 = $row1["Username"];
		$fan++;
		//echo"$fan<br/>";
		}
//echo"$fan";
	}
//echo"$fan";	
	
	
	
	$sql2 = "SELECT concertName, datetime FROM concert WHERE BandID = '$Band'";
	//echo $sql2;

	$result2 = $conn->query($sql2);

	if ($result2->num_rows == 0) 
	{
		echo "Opps..!! no result found.. \r\nPlease try again !!";
	}
	else
	{	
		while($row2 = $result2->fetch_assoc()) {	
		$concertName = $row2["concertName"];
		$datetime = $row2["datetime"];
		$concert++;
		//echo"$fan<br/>";
		}
//echo"$fan";
	}
//echo"$fan";	
	
	 
	 
	 
	 
		

	
}

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title></title>

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
					<a href="./dashboard.php"><img src="./img/ws_logo.png" alt="Dashboard" /></a>
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
					<!--li><span class="folder"><img src="./img/key.png" align="left" style="padding-right: 4px;" alt="Login Page" /><a href="./login.html">Login Page</a></span></li-->
					<li><span class="folder"><img src="./img/house.png" align="left" style="padding-right: 4px;" alt="Dashboard" /><a href="dashboard.php">Dashboard</a></span></li>
					<li><span class="folder"><img src="./img/application_form.png" align="left" style="padding-right: 4px;" alt="Form" /><a href="profile.php">Profile</a></span></li>
					
				</ul>
			</div><!-- END OF #menu -->

			<div id="content">
			
				
				<div class = "box">
				
				<div class="text2">
				<h1>Profile : <?php echo "$BandName";?></h1>
				</div>
				
				</div>
				<div class="box">
				<h2>Quick Info</h2>
					<div>
						<div class="text">
							<p><?php echo "$Info";?></p>
						</div>
					</div>
				</div>
				<div class="box">
					<h2></h2>
					<div>
						<div class="text">
							<div class="infoColumnUp">
								<div class="infoColumnIcon"><img src="./img/quick_info_up.png" alt="Up" /></div>
								<div class="infoColumnNumber"><div class="infoColumnNumberBg"><?php echo"$fan";?></div></div>
								<p>Followers</p>
							</div>
							
							<div class="infoColumnDown">
								<div class="infoColumnIcon"><img src="./img/quick_info_same.png" alt="Down" /></div>
								<div class="infoColumnNumber"><div class="infoColumnNumberBg"><?php echo"$concert";?></div></div>
								<p>Concerts</p>
							</div>
							<!--div class="bigIcons"-->
							<?php if ($show == 1) { ?>
								<div class="infoColumnUp">
								<a  href="fan.php?BandID=<?php echo"$Band";?>"><img src="./img/quick_info_up.png" alt="" /></a>
								<div class="bigIconText"><a href="#">Follow</a></div>
								</div>
							<?php } ?>

							<div class="clear"></div>
						</div>
					</div>
				</div>

				<div class="box">
					<h2>Recent Posts</h2>
					<div>
						<div class="text">
							<table class="">
								<thead>
								<tr>
									<th>Post</th>
									<th>Date</th>
								</tr>
								</thead>

								<tbody>
								
								<?php
								$sql4 = "SELECT Content, dateTimeStamp FROM post WHERE BandID = '$Band'";
								//	echo $sql2;

								$result4 = $conn->query($sql4);

								if ($result4->num_rows == 0) 
								{
									echo "Opps..!! no result found.. \r\nPlease try again !!";
								}
								else
								{	
									while($row4 = $result4->fetch_assoc()) {
									$Content = $row4["Content"];
									$dateTimeStamp = $row4["dateTimeStamp"];
									//$datetime = $row3["datetime"];
								?>	
									<tr>
									<td><!--a href="concert_profile.php?ConcertID=<?php// echo"$ConcertID";?>"!--><?php echo"$Content";?></a></td>
									<td><?php echo"$dateTimeStamp";?></td>
									</tr>
								<?php			
									}
							//echo"$fan";
								}
								?>
								
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
				
				<div class="box2">
					<h2>Concerts hosted by <?php echo"$BandName"; ?></h2>

					<div>
						<table>
							<thead>
								<tr>
									<th>Concert Name</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
							
								<?php
								$sql3 = "SELECT ConcertID, concertName, datetime FROM concert WHERE BandID = '$Band'";
								//	echo $sql2;

								$result3 = $conn->query($sql3);

								if ($result3->num_rows == 0) 
								{
									echo "Opps..!! no result found.. \r\nPlease try again !!";
								}
								else
								{	
									while($row3 = $result3->fetch_assoc()) {
									$ConcertID = $row3["ConcertID"];
									$concertName = $row3["concertName"];
									$datetime = $row3["datetime"];
								?>	
									<tr>
									<td><a href="concert_profile.php?ConcertID=<?php echo"$ConcertID";?>"><?php echo"$concertName";?></a></td>
									<td><?php echo"$datetime";?></td>
									</tr>
								<?php			
									}
							//echo"$fan";
								}
								?>
								
							</tbody>
						</table>
					</div>
				</div>

			</div><!-- END OF #content -->

			<div id="footer">
				<strong>WebStarter Content Management System</strong> - 2011 &copy; YourDomainName.com
			</div>

		</div><!-- END OF #bgBottom -->
		
	</div><!-- END OF #core -->

</div><!-- END OF #bgTop -->

</body>
</html>