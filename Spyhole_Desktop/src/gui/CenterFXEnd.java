package gui;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnector;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

public class CenterFXEnd {
	
	final Button buttonSave = new Button("Speichern");
	final Button buttonCancel = new Button("Abbrechen");
	final Button buttonStream = new Button("Stream starten");
	final Button buttonDoorLogger = new Button("Benutzertabelle");
	final Button buttonOpenDoor = new Button("Tür öffnen");
	
	//final static String IP_STREAM = "http://10.0.0.50:1900/javascript_simple.html";
	final static String IP_STREAM = "https://mperkowski.com";
	//final static String IP_STREAM = "http://spyhole.no-ip.biz:1900/javascript_simple.html";
	
	
	public GridPane addLoginGridPane() {
		GridPane grid = new GridPane();
		
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(30, 30, 30, 10));
		   
		Text TextRegis = new Text("Bitte melden Sie sich an...");
		TextRegis.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		grid.add(TextRegis, 2, 0); 
		
		return grid;
	}
	
	public GridPane addRegisGridPane() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		   
		Text TextRegis = new Text("Registrierung:");
		TextRegis.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(TextRegis, 1, 0); 
		   
		Label lblUser = new Label("Benutzername: ");
		grid.add(lblUser, 1, 2);
		 
		TextField txtfUser = new TextField();
		txtfUser.setPromptText("Hier Benutzername eingeben...");
		txtfUser.setMinWidth(250);
		grid.add(txtfUser, 2, 2);
		
		Label lblFirstName = new Label("Vorname: ");
		grid.add(lblFirstName, 1, 3);
		 
		TextField txtfFirstName = new TextField();
		txtfFirstName.setPromptText("Hier Vorname eingeben...");
		grid.add(txtfFirstName, 2, 3);
		
		Label lblLastName = new Label("Nachname: ");
		grid.add(lblLastName, 1, 4);
		 
		TextField txtfLastName = new TextField();
		txtfLastName.setPromptText("Hier Nachname eingeben...");
		grid.add(txtfLastName, 2, 4);
		
		Label lblMail = new Label("E-Mail Adresse: ");
		grid.add(lblMail, 1, 5);
		 
		TextField txtfMail = new TextField();
		txtfMail.setPromptText("Hier E-Mail Adresse eingeben...");
		grid.add(txtfMail, 2, 5);
		
		Label lblPassword = new Label("Passwort: ");
		grid.add(lblPassword, 1, 6);
		 
		PasswordField PwfPassword = new PasswordField();
		PwfPassword.setPromptText("Hier Password eingeben...");
		grid.add(PwfPassword, 2, 6);
		
		Label lblCheckPassword = new Label("Passwort zur Bestätigung: ");
		grid.add(lblCheckPassword, 1, 7);
		 
		PasswordField PwfCheckPassword = new PasswordField();
		PwfCheckPassword.setPromptText("Hier Password eingeben...");
		grid.add(PwfCheckPassword, 2, 7);
		
		   // Subtitle in columns 2-3, row 2
//		Text chartSubtitle = new Text("Goods and Services");
//		grid.add(chartSubtitle, 1, 1, 2, 1);
		   
		   // House icon in column 1, rows 1-2
//		ImageView imageHouse = new ImageView(
//				new Image(WindowController.class.getResourceAsStream("graphics/house.png")));
//		grid.add(imageHouse, 0, 0, 1, 2);
		   
		           // Left label in column 1 (bottom), row 3
//		Text goodsPercent = new Text("Goods\n80%");
//		GridPane.setValignment(goodsPercent, VPos.BOTTOM);
//		grid.add(goodsPercent, 0, 2);
		   
		   // Chart in columns 2-3, row 3
//		ImageView imageChart = new ImageView(
//				new Image(WindowController.class.getResourceAsStream("graphics/piechart.png")));
//		grid.add(imageChart, 1, 2, 2, 1);
//		   
		   // Right label in column 4 (top), row 3
