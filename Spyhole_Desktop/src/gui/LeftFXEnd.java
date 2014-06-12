package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LeftFXEnd {
	   public VBox addMenu() {
	        
	        VBox vbox = new VBox();
	        vbox.setPadding(new Insets(10)); // Set all sides to 10
	        vbox.setSpacing(8);              // Gap between nodes

	        Text title = new Text("Menü");
	        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
	        vbox.getChildren().add(title);
	        
	        Hyperlink options[] = new Hyperlink[] {
	            new Hyperlink("Stream"),
	            new Hyperlink("Benutzertabelle"),
	            new Hyperlink("Einstellung")
	        };
	
	        for (int i=0; i<options.length; i++) {
	            // Add offset to left side to indent from title
	            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
	            vbox.getChildren().add(options[i]);
	        }
	        
	        return vbox;
	    }
	
}
