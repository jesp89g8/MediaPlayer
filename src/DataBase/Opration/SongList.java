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
public class SongList extends PlayList{
    private int songList;
    private int playListID;
    private ArrayList<Integer> musicID = new ArrayList<>();

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

    public ArrayList<Integer> playListIdToSongId(int playListID){
        String query = String.format("select fldMusicID from table_Songlist " +
                "where fldPlaylistID = %d",playListID);
        ArrayList<String> ids = SQL.selectSQL(query);

        for (String s: ids) { musicID.add(Integer.parseInt(s)); }

        return getMusicID();
    }

    public int findID(int id, String playlistName ){
        int playlistID = nameToId(playlistName);
        String query = String.format("select fldSongListID from table_Songlist " +
                "where fldMusicID = %d and fldPLaylistID = %d",id,playlistID);
        String findId = SQL.selectSQL(query).get(0);
        int songListID = Integer.parseInt(findId);

        System.out.println(
                "the music id is : " + id + " ; " +
                        " the playlist name is : " + playlistName + " ; " +
                        "so the songlist id is : " + songListID);
        System.out.println();
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
        System.out.println();
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
