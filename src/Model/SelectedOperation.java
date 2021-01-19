package Model;

import DataBase.Opration.Music;
import DataBase.Opration.PlayList;
import DataBase.Opration.PlaylistInfoList;
import javafx.scene.control.ListView;

import java.util.ArrayList;


/**
 * @ author Fei Gu
 * @ create 2021-01-14-22.13
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class SelectedOperation {

    public Music selectedMusic(ListView<String> listView){
        String selectedMusicName = listView.getSelectionModel().getSelectedItem();
        if(selectedMusicName == null){
            System.out.println("There is not music be selected...");
            return null;
        }

        Music selectedMusic = new Music(selectedMusicName);
        selectedMusic.setSourceListView(listView);
        return selectedMusic;
    }

    public PlayList selectedPlaylist(ListView<String> listView){
        String selectedPlaylistName = listView.getSelectionModel().getSelectedItem();
        if(selectedPlaylistName == null){
            System.out.println("There is not playlist be selected...");
            return null;
        }

        PlayList selectedPlaylist = new PlayList(selectedPlaylistName);

        int playlistID = selectedPlaylist.nameToId(selectedPlaylistName);
        selectedPlaylist.setPlayListID(playlistID);

        ArrayList<Integer> musicIDs = new PlaylistInfoList().playListIdToSongId(playlistID);
        selectedPlaylist.setMusicID(musicIDs);

        ArrayList<String> musicPaths = getPlayListPaths(musicIDs);
        selectedPlaylist.initMediaPlayers(musicPaths);

        return selectedPlaylist;
    }

    public ArrayList<String> getPlayListPaths(ArrayList<Integer> musicIds){
        ArrayList<String> path = new ArrayList<>();

        for (Integer id: musicIds) {
            path.add(new Music().idToPath(id));
        }

        return path;
    }
}
