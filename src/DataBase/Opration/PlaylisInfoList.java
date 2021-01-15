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
public class PlaylisInfoList{
    private int songList;
    private int playListID;
    private ArrayList<Integer> musicID = new ArrayList<>();
    private Music musicOperation = new Music();

    public int getSongList() {
        return songList;
    }

    public void setSongList(int songList) {
        this.songList = songList;
    }

    public ArrayList<Integer> getMusicID() {
        return musicID;
    }

    public void setMusicID(ArrayList<Integer> musicID) {
        this.musicID = musicID;
    }

    public int getPlayListID() {
        return playListID;
    }

    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }


    /**
     * get all of the music id from the playlist
     * @param playListID the id of the playlist
     * @return an ArrayList of the music id
     */
    public ArrayList<Integer> playListIdToSongId(int playListID){
        String query = String.format("select fldMusicID from table_Songlist " +
                "where fldPlaylistID = %d",playListID);
        ArrayList<String> ids = SQL.selectSQL(query);

        for (String s: ids) { musicID.add(Integer.parseInt(s)); }

        return getMusicID();
    }



    /**
     * this is working for base the music id and the playlist to get the songlist id
     * @param id music id
     * @param playlistName playlist name
     * @return songlist id
     */
    public int findID(int id, String playlistName ){
        int playlistID = new PlayList().nameToId(playlistName);
        String query = String.format("select fldSongListID from table_Songlist " +
                "where fldMusicID = %d and fldPLaylistID = %d",id,playlistID);

        /*
        Here is a bug when the findId can not get a value because the record is not exist
        fx. selected music you choose from all music list
            selected playlist you choose from playlist which is not have the music you were choose
            so it shouldn't have the songlistID
            then exception
         */
        String findId = SQL.selectSQL(query).get(0);
        if(findId == null){
            System.out.println("The music " + musicOperation.idToName(id) + " is not in the playlist " + playlistName);
            return 0 ;
        }
        int songListID = Integer.parseInt(findId);

        System.out.println(
                "the music id is : " + id + " ; " +
                        " the playlist name is : " + playlistName + " ; " +
                        "so the songlist id is : " + songListID);
        return songListID;
    }

    /**
     * There is the method about add music into the songlist which belong to one of the playlist.
     * @param id the id of music
     * @param playListID the id of playlist
     */
    public void addMusic(int id,int playListID){
        String maxSonglistIDQuery = String.format("select max(fldSonglistID) from table_Songlist");
        String maxSonglistID = SQL.selectSQL(maxSonglistIDQuery).get(0);
        int songlistId = Integer.parseInt(maxSonglistID) + 1;
        String query = String.format("insert into table_Songlist values (%s,%s,%s)",songlistId,id,playListID);
        DB.insertSQL(query);

        System.out.println("the songlist : " + songlistId +
                " add the music : " + id +" ; " +
                "into " + playListID );
    }

    /**
     * there is the method about delete music from the songlist which belong to one of the playlist
     * @param songListID the id of songlist
     */
    public void deleteMusic (int songListID){
        String query = String.format("delete from table_Songlist where fldSongListID = %d",songListID);
        DB.deleteSQL(query);

        System.out.println("delete the music : " + songListID);
    }


}
