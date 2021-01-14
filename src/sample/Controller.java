package sample;

import DataBase.DBSetter.SQL;
import Model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import DataBase.Opration.*;

import java.util.ArrayList;


/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */


public class Controller{

    @FXML
    private Button  btnPlay,btnPause,btnStop,btnNewPlaylist,
                    btnDeletePlaylist,btnAddToPlaylist,btnDeleteFromPlaylist,btnNextSong;
    @FXML
    private ComboBox<String> comboBoxSearchCriteria;

    @FXML
    private ListView<String> showMusic, showInfo, showPlaylist;
    @FXML
    private TextField txtfldSelected,txtfldSearch;

    private Music selectedMusic;
    private PlayList selectedPlaylist;

    public MusicOpration musicOpration = new MusicOpration();
    public PlaylistOpration playlistOpration = new PlaylistOpration();
    public PlaylistInfoOpration playlistInfoOpration = new PlaylistInfoOpration();




    public void initialize(){
        comboBoxSearchCriteria.getItems().addAll("Title","Artist");
        initListviews();
        initPlaylistview();
        //handleListViewMusic();  //????????

        showPlaylist.setEditable(true);
        showPlaylist.setCellFactory(TextFieldListCell.forListView());
    }

    /**
     * Initializes the Song and Playlist Listview
     */
    private void initListviews() {
        System.out.println("loading the listview...");

        System.out.println("add all the music into the Song list view...");
        insertIntoListview("select fldMusicName from table_music", showMusic);

    }
    private void initPlaylistview(){
        System.out.println("add all the playlist into the Playlist view...");
        insertIntoListview("select fldPlaylistName from table_Playlist", showPlaylist);
    }

    /**
     * Queries the database with a select statement and inserts the output
     * into the specified listview
     * @param query select query
     * @param listview listview to insert into
     */
    private void insertIntoListview(String query,ListView<String> listview){
        ArrayList<String> queryData = SQL.selectSQL(query);

        for (String data : queryData) {
            listview.getItems().add(data);
        }
    }

    public void selectMusic(ListView<String> listView){
        selectedMusic = new SelectedOpration().selectedMusic(listView);
        txtfldSelected.setText("Selected: " + selectedMusic.getMusicName());    // update the "selected: " text field
        new LodingMediaPlay().lodingMediaPlay(selectedMusic);

        /*

        Media media;                // the media for the selected song
        MediaPlayer mediaPlayer;    // the media player for the selected song


        // sql query setup
        String query = String.format(
                "select * from table_music where fldMusicName = '%s'",    // the query statement, get path from DB
                selectedSong.replace("'","''")  // replace apostrophes with double apostrophes to avoid errors in sql
        );

        ArrayList<String> musicData = SQL.selectSQL(query); // query the DB for the music data


        // initialize the selected music object
        this.selectedMusic = new Music(
                Integer.parseInt(musicData.get(0)), // music id
                musicData.get(1),                   // music name
                musicData.get(2),                   // music artist
                musicData.get(5)                    // music path
        );


        String path = new File(this.selectedMusic.getPath()).getAbsolutePath();    // get the absolute path of the music
        media = new Media(new File(path).toURI().toString());   // initialize the media with the path
        mediaPlayer = new MediaPlayer(media);                   // attach the media to a media player

        this.selectedMusic.setMedia(media);              // set the media of the music to the media containing the song
        this.selectedMusic.setMediaPlayer(mediaPlayer);  // set the media player of the music, with the media player containing the media
         */
    }

    /* After this is the handle about play functional. */
    /**
     * Plays the selected music
     */
    public void handlePlay(){
        musicOpration.play(selectedMusic);
    }

    /**
     * Pauses the playing music
     */
    public void handlePause(){
        musicOpration.pause();
    }

    /**
     * Stop the playling music
     */
    public void handleStop() {
        musicOpration.stop();
    }

    /**
     * Play the next music following the selected music.
     */
    public void handleNextSong(){
        if(selectedMusic == null) {
            System.out.println(" there is no music be selected !");
            return;
        }
        if((selectedMusic.getId() + 1) > selectedMusic.getMaxSongID()){
            selectedMusic.setId(0);
        }
        musicOpration.next(selectedMusic);
        selectedMusic.setId(selectedMusic.getId() + 1);
    }


    /* After this is the handle about playlist Operations */

    /**
     * Add the new playlist
     */
    public void handleNewPlayList(){
        playlistOpration.newPlayList();
        showPlaylist.getItems().clear();
        initPlaylistview();
    }

