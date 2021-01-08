package sample;

import DataBase.DBSetter.DB;
import DataBase.SQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;


/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */


public class Controller {

    @FXML
    private Button btnPlay,btnPause,btnStop,btnNewPlaylist,btnDeletePlaylist,btnAddToPlaylist,btnDeleteFromPlaylist;
    @FXML
    private ListView<String> listviewSong, listviewInfo, listviewPlaylist;

    private Media media;
    private MediaPlayer mediaPlayer;



    public void initialize() {
        // Build the path to the location of the media file
        String path = new File(SQL.getPath()).getAbsolutePath();
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
