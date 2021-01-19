package Model;

import DataBase.Opration.Music;
import javafx.scene.control.ListView;

/**
 * This class provides the functions to play a music. These methods
 * should have been implemented in the Music class, but since the Playable
 * interface was created at a late time, there was not enough time to
 * implement this and restructure the whole Controller class.
 * @author Jesper Raheela Zia and Fei
 */
public class MusicOperation extends LoadingMediaPlay {

    public Music playingMusic;  // current playing music

    /**
     * This play method starts the music by playing the associated
     * media player.
     * @param selectedMusic the music to play
     */
    public void play(Music selectedMusic){
        if(selectedMusic == null) {                                 // if the selected music is null
            System.out.println("There is no music be selected.");   // print error and do nothing
        }
        else if(playingMusic == null){  // if the playing music does not exist
            // print out the selected music
            System.out.printf(
                    "There is no music be playing, so play the selected music. The music %s has been selected.",
                    selectedMusic.getMusicName()
            );

            // if playingMusic is null, then start play selected music
            playingMusic = selectedMusic;
            playingMusic.getMediaPlayer().play();
        }
        else if(playingMusic != selectedMusic){     // if the selected music does is not the same as playing music
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

    /**
     * This is the pause function for a currently playing music
     */
    public void pause(){
        // return if there is no music playing or if the playing music player is null
        if(playingMusic == null || playingMusic.getMediaPlayer() == null) {
            System.out.println("There is no music be playing or no music be loading...");
            return;
        }
        System.out.println("Pause the playing music: " + playingMusic.getMusicName());
        playingMusic.getMediaPlayer().pause();              // pause the playing music
    }

    /**
     * Stops the currently playing music
     */
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

    /**
     * This next function is used to select the next music on relative
     * to the current playing music
     */
    public void next(){
        System.out.println("Change to the next music...");
        // return if there is no playing music
        if(playingMusic == null) return;

        // get the current listview which contains the playing music
        ListView<String> sourceListView = playingMusic.getSourceListView();

        // get the current playing music name
        String playingMusicName = playingMusic.getMusicName();
        // get the index of the playing music from the source list view
        int playingMusicIndex = playingMusic.getSourceListView().getItems().indexOf(playingMusicName);
        // gets the next music name of this listview
        String nextMusicName = playingMusic.getSourceListView().getItems().get((playingMusicIndex + 1) % sourceListView.getItems().size());

        Music nextMusic = new Music(nextMusicName);                     // create a new music object with new music name
        nextMusic.setId(nextMusic.nameToId(nextMusic.getMusicName()));  // set the id of new music
        nextMusic.setSourceListView(sourceListView);                    // set the new source list view

        loadingMediaPlay(nextMusic);    // load the next music
        play(nextMusic);                // play the next music
    }
}
