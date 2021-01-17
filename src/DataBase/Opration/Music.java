package DataBase.Opration;

import DataBase.DBSetter.SQL;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */
public class Music implements Playable{
    private int id;
    private String name;
    private String artist;
    private String path;
    private Media media;
    private MediaPlayer mediaPlayer;
    private ListView<String> sourceListView;


    public Music() {
    }

    public Music(int id) {
        this.id = id;
        this.name = idToName(this.id);
        this.path = idToPath(this.id);
    }

    public Music(String name) {
        this.name = name;
        this.id = nameToId(this.name);
        this.path = idToPath(this.id);
    }

    public Music(int id, String name, String artist, String path){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.path = path;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicName() {
        return this.name;
    }

    public void setMusicName(String musicName) {
        this.name = musicName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }




    /**
     * trans the music id to music name
     * and i think we can talk to use these function to instead some part make the code clearly.
     * @param id music id int
     * @return the music name String
     */
    public String idToName(int id){
        String query = String.format("select fldMusicName from table_music where fldMusicID = '%d'",id);
        setMusicName(SQL.selectSQL(query).get(0));
        return getMusicName();
    }

    /**
     * trans the music name to music id
     * @param musicName the name of music
     * @return the id of music
     */
    public int nameToId(String musicName) {
        String query = String.format("select fldMusicID from table_music where fldMusicName = '%s'",musicName.replace("'","''"));
        String musicID = SQL.selectSQL(query).get(0);
        setId(Integer.parseInt(musicID));
        return getId();
    }


    /**
     * trans the music id to music path
     * @param id the id of the music
     * @return the path of the music
     */
    public String idToPath(int id){
        String query = String.format("select fldPath from table_music where fldMusicID = '%d'",id);
        setPath(SQL.selectSQL(query).get(0));
        return getPath();
    }


    public int getMaxSongID() {
        String maxMusicIDQuery = "select max(fldMusicID) from table_music";
        String maxMusicID = SQL.selectSQL(maxMusicIDQuery).get(0);
        return Integer.parseInt(maxMusicID);
    }


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

    public void setSourceListView(ListView<String> listView) {
        this.sourceListView = listView;
    }

    public ListView<String> getSourceListView() {
        return sourceListView;
    }
}
