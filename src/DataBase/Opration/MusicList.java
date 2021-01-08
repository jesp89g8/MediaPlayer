package DataBase.Opration;

/**
 * @ author Fei Gu
 * @ create 2021-01-08-12.31
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class MusicList {
    private int musicID;
    private String musicName;
    private String Artist;
    private String Path;

    public int getMusicID() {
        return musicID;
    }

    public void setMusicID(int musicID) {
        this.musicID = musicID;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
