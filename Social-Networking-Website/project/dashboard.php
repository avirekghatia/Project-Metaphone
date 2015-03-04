<?php

session_start();
$Username = $_SESSION['s'];
$isBand = $_SESSION["isBand"];
$fan = 0;
//echo"isBand :: $isBand";

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
if($isBand == 1){
$sql1 = "SELECT bandid, Username FROM fan WHERE bandid = '$Username'";
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
		$Users = $row1["Username"];
		$fan++;
		//echo"$fan<br/>";
		}
//echo"$fan";
	}
}

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>WebStarter Admin Template - Dashboard</title>

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
					<li><span class="folder"><img src="./img/application_error.png" align="left" style="padding-right: 4px;" alt="Messages" /><a href="./messages.html">Messages</a></span></li>
					<li><span class="folder"><img src="./img/application_view_columns.png" align="left" style="padding-right: 4px;" alt="Table" /><a href="./datagrid.html">Table</a></span></li>
					<li><span class="folder"><img src="./img/chart_bar.png" align="left" style="padding-right: 4px;" alt="Charts" /><a href="./charts.html">Charts</a></span></li>
					<li><span class="folder"><img src="./img/plugin.png" align="left" style="padding-right: 4px;" alt="Other Widgets" /><a href="./others.html">Other Widgets</a></span></li>
				</ul>
			</div><!-- END OF #menu -->

			<div id="content">
				<div class="bigIcons">
					<div class="bigIcon">
					<?php if ($isBand == 0) {?>
						<a href="edit_user.php"><img src="./img/big_icon_settings.png" alt="Edit Profile" /></a>
						<div class="bigIconText"><a href="edit_user.php">Edit Profile</a></div>
						<?php } else {?>
						<a href="edit_band.php"><img src="./img/big_icon_settings.png" alt="Edit Profile" /></a>
						<div class="bigIconText"><a href="edit_band.php">Edit Profile</a></div>
						<?php }?>
					</div>
					<?php if($isBand == 1){?>
					<div class="bigIcon">
						<a href="create_concert.php"><img src="./img/big_icon_orders.png" alt="Orders" /></a>
						<div class="bigIconText"><a href="create_concert.php">Create Concert</a></div>
					</div>
					<?php } ?>
					
					
					<div class="bigIcon">
						<a href="#"><img src="./img/big_icon_email.png" alt="Email" /></a>
						<div class="bigIconText"><a href="#">Send Newsletter</a></div>
					</div>
					
					<div class="bigIcon">
						<a href="new_post.php"><img src="./img/big_icon_note.png" alt="Page" /></a>
						<div class="bigIconText"><a href="new_post.php">New Post</a></div>
					</div>
					
					<div class="bigIcon">
						<a href="search.php"><img src="./img/big_icon_customer.png" alt="Customer" /></a>
						<div class="bigIconText"><a href="#">Search</a></div>
					</div>
					
					<div class="bigIcon">
						<a href="music_choice.php"><img src="./img/big_icon_stat.png" alt="Stat" /></a>
						<div class="bigIconText"><a href="music_choice.php">Music choice</a></div>
					</div>
					
					<div class="clear"></div>
				</div><!-- END OF .bigIcons -->

				<div class="box">
					<h2>Quick Info</h2>
					<div>
						<div class="text">
							<div class="infoColumnUp">
								<div class="infoColumnIcon"><img src="./img/quick_info_up.png" alt="Up" /></div>
								<div class="infoColumnNumber"><div class="infoColumnNumberBg"><?php echo"$fan";?></div></div>
								<p>Fans</p>
							</div>
							
							

							<div class="clear"></div>
						</div>
					</div>
				</div>

				<div class="box">
					<h2>Recent Posts:: </h2>
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

									  $sql = "SELECT * FROM `post` where 
									 (username in (select following from follow where follower = '$Username')) or
									 (BandID in (select bandID from fan where `fan`.`Username` = '$Username')) 
									 ORDER BY `post`.`dateTimeStamp`  DESC";
									 $result1 = $conn->query($sql);
									//echo $sql;
									 if ($result1->num_rows == 0) 
									 {
									  echo "Opps..!! no result found.. \r\nPlease try again !!";
									 }
									 else
									 { 
									  while($row1 = $result1->fetch_assoc()) { 
									  $post = $row1["Content"];
									  $Band = $row1["BandID"];
									  $user = $row1["username"];
									  $date = $row1["dateTimeStamp"];
									  echo "<tr>";
									  
									  if (is_null ($Band))
									  {
									   echo "<td>".$user.": <b>".$post."</b></td>";
									   echo "<td>".$date."</td>";
									  }
									  else
									  {
									   echo "<td>".$Band.": <b>".$post."</b></td>";
									    echo "<td>".$date."</td>";
									  }
									  
									  echo "</tr>";
									  
									  //echo"$fan<br/>";
									  }
									//echo"$fan";
									 }
									 ?>
								
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
				<?php if ($isBand == 1) { ?>
				<div class="box2">
					<h2>Concerts hosted by you</h2>

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
								
								
								$sql3 = "SELECT ConcertID, concertName, datetime FROM concert WHERE BandID = '$Username'";
									//echo $sql3;

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
									<td><a href="edit_concert.php?ConcertID=<?php echo"$ConcertID";?>"><?php echo"$concertName";?></a></td>
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
				<?php } else { ?>
				
				
				<div class="box2">
					<h2>Concerts Wishlisted by you</h2>

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
								
								
								$sql3 = "SELECT ConcertID, concertName, datetime FROM concert WHERE  ConcertID in (select concertID from userlist where username = '$Username')";
									//echo $sql3;

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
				
				<?php } ?>
				
				
				
				
				
				

			</div><!-- END OF #content -->

			<div id="footer">
				<strong>WebStarter Content Management System</strong> - 2011 &copy; YourDomainName.com
			</div>

		</div><!-- END OF #bgBottom -->
		
	</div><!-- END OF #core -->

</div><!-- END OF #bgTop -->

</body>
</html>