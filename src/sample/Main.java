package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

import DataBase.SQL;

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

    /**
     * Searches for the song listview, which should contain the available
     * songs from the database. Queries the database for the songs and
     * inserts them into the listview.
     * @param primaryStage stage of the app, used to locate the song listview
     */
    private void getSongs(Stage primaryStage) {
        ListView<String> listviewSong = (ListView<String>) primaryStage.getScene().lookup(LISTVIEWSONG_FXID);
        ArrayList<String> song = SQL.selectSQL("select fldMusicName from table_music");

        for (String songName : song) {
            listviewSong.getItems().add(songName);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
