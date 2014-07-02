<?php
	$host='127.0.0.1';
	$uname='pi';
	$pwd='spyhole';
	$db="db_spyhole";

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	 
	//$id=$_REQUEST['id'];
	$user=$_REQUEST['user'];
	$firstname=$_REQUEST['firstname'];
	$name=$_REQUEST['name'];
	$mail=$_REQUEST['mail'];
	$password=$_REQUEST['password'];

	$flag['code']=0;

	if($r=mysql_query("INSERT INTO `tb_user` (user,firstname,name,email,password,time_stamp) VALUES('$user','$firstname','$name','$mail','$password',NOW())",$con))
	{
		$flag['code']=1;
		echo"hi";
	}

	print(json_encode($flag));
	mysql_close($con);
?>