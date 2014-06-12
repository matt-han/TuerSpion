package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnector;

public class LoginController {
	
	boolean validateLogin;
	String UserName;
	String UserPass;
	
	public LoginController(String UserNameC, String UserPassC){
		this.UserName = UserNameC;
		this.UserPass = UserPassC;
	}

	public void checkLogin(){
		
		String UsernameDB = "admin";
		String PassDB = "123";
		
		if (this.UserName.equals(UsernameDB) && this.UserPass.equals(PassDB)){
			this.validateLogin = true;
		} else {
			this.validateLogin = false;
		}
		
	}
	
	public void checkLoginDB(){	
		
        Connection conn = null;  
        try {  
             conn = DBConnector.connect();  
        } catch (SQLException e2) {  
            e2.printStackTrace();  
        }  
        String sql = null;  
        try {   
             sql = "SELECT * FROM `tb_user` WHERE `user` = '"+this.UserName+"' AND `password` = '"+this.UserPass+"'";  
             ResultSet rs = conn.createStatement().executeQuery(sql);
             if(rs.first()){
            	 this.validateLogin = true;
             } else {
            	 this.validateLogin = false;
             }
        } catch (SQLException e1) {  
            e1.printStackTrace();  
        }   
		
	}
	
	public boolean getValidateLogin(){
		return this.validateLogin;
	}
	
	public void setLogoff(){
		this.validateLogin = false;
	}
}
