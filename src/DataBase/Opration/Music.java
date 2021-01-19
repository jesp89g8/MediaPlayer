package DataBase.Opration;

import DataBase.DBSetter.SQL;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This is the music class, which holds the database properties, and
 * provides functionality to creating and initializing a music object.
 * It also implements the Playable interface, since this class should have access
 * to a play(), stop(), pause() and next() function
 * @author Jesper Raheela Zia and Fei
 */
public class Music implements Playable{
    private int id;
    private String name;
    private String artist;
    private String path;
    private Media media;
    private MediaPlayer mediaPlayer;
    private ListView<String> sourceListView;

    /**
     * constructs a music object with no initialized properties, mostly
     * used for getting access to music functionalities thoughout the
     * program
     */
    public Music() {
    }

    /**
     * constructs a music object with a given id. Uses this id to get the name
     * and path from the databse.
     * @param id music id
     */
    public Music(int id) {
        this.id = id;
        this.name = idToName(this.id);
        this.path = idToPath(this.id);
    }

    /**
     * Constructs a music object with a name. Uses the name to determine the id
     * and path.
     * @param name music name
     */
    public Music(String name) {
        this.name = name;
        this.id = nameToId(this.name);
        this.path = idToPath(this.id);
    }

    /**
     * Constructs a music object with an id, name, artist and path
     * @param id music id
     * @param name music name
     * @param artist music artist
     * @param path music path
     */
    public Music(int id, String name, String artist, String path){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.path = path;
    }


    /**
     * Gets the id of the music
     * @return music id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the music
     * @param id music id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the music name of the music object
     * @return music name
     */
    public String getMusicName() {
        return this.name;
    }

    /**
     * Sets the music name of the object
     * @param musicName music name
     */
    public void setMusicName(String musicName) {
        this.name = musicName;
    }

    /**
     * Gets the artist of this music object
     * @return artist name
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist name of this object
     * @param artist artist name
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Sets the path of this object
     * @param path path of music
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the path of this music object
     * @return music path
     */
    public String getPath(){
        return this.path;
    }

    /**
     * Gets the media player associated with this music object
     * @return music media player
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Sets the media player of this music object
     * @param mediaPlayer media player to be set
     */
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * Gets the source media for the media player for this music object
     * @return source media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * Sets the music media
     * @param media media of music
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * trans the music id to music name
     * @param id music id int
     * @return the music name String
     */
    public String idToName(int id){
        //SQL setup
        String query = String.format("select fldMusicName from table_music where fldMusicID = '%d'",id);
        setMusicName(SQL.selectSQL(query).get(0));  // set music name
        return getMusicName();
    }

    /**
     * trans the music name to music id
     * @param musicName the name of music
     * @return the id of music
     */
    public int nameToId(String musicName) {
        // query setup to fetch id from database
        String query = String.format(
                "select fldMusicID from table_music where fldMusicName = '%s'", // SQL select
                musicName.replace("'","''")                     // Sanitize the string apostrophe is accepted
        );

        String musicID = SQL.selectSQL(query).get(0);   // the returned music id
        setId(Integer.parseInt(musicID));               // sets the id of the object
        return getId();
    }


    /**
     * trans the music id to music path
     * @param id the id of the music
     * @return the path of the music
     */
    public String idToPath(int id){
        // SQL setup
        String query = String.format("select fldPath from table_music where fldMusicID = '%d'",id); //SQL select
        setPath(SQL.selectSQL(query).get(0));   // get the path from database and set it to the path of the music
        return getPath();
    }

    /**
     * Gets the greatest song id from the music table in the database
     * @return the greatest song id
     */
    public int getMaxSongID() {
        String maxMusicIDQuery = "select max(fldMusicID) from table_music"; // SQL query
        String maxMusicID = SQL.selectSQL(maxMusicIDQuery).get(0);          // get the max id from database
        return Integer.parseInt(maxMusicID);
    }

    /**
     * toString which returns the properties of this object
     * @return string containing info about the object
     */
    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", path='" + path + '\'' +
                ", media=" + media +
                ", mediaPlayer=" + mediaPlayer +
                '}';
    }

    /**
     * Sets the source listview, which the music object is contained
     * @param listView source listview
     */
    public void setSourceListView(ListView<String> listView) {
        this.sourceListView = listView;
    }

    /**
     * Gets the source list view of this music object
     * @return reference to the source listview
     */
    public ListView<String> getSourceListView() {
        return sourceListView;
    }
}
