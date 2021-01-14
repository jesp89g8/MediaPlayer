package Model;

import DataBase.Opration.Music;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * @ author Fei Gu
 * @ create 2021-01-14-13.34
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class LodingMediaPlay {
    String path;
    Media media;
    MediaPlayer mediaPlayer;

    public void lodingMediaPlay(Music selectedMusic){
        path = new File(selectedMusic.getPath()).getAbsolutePath();    // get the absolute path of the music
        media = new Media(new File(path).toURI().toString());   // initialize the media with the path
        mediaPlayer = new MediaPlayer(media);                   // attach the media to a media player

        selectedMusic.setMedia(media);
        selectedMusic.setMediaPlayer(mediaPlayer);

    }
}
