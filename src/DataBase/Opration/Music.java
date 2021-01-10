package DataBase.Opration;

import DataBase.SQL;
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

    public Music(int id,String name, String artist,String path){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.path = path;
    }

    public int getMusicID() {
        return id;
    }

    public void setMusicID(int id) {
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
        System.out.println("loading the path by quary..");
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
}
