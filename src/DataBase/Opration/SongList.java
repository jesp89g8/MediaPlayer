package DataBase.Opration;

/**
 * @ author Fei Gu
 * @ create 2021-01-08-12.32
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class SongList {
    private int songList;
    private int musicID;
    private int playListID;

    public int getSongList() {
        return songList;
    }

    public void setSongList(int songList) {
        this.songList = songList;
    }

    public int getMusicID() {
        return musicID;
    }

    public void setMusicID(int musicID) {
        this.musicID = musicID;
    }

    public int getPlayListID() {
        return playListID;
    }

    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }
}
