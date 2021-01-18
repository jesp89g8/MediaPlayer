package Model;

import DataBase.DBSetter.DB;
import DataBase.Opration.Music;
import DataBase.Opration.PlayList;
import DataBase.Opration.Playable;
import DataBase.Opration.PlaylisInfoList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.util.ArrayList;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the playlist opration class which is oprate the playlist by add, delete, modify
 * @ Version 0.1
 *
 */
public class PlaylistOpration extends PlayList{

    PlayList playingPlaylist;

    public PlayList getPlayingPlaylist(){
        return this.playingPlaylist;
    }


    /**
     * Create an empty new playlist
     */
    public void newPlayList(){
        int playListMaxID = getMaxPlaylistID();
        addPlaylist("New Playlist " + (playListMaxID + 1));
    }

    public void deletePlaylist(String selectedPlaylist){
        if(selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }
        deleteAllMusic(selectedPlaylist);
        super.deletePlayList(selectedPlaylist);
    }


    public void deleteAllMusic(String selectedPlaylist){
        int playlistID = new PlayList().nameToId(selectedPlaylist);

        String query = String.format("delete from table_Songlist where fldPlaylistID = %d", playlistID);
        DB.deleteSQL(query);
    }

    public void play(PlayList selectedPlaylist) {
        if(selectedPlaylist == null) {
            System.out.println("There is no playlist be selected.");
        }
        else if(playingPlaylist == null){
            System.out.println("There is no playlist be playing, so play the selected playlist." +
                    "The playlist " + selectedPlaylist.getPlayListName() + " has been selected. ");
            // if playingMusic is null, then start play selected music
            playingPlaylist = selectedPlaylist;
            playingPlaylist.getCurrentPlaying().play();
        }
        else if(playingPlaylist != selectedPlaylist){
            // if there is some music playing, and the user select another
            System.out.println("selected playlist is : " + selectedPlaylist.getPlayListName());
            System.out.println("playing playlist is : " + playingPlaylist.getPlayListName());

            playingPlaylist.getCurrentPlaying().stop();//stop the recently music
            playingPlaylist = selectedPlaylist; // load the new select

            System.out.println("Now the selected playlist is : " + selectedPlaylist.getPlayListName());
            System.out.println("Now the playing playlist is : " + playingPlaylist.getPlayListName());
            playingPlaylist.getCurrentPlaying().play(); // play the selected music
        }
        else{
            System.out.println("There is a playlist : " + playingPlaylist.getPlayListName() + " has been paused. " +
                    " Now play it...");
            playingPlaylist.getCurrentPlaying().play();   // else play the playing music has been paused
        }
    }

    public void pause() {
        playingPlaylist.getCurrentPlaying().pause();
    }

    public void stop() {
        playingPlaylist.getCurrentPlaying().stop();
    }

    public void next(){
        if(playingPlaylist == null) return;
        ArrayList<MediaPlayer> currentMediaPlayer = playingPlaylist.getMediaPlayer();
        int currentPlayingMediaPlayerIndex = currentMediaPlayer.indexOf(playingPlaylist.getCurrentPlaying());
        MediaPlayer nextPlayer = currentMediaPlayer.get((currentPlayingMediaPlayerIndex + 1) % currentMediaPlayer.size());

        playingPlaylist.getCurrentPlaying().stop();
        playingPlaylist.setCurrentPlaying(nextPlayer);
        playingPlaylist.getCurrentPlaying().play();
    }
}
