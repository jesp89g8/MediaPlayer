package sample;

import DataBase.DBSetter.SQL;
import Model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import DataBase.Opration.*;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;


/**
 * The controller of the program, which handles the different events
 * that will happen, when the user interacts with the GUI.
 * @author Jesper Raheela Zia and Fei
 */
public class Controller{
    @FXML
    private ComboBox<String> comboBoxSearchCriteria;

    @FXML
    private ListView<String> showMusic, showInfo, showPlaylist;
    @FXML
    private TextField txtfldSelected,txtfldSearch,txtfldPlaying;

    public InitListView initListView = new InitListView();  // used for initializing the listviews

    private Music selectedMusic;            // selected music object
    private PlayList selectedPlaylist;      // selected playlist object
    private Playable selectedPlayableItem;  // selected playable object

    /*
    used for performing basic media player actions for a music object, like play,
    stop, pause
     */
    public MusicOperation musicOperation = new MusicOperation();

    /*
    used for performing basic media player actions for a playlist object, like play,
    stop, pause
     */
    public PlaylistOperation playlistOperation = new PlaylistOperation();

    /*
    used for adding or deleting playlists to the database
     */
    public PlaylistInfoOperation playlistInfoOperation = new PlaylistInfoOperation();


    /**
     * This is the start of when the application implemented what should be loaded.
     */
    public void initialize(){
        /*
         * Load and show the two list on the scene.
         */
        initListView.initListviews(showMusic); // show the music list
        initListView.initPlaylistview(showPlaylist);  // show the play list

        /*
         * load the search area and function
         */
        comboBoxSearchCriteria.getItems().addAll("Title","Artist");

        /*
         * Load the playlist edit function
         */
        showPlaylist.setEditable(true);
        showPlaylist.setCellFactory(TextFieldListCell.forListView());
    }

    /**
     * This will select music when the select operation happened
     * @param listView means which list happen the select operation.
     */
    public void selectMusic(ListView<String> listView){
        // create new selected playable item
        selectedPlayableItem = new SelectedOperation().selectedMusic(listView);

        // if selected playable item is null, return
        if(selectedPlayableItem == null) return;

        // trans the selected item to Music object
        selectedMusic = (Music) selectedPlayableItem;

        // update the "selected: " text field
        txtfldSelected.setText("Selected Music: " + selectedMusic.getMusicName());

        // loading the play function for ready play selected music
        new LoadingMediaPlay().loadingMediaPlay(selectedMusic);
    }

    /**
     * This will select playlist when the select operation happened.
     * @param listView the list of playlist //(showPlaylist)
     */
    public void selectPlaylist(ListView<String> listView){
        // create new selected playable item
        selectedPlayableItem = new SelectedOperation().selectedPlaylist(listView);

        // if playable item is null, return
        if(selectedPlayableItem == null) return;

        // trans the selected item to Music object
        selectedPlaylist = (PlayList) selectedPlayableItem;

        // update the selected playlist text field
        txtfldSelected.setText("Selected Playlist: " + selectedPlaylist.getPlayListName());
    }

    /**
     * Handler for when the user mouse is exiting the playlist
     * listview. This method exists to prevent an annoyance
     * which occurs, when the user clicks an existing playlist
     * and then clicks somewhere else in the gui. At this point
     * if the user selects the same playlist, it will start editing
     * the name, which in most cases is not the intention.
     */
    public void handlePlaylistMouseExited(){
        // get the cell item index if it is being edited
        int editedIndex = showPlaylist.getEditingIndex();

        // if cell is being edited
        if(editedIndex != -1){
            showPlaylist.getSelectionModel().clearSelection();      // clear the selection
            showPlaylist.getSelectionModel().select(editedIndex);   // select it again
        }
        showPlaylist.setEditable(false);    // make the listview not editable
    }

    /* After this is the handle about play functional. */
    /**
     * Plays the selected music
     */
    public void handlePlay(){
        //if the selected item is of type music
        if(selectedPlayableItem instanceof Music){

            // cast the playable item to a music object
            Music m = (Music) selectedPlayableItem;

            // check if selected playlist is not null
            if(selectedPlaylist != null){
                // stop the current playing playlist
                selectedPlaylist.getCurrentPlaying().stop();
            }

            // check if there is some active music and if it is paused
            if( musicOperation.playingMusic != null &&
                musicOperation.playingMusic.getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED
            ){
                // play the paused music and return
                musicOperation.playingMusic.getMediaPlayer().play();
                return;
            }

            // update the text with new music name
            txtfldPlaying.setText("Playing: " + m.getMusicName());

            // start playing the selected music
            musicOperation.play(m);
        }
        //if the selected item is of type PlayList
        else if(selectedPlayableItem instanceof PlayList){
            // cast the playable item to a playlist object
            PlayList pl = (PlayList) selectedPlayableItem;

            // if some music is playing
            if(selectedMusic != null){
                // stop the music
                selectedMusic.getMediaPlayer().stop();
            }
            // if some music is playing
            if(musicOperation.playingMusic != null){
                // stop the music
                musicOperation.playingMusic.getMediaPlayer().stop();
            }

            // update the playing text field
            txtfldPlaying.setText("Playing: " + pl.getPlayListName());

            // play the new playlist
            playlistOperation.play(pl);
        }
    }

