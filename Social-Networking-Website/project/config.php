<?php
$conn=mysql_connect('localhost','root','root');
if(!$conn)
{
die('problem in connection');
}
mysql_select_db('db_proj_1',$conn);
?>