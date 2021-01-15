package Model;

import DataBase.Opration.Music;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */
public class MusicOperation extends LodingMediaPlay {

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

    public void next(Music selectedMusic){
        System.out.println("Change to the next music...");
        int nextId = selectedMusic.getId() + 1;
       // String path = selectedMusic.idToPath(nextId);

        Music nextMusic = new Music(nextId);
        lodingMediaPlay(nextMusic);
        // selectedMusic.setPath(path);
        play(nextMusic);
    }
}
