package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnector;

public class DoorLoggerController {

	boolean successInserted = false;
	boolean successGetID = false;
	String UserID;
	String DoorLoggerID;
	
//	public RegistrationController(String UserNameC, String UserFirstNameC, String UserLastNameC, String UserMailC, String UserPassC){
//		this.UserName = UserNameC;
//		this.UserLastName = UserLastNameC;
//		this.UserPass = UserPassC;
//		this.UserMail = UserMailC;
//		this.UserFirstName = UserFirstNameC;
//	}
	
	public String setDoorlogger(String UserID){
		this.UserID = UserID;
		Connection conn = null;  
        try {  
             conn = DBConnector.connect();  
        } catch (SQLException e2) {  
            e2.printStackTrace();  
        }  
        String sql = null; 
        try {
        	sql = "INSERT INTO `tb_doorlogger` (U_ID, date) VALUES ('"+this.UserID+"' ,NOW())";  
            if(conn.createStatement().execute(sql)){
           	 this.successInserted = true;
            } else {
           	 this.successInserted = false;
            }
        } catch (SQLException e3){
        	 e3.printStackTrace(); 
        }
        try {   
             sql = "SELECT * FROM `tb_doorlogger`";  
             ResultSet rs = conn.createStatement().executeQuery(sql);
             if(rs.last()){
            	 this.DoorLoggerID = rs.getString("ID");
            	 this.successGetID = true;
             } else {
            	 this.successGetID = false;
             }
        } catch (SQLException e1) {  
            e1.printStackTrace();
            this.successGetID = false;
        }
		return this.DoorLoggerID;
	}
	
}
