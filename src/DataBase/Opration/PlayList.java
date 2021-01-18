package DataBase.Opration;

import DataBase.DBSetter.DB;
import DataBase.DBSetter.SQL;

import java.util.ArrayList;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */
public class PlayList extends PlaylisInfoList implements Playable{
    private int playListID;
    private String playListName;



    public PlayList() {
    }

    public PlayList(String playListName) {
        this.playListName = playListName;
        this.playListID = nameToId(this.playListName);
    }

    public PlayList(int playListID) {
        this.playListID = playListID;
    }

    public PlayList(int playListID, String playListName) {
        this.playListID = playListID;
        this.playListName = playListName;
    }

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
        String query = String.format("select fldPlaylistID from table_Playlist where fldPlaylistName = '%s'",playListName.replace("'","''"));
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
        String query = String.format("insert into table_Playlist values (%s,'%s')",id,playListName.replace("'","''"));
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

    public int getMaxPlaylistID() {
        String maxMusicIDQuery = "select max(fldPlaylistID) from table_Playlist";
        String maxMusicID = SQL.selectSQL(maxMusicIDQuery).get(0);

        return Integer.parseInt(maxMusicID);
    }

    public boolean editPlayListName(String oldName,String newName){
        String query = String.format("select fldPlaylistName from table_Playlist where fldPlaylistName = '%s'",newName.replace("'","''"));

        ArrayList<String> existingName = SQL.selectSQL(query);

        if(existingName.isEmpty()){
            query = String.format(
                    "update table_Playlist set fldPlaylistName = '%s' where fldPlaylistName = '%s'",
                    newName,oldName
            );
            DB.updateSQL(query);
            return true;
        }
        else{
            return false;
        }
    }
}
