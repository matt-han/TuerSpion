package gui;

import database.DBConnector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;

import controller.LoginController;

public class TopFXEnd {

	final Button btnGoLogin = new Button("Anmelden");
	
	final Button buttonGoLogoff = new Button("Abmelden");
	
	final Button buttonRegis = new Button("Registieren");
	
	final TextField TFUser = new TextField();
	
	final PasswordField PFPass = new PasswordField();
	
	public HBox addLogin() {
	
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	
	    TFUser.setDisable(false);
	    
		   
	    PFPass.setDisable(false);
	    
	    TFUser.setPromptText("Benutzername");
	    
	   
	    PFPass.setPromptText("Passwort");
	    
	    TFUser.setText(null);
	    
	    PFPass.setText(null);
	    
	  //  Button btnGoLogin = new Button("Anmelden");
	    btnGoLogin.setPrefSize(100, 20);
	    
	    
	    buttonRegis.setPrefSize(100, 20);
	    
	    hbox.getChildren().addAll(TFUser, PFPass, btnGoLogin, buttonRegis);
	    
	    return hbox;
	}
	
	public HBox addLogoff() {
		
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	
	    
	    TFUser.setDisable(true);
	    
	   
	    PFPass.setDisable(true);
	    
	  //  Button btnGoLogin = new Button("Anmelden");
	    btnGoLogin.setPrefSize(100, 20);
	    
	    
	    buttonRegis.setPrefSize(100, 20);
	    
	    hbox.getChildren().addAll(TFUser, PFPass, buttonGoLogoff);
	    
	    return hbox;
	}
}