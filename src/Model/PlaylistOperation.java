package Model;

import DataBase.DBSetter.DB;
import DataBase.Opration.PlayList;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

/**
 * This playlist class "implements" the functionality a playable item should
 * have "using" the Playable interface. Since the interface Playable was added
 * quite late in the project span, it would therefore cost a lot of extra
 * work and time to restructure the controller.java to be compatible with
 * with the interface.
 * The class is used also used for creating and deleting playlists.
 * @author Jesper Raheela Zia and Fei
 */
public class PlaylistOperation extends PlayList{
    PlayList playingPlaylist;   // current playing playlist

    /**
     * Gets the current playing playlist
     * @return playing playlist
     */
    public PlayList getPlayingPlaylist(){
        return this.playingPlaylist;
    }

    /**
     * Set the current playing playlist
     * @param playingPlaylist the playlist to set to
     */
    public void setPlayingPlaylist(PlayList playingPlaylist) {
        this.playingPlaylist = playingPlaylist;
    }

    /**
     * Create an empty new playlist
     */
    public void newPlayList(){
        int playListMaxID = getMaxPlaylistID();
        addPlaylist("New Playlist " + (playListMaxID + 1));
    }

    /**
     * Deletes an existing playlist.
     * @param selectedPlaylist selected playlist to delete
     */
    public void deletePlaylist(String selectedPlaylist){
        if(selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }
        deleteAllMusic(selectedPlaylist);       // delete all music from the playlist and playlist data from database
        super.deletePlayList(selectedPlaylist); // delete the playlist
    }

    /**
     * Delete all music from the songlist table from Database
     * @param selectedPlaylist  selected playlist
     */
    public void deleteAllMusic(String selectedPlaylist){
        int playlistID = new PlayList().nameToId(selectedPlaylist);

        String query = String.format("delete from table_Songlist where fldPlaylistID = %d", playlistID);
        DB.deleteSQL(query);
    }

    /**
     * The play method, used to start playing the playlist
     * @param selectedPlaylist  the playlist to start
     */
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

    /**
     * Pause the currently playing playlist
     */
    public void pause() {
        if(playingPlaylist == null) return;
        playingPlaylist.getCurrentPlaying().pause();
    }

    /**
     * Stop the currently playing playlist
     */
    public void stop() {
        playingPlaylist.getCurrentPlaying().stop();
    }

    /**
     * Start next song in the playlist
     */
    public void next(){
        // return if playing playlist is null
        if(playingPlaylist == null) return;

        // get the current playing media player ArrayList
        ArrayList<MediaPlayer> currentMediaPlayer = playingPlaylist.getMediaPlayer();

        // get the current playing media player index in the media player ArrayList
        int currentPlayingMediaPlayerIndex = currentMediaPlayer.indexOf(playingPlaylist.getCurrentPlaying());

        // get the next media player in the media player ArrayList by adding 1 to current playing media player index
        MediaPlayer nextPlayer = currentMediaPlayer.get((currentPlayingMediaPlayerIndex + 1) % currentMediaPlayer.size());


        playingPlaylist.getCurrentPlaying().stop();     // stop the current playing media player
        playingPlaylist.setCurrentPlaying(nextPlayer);  // set current player to next media player
        playingPlaylist.getCurrentPlaying().play();     // start the new media player
    }
}
