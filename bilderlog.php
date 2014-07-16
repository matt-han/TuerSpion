<?php
 
	$host='127.0.0.1';
	$uname='pi';
	$pwd='spyhole';
	$db="db_spyhole";

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed"); 
 
      
    $q=mysql_query("SELECT * FROM users");
    while($row=mysql_fetch_assoc($q))
            $json_output[]=$row;
      
    print(json_encode($json_output));
      
    mysql_close();
     
?>