    /**
     * Pauses the playing music
     */
    public void handlePause(){
        if(selectedPlayableItem instanceof Music){
            musicOperation.pause();
        }
        else if(selectedPlayableItem instanceof PlayList){
            playlistOperation.pause();
        }
    }

    /**
     * Stop the playing music
     */
    public void handleStop() {

        // if there is an active playing playlist
        if(playlistOperation.getPlayingPlaylist() != null){
            //get the media player of this playlist
            MediaPlayer mp = playlistOperation.getPlayingPlaylist().getCurrentPlaying();

            // check if the media player is playing or paused
            if(mp.getStatus() == MediaPlayer.Status.PLAYING || mp.getStatus() == MediaPlayer.Status.PAUSED){
                // update the playing text field
                txtfldPlaying.setText("Playing: No music");
                // stop the media player
                mp.stop();
                // discard the playing playlist object
                playlistOperation.setPlayingPlaylist(null);
            }
        }

        // if there is an active playing music
        if(musicOperation.playingMusic != null && musicOperation.playingMusic.getMediaPlayer() != null){
            // get the media player of this music
            MediaPlayer mp = musicOperation.playingMusic.getMediaPlayer();

            // check if the media player is playing or paused
            if(mp.getStatus() == MediaPlayer.Status.PLAYING || mp.getStatus() == MediaPlayer.Status.PAUSED){
                // update the playing text field
                txtfldPlaying.setText("Playing: No music");
                // stop the media player
                mp.stop();
                // discard the playing music object
                musicOperation.playingMusic = null;
            }
        }
    }

    /**
     * Play the next music following the selected music.
     */
    public void handleNextSong(){
        // if selected playable item is of type Music
        if(selectedPlayableItem instanceof Music){
            // if playing music is null, return
            if(musicOperation.playingMusic == null) return;
            // if selected music is null, return
            if(selectedMusic == null) {
                System.out.println(" there is no music be selected !");
                return;
            }
            // play the next music
            musicOperation.next();
            //update text field
            txtfldPlaying.setText("Playing: " + musicOperation.playingMusic.getMusicName());
        }
        // if selected playable item is of type PlayList
        else if(selectedPlayableItem instanceof PlayList){
            // play next music in playlist
            playlistOperation.next();
        }
    }


    /* After this is the handle about playlist Operations */

    /**
     * Add the new playlist
     */
    public void handleNewPlayList(){
        playlistOperation.newPlayList();                // create a new playlist in the listview
        showPlaylist.getItems().clear();                // clear the listview
        initListView.initPlaylistview(showPlaylist);    // update the listview
    }

    /**
     * Delete a playlist
     */
    public void handleDeletePlaylist(){
        // get the selected playlist name
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();
        // if name is null, return
        if(selectedPlaylist == null) return;

        // get the index of selected playlist in the listview
        int index = showPlaylist.getSelectionModel().getSelectedIndex();

        // delete the selected playlist
        playlistOperation.deletePlaylist(selectedPlaylist);

        // clear the listview
        showInfo.getItems().clear();

        // update the listview
        showPlaylist.getItems().remove(index);
    }

    /**
     * Handles the edit event, when editing the name of a playlist.
     * @param e event passed to this event handler
     */
    public void handlePlaylistNameEdit(Event e){
        // cast the event to an editevent
        ListView.EditEvent<String> editEvent = (ListView.EditEvent) e;

        // get the index of the item in the listview
        int itemIndex = editEvent.getIndex();

        // get the new name of the edit
        String newName = editEvent.getNewValue();

        // get the old name
        String oldName = showPlaylist.getItems().get(itemIndex);

        // try to changes the old name with the new one in the database
        boolean successNameChange = playlistOperation.editPlayListName(oldName,newName);

        // if success, then change the name in the listview
        if(successNameChange){
            System.out.println(" The playlist name has been changed... from " +
                    oldName + " to " + newName );
            showPlaylist.getItems().set(itemIndex,newName);
        }
    }

