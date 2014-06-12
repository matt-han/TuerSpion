package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class BottomFXEnd {
	
	String StringStatus;
	Label lblStatusString = new Label("Bereit...");
	ProgressBar PBar = new ProgressBar();
	
	public BottomFXEnd(){
		
	}
	
//	public BottomFXEnd(double status){
//		this.Status = status;
//	}

	public void setStatusString(String sstatus){
		this.StringStatus = sstatus;
		lblStatusString.setText(this.StringStatus);
		
	}
	
	public HBox addStatus() {

	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    
	    Label lblStatus = new Label("Status: ");
	    
	    ProgressBar PBar = new ProgressBar();
	    PBar.setProgress(-1);
	    System.out.println("Status\n");
	    
	    
//	    TextField TFUser = new TextField("Username");
//	    
//	    PasswordField PFPass = new PasswordField();
//	    
//	    Button buttonCurrent = new Button("Anmelden");
//	    buttonCurrent.setPrefSize(100, 20);
//
//	    Button buttonProjected = new Button("Registieren");
//	    buttonProjected.setPrefSize(100, 20);
//	    
	    hbox.getChildren().addAll(PBar, lblStatus, lblStatusString);
	    
	    return hbox;
	}

}
