<?php session_start();
$Username = $_SESSION['s'];


$servername = "localhost";
$username = "root";
$password = "";
$dbname = "db_proj_1";

 if(isset($_POST['submit'])){ 
	  if(isset($_GET['go'])){ 
		if(preg_match("/^[  a-zA-Z]+/", $_POST['name'])){ 
			  $name=$_POST['name']; 

				// Create connection
				$conn = new mysqli($servername, $username, $password, $dbname);
				// Check connection
				if ($conn->connect_error)
				{
					die("Connection failed: " . $conn->connect_error);
				}
				$sql = "SELECT Username, fName, lName, city FROM user WHERE fName = '$name' OR lName ='$name' ";
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
					$user = $row["Username"];
					$fName = $row["fName"];
					$lName = $row["lName"];
					$city = $row["city"];
					
					
				
					//echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
					}
				
				
				}
				//echo "$name";
			} //if preg_match ends
		} // if isset GET ends
	}	//if isset SUBMIT ends			
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>WebStarter Admin Template - DataGrid</title>

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

			<div id="content">
			
				<div class="dataGrid">
				
					<div class="dataGridControl">
						<div class="dataGridControlButtons">
							<form  method="post" action="search_users.php?go"  id="searchform"> 
							<input  type="text" name="name"> 
							
							<input  type="submit" name="submit" value="Search"> 
							
							</form> 
						</div>

						<div class="dataGridPages">
							Page: <img src="./img/resultset_first.png" alt="First" /> <img src="./img/resultset_previous.png" alt="Previous" /> <select name="pageTop"><option value="1">1</option><option value="2">2</option><option value="3">3</option></select> <img src="./img/resultset_next.png" alt="Next" /> <img src="./img/resultset_last.png" alt="Last" />&nbsp; Items per page: <select name="perPageTop"><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>
						</div>

						<div class="clear"></div>
					</div>
				
					<table>
						<thead>
							<tr>

								<th></th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>City</th>
								<th>Phone</th>
								<th>E-mail</th>
								<th>&nbsp;</th>
								<th><input type="checkbox" name="deleteAll" value="" /></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								
							<?php if(isset($_POST['submit'])){ ?>
								<td><?php echo"$user";?></td>
								<td><a  href="profile.php?fName=<?php echo"$fName" ?>&username=<?php echo"$user";?>"><?php echo"$fName";?></a></td>
								<td><?php echo"$lName";?></td>
								<td><?php echo"$city";?></td>
							<!--/tr-->
							<?php } else {
							
									$sql1 = "SELECT Username, fName, lName, city FROM user";
									//echo $sql1;
									$conn1 = new mysqli($servername, $username, $password, $dbname);
									$result1 = $conn1->query($sql1);
							    	// output data of each row
									while($row1 = $result1->fetch_assoc()) {
										$user = $row1["Username"];
										$fName = $row1["fName"];
										$lName = $row1["lName"];
										$city = $row1["city"];
										//echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
										?>
										<!--tr-->
											<td></td>
											<td><a  href="profile.php?fName=<?php echo"$fName";?>&username=<?php echo"$user";?>"><?php echo"$fName";?></a></td>
											<td><?php echo"$lName";?></td>
											<td><?php echo"$city";?></td>
										</tr>
										<?php
										}
									
										?>
		
							<?php } ?>		
								<!--td><img src="./img/pencil.png" alt="Edit" /></td>
								<td><input type="checkbox" name="delete[]" value="" /></td-->
							<!--/tr-->
							
						</tbody>

					</table>

					<div class="dataGridControl">
						<div class="dataGridControlButtons">
							<!--img src="./img/add.png" alt="New" /> <a href="#">New record</a> <img src="./img/page_excel.png" alt="CSV export" /> <a href="#">CSV export</a> <img src="./img/printer.png" alt="Print" /> <a href="#">Print</a> <img src="./img/delete.png" alt="Remove" /> <a href="#">Remove selected</a-->
						</div>

						<div class="dataGridPages">
							Page: <img src="./img/resultset_first.png" alt="First" /> <img src="./img/resultset_previous.png" alt="Previous" /> <select name="pageBottom"><option value="1">1</option><option value="2">2</option><option value="3">3</option></select> <img src="./img/resultset_next.png" alt="Next" /> <img src="./img/resultset_last.png" alt="Last" />&nbsp; Items per page: <select name="perPageBottom"><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>
						</div>

						<div class="clear"></div>
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