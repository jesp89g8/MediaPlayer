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
    private int musicID;
    private String musicName;
    private String Artist;
    private String Path;
    private Media media;
    private MediaPlayer mediaPlayer;

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


    /**
     * This is working for get the music file path from DB
     * @return Path : the music file path as String
     */
    public String getPath(){
        return "test";
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
