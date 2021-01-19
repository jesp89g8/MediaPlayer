package Model;

import DataBase.Opration.Music;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * This class holds the methods to load a selected music with a
 * media player.
 * @author Fei Gu
 */
public class LoadingMediaPlay {
    String path;                // path for the music
    Media media;                // media using the path
    MediaPlayer mediaPlayer;    // media player using the media

    public void loadingMediaPlay(Music selectedMusic){
        path = new File(selectedMusic.getPath()).getAbsolutePath();     // get the absolute path of the music
        media = new Media(new File(path).toURI().toString());           // initialize the media with the path
        mediaPlayer = new MediaPlayer(media);                           // attach the media to a media player

        selectedMusic.setMedia(media);              // set the selected musics media
        selectedMusic.setMediaPlayer(mediaPlayer);  // set the selected musics media player
    }
}
