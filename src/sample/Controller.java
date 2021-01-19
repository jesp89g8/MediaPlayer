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
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the controller center which is the application connect between method with GUI
 * @ Version 0.8
 *
 */


public class Controller{
    @FXML
    private ComboBox<String> comboBoxSearchCriteria;

    @FXML
    private ListView<String> showMusic, showInfo, showPlaylist;
    @FXML
    private TextField txtfldSelected,txtfldSearch,txtfldPlaying;

    public InitListView initListView = new InitListView();

    private Music selectedMusic;
    private PlayList selectedPlaylist;
    private Playable selectedPlayableItem;


    public MusicOperation musicOperation = new MusicOperation();
    public PlaylistOperation playlistOperation = new PlaylistOperation();
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
        selectedPlayableItem = new SelectedOperation().selectedMusic(listView);
        if(selectedPlayableItem == null) return;

        selectedMusic = (Music) selectedPlayableItem;     //trans the selected item to Music object
        txtfldSelected.setText("Selected Music: " + selectedMusic.getMusicName());    // update the "selected: " text field
        new LoadingMediaPlay().lodingMediaPlay(selectedMusic);       // loading the play function for ready play selected music
    }

    /**
     * This will select playlist when the select operation happened.
     * @param listView the list of playlist //(showPlaylist)
     * @return which playlist been selected from the list of playlist //(showinfo)
     */
    public void selectPlaylist(ListView<String> listView){
        selectedPlayableItem = new SelectedOperation().selectedPlaylist(listView);
        if(selectedPlayableItem == null) return;
        selectedPlaylist = (PlayList) selectedPlayableItem;     //trans the selected item to Music object
        txtfldSelected.setText("Selected Playlist: " + selectedPlaylist.getPlayListName());
    }

    public void handlePlaylistMouseExited(){
        int editedIndex = showPlaylist.getEditingIndex();
        if(editedIndex != -1){
            showPlaylist.getSelectionModel().clearSelection();
            showPlaylist.getSelectionModel().select(editedIndex);
        }
        showPlaylist.setEditable(false);
    }

    /* After this is the handle about play functional. */
    /**
     * Plays the selected music
     */
    public void handlePlay(){
        if(selectedPlayableItem instanceof Music){
            Music m = (Music) selectedPlayableItem;
            if(selectedPlaylist != null){
                selectedPlaylist.getCurrentPlaying().stop();
            }
            if( musicOperation.playingMusic != null &&
                musicOperation.playingMusic.getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED
            ){
                musicOperation.playingMusic.getMediaPlayer().play();
                return;
            }

            txtfldPlaying.setText("Playing: " + m.getMusicName());
            musicOperation.play(m);
        }
        else if(selectedPlayableItem instanceof PlayList){
            PlayList pl = (PlayList) selectedPlayableItem;
            if(selectedMusic != null){
                selectedMusic.getMediaPlayer().stop();
            }
            if(musicOperation.playingMusic != null){
                musicOperation.playingMusic.getMediaPlayer().stop();
            }

            txtfldPlaying.setText("Playing: " + pl.getPlayListName());
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
        if(playlistOperation.getPlayingPlaylist() != null){
            MediaPlayer mp = playlistOperation.getPlayingPlaylist().getCurrentPlaying();
            if(mp.getStatus() == MediaPlayer.Status.PLAYING || mp.getStatus() == MediaPlayer.Status.PAUSED){
                txtfldPlaying.setText("Playing: No music");
                mp.stop();
                playlistOperation.setPlayingPlaylist(null);
            }
        }

        if(musicOperation.playingMusic != null && musicOperation.playingMusic.getMediaPlayer() != null){
            MediaPlayer mp = musicOperation.playingMusic.getMediaPlayer();
            if(mp.getStatus() == MediaPlayer.Status.PLAYING || mp.getStatus() == MediaPlayer.Status.PAUSED){
                txtfldPlaying.setText("Playing: No music");
                mp.stop();
                musicOperation.playingMusic = null;
            }
        }
    }

    /**
     * Play the next music following the selected music.
     */
    public void handleNextSong(){
        if(selectedPlayableItem instanceof Music){
            if(musicOperation.playingMusic == null) return;
            if(selectedMusic == null) {
                System.out.println(" there is no music be selected !");
                return;
            }
            musicOperation.next();
            txtfldPlaying.setText("Playing: " + musicOperation.playingMusic.getMusicName());
        }
        else if(selectedPlayableItem instanceof PlayList){
            playlistOperation.next();
        }
    }


    /* After this is the handle about playlist Operations */

    /**
     * Add the new playlist
     */
    public void handleNewPlayList(){
        playlistOperation.newPlayList();
        showPlaylist.getItems().clear();
        initListView.initPlaylistview(showPlaylist);
    }

    public void handleDeletePlaylist(){
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();
        if(selectedPlaylist == null) return;


        int index = showPlaylist.getSelectionModel().getSelectedIndex();

        playlistOperation.deletePlaylist(selectedPlaylist);
        showInfo.getItems().clear();
        showPlaylist.getItems().remove(index);
    }

    public void handlePlaylistNameEdit(Event e){
        ListView.EditEvent<String> editEvent = (ListView.EditEvent) e;
        int itemIndex = editEvent.getIndex();

        String newName = editEvent.getNewValue();
        String oldName = showPlaylist.getItems().get(itemIndex);

        boolean successNameChange = playlistOperation.editPlayListName(oldName,newName);

        if(successNameChange){
            System.out.println(" The playlist name has been changed... from " +
                    oldName + " to " + newName );
            showPlaylist.getItems().set(itemIndex,newName);
        }
    }

    /*  After this is the playlist info operation  */

    public void handleAddToPlaylist(){
        //String selectedMusic = showMusic.getSelectionModel().getSelectedItem();
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();
        if(selectedPlaylist == null) return;

        playlistInfoOperation.addToPlaylist(selectedMusic,selectedPlaylist);
        showInfo.getItems().clear();
        handleListViewPlaylist();
    }

    public void handleDeleteFromPlaylist(){
        //String selectedMusic = showInfo.getSelectionModel().getSelectedItem();
        String selectedPlaylist = showPlaylist.getSelectionModel().getSelectedItem();
        if(selectedPlaylist == null) return;

        playlistInfoOperation.deleteFromPlaylist(selectedMusic,selectedPlaylist);
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
        selectPlaylist(showPlaylist);

        if(selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }

        ArrayList<String> getitems = handleSongListInfo(selectedPlaylist.getPlayListName());
        showInfo.getItems().clear();

        for(String a : getitems){
            showInfo.getItems().add(a);
        }
        showPlaylist.setEditable(true);
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
     * Handles the event from the search text field and queries the database
     * with a specified criteria. Updates the listviewSong with the songs
     * matching the search criteria.
     * @param e the event passed to the search
     * @param criteria criteria for the search
     */
    public void startSearch(Event e, String criteria){
        KeyEvent kEvent = (KeyEvent) e; // cast the event into a KeyEvent object reference
        ArrayList<String> result;       // arraylist containing the result of songs from the search

        if(kEvent.getCode() == KeyCode.ENTER){              // if the user pressed enter
            String searchString = txtfldSearch.getText();   // get the user search string from the search text field

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

    public ArrayList<String> handleSongListInfo(String playListName){
        PlayList pl = new PlayList();
        int id = pl.nameToId(playListName);
        //System.out.println(id);

        PlaylistInfoList sl = new PlaylistInfoList();
        Music music = new Music();

        ArrayList<Integer> al = sl.playListIdToSongId(id);
        ArrayList<String> musicName = new ArrayList<>();

        for (Integer i: al) {
             musicName.add(music.idToName(i));
        }
        return musicName;
    }
}