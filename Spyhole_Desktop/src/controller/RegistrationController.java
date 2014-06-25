package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnector;

public class RegistrationController {

	boolean validateRegis;
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
            	 this.validateRegis = true;
             } else {
            	 this.validateRegis = false;
             }
        } catch (SQLException e1) {  
            e1.printStackTrace();  
        }
        if (this.validateRegis){
        	try {
        		sql = "SELECT * FROM `tb_user` WHERE `user` = '"+this.UserName+"'";  
                ResultSet rs = conn.createStatement().executeQuery(sql);
                if(rs.first()){
               	 this.validateRegis = true;
                } else {
               	 this.validateRegis = false;
                }
        	} catch (SQLException e3) {  
                e3.printStackTrace();  
            }
        }
       	
	}
	
	public boolean getValidateLogin(){
		return this.validateRegis;
	}
	
	public void setLogoff(){
		this.validateRegis = false;
		this.UserName = null;
		this.UserPass = null;
	}
	
}
