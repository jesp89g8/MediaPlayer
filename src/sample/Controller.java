package sample;

import DataBase.DBSetter.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Button btnPlay,btnPause,btnStop,btnNewPlaylist,btnDeletePlaylist,btnAddToPlaylist,btnDeleteFromPlaylist;
    @FXML
    private ListView<String> listviewSong, listviewInfo, listviewPlaylist;

    private Media media;
    private MediaPlayer mediaPlayer;

    public String getPath(){
        DB.selectSQL("select fldPath from table_music where fldMusicID = 1");
        return DB.getData();
    }


    public void initialize(){
        // Build the path to the location of the media file
        String path = new File(getPath()).getAbsolutePath();
        // Create new Media object (the actual media content)
        media = new Media(new File(path).toURI().toString());
        // Create new MediaPlayer and attach the media to be played
        mediaPlayer = new MediaPlayer(media);
        //
        // mp.setAutoPlay(true);
        // If autoplay is turned off the method play(), stop(), pause() etc controls how/when medias are played
        mediaPlayer.setAutoPlay(false);

    }



    public void handlePlay(){
        mediaPlayer.play();

    }

    public void handlePause(){
        mediaPlayer.pause();
    }

    public void handleStop(){
        mediaPlayer.stop();
    }

    public void handleNewPlayList(){
    }

    public void handleDeletePlaylist(){

    }

    public void handleAddToPlaylist(){

    }

    public void handleDeleteFromPlaylist(){

    }
}
