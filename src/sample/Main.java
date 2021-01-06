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
    final String LISTVIEWPLAYLIST_FXID = "#listviewPlaylist";
    final int RES_WIDTH = 1200;
    final int RES_HEIGHT = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(GUI));
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, RES_WIDTH, RES_HEIGHT));
        primaryStage.show();

        initListviews(primaryStage);
    }

    /**
     * Initializes the Song and Playlist Listview
     * @param primaryStage
     */
    private void initListviews(Stage primaryStage) {
        ListView<String> listviewSong = (ListView<String>) primaryStage.getScene().lookup(LISTVIEWSONG_FXID);
        ListView<String> listviewPlaylist = (ListView<String>) primaryStage.getScene().lookup(LISTVIEWPLAYLIST_FXID);

        insertIntoListview("select fldMusicName from table_music",listviewSong);
        insertIntoListview("select fldPlaylistName from table_Playlist",listviewPlaylist);
    }

    /**
     * Queries the database with a select statement and inserts the output
     * into the specified listview
     * @param query select query
     * @param listview listview to insert into
     */
    private void insertIntoListview(String query,ListView<String> listview){
        ArrayList<String> queryData = SQL.selectSQL(query);

        for (String data : queryData) {
            listview.getItems().add(data);
        }
    }
}
