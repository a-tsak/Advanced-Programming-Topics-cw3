package gr.uop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
private int maxCharacters = 160;





    @Override
    public void start(Stage stage) {
        

        //The first HBox containing the receiver textfield and the add button
        HBox top = new HBox(5); 
        top.setAlignment(Pos.CENTER);
        HBox.setHgrow(top, Priority.ALWAYS);

        Label label = new Label("To:");     //The label
        label.setMinWidth(20);

        TextField numbers = new TextField();    //The number textfield
        numbers.setPromptText("Type numbers separated with ';'");
        numbers.setPrefWidth(1920);
        

        Button addButton = new Button("Add...");    //The add button
        addButton.setMinWidth(50);

        top.getChildren().addAll(label,numbers,addButton);






        //The center message textfield



        TextField message = new TextField();
        message.setPromptText("Type message text.");
        message.setAlignment(Pos.TOP_LEFT);
        message.setPrefSize(1920, 1080);




        //The bottom send button
        Button sendButton = new Button("Send");
        
        sendButton.setPrefWidth(1920);
        sendButton.setAlignment(Pos.CENTER);




        //The bottom charcount labels
        Label charcountLabel = new Label("0");
        Label maxcharLabel = new Label("/"+maxCharacters);
        HBox labelBox = new HBox(5);                    //a HBox containing the labels
        labelBox.getChildren().addAll(charcountLabel,maxcharLabel);
        labelBox.setAlignment(Pos.CENTER_RIGHT);


















        //The VBox containing all the items
        VBox pane = new VBox(5);
        
        
        
        pane.setPadding(new Insets(5));
        pane.getChildren().addAll(top,message,sendButton,labelBox);





        var scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}