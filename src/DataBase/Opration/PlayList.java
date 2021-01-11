package DataBase.Opration;

import DataBase.SQL;

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

    public int trans(String playListName){
        setPlayListID(SQL.selectSQL("select fldPlaylistID from table_Playlist where fldPlaylistName = %s" + playListName).get(0);
        return getPlayListID();
    }
}
