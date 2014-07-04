package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class BottomFXEnd {
	
	String StringStatus;
	Label lblStatusString = new Label("Bereit...");
	ProgressBar PBar = new ProgressBar();
	
	
	public HBox addStatus() {

	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(20);
	    hbox.setStyle("-fx-background-color: #336699;");
	    
	    Label lblStatus = new Label("Status: ");
	    
	    ProgressIndicator PBar = new ProgressIndicator();
	    PBar.setMaxSize(20, 20);
	    PBar.setProgress(-1);
	    
	    hbox.getChildren().addAll(PBar, lblStatus, lblStatusString);
	    
	    return hbox;
	}

}
