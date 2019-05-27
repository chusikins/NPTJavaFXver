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
    ArrayList traininfo;
    ListView mListView = new ListView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        ArrayList<String> info1 = new ArrayList<>();

        window = primaryStage;
        primaryStage.setTitle("Rasp Thingy");


        Button torasppage = new Button("Get times");
        torasppage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               traininfo = Getter.getter();
                for (int i = 0; i < traininfo.size(); i++){
                    String temp = traininfo.get(i).toString();
                    String[] temp2 = temp.split("/");
                    info1.add((temp2[0] + "  " + temp2[1] + "  " + temp2[2]));
                }
                for (String info1:info1){
                    mListView.getItems().add(info1);
                }

                window.setScene(rapspage);
            }
        });

        VBox mainpagelay = new VBox(20);
        mainpagelay.getChildren().addAll(torasppage);
        mainpage = new Scene(mainpagelay, 300, 250);

        Button tomain = new Button("Get back");
        tomain.setOnAction(e -> window.setScene(mainpage));

        ArrayList<Text> texti = new ArrayList<>();
        Label rasppagetext = new Label("Schedule");
        VBox rasppagelay = new VBox(20);
        rasppagelay.setSpacing(5);
        rasppagelay.getChildren().addAll(rasppagetext,tomain,mListView);

        rapspage = new Scene(rasppagelay, 600, 300);
        window.setScene(mainpage);
        window.show();

    }
}



