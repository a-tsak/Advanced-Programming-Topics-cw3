package gr.uop;

import java.util.List;



import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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




        //Functionalities


        addButton.setOnAction((e)->{
            Dialog<String> addRecipients = new Dialog<>();
            //Making 2 observable lists and filling the left one with random numbers
            ObservableList<String> leftList = FXCollections.observableArrayList(); 
            ObservableList<String> rightList = FXCollections.observableArrayList(); 
        

            leftList = randomPhones(leftList);    //Filling the left list with random phones
            rightList= stringToList(numbers.getText());
    



            //Dialog options
            addRecipients.initModality(Modality.WINDOW_MODAL);
            addRecipients.initOwner(stage);
            addRecipients.setTitle("Add recipients");
            //the 2 lists
            ListView<String> itemsLeft = new ListView<String>(leftList);
            ListView<String> itemsRight = new ListView<String>(rightList);
            itemsLeft.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            itemsRight.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            itemsLeft.setPrefSize(250,350);
            itemsRight.setPrefSize(250,350);


            //The buttons in a VBox
            VBox dButtons = new VBox(5);
            dButtons.setAlignment(Pos.CENTER);
            Button dAdd = new Button("Add");
            Button dRemove = new Button("Remove");
            dAdd.setPrefWidth(100);
            dRemove.setPrefWidth(100);
            dButtons.getChildren().addAll(dAdd,dRemove);
            dAdd.setDisable(true);
            dRemove.setDisable(true);

            //A HBox with the lists and the buttons
            HBox dItems = new HBox(5);
            dItems.setPadding(new Insets(5, 10, 10, 10));
            dItems.setAlignment(Pos.CENTER);
            dItems.getChildren().addAll(itemsLeft,dButtons,itemsRight);


            addRecipients.getDialogPane().setContent(dItems);
            addRecipients.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);



            


            addRecipients.show();

        
            //2 list listeners so that if no item is selected the button gets disabled
            itemsLeft.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<String>() {                     

                @Override
                public void onChanged(Change<? extends String> c) {
                    if(c!=null){
                        dAdd.setDisable(false);
                    }
                    else{
                        dAdd.setDisable(true);
                    }
                    
                }
                
            });


            itemsRight.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<String>() {

                @Override
                public void onChanged(Change<? extends String> c) {
                    if(c!=null){
                        dRemove.setDisable(false);
                    }
                    else{
                        dRemove.setDisable(true);
                    }
                    
                }
                
            });



        });

        
















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









    public ObservableList<String> randomPhones(ObservableList<String> list){            //A function that generates random phone numbers

        for(int i=0;i<50;i++){
            String phone = "69";

            for(int j=0;j<8;j++){
                int num = (int)(Math.random()*10);
                phone += num;
            }


            list.add(phone);

        }



        return list;
    }



    public String listToString(ObservableList<String> list){                            //Takes a list of numbers and converts it to a string ;number;number;
        String numbers = ";";

        for (String item : list) {
            numbers += item + ";";
        }


        return numbers;
    }



    public ObservableList<String> stringToList(String phones){            //A function that takes a string and extracts phones to a list
        ObservableList<String> list = FXCollections.observableArrayList();
        String[] array;

        array = phones.split(";");
        
        for(String phone: array){
            if(!phone.isBlank()){
                list.add(phone);
            }

        }



        return list;
    }












}