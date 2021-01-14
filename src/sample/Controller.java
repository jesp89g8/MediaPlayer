package sample;

import DataBase.SQL;
import Model.PlayFunction;
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
    private ListView<String> listviewSong, listviewInfo, listviewPlaylist;
    @FXML
    private TextField txtfldSelected,txtfldSearch;

    private Music selectedMusic;

    private PlayFunction musicOperation = new PlayFunction();

    public void initialize(){
        comboBoxSearchCriteria.getItems().addAll("Title","Artist");
        initListviews();
        initPlaylistview();
        handleListViewSong();

        listviewPlaylist.setEditable(true);
        listviewPlaylist.setCellFactory(TextFieldListCell.forListView());
    }

    /**
     * Initializes the Song and Playlist Listview
     */
    private void initListviews() {
        System.out.println("loading the listview...");

        System.out.println("add all the music into the Song list view...");
        insertIntoListview("select fldMusicName from table_music",listviewSong);

        /*
        System.out.println("add all the playlist into the Playlist view...");
        insertIntoListview("select fldPlaylistName from table_Playlist",listviewPlaylist);

         */

    }
    private void initPlaylistview(){
        System.out.println("add all the playlist into the Playlist view...");
        insertIntoListview("select fldPlaylistName from table_Playlist",listviewPlaylist);
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

    /**
     * Plays the selected music
     */
    public void handlePlay(){
        musicOperation.play(selectedMusic);
    }

    /**
     * Pauses the playing music
     */
    public void handlePause(){
        musicOperation.pause();
        
    }

    public void handleStop() {
        musicOperation.stop();
    }

    public void handleNewPlayList(){
        ArrayList<String> s = SQL.selectSQL("select fldPlaylistName from table_Playlist where fldPlaylistName = 'New Playlist'");
        PlayList newPlaylist = new PlayList();

        int playListMaxID = newPlaylist.getMaxPlaylistID();
        newPlaylist.addPlaylist("New Playlist " + (playListMaxID + 1));

        listviewPlaylist.getItems().clear();
        initPlaylistview();
    }

    public void handleListViewPlaylistEdit(Event e){
        ListView.EditEvent<String> editEvent = (ListView.EditEvent) e;
        int itemIndex = editEvent.getIndex();

        String newName = editEvent.getNewValue();
        String oldName = listviewPlaylist.getItems().get(itemIndex);

        PlayList playList = new PlayList();
        boolean successNameChange = playList.editPlayListName(oldName,newName);

        if(successNameChange){
            listviewPlaylist.getItems().set(itemIndex,newName);
        }
    }

    public void handleDeletePlaylist(){
        PlayList playList = new PlayList();

        String selectedPlaylist = listviewPlaylist.getSelectionModel().getSelectedItem();
        int index = listviewPlaylist.getSelectionModel().getSelectedIndex();
        listviewPlaylist.getItems().remove(index);

        playList.deletePlayList(selectedPlaylist);
    }

    public void handleAddToPlaylist(){
        String selectedSong = listviewSong.getSelectionModel().getSelectedItem();
        String selectedPlayList = listviewPlaylist.getSelectionModel().getSelectedItem();

        Music music = new Music();
        PlayList playList = new PlayList();
        SongList songList = new SongList();

        int musicID = music.nameToId(selectedSong);
        int playListID = playList.nameToId(selectedPlayList);

        songList.addMusic(musicID,playListID);
        listviewInfo.getItems().clear();
        handleListViewPlaylist();
    }

    public void handleDeleteFromPlaylist(){
        String selectedSong = listviewInfo.getSelectionModel().getSelectedItem();
        String selectedPlayList = listviewPlaylist.getSelectionModel().getSelectedItem();

        Music music = new Music();
        PlayList playList = new PlayList();
        SongList songList = new SongList();

        int musicID = music.nameToId(selectedSong);
        int songlistID = songList.findID(musicID,selectedPlayList);

        songList.deleteMusic(songlistID);
        listviewInfo.getItems().clear();
        handleListViewPlaylist();
    }

    public void handleNextSong(){
        if((selectedMusic.getId() + 1) > selectedMusic.getMaxSongID()){
            selectedMusic.setId(0);
        }

        musicOperation.next(selectedMusic);
        selectedMusic.setId(selectedMusic.getId() + 1);
    }



    /**
     * Handles user selection in the song ListView
     */
    public void handleListViewSong(){
        selectMusic(listviewSong);
    }

    public void handleListViewInfo(){
        selectMusic(listviewInfo);
    }

    public void selectMusic(ListView<String> lv){
        Media media;                // the media for the selected song
        MediaPlayer mediaPlayer;    // the media player for the selected song

        String selectedSong = lv.getSelectionModel().getSelectedItem();   // get the name of the selected song
        if(selectedSong == null) return;                        // if selected song name is null, return
        txtfldSelected.setText("Selected: " + selectedSong);    // update the "selected: " text field

        // sql query setup
        String query = String.format(
                "select * from table_music where fldMusicName = '%s'",    // the query statement, get path from DB
                selectedSong.replace("'","''")  // replace apostrophes with double apostrophes to avoid errors in sql
        );

        ArrayList<String> musicData = SQL.selectSQL(query); // query the DB for the music data


        // initialize the selected music object
        selectedMusic = new Music(
                Integer.parseInt(musicData.get(0)), // music id
                musicData.get(1),                   // music name
                musicData.get(2),                   // music artist
                musicData.get(5)                    // music path
        );

        String path = new File(selectedMusic.getPath()).getAbsolutePath();    // get the absolute path of the music
        media = new Media(new File(path).toURI().toString());   // initialize the media with the path
        mediaPlayer = new MediaPlayer(media);                   // attach the media to a media player

        selectedMusic.setMedia(media);              // set the media of the music to the media containing the song
        selectedMusic.setMediaPlayer(mediaPlayer);  // set the media player of the music, with the media player containing the media
    }

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
                    criteria,searchString   // criteria and user search string passed into the format string
            );
            result = SQL.selectSQL(query);  // get the output from the database

            listviewSong.getItems().clear();    // clear the song listview, as we new records will be added

            // for each matched song
            for (String s: result) {
                listviewSong.getItems().add(s); // add the song to the song listview
            }
        }
    }

    public void handleListViewPlaylist(){
        String selectedPlaylist = listviewPlaylist.getSelectionModel().getSelectedItem();
        if(selectedPlaylist == null) return;

        ArrayList<String> getitems = handleSongListView(selectedPlaylist);
        listviewInfo.getItems().clear();

        for(String a : getitems){
            listviewInfo.getItems().add(a);
        }
    }


    /**
     * this is the "try to make" by fei and we can just delect it :P
     * @param playListName
     * @return
     */
    public ArrayList<String> handleSongListView(String playListName){
        PlayList pl = new PlayList();
        int id = pl.nameToId(playListName);
        //System.out.println(id);

        SongList sl = new SongList();
        Music music = new Music();

        ArrayList<Integer> al = sl.playListIdToSongId(id);
        ArrayList<String> musicName = new ArrayList<>();

        for (Integer i: al) {
             musicName.add(music.idToName(i));
        }
        return musicName;
    }

}