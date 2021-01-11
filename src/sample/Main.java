package sample;

import DataBase.DBSetter.DBTester;
import DataBase.Opration.SongList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

import DataBase.SQL;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */

public class Main extends Application {

    final String GUI = "MediaPlayer.fxml";
    final String TITLE = "MediaPlayer";
    final String LISTVIEWSONG_FXID = "#listviewSong";
    final String LISTVIEWPLAYLIST_FXID = "#listviewPlaylist";
    final String LISTVIEWINFO_FXID = "#listviewInfo";
    final int RES_WIDTH = 1000;
    final int RES_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is the basic part of showing the main window
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("loading the scene...");
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
        System.out.println("loading the listview...");
        ListView<String> listviewSong = (ListView<String>) primaryStage.getScene().lookup(LISTVIEWSONG_FXID);
        ListView<String> listviewPlaylist = (ListView<String>) primaryStage.getScene().lookup(LISTVIEWPLAYLIST_FXID);
        ListView<String> ListViewInfo = (ListView<String>) primaryStage.getScene().lookup(LISTVIEWINFO_FXID);


        System.out.println("add all the music into the Song list view...");
        insertIntoListview("select fldMusicName from table_music",listviewSong);

        System.out.println("add all the playlist into the Playlist view...");
        insertIntoListview("select fldPlaylistName from table_Playlist",listviewPlaylist);

        /*
        System.out.println("add 1st playlist into the Infolist...");
        Controller hsl= new Controller();
        ArrayList<String> getitems = hsl.handleSongListView("chinese song");
        for(String a : getitems){
            ListViewInfo.getItems().add(a);
        }

         */
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
