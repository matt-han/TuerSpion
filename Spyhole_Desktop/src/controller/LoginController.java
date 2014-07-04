package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.controlsfx.dialog.Dialogs;

import javafx.stage.Stage;
import database.DBConnector;

public class LoginController {
	
	boolean validateLogin;
	String UserName;
	String UserPass;
	String UserID;
	Stage stage;
	
//	public LoginController(String UserNameC, String UserPassC){
//		this.UserName = UserNameC;
//		this.UserPass = UserPassC;
//	}

	public LoginController(Stage stage) {
		this.stage = stage;
	}
	
	public void setLoginData(String UserNameC, String UserPassC){
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
            Dialogs.create()
	        .owner(stage)
	        .title("Fehlermeldung")
	        .masthead("Fehler bei der Datenbankverarbeitung")
	        .message("Es ist ein Fehler aufgetreten. Mehr Informationen siehe unten.")
	        .showException(e2);
        }  
        String sql = null;  
        try {   
             sql = "SELECT * FROM `tb_user` WHERE `user` = '"+this.UserName+"' AND `password` = '"+this.UserPass+"'";  
             ResultSet rs = conn.createStatement().executeQuery(sql);
             if(rs.first()){
            	 this.UserID = rs.getString("ID");
            	 this.validateLogin = true;
            	 System.out.println(this.UserID);
             } else {
            	 this.validateLogin = false;
             }
        } catch (SQLException e1) {  
            e1.printStackTrace();
            this.validateLogin = false;
            Dialogs.create()
	        .owner(stage)
	        .title("Fehlermeldung")
	        .masthead("Fehler bei der Datenbankverarbeitung")
	        .message("Es ist ein Fehler aufgetreten. Mehr Informationen siehe unten.")
	        .showException(e1);
        }   
		
	}
	
	public boolean getValidateLogin(){
		return this.validateLogin;
	}
	
	public void setLogoff(){
		this.validateLogin = false;
		this.UserName = null;
		this.UserPass = null;
	}
	
	public String getUserID(){
		return this.UserID;
	}
}
