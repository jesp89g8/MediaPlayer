package DataBase.Opration;

import DataBase.SQL;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private int id;
    private String name;
    private String artist;
    private String path;
    private Media media;
    private MediaPlayer mediaPlayer;


    public Music() {
    }

    public Music(int id, String name, String artist, String path){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicName() {
        return this.name;
    }

    public void setMusicName(String musicName) {
        this.name = musicName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }




    /**
     * this is "try to make it easier" by fei
     * and i think we can talk to use these function to instead some part make the code clearly.
     * @param id
     * @return
     */
    public String idToName(int id){
        String query = String.format("select fldMusicName from table_music where fldMusicID = '%d'",id);
        setMusicName(SQL.selectSQL(query).get(0));
        return getMusicName();
    }

    public int nameToId(String musicName) {
        String query = String.format("select fldMusicID from table_music where fldMusicName = '%s'",musicName);
        String musicID = SQL.selectSQL(query).get(0);

        setId(Integer.parseInt(musicID));

        return getId();
    }

    public String idToPath(int id){
        String query = String.format("select fldPath from table_music where fldMusicID = '%d'",id);
        setPath(SQL.selectSQL(query).get(0));
        return getPath();
    }
}
