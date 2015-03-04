<?php

session_start();
$Username = $_SESSION['s'];

$ConcertID = $_REQUEST['ConcertID'];
$going = 0;


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

	$sql = "SELECT concertName, BandID, venueID, datetime, ticketLink, TicketPrice, seatsAvailable FROM concert WHERE ConcertID = $ConcertID";
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
		$concertName = $row["concertName"];
		$BandID = $row["BandID"];
		$venueID = $row["venueID"];	
		$datetime = $row["datetime"];
		//echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
		}
	
	}

	$sql11 = "SELECT concertID FROM attend WHERE username = '$Username' AND concertID = $ConcertID";
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
	
	
	$sql1 = "SELECT BandName FROM band WHERE BandID = '$BandID'";
	//echo $sql1;

	$result1 = $conn->query($sql1);

	if ($result1->num_rows == 0) 
	{
		echo "Opps..!! no result found.. \r\nPlease try again !!";
	}
	else
	{	
		while($row1 = $result1->fetch_assoc()) {	
		$BandName = $row1["BandName"];
		//$Username1 = $row1["Username"];
		//$fan++;
		//echo"$fan<br/>";
		}
//echo"$fan";
	}
//echo"$fan";	
	
	
	
	$sql2 = "SELECT Name, City, ticketLink FROM venue WHERE venueID = '$venueID'";
	//echo $sql2;

	$result2 = $conn->query($sql2);

	if ($result2->num_rows == 0) 
	{
		echo "Opps..!! no result found.. \r\nPlease try again !!";
	}
	else
	{	
		while($row2 = $result2->fetch_assoc()) {	
		$Name = $row2["Name"];
		$City = $row2["City"];
		$ticketLink = $row2["ticketLink"];
		//$concert++;
		//echo"$fan<br/>";
		}
//echo"$fan";
	}
//echo"$fan";	
	
	 
	$sql0 = "SELECT username FROM attend WHERE concertID = $ConcertID";
	//echo $sql0;

	$result0 = $conn->query($sql0);

	if ($result0->num_rows == 0) 
	{
		echo "Opps..!! no result found.. \r\nPlease try again !!";
	}
	else
	{ 
	
	// output data of each row
	while($row0 = $result0->fetch_assoc()) {
		
		//$BandID = $row["BandID"];
		
		$going++;
		//echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
		}
	
	}

	 
	 
	 
		

	
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
				<h1><?php echo "$concertName";?></h1>
				</div>
				
				</div>
				<div class="box">
				<h2>Quick Info</h2>
					<div>
						<div class="text">
							<p>"<?php echo "$concertName";?>" is hosted by the band : <a  href="band_profile.php?BandID=<?php echo"$BandID";?>"><?php echo "$BandName";?></a> at the venue : <?php echo "$Name";?> in the city of <?php echo "$City";?> on <?php echo"$datetime";?>. To book the tickets, please visit <a href ="<?php echo "$ticketLink";?>" ><?php echo "$ticketLink";?></a>
							</p>
						</div>
					</div>
				</div>
				<div class="box">
				
					<div>
						<div class="text">
							<div class="infoColumnUp">
								<div class="infoColumnIcon"><img src="./img/quick_info_up.png" alt="Up" /></div>
								<div class="infoColumnNumber"><div class="infoColumnNumberBg"><?php echo"$going";?></div></div>
								<p>Are Going</p>
							</div>
							
							
							<!--div class="bigIcons"-->
							<?php if ($show == 1) { ?>
								<div class="infoColumnUp">
								<a  href="attend.php?ConcertID=<?php echo"$ConcertID";?>"><img src="./img/quick_info_up.png" alt="" /></a>
								<div class="bigIconText"><a href="#">Going??</a></div>
								</div>
							<?php } ?>
							
							<?php
							$sql01 = "SELECT username FROM attend WHERE concertID = $ConcertID";
								//echo $sql01;

								$result01 = $conn->query($sql0);

								if ($result01->num_rows == 0) 
								{
									echo "Opps..!! no result found.. \r\nPlease try again !!";
								}
								else
								{ 
								
								// output data of each row
								while($row01 = $result01->fetch_assoc()) {
									
									//$BandID = $row["BandID"];
									
									$going++;
									//echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
									}
								
								}
							?>
							
							<div class="infoColumnUp">
								<a  href="ulist.php?ConcertID=<?php echo"$ConcertID";?>"><img src="./img/quick_info_up.png" alt="wishlist" /></a>
								<div class="bigIconText"><a href="ulist.php?ConcertID=<?php echo"$ConcertID";?>">Add to wishlist</a></div>
							</div>
							
							<div class="infoColumnUp">
								<a  href="post_review.php?ConcertID=<?php echo"$ConcertID";?>"><img src="./img/quick_info_up.png" alt="" /></a>
								
								<p>Write your review</p>
							</div>
								
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="box">
					<h2>Recent Reviews</h2>
					<div>
						<div class="text">
							<table class="">
								<thead>
								<tr>
									<th>Review</th>
									<th>Rating</th>
									<th>User</th>
									<th>Date</th>
								</tr>
								</thead>

								<tbody>
								
								<?php
								$sql4 = "SELECT review, dateTime, rate, username FROM attend WHERE concertID = $ConcertID";
								//	echo $sql2;

								$result4 = $conn->query($sql4);

								if ($result4->num_rows == 0) 
								{
									echo "Opps..!! no result found.. \r\nPlease try again !!";
								}
								else
								{	
									while($row4 = $result4->fetch_assoc()) {
									
									$review = $row4["review"];
									if(isset($review))
									{									
										$dateTime = $row4["dateTime"];
										$rate = $row4["rate"];
										$user = $row4["username"];
									
								?>	
									<tr>
									<td><?php echo"$review";?></td>
									<td><?php echo"$rate";?></td>
									<td><a href="profile.php?username=<?php echo"$user";?>"><?php echo"$user";?></a></td>
									<td><?php echo"$dateTime";?></td>
									</tr>
								<?php	
										}
									}
							//echo"$fan";
								}
								?>
								
									
								</tbody>
							</table>
						</div>
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