    /*  After this is the playlist info operation  */

    /**
     * Adds a song to the playlist.
     */
    public void handleAddToPlaylist(){
        // get the selected playlist
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();

        // if selected playlist is null, return
        if(selectedPlaylist == null) return;

        playlistInfoOperation.addToPlaylist(selectedMusic,selectedPlaylist);    // add the selected song to playlist
        showInfo.getItems().clear();                                            // clear the listview
        handleListViewPlaylist();                                               // update the list view
    }

    /**
     * Delete a song from a playlist
     */
    public void handleDeleteFromPlaylist(){
        // get selected playlist
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();
        // return if null
        if(selectedPlaylist == null) return;

        playlistInfoOperation.deleteFromPlaylist(selectedMusic,selectedPlaylist);   // delete the song from playlist
        showInfo.getItems().clear();                                                // clear listview
        handleListViewPlaylist();                                                   // update the listview
    }

    /**
     * Handles user selection in the song ListView
     */
    public void handleListViewMusic(){
        selectMusic(showMusic);
    }

    /**
     * Handles user selection in the info ListView
     */
    public void handleListViewInfo(){
        selectMusic(showInfo);
    }

    /**
     * Handles user selection the playlist ListView
     */
    public void handleListViewPlaylist(){
        selectPlaylist(showPlaylist);

        // return, if selected playlist is null
        if(selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }

        // get the song names for the selected play list
        ArrayList<String> getitems = handleSongListInfo(selectedPlaylist.getPlayListName());
        showInfo.getItems().clear();

        // insert each song name associated with the selected playlist
        for(String s : getitems){
            showInfo.getItems().add(s);
        }
        // set editable to true, in case the user want to edit a playlist name
        showPlaylist.setEditable(true);
    }


    /* After this is the search function */

    /**
     * Handles the search text field. The type of search is determined by
     * a combobox showing a specific criteria.
     * @param e the event passed to the search
     */
    public void handleSearch(Event e){
        // get the criteria from the combo box
        String selectedCriteria = comboBoxSearchCriteria.getSelectionModel().getSelectedItem();

        // if selected criteria is null or is "Title"
        if(selectedCriteria == null || selectedCriteria.equals("Title")){
            startSearch(e,"fldMusicName");  // search the database using the title as criteria
        }
        // if selected criteria is "Artist"
        else if(selectedCriteria.equals("Artist")){
            startSearch(e,"fldArtist"); // search the database using the artist as criteria
        }
    }

    /**
     * Handles the event from the search text field and queries the database
     * with a specified criteria. Updates the listviewSong with the songs
     * matching the search criteria.
     * @param e the event passed to the search
     * @param criteria criteria for the search
     */
    public void startSearch(Event e, String criteria){
        KeyEvent kEvent = (KeyEvent) e; // cast the event into a KeyEvent object reference
        ArrayList<String> result;       // arraylist containing the result of songs from the search

        // if the user pressed enter
        if(kEvent.getCode() == KeyCode.ENTER){
            String searchString = txtfldSearch.getText();   // get the user search string from the search text field


            // query setup

            String query = String.format(
                    // select the music name, that matches the criteria and search string
                    "select fldMusicName from table_music where %s like '%%%s%%'",
                    // criteria and user search string passed into the format string
                    criteria,searchString.replace("'","''")
            );
            result = SQL.selectSQL(query);  // get the output from the database

            showMusic.getItems().clear();    // clear the song listview, as we new records will be added

            // for each matched song
            for (String s: result) {
                showMusic.getItems().add(s); // add the song to the song listview
            }
        }
    }

    /**
     * Creates a music name ArrayList, containing the names of the music
     * in a specified playlist.
     * @param playListName name of the playlist
     * @return ArrayList of music names
     */
    public ArrayList<String> handleSongListInfo(String playListName){
        PlayList pl = new PlayList();           // create new playlist to gain access to useful methods
        int id = pl.nameToId(playListName);     // get the id of the playlist

        PlaylistInfoList sl = new PlaylistInfoList();   // create new playlistinfolist for useful methods
        Music music = new Music();                      // create music object for useful methods

        ArrayList<Integer> al = sl.playListIdToSongId(id);  // arraylist containing music ids of a playlist
        ArrayList<String> musicName = new ArrayList<>();    // create new music name ArrayList

        for (Integer i: al) {
             musicName.add(music.idToName(i));  // add music names from a playlist to the music name ArrayList
        }
        return musicName;
    }
}