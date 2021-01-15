package Model;

import DataBase.Opration.PlayList;
import DataBase.Opration.PlaylisInfoList;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.util.ArrayList;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the playlist opration class which is oprate the playlist by add, delete, modify
 * @ Version 0.1
 *
 */
public class PlaylistOpration extends PlayList{

    /**
     * Create an empty new playlist
     */
    public void newPlayList(){
        int playListMaxID = getMaxPlaylistID();
        addPlaylist("New Playlist " + (playListMaxID + 1));
    }

    public void deletePlaylist(String selectedPlaylist){
        if(selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }
        deleteAllMusic(selectedPlaylist);
        super.deletePlayList(selectedPlaylist);
    }


    public void deleteAllMusic(String selectedPlaylist){
        ArrayList<Integer> musicID = playListIdToSongId(nameToId(selectedPlaylist));
        for(int ids: musicID){
            int songlistId = new PlaylisInfoList().findID(ids,selectedPlaylist);
            deleteMusic(songlistId);
        }
    }

    public void play(PlayList pl) {

    }
}