//		Text servicesPercent = new Text("Services\n20%");
//		GridPane.setValignment(servicesPercent, VPos.TOP);
//		grid.add(servicesPercent, 3, 2);
		   
		//		           grid.setGridLinesVisible(true);
		return grid;
}
	
	
	public AnchorPane addRegisAnchorPane(GridPane grid) {

		AnchorPane anchorpane = new AnchorPane();

		HBox hb = new HBox();
		hb.setPadding(new Insets(0, 10, 10, 10));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonSave, buttonCancel);
	
		anchorpane.getChildren().addAll(grid,hb);
      // Anchor buttons to bottom right, anchor grid to top
		AnchorPane.setBottomAnchor(hb, 8.0);
		AnchorPane.setRightAnchor(hb, 5.0);
		AnchorPane.setTopAnchor(grid, 10.0);

		return anchorpane;
	}
	
public GridPane addStreamGridPane() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		   
		Text TextRegis = new Text("Stream:");
		TextRegis.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(TextRegis, 1, 0); 
		
		WebView WView = new WebView();
		WebEngine WEngine = WView.getEngine();
		WEngine.setJavaScriptEnabled(true);
		WEngine.load(IP_STREAM);
		WView.setMaxSize(400, 250);
		WView.setVisible(true);
		grid.add(WView, 1, 1);
		   
		//grid.setGridLinesVisible(true);
		
		return grid;
}





public GridPane addDoorLoggerTableGridPane() {
	
	GridPane grid = new GridPane();
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(0, 10, 0, 10));
	   
	Text TextRegis = new Text("Türöffner Tabelle:");
	TextRegis.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	grid.add(TextRegis, 1, 0); 
	/*
	WebView WView = new WebView();
	WebEngine WEngine = WView.getEngine();
	WEngine.setJavaScriptEnabled(true);
	WEngine.load(IP_STREAM);
	WView.setMaxSize(400, 250);
	WView.setVisible(true);
	*/

	TableView<Object> tableview = new TableView<>();
    ObservableList<Object> data = FXCollections.observableArrayList();
    try{
      Connection conn = DBConnector.connect();
      //SQL FOR SELECTING ALL OF CUSTOMER
      String SQL = "SELECT * from tb_doorlogger";
      //ResultSet
      ResultSet rs = conn.createStatement().executeQuery(SQL);

      /**********************************
       * TABLE COLUMN ADDED DYNAMICALLY *
       **********************************/
      for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
          //We are using non property style for making dynamic table
          final int j = i;                
          TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
          col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
              public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                  return new SimpleStringProperty(param.getValue().get(j).toString());                        
              }                    
          });
         
          tableview.getColumns().addAll(col); 
          System.out.println("Column ["+i+"] ");
      }

      /********************************
       * Data added to ObservableList *
       ********************************/
      while(rs.next()){
          //Iterate Row
          ObservableList<String> row = FXCollections.observableArrayList();
          for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
              //Iterate Column
              row.add(rs.getString(i));
          }
          System.out.println("Row [1] added "+row );
          data.add(row);

      }

      //FINALLY ADDED TO TableView
      tableview.setItems(data);
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("Error on Building Data");             
    }
	
	grid.add(tableview, 1, 1);
	   
	//grid.setGridLinesVisible(true);
	
	return grid;
	
            
}

public AnchorPane addStreamAnchorPane(GridPane grid) {

	AnchorPane anchorpane = new AnchorPane();

	HBox hb = new HBox();
	hb.setPadding(new Insets(0, 10, 10, 10));
	hb.setSpacing(10);
	hb.getChildren().addAll(buttonStream, buttonOpenDoor, buttonDoorLogger);

	anchorpane.getChildren().addAll(grid,hb);
  // Anchor buttons to bottom right, anchor grid to top
	AnchorPane.setBottomAnchor(hb, 8.0);
	AnchorPane.setRightAnchor(hb, 5.0);
	AnchorPane.setTopAnchor(grid, 10.0);

	return anchorpane;
}
	
}
