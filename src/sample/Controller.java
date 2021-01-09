package sample;

import DataBase.DBSetter.DB;
import DataBase.SQL;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import DataBase.Opration.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */


public class Controller{

    @FXML
    private Button btnPlay,btnPause,btnStop,btnNewPlaylist,btnDeletePlaylist,btnAddToPlaylist,btnDeleteFromPlaylist;
    @FXML
    private ListView<String> listviewSong, listviewInfo, listviewPlaylist;
    @FXML
    private TextField txtfldSelected,txtfldSearch;

    private Music selectedMusic;
    private Music playingMusic;

    /*public void initialize(URL location, ResourceBundle resources) {
        // Build the path to the location of the media file
        Music musicPath = new Music();
        String path = new File(musicPath.getPath()).getAbsolutePath();
        // Create new Media object (the actual media content)

        media = new Media(new File(path).toURI().toString());
        // Create new MediaPlayer and attach the media to be played
        mediaPlayer = new MediaPlayer(media);
        //
        // mp.setAutoPlay(true);
        // If autoplay is turned off the method play(), stop(), pause() etc controls how/when medias are played
        mediaPlayer.setAutoPlay(false);
    }*/


    /**
     * Plays the selected music
     */
    public void handlePlay(){
        if(selectedMusic == null) return;           // return if the no music is selected
        if(playingMusic == null){                   // if playingMusic is null, then start play selected music
            selectedMusic.getMediaPlayer().play();
            playingMusic = selectedMusic;
        }
        else{
            playingMusic.getMediaPlayer().play();   // else play the playing music has been paused
        }

    }

    /**
     * Pauses the playing music
     */
    public void handlePause(){
        // return if there is no music playing or if the playing music player is null
        if(playingMusic == null || playingMusic.getMediaPlayer() == null) return;
        playingMusic.getMediaPlayer().pause();              // pause the playing music
    }

    public void handleStop(){
        // return if there is no music playing or if the playing music player is null
        if(playingMusic == null || playingMusic.getMediaPlayer() == null) return;
        playingMusic.getMediaPlayer().stop();               // stop the playing music
        playingMusic = null;                                // delete the playing music object
    }

    public void handleNewPlayList(){
    }

    public void handleDeletePlaylist(){

    }

    public void handleAddToPlaylist(){

    }

    public void handleDeleteFromPlaylist(){

    }

    /**
     * Handles user selection in the song ListView
     */
    public void handleListViewSong(){
        Media media;                // the media for the selected song
        MediaPlayer mediaPlayer;    // the media player for the selected song

        String selectedSong = listviewSong.getSelectionModel().getSelectedItem();   // get the name of the selected song
        if(selectedSong == null) return;                        // if selected song name is null, return
        txtfldSelected.setText("Selected: " + selectedSong);    // update the "selected: " text field

        // sql query setup
        String query = String.format(
                "select * from table_music where fldMusicName = '%s'",    // the query statement, get path from DB
                selectedSong.replace("'","''")  // replace apostrophes with double apostrophes to avoid errors in sql
        );

        ArrayList<String> musicData = SQL.selectSQL(query); // query the DB for the music data
        // initialize the selected music object
        selectedMusic = new Music(
                Integer.parseInt(musicData.get(0)), // music id
                musicData.get(1),                   // music name
                musicData.get(2),                   // music artist
                musicData.get(5)                    // music path
        );

        String path = new File(selectedMusic.getPath()).getAbsolutePath();    // get the absolute path of the music
        media = new Media(new File(path).toURI().toString());   // initialize the media with the path
        mediaPlayer = new MediaPlayer(media);                   // attach the media to a media player

        selectedMusic.setMedia(media);              // set the media of the music to the media containing the song
        selectedMusic.setMediaPlayer(mediaPlayer);  // set the media player of the music, with the media player containing the song
    }

    public void handleSearch(Event e){
        KeyEvent kEvent = (KeyEvent) e;
        ArrayList<String> result;

        if(kEvent.getCode() == KeyCode.ENTER){
            String searchString = txtfldSearch.getText();
            String query = String.format("select fldMusicName from table_music where fldMusicName like '%%%s%%'",searchString);
            result = SQL.selectSQL(query);

            listviewSong.getItems().clear();

            for (String s: result) {
                listviewSong.getItems().add(s);
            }
        }
    }
}