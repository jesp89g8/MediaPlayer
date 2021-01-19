package DataBase.Opration;

import DataBase.DBSetter.DB;
import DataBase.DBSetter.SQL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

/**
 * This class has information about the a playlist. It contains the music ids
 * of the music in this playlist and controls the current playing media player.
 * Methods for adding and deleting music from the playlist is also present in
 * this class.
 * @author Jesper Raheela Zia and Fei
 */
public class PlaylistInfoList {
    private int songList;   // songlist id
    private int playListID; // playlist id
    private ArrayList<Integer> musicID = new ArrayList<>();         // music ids
    private ArrayList<MediaPlayer> mediaPlayer = new ArrayList<>(); // music media players
    private Music musicOperation = new Music();                     // music object used for basic music operations
    private MediaPlayer currentPlaying;                             // the currently playing media player

    /**
     * Sets the current playing media player to a given media
     * player.
     * @param mp media player reference
     */
    public void setCurrentPlaying(MediaPlayer mp){
        this.currentPlaying = mp;
    }

    /**
     * Gets the media player all the music in this playlist.
     * @return ArrayList of media players
     */
    public ArrayList<MediaPlayer> getMediaPlayer(){
        return this.mediaPlayer;
    }

    /**
     * Gets the song list
     * @return song list
     */
    public int getSongList() {
        return songList;
    }

    /**
     * Sets the song list
     * @param songList song list
     */
    public void setSongList(int songList) {
        this.songList = songList;
    }

    /**
     * Gets the music ids
     * @return ArrayList of music ids
     */
    public ArrayList<Integer> getMusicID() {
        return musicID;
    }

    /**
     * Sets the music id ArrayList
     * @param musicID reference to a music id ArrayList
     */
    public void setMusicID(ArrayList<Integer> musicID) {
        this.musicID = musicID;
    }

    /**
     * Get the playlist id
     * @return playlist id
     */
    public int getPlayListID() {
        return playListID;
    }

    /**
     * Sets the playlist id
     * @param playListID play list id to set
     */
    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }


    /**
     * get all of the music id from the playlist
     * @param playListID the id of the playlist
     * @return an ArrayList of the music id
     */
    public ArrayList<Integer> playListIdToSongId(int playListID){
        String query = String.format("select fldMusicID from table_Songlist where fldPlaylistID = %d",playListID);
        ArrayList<String> ids = SQL.selectSQL(query);

        for (String s: ids) { musicID.add(Integer.parseInt(s)); }

        return getMusicID();
    }



    /**
     * this is working for base the music id and the playlist to get the songlist id
     * @param id music id
     * @param playlistName playlist name
     * @return songlist id
     */
    public int findID(int id, String playlistName ){
        int playlistID = new PlayList().nameToId(playlistName);

        // Query setup to find song list id
        String query = String.format(
                "select fldSongListID from table_Songlist where fldMusicID = %d and fldPLaylistID = %d",
                id,playlistID
        );

        /*
        Here is a bug when the findId can not get a value because the record is not exist
        fx. selected music you choose from all music list
            selected playlist you choose from playlist which is not have the music you were choose
            so it shouldn't have the songlistID
            then exception
         */

        ArrayList<String> musicId = SQL.selectSQL(query);

        // if music id does not exist, return 0
        if(musicId.isEmpty()){
            return 0;
        }
        String findId = musicId.get(0);

        // if found id is null, return 0
        if(findId == null){
            System.out.println("The music " + musicOperation.idToName(id) + " is not in the playlist " + playlistName);
            return 0 ;
        }
        int songListID = Integer.parseInt(findId);

        System.out.println(
                "the music id is : " + id + " ; " +
                        " the playlist name is : " + playlistName + " ; " +
                        "so the songlist id is : " + songListID);
        return songListID;
    }

    /**
     * There is the method about add music into the songlist which belong to one of the playlist.
     * @param id the id of music
     * @param playListID the id of playlist
     */
    public void addMusic(int id,int playListID){
        String maxSonglistIDQuery = String.format("select max(fldSonglistID) from table_Songlist");
        String maxSonglistID = SQL.selectSQL(maxSonglistIDQuery).get(0);
        int songlistId = Integer.parseInt(maxSonglistID) + 1;
        String query = String.format("insert into table_Songlist values (%s,%s,%s)",songlistId,id,playListID);
        DB.insertSQL(query);

        System.out.println("the songlist : " + songlistId +
                " add the music : " + id +" ; " +
                "into " + playListID );
    }

    /**
     * there is the method about delete music from the songlist which belong to one of the playlist
     * @param songListID the id of songlist
     */
    public void deleteMusic (int songListID){
        String query = String.format("delete from table_Songlist where fldSongListID = %d",songListID);
        DB.deleteSQL(query);

        System.out.println("delete the music : " + songListID);
    }

    /**
     * Assigns media players to the media player ArrayList with a given
     * path ArrayList. For each media player, there will be a new implementation
     * of the event handling of onEndOfMedia. This implementation makes it possible
     * to play the playlist songs in sequence.
     * @param musicPath ArrayList containing paths to the playlist songs
     */
    public void initMediaPlayers(ArrayList<String> musicPath){
        // add media players for each song in this playlist
        for (String path:musicPath) {
            path = new File(path).getAbsolutePath();    // get the absolute path of the music
            path = new File(path).toURI().toString();   // get URI path

            MediaPlayer mp = new MediaPlayer(new Media(path));  // create new media player
            mediaPlayer.add(mp);                                // add the media player
        }

        // implement the event handling of onEndOfMedia for each media player
        for (int i = 0; i < mediaPlayer.size(); i++) {
            final MediaPlayer player = mediaPlayer.get(i); // get the i'th media player
            final MediaPlayer nextPlayer = mediaPlayer.get((i + 1) % mediaPlayer.size());   // get the next media player

            // implementation of the event handling
            player.setOnEndOfMedia(new Runnable() {
                @Override public void run() {
                    currentPlaying = nextPlayer;    // set current playing media player to next media player
                    nextPlayer.play();              // play the next media player
                }
            });
        }

        // assign the current media player to the first, if it exists
        if(!mediaPlayer.isEmpty()){
            currentPlaying = mediaPlayer.get(0);
        }
    }

    /**
     * Gets the current playing media player
     * @return media player
     */
    public MediaPlayer getCurrentPlaying() {
        return this.currentPlaying;
    }
}
