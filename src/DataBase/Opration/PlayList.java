package DataBase.Opration;

import DataBase.DBSetter.DB;
import DataBase.SQL;

import java.util.ArrayList;

/**
 * @ author Fei Gu
 * @ create 2021-01-08-12.32
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class PlayList {
    private int playListID;
    private String playListName;

    public int getPlayListID() { return playListID; }

    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }


    /**
     * Trans the plaýlist name to playlist id
     * @param playListName as String
     * @return playlist Id as intger
     */
    public int nameToId(String playListName){
        String query = String.format("select fldPlaylistID from table_Playlist where fldPlaylistName = '%s'",playListName);
        String playlistID = SQL.selectSQL(query).get(0);
        setPlayListID(Integer.parseInt(playlistID));

        return getPlayListID();
    }


    /**
     * This is the method for add a new playlist into the DB
     * @param playListName String
     */
    public void addPlaylist(String playListName){

        String maxPlaylistIDQuery = String.format("select max(fldPlaylistID) from table_Playlist");
        String maxPlaylistID = SQL.selectSQL(maxPlaylistIDQuery).get(0);
        int id = Integer.parseInt(maxPlaylistID) + 1;
        String query = String.format("insert into table_Playlist values (%s,'%s')",id,playListName);
        DB.insertSQL(query);
    }

    /**
     * This is the method for delete the play list by playlistID
     * @param playListID int
     */
    public void deletePlayList(int playListID){
        String query = String.format("delete from table_Playlist where fldPlaylistID = %s",playListID);
        DB.deleteSQL(query);
    }

    /**
     * This is the method for delete the play list by playlistID
     * @param playListName String
     */
    public void deletePlayList(String playListName){
        String query = String.format("delete from table_Playlist where fldPlaylistName = '%s'",playListName);
        DB.deleteSQL(query);
    }
}
