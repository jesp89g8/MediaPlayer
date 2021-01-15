package DataBase.Opration;

import DataBase.DBSetter.SQL;
import javafx.scene.control.ListView;

import java.util.ArrayList;

/**
 * @ author Fei Gu
 * @ create 2021-01-15-07.53
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class InitListView {

    /**
     * Initializes the Song and Playlist Listview
     */
    public void initListviews(ListView<String> listview) {

        insertIntoListview("select fldMusicName from table_music", listview);
        System.out.println("add all the music into the Song list view...");

    }
    public void initPlaylistview(ListView<String> listView){
        insertIntoListview("select fldPlaylistName from table_Playlist", listView);
        System.out.println("add all the playlist into the Playlist view...");
    }

    public void initPlaylistInfo(PlayList selectedPlaylist,ListView<String> listView){
        int playlistId = selectedPlaylist.getPlayListID();
        ArrayList<Integer> musicID = new PlaylisInfoList().playListIdToSongId(playlistId);

        String queryGetName = String.format("select fldMusicName from table_music where fldMusicID = %d",musicID);

        insertIntoListview(queryGetName,listView);
    }
    /**
     * Queries the database with a select statement and inserts the output
     * into the specified listview
     * @param query select query
     * @param listview listview to insert into
     */
    private void insertIntoListview(String query, ListView<String> listview){
        ArrayList<String> queryData = SQL.selectSQL(query);

        for (String data : queryData) {
            listview.getItems().add(data);
        }
    }
}
