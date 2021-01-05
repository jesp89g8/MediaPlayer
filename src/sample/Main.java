package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    final String GUI = "MediaPlayer.fxml";
    final String TITLE = "MediaPlayer";
    final String LISTVIEWSONG_FXID = "#listviewSong";
    final int RES_WIDTH = 1200;
    final int RES_HEIGHT = 800;



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(GUI));
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, RES_WIDTH, RES_HEIGHT));
        primaryStage.show();

        getSongs(primaryStage);
    }

    private void getSongs(Stage primaryStage) {
        ListView<String> listviewSong = (ListView<String>) primaryStage.getScene().lookup(LISTVIEWSONG_FXID);
        ArrayList<String> song = new ArrayList<>();

        /*
        get songs from db,
        add them to song ArrayList,
        insert them into listviewSong
         */

    }

    public static void main(String[] args) {
        launch(args);
    }
}
