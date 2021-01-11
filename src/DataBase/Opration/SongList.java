package DataBase.Opration;

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
        String query = String.format("select fldMusicID from table_Songlist where fldPlaylistID = %d",playListID);
        ArrayList<String> ids = SQL.selectSQL(query);

        for (String s: ids) { musicID.add(Integer.parseInt(s)); }

        return getMusicID();
    }
}