    public void handleDeletePlaylist(){

        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();
        int index = showPlaylist.getSelectionModel().getSelectedIndex();

        playlistOpration.deletePlaylist(selectedPlaylist);
        showInfo.getItems().clear();
        showPlaylist.getItems().remove(index);
    }

    public void handlePlaylistNameEdit(Event e){
        ListView.EditEvent<String> editEvent = (ListView.EditEvent) e;
        int itemIndex = editEvent.getIndex();

        String newName = editEvent.getNewValue();
        String oldName = showPlaylist.getItems().get(itemIndex);

        boolean successNameChange = playlistOpration.editPlayListName(oldName,newName);

        if(successNameChange){
            System.out.println(" The playlist name has been changed... from " +
                    oldName + " to " + newName );
            showPlaylist.getItems().set(itemIndex,newName);
        }
    }

    /*  After this is the playlist info opration  */

    public void handleAddToPlaylist(){
        //String selectedMusic = showMusic.getSelectionModel().getSelectedItem();
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();

        playlistInfoOpration.addToPlaylist(selectedMusic,selectedPlaylist);
        showInfo.getItems().clear();
        handleListViewPlaylist();
    }

    public void handleDeleteFromPlaylist(){
        //String selectedMusic = showInfo.getSelectionModel().getSelectedItem();
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();

        playlistInfoOpration.deleteFromPlaylist(selectedMusic,selectedPlaylist);
        showInfo.getItems().clear();
        handleListViewPlaylist();
    }

    /**
     * Handles user selection in the song ListView
     */
    public void handleListViewMusic(){
        selectMusic(showMusic);
    }

    public void handleListViewInfo(){
        selectMusic(showInfo);
    }

    public void handleListViewPlaylist(){
        selectedPlaylist = new SelectedOpration().selectedPlaylist(showPlaylist);

        /*
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();
        if(selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }
         */

        ArrayList<String> getitems = handleSongListView(selectedPlaylist.getPlayListName());
        showInfo.getItems().clear();

        for(String a : getitems){
            showInfo.getItems().add(a);
        }

    }


    /* After this is the search function */

    /**
     * Handles the search text field. The type of search is determined by
     * a combobox showing a specific criteria.
     * @param e the event passed to the search
     */
    public void handleSearch(Event e){
        String selectedCriteria = comboBoxSearchCriteria.getSelectionModel().getSelectedItem(); // get the criteria from the combo box

        if(selectedCriteria == null || selectedCriteria.equals("Title")){   // if selected criteria is null or is "Title"
            startSearch(e,"fldMusicName");  // search the database using the title as criteria
        }
        else if(selectedCriteria.equals("Artist")){ // if selected criteria is "Artist"
            startSearch(e,"fldArtist"); // search the database using the artist as criteria
        }
    }

    /**
     * Handles the event from the search textfield and queries the database
     * with a specified criteria. Updates the listviewSong with the songs
     * matching the search criteria.
     * @param e the event passed to the search
     * @param criteria criteria for the search
     */
    public void startSearch(Event e, String criteria){
        KeyEvent kEvent = (KeyEvent) e; // cast the event into a KeyEvent object reference
        ArrayList<String> result;       // arraylist containing the result of songs from the search

        if(kEvent.getCode() == KeyCode.ENTER){              // if the user pressed enter
            String searchString = txtfldSearch.getText();   // get the user search string from the search textfield

            // query setup
            String query = String.format(
                    "select fldMusicName from table_music where %s like '%%%s%%'",  // select the music name, that matches the criteria and search string
                    criteria,searchString.replace("'","''")   // criteria and user search string passed into the format string
            );
            result = SQL.selectSQL(query);  // get the output from the database

            showMusic.getItems().clear();    // clear the song listview, as we new records will be added

            // for each matched song
            for (String s: result) {
                showMusic.getItems().add(s); // add the song to the song listview
            }
        }
    }

    /*
    * 还没弄...
     */



    public ArrayList<String> handleSongListView(String playListName){
        PlayList pl = new PlayList();
        int id = pl.nameToId(playListName);
        //System.out.println(id);

        PlaylisInfoList sl = new PlaylisInfoList();
        Music music = new Music();

        ArrayList<Integer> al = sl.playListIdToSongId(id);
        ArrayList<String> musicName = new ArrayList<>();

        for (Integer i: al) {
             musicName.add(music.idToName(i));
        }
        return musicName;
    }



}