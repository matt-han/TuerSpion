package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnector;

public class RegistrationController {

	boolean successRegis = false;
	boolean readyToRegis = false;
	String UserName;
	String UserPass;
	String UserMail;
	String UserFirstName;
	String UserLastName;
	
	public RegistrationController(String UserNameC, String UserFirstNameC, String UserLastNameC, String UserMailC, String UserPassC){
		this.UserName = UserNameC;
		this.UserLastName = UserLastNameC;
		this.UserPass = UserPassC;
		this.UserMail = UserMailC;
		this.UserFirstName = UserFirstNameC;
	}
	
	public void checkToRegisDB(){	
		
        Connection conn = null;  
        try {  
             conn = DBConnector.connect();  
        } catch (SQLException e2) {  
            e2.printStackTrace();  
        }  
        String sql = null;  
        try {   
             sql = "SELECT * FROM `tb_user` WHERE `user` = '"+this.UserName+"'";  
             ResultSet rs = conn.createStatement().executeQuery(sql);
             if(rs.first()){
            	 this.readyToRegis = false;
             } else {
            	 this.readyToRegis = true;
             }
        } catch (SQLException e1) {  
            e1.printStackTrace();
            this.readyToRegis = false;
        }
	}
	
	public boolean getCheckRegis(){
		return this.readyToRegis;
	}
	
	public boolean saveRegis(){
		Connection conn = null;  
        try {  
             conn = DBConnector.connect();  
        } catch (SQLException e2) {  
            e2.printStackTrace();  
        }  
        String sql = null;  
        try {   
             sql = "INSERT INTO `tb_user` (user,firstname,name,email,password,u_timestamp) VALUES ('"+this.UserName+"','"+this.UserFirstName+"','"+this.UserLastName+"','"+this.UserMail+"','"+this.UserPass+"',NOW())";  
             
             if(conn.createStatement().execute(sql)){
            	 this.successRegis = true;
             } else {
            	 this.successRegis = false;
             }
        } catch (SQLException e1) {  
            e1.printStackTrace();
            this.successRegis = false;
        }
		return this.successRegis;
	}
	
	public void setRegisReset(){
		this.UserName = null;
		this.UserLastName = null;
		this.UserPass = null;
		this.UserMail = null;
		this.UserFirstName = null;
	}
	
}
