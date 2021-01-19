package DataBase.Opration;

import DataBase.DBSetter.DB;
import DataBase.DBSetter.SQL;

import java.util.ArrayList;

/**
 * The Playlist class contains fields, describing the the id and name
 * of the playlist. The class also provides useful functionality like
 * nameToId and addPlaylist, which is used throughout the project.
 * @author Jesper Raheela Zia and Fei
 */
public class PlayList extends PlaylistInfoList implements Playable{
    private int playListID;         // playlist id
    private String playListName;    // playlist name


    public PlayList() {
    }

    /**
     * Constructs a playlist object with a given name, and then
     * queries the database for the corresponding id.
     * @param playListName name of the playlist
     */
    public PlayList(String playListName) {
        this.playListName = playListName;
        this.playListID = nameToId(this.playListName);
    }

    /**
     * Constructs a playlist with a given id
     * @param playListID play list id
     */
    public PlayList(int playListID) {
        this.playListID = playListID;
    }

    /**
     * Constructs a playlist with a given playlist id and
     * name
     * @param playListID playlist id
     * @param playListName playlist name
     */
    public PlayList(int playListID, String playListName) {
        this.playListID = playListID;
        this.playListName = playListName;
    }

    /**
     * Gets the id of the playlist object.
     * @return playlist id
     */
    public int getPlayListID() { return playListID; }

    /**
     * Sets the playlist id of this object.
     * @param playListID id to set
     */
    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }

    /**
     * Gets the playlist name.
     * @return playlist name
     */
    public String getPlayListName() {
        return playListName;
    }

    /**
     * Sets the playlist name with a given name
     * @param playListName name
     */
    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    /**
     * Trans the playlist name to playlist id
     * @param playListName as String
     * @return playlist Id as integer
     */
    public int nameToId(String playListName){
        // Query setup
        String query = String.format(
                "select fldPlaylistID from table_Playlist where fldPlaylistName = '%s'",
                playListName.replace("'","''"));

        String playlistID = SQL.selectSQL(query).get(0);    // get the id of the playlist
        setPlayListID(Integer.parseInt(playlistID));        // set playlist id

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

        String query = String.format(
                "insert into table_Playlist values (%s,'%s')",
                id,playListName.replace("'","''")
        );
        DB.insertSQL(query);

        System.out.println("add the playlist : " + playListName + " ; the id is : " + id);
        System.out.println();
    }

    /**
     * This is the method for delete the play list by playlistID
     * @param playListID int
     */
    public void deletePlayList(int playListID){
        String query = String.format("delete from table_Playlist where fldPlaylistID = %s",playListID);
        DB.deleteSQL(query);

        System.out.println("delete the playlist, the id is : " + playListID);
        System.out.println();
    }

    /**
     * This is the method for delete the play list by playlistID
     * @param playListName String
     */
    public void deletePlayList(String playListName){
        String query = String.format("delete from table_Playlist where fldPlaylistName = '%s'",playListName);
        DB.deleteSQL(query);

        System.out.println("delete the playlist, the name is : " + playListName);
        System.out.println();
    }

    /**
     * Method used for getting the greatest value of playlist id
     * @return greatest playlist id
     */
    public int getMaxPlaylistID() {
        String maxMusicIDQuery = "select max(fldPlaylistID) from table_Playlist";
        String maxMusicID = SQL.selectSQL(maxMusicIDQuery).get(0);

        return Integer.parseInt(maxMusicID);
    }

    /**
     * This method is used to the name of a playlist. The method will first
     * perform a check in the database to determine if the new name of the
     * playlist already exists. If not the new name will be updated in the
     * database.
     * @param oldName old name of the playlist
     * @param newName new name
     * @return true if success, false if fail
     */
    public boolean editPlayListName(String oldName,String newName){
        // Query setup, used to check for an existing name
        String query = String.format(
                "select fldPlaylistName from table_Playlist where fldPlaylistName = '%s'",
                newName.replace("'","''")
        );

        // if the query returns null, then the name does not exist
        ArrayList<String> existingName = SQL.selectSQL(query);

        // if existing name is empty
        if(existingName.isEmpty()){
            // update sql statement
            query = String.format(
                    "update table_Playlist set fldPlaylistName = '%s' where fldPlaylistName = '%s'",
                    newName,oldName
            );
            DB.updateSQL(query);    // update the playlist row
            return true;
        }
        else{
            return false;
        }
    }
}
