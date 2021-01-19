package DataBase.Opration;

import DataBase.DBSetter.SQL;
import javafx.scene.control.ListView;

import java.util.ArrayList;

/**
 * This class provides methods to initialize the Song listview
 * and the playlist listview
 * @author Fei Gu
 */
public class InitListView {

    /**
     * Initializes the song listview
     * @param listview the song listview
     */
    public void initListviews(ListView<String> listview) {
        insertIntoListview("select fldMusicName from table_music", listview);
        System.out.println("add all the music into the Song list view...");

    }

    /**
     * Initializes the playlist listview
     * @param listView playlist listview
     */
    public void initPlaylistview(ListView<String> listView){
        insertIntoListview("select fldPlaylistName from table_Playlist", listView);
        System.out.println("add all the playlist into the Playlist view...");
    }

    /**
     * Queries the database with a select statement and inserts the output
     * into the specified listview
     * @param query select query
     * @param listview listview to insert into
     */
    private void insertIntoListview(String query, ListView<String> listview){
        ArrayList<String> queryData = SQL.selectSQL(query);     // selects data from the database with a given query

        // input the data into the given listview
        for (String data : queryData) {
            listview.getItems().add(data);
        }
    }
}
