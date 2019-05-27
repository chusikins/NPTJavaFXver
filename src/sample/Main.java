package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    Stage window;
    Scene mainpage, rapspage;
    ListView mListView = new ListView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        window = primaryStage;
        primaryStage.setTitle("Rasp Thingy");
        window.setOnCloseRequest(e -> closeProgram());


        TextField keyinput = new TextField();
        TextField stationinput = new TextField();




        Button torasppage = new Button("Get times");
        torasppage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String key = keyinput.getText();
                String station = stationinput.getText();
                ArrayList info1 = trninfconverter(key,station);
                for (int i = 0; i < info1.size(); i++){
                    mListView.getItems().add(info1.get(i));
                }

                window.setScene(rapspage);
            }
        });
        Button quit = new Button("Quit");
        quit.setOnAction(e -> closeProgram());

        VBox mainpagelay = new VBox(20);
        mainpagelay.getChildren().addAll(keyinput,stationinput,torasppage,quit);
        mainpage = new Scene(mainpagelay, 300, 250);

        Button tomain = new Button("Get back");
        tomain.setOnAction(e -> window.setScene(mainpage));

        Label rasppagetext = new Label("Schedule");
        VBox rasppagelay = new VBox(20);
        rasppagelay.setSpacing(5);
        rasppagelay.getChildren().addAll(rasppagetext,tomain,mListView);

        rapspage = new Scene(rasppagelay, 600, 300);
        window.setScene(mainpage);
        window.show();

    }
    private static ArrayList trninfconverter(String key,String station){
        ArrayList traininfo;
        ArrayList<String> info = new ArrayList<>();
        traininfo = Getter.getter(key, station);
        for (int i = 0; i < traininfo.size(); i++){
            String temp = traininfo.get(i).toString();
            String[] temp2 = temp.split("/");
            info.add((temp2[0] + "  " + temp2[1] + "  " + temp2[2]));
        }
        return info;
    }

    private void closeProgram(){
        Boolean answer = AlertBox.display("Title", "Are you sure you want to exit?");
        if(answer){
            window.close();
        }
    }
}



