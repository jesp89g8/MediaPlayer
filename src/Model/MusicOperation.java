package Model;

import DataBase.Opration.Music;
import javafx.scene.control.ListView;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * This class provides the functions to play a music. These methods
 * should have been implemented in the Music class, but since the Playable
 * interface was created at a late time, this would have cost a lot of work
 * and time.
 * @ Version 0.1
 */
public class MusicOperation extends LoadingMediaPlay {

    public Music playingMusic;


    public void play(Music selectedMusic){
        if(selectedMusic == null) {
            System.out.println("There is no music be selected.");
            //return;           // return if the no music is selected
        }
        else if(playingMusic == null){
            System.out.println("There is no music be playing, so play the selected music." +
                    "The music " + selectedMusic.getMusicName() + " has been selected. ");
            // if playingMusic is null, then start play selected music
            playingMusic = selectedMusic;
            playingMusic.getMediaPlayer().play();
        }
        else if(playingMusic != selectedMusic){
            // if there is some music playing, and the user select another
            System.out.println("selected music is : " + selectedMusic.getMusicName());
            System.out.println("playing music is : " + playingMusic.getMusicName());

            playingMusic.getMediaPlayer().stop();//stop the recently music
            playingMusic = selectedMusic; // load the new select

            System.out.println("Now the selected music is : " + selectedMusic.getMusicName());
            System.out.println("Now the playing music is : " + playingMusic.getMusicName());
            playingMusic.getMediaPlayer().play(); // play the selected music
        }
        else{
            System.out.println("There is a music : " + playingMusic.getMusicName() + " has been paused. " +
                    " Now play it...");
            playingMusic.getMediaPlayer().play();   // else play the playing music has been paused
        }
    }

    public void pause(){
        // return if there is no music playing or if the playing music player is null
        if(playingMusic == null || playingMusic.getMediaPlayer() == null) {
            System.out.println("There is no music be playing or no music be loading...");
            return;
        }
        System.out.println("Pause the playing music: " + playingMusic.getMusicName());
        playingMusic.getMediaPlayer().pause();              // pause the playing music
    }

    public void stop(){
        // return if there is no music playing or if the playing music player is null
        if(playingMusic == null || playingMusic.getMediaPlayer() == null) {
            System.out.println("There is no music be playing or no music be loading...");
            return;
        }
        System.out.println("Stop the playing music: " + playingMusic.getMusicName());
        playingMusic.getMediaPlayer().stop();               // stop the playing music
        playingMusic = null;                                // delete the playing music object
    }

    public void next(){
        System.out.println("Change to the next music...");
        if(playingMusic == null) return;
        ListView<String> sourceListView = playingMusic.getSourceListView();

        String playingMusicName = playingMusic.getMusicName();
        int playingMusicIndex = playingMusic.getSourceListView().getItems().indexOf(playingMusicName);
        String nextMusicName = playingMusic.getSourceListView().getItems().get((playingMusicIndex + 1) % sourceListView.getItems().size());

        Music nextMusic = new Music(nextMusicName);
        nextMusic.setId(nextMusic.nameToId(nextMusic.getMusicName()));
        nextMusic.setSourceListView(sourceListView);

        lodingMediaPlay(nextMusic);
        play(nextMusic);
    }
}
