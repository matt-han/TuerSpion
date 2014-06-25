package gui;

import controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
import gui.TopFXEnd;
//import socket.SocketController;

/**
 * Sample application that shows examples of the different layout panes
 * provided by the JavaFX layout API.
 * The resulting UI is for demonstration purposes only and is not interactive.
 */
public class WindowFXEnd extends Application {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(WindowFXEnd.class, args);
    }
    
    @Override
    public void start(Stage stage) {

// Use a border pane as the root for scene
        final BorderPane border = new BorderPane();
        
        final TopFXEnd Top = new TopFXEnd();
        final BottomFXEnd Bottom = new BottomFXEnd();
        final CenterFXEnd Center = new CenterFXEnd();
        //final LeftFXEnd Left = new LeftFXEnd();
        
      //  HBox hbox = Login.addLogin();
      //  VBox vbox = addVBox();
        border.setTop(Top.addLogin());
      //  border.setLeft(Left.addMenu());
        border.setBottom(Bottom.addStatus());
        
// Add a stack to the HBox in the top region
     //  addStackPane(Login.addLogin());  
        
// To see only the grid in the center, uncomment the following statement
// comment out the setCenter() call farther down        
//        border.setCenter(addGridPane());
        
// Choose either a TilePane or FlowPane for right region and comment out the
// one you aren't using        
//        border.setRight(addFlowPane());
//        border.setRight(addTilePane());
        
// To see only the grid in the center, comment out the following statement
// If both setCenter() calls are executed, the anchor pane from the second
// call replaces the grid from the first call  
      //  System.out.print(LoginC.getValidateLogin());
        
        final TextField TFUser = Top.TFUser;
        
        final PasswordField PFPass = Top.PFPass;
        
        final LoginController LoginC = new LoginController(TFUser.getText(),PFPass.getText());
        
        Top.btnGoLogin.setOnAction(new EventHandler<ActionEvent>() {  
	        @Override  
	        public void handle(ActionEvent e) {  
	        	 LoginController LoginC = new LoginController(TFUser.getText(),PFPass.getText());
	        	 Bottom.lblStatusString.setText("Anmelden...");
	        	 Bottom.StringStatus = "Anmelden";
	        	 LoginC.checkLoginDB();
	        	// System.out.print(LoginC.getValidateLogin());
	        	 if(LoginC.getValidateLogin()){
	        		 border.setCenter(Center.addStreamAnchorPane(Center.addStreamGridPane()));
	        		 border.setTop(Top.addLogoff());
	        		// System.out.print(LoginC.getValidateLogin());
	        		 Bottom.lblStatusString.setText("Anmeldung erfolgreich...");
	        	 } else {
	        		 Bottom.lblStatusString.setText("Anmeldung fehlgeschlagen...");
	        	 }
	        }  
	   });  
        
        Top.buttonGoLogoff.setOnAction(new EventHandler<ActionEvent>() {  
	        @Override  
	        public void handle(ActionEvent e) {  
	        	 LoginC.setLogoff();
	        	// System.out.print(LoginC.getValidateLogin());
	        	 if(LoginC.getValidateLogin()){
	        		 border.setCenter(Center.addStreamAnchorPane(Center.addStreamGridPane()));
	        		 border.setTop(Top.addLogoff());
	        		 Bottom.lblStatusString.setText("Anmeldung erfolgreich...");
	        	 } else {
	        		 border.setTop(Top.addLogin());
	        		 border.setCenter(Center.addLoginGridPane());
	        		 Bottom.lblStatusString.setText("Abmeldung erfolgreich...");
	        		 
	        	 }
	        }  
	   }); 
        
 
        
        Top.buttonRegis.setOnAction(new EventHandler<ActionEvent>() {  
	        @Override  
	        public void handle(ActionEvent e) {  
	        	border.setCenter(Center.addRegisAnchorPane(Center.addRegisGridPane()));
	        	Bottom.lblStatusString.setText("Registrierung...");
	        	//SocketController socketC = new SocketController();
	        	//socketC.SocketClient("2","22","11");
	        }  
	   });  
        
        Center.buttonSave.setOnAction(new EventHandler<ActionEvent>() {  
	        @Override  
	        public void handle(ActionEvent e) {  
	        	border.setCenter(Center.addRegisAnchorPane(Center.addRegisGridPane()));
	        	Bottom.lblStatusString.setText("Registrierung...");
	        }  
	   });  
        
        
        Center.buttonCancel.setOnAction(new EventHandler<ActionEvent>() {  
	        @Override  
	        public void handle(ActionEvent e) {  
	        	 border.setCenter(Center.addLoginGridPane());
	        	 Bottom.lblStatusString.setText("Anmeldung erforderlich...");
	        }  
	   });  
        
        Center.buttonDoorLogger.setOnAction(new EventHandler<ActionEvent>() {  
	        @Override  
	        public void handle(ActionEvent e) {  
	        	 border.setCenter(Center.addDoorLoggerAnchorPane(Center.addDoorLoggerTableGridPane()));
	        }  
	   });  
        
        Center.buttonBackToStream.setOnAction(new EventHandler<ActionEvent>() {  
	        @Override  
	        public void handle(ActionEvent e) {  
	        	 border.setCenter(Center.addStreamAnchorPane(Center.addStreamGridPane()));
	        }  
	   });  
        
        
       
       
        
        
        border.setCenter(Center.addLoginGridPane());
        //border.setCenter(Center.addStreamAnchorPane(Center.addStreamGridPane()));
        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setTitle("Spyhole");
        
        stage.setWidth(700);
        stage.setMinWidth(700);
        stage.setMaxWidth(700);
        
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.setMaxHeight(500);
        
        stage.show();
    }

/*
 * Creates an HBox with two buttons for the top region
 */

	/*
 * Creates a VBox with a list of links for the left region
 */
    private VBox addVBox() {
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10)); // Set all sides to 10
        vbox.setSpacing(8);              // Gap between nodes

//        Text title = new Text("Data");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        vbox.getChildren().add(title);
//        
//        Hyperlink options[] = new Hyperlink[] {
//            new Hyperlink("Sales"),
//            new Hyperlink("Marketing"),
//            new Hyperlink("Distribution"),
//            new Hyperlink("Costs")};
//
//        for (int i=0; i<4; i++) {
//            // Add offset to left side to indent from title
//            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
//            vbox.getChildren().add(options[i]);
       // }
        
//        return vbox;
        return vbox;
    }

/*
 * Uses a stack pane to create a help icon and adds it to the right side of an HBox
 * 
 * @param hb HBox to add the stack to
 */
    private void addStackPane(HBox hb) {

        StackPane stack = new StackPane();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
            new Stop[]{
            new Stop(0,Color.web("#4977A3")),
            new Stop(0.5, Color.web("#B0C6DA")),
            new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);
        
        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0")); 
        
        stack.getChildren().addAll(helpIcon, helpText);
        stack.setAlignment(Pos.CENTER_RIGHT);
        // Add offset to right for question mark to compensate for RIGHT 
        // alignment of all nodes
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));
        
        hb.getChildren().add(stack);
        HBox.setHgrow(stack, Priority.ALWAYS);
                
    }

/*
 * Creates a grid for the center region with four columns and three rows
 */
//    private GridPane addGridPane() {
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(0, 10, 0, 10));
//
//        // Category in column 2, row 1
//        Text category = new Text("Sales:");
//        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        grid.add(category, 1, 0); 
//        
//        // Title in column 3, row 1
//        Text chartTitle = new Text("Current Year");
//        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        grid.add(chartTitle, 2, 0);
//        
//        // Subtitle in columns 2-3, row 2
//        Text chartSubtitle = new Text("Goods and Services");
//        grid.add(chartSubtitle, 1, 1, 2, 1);
//        
//        // House icon in column 1, rows 1-2
//        ImageView imageHouse = new ImageView(
//                    new Image(WindowController.class.getResourceAsStream("graphics/house.png")));
//        grid.add(imageHouse, 0, 0, 1, 2);
//
//        // Left label in column 1 (bottom), row 3
//        Text goodsPercent = new Text("Goods\n80%");
//        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
//        grid.add(goodsPercent, 0, 2);
//        
//        // Chart in columns 2-3, row 3
//        ImageView imageChart = new ImageView(
//                    new Image(WindowController.class.getResourceAsStream("graphics/piechart.png")));
//        grid.add(imageChart, 1, 2, 2, 1);
//        
//        // Right label in column 4 (top), row 3
//        Text servicesPercent = new Text("Services\n20%");
//        GridPane.setValignment(servicesPercent, VPos.TOP);
//        grid.add(servicesPercent, 3, 2);
//        
////        grid.setGridLinesVisible(true);
//        return grid;
//    }

/*
 * Creates a horizontal flow pane with eight icons in four rows
 */
//    private FlowPane addFlowPane() {
//
//        FlowPane flow = new FlowPane();
//        flow.setPadding(new Insets(5, 0, 5, 0));
//        flow.setVgap(4);
//        flow.setHgap(4);
//        flow.setPrefWrapLength(170); // preferred width allows for two columns
//        flow.setStyle("-fx-background-color: DAE6F3;");
//
//        ImageView pages[] = new ImageView[8];
//        for (int i=0; i<8; i++) {
//            pages[i] = new ImageView(
//                    new Image(WindowController.class.getResourceAsStream(
//                    "graphics/chart_"+(i+1)+".png")));
//            flow.getChildren().add(pages[i]);
//        }
//
//        return flow;
//    }
    
/*
 * Creates an anchor pane using the provided grid and an HBox with buttons
 * 
 * @param grid Grid to anchor to the top of the anchor pane
 */
//    private AnchorPane addAnchorPane(GridPane grid) {
//
//        AnchorPane anchorpane = new AnchorPane();
//        
//        Button buttonSave = new Button("Save");
//        Button buttonCancel = new Button("Cancel");
//
//        HBox hb = new HBox();
//        hb.setPadding(new Insets(0, 10, 10, 10));
//        hb.setSpacing(10);
//        hb.getChildren().addAll(buttonSave, buttonCancel);
//
//        anchorpane.getChildren().addAll(grid,hb);
//        // Anchor buttons to bottom right, anchor grid to top
//        AnchorPane.setBottomAnchor(hb, 8.0);
//        AnchorPane.setRightAnchor(hb, 5.0);
//        AnchorPane.setTopAnchor(grid, 10.0);
//
//        return anchorpane;
//    }
}
