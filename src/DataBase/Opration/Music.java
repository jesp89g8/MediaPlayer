package DataBase.Opration;

import DataBase.DBSetter.DB;
import DataBase.SQL;
import sample.Controller;

import java.util.ArrayList;

/**
 * @ author Fei Gu
 * @ create 2021-01-08-12.31
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class Music {
    private int musicID;
    private String musicName;
    private String Artist;
    private String Path;

    public Music() {
    }

    public Music(int musicID, String musicName, String artist, String path) {
        this.musicID = musicID;
        this.musicName = musicName;
        Artist = artist;
        Path = path;
    }

    public int getMusicID() {
        return musicID;
    }

    public void setMusicID(int musicID) {
        this.musicID = musicID;
    }

    public String getMusicName() {

        return this.musicName;
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




    public void setPath(String path) {
        Path = path;
    }


    @Override
    public String toString() {
        return "Music{" +
                "musicID=" + musicID +
                ", musicName='" + musicName + '\'' +
                ", Artist='" + Artist + '\'' +
                ", Path='" + Path + '\'' +
                '}';
    }

    /**
     * This is working for get the music file path from DB
     * @return Path : the music file path as String
     */
    public String getPath(){
        Controller musicName = new Controller();
        String musicPath = musicName.handleTest();
        ArrayList<String> getMusicPath = SQL.selectSQL("select fldPath from table_music where fldMusicName =" + getMusicName());

        return getMusicPath.get(0);
    }
}
