package Model;

import DataBase.Opration.Music;
import DataBase.Opration.PlayList;
import DataBase.Opration.PlaylistInfoList;
import javafx.scene.control.ListView;

import java.util.ArrayList;


/**
 * Class which handles the selected item from the GUI
 * @author Fei Gu
 */
public class SelectedOperation {

    /**
     * Creates a new music object.
     * @param listView source listview which the selected item is contained
     * @return the selected object
     */
    public Music selectedMusic(ListView<String> listView){

        String selectedMusicName = listView.getSelectionModel().getSelectedItem();  // get the selected item name

        // return if the selected item is null
        if(selectedMusicName == null){
            System.out.println("There is not music be selected...");
            return null;
        }

        Music selectedMusic = new Music(selectedMusicName); // create new music object with the selected name
        selectedMusic.setSourceListView(listView);          // set the source listview in music object
        return selectedMusic;
    }

    /**
     * Creates a new playlist object.
     * @param listView the listview the playlist is contained in
     * @return a new playlist object
     */
    public PlayList selectedPlaylist(ListView<String> listView){

        // get the selected playlist name
        String selectedPlaylistName = listView.getSelectionModel().getSelectedItem();

        // if selected playlist is null, then return
        if(selectedPlaylistName == null){
            System.out.println("There is not playlist be selected...");
            return null;
        }

        // create new playlist with the selected name
        PlayList selectedPlaylist = new PlayList(selectedPlaylistName);

        // get the id for the playlist by querying the database using the name
        int playlistID = selectedPlaylist.nameToId(selectedPlaylistName);

        // set the playlist id
        selectedPlaylist.setPlayListID(playlistID);

        /*
        get the music ids associated with this playlist, by querying the database
        using the playlist id.
         */
        ArrayList<Integer> musicIDs = new PlaylistInfoList().playListIdToSongId(playlistID);

        // set the music ids for the playlist
        selectedPlaylist.setMusicID(musicIDs);

        /*
        get the playlist music paths, using the music ids as condition for the
        database query
         */
        ArrayList<String> musicPaths = getPlayListPaths(musicIDs);

        // initialize the playlist media players using the music paths
        selectedPlaylist.initMediaPlayers(musicPaths);

        return selectedPlaylist;
    }

    /**
     * Method for getting the music paths of a playlist
     * @param musicIds an ArrayList containing music ids
     * @return ArrayList of string paths
     */
    public ArrayList<String> getPlayListPaths(ArrayList<Integer> musicIds){
        ArrayList<String> path = new ArrayList<>();

        for (Integer id: musicIds) {
            path.add(new Music().idToPath(id));
        }

        return path;
    }
}
