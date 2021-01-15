package Model;

import DataBase.Opration.Music;
import DataBase.Opration.PlayList;
import DataBase.Opration.PlaylisInfoList;
import javafx.scene.control.ListView;

import java.util.ArrayList;


/**
 * @ author Fei Gu
 * @ create 2021-01-14-22.13
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class SelectedOpration {

    public Music selectedMusic(ListView<String> listView){
        String selectedMusicName = listView.getSelectionModel().getSelectedItem();
        if(selectedMusicName == null){
            System.out.println("There is not music be selected...");
            return new Music(1);
        }
        return new Music(selectedMusicName);
    }

    public PlayList selectedPlaylist(ListView<String> listView){
        String selectedPlaylistName = listView.getSelectionModel().getSelectedItem();
        if(selectedPlaylistName == null){
            System.out.println("There is not playlist be selected...");
            return new PlayList(1);
        }

        PlayList selectedPlaylist = new PlayList(selectedPlaylistName);
        int playlistID = selectedPlaylist.nameToId(selectedPlaylistName);

        selectedPlaylist.setPlayListID(playlistID);
        ArrayList<Integer> musicIDs = new PlaylisInfoList().playListIdToSongId(playlistID);

        for(Integer i : musicIDs){
            System.out.println(i);
        }

        return selectedPlaylist;
    }
}
