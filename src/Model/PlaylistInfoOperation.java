package Model;

import DataBase.Opration.Music;
import DataBase.Opration.PlayList;
import DataBase.Opration.PlaylistInfoList;


/**
 * @ author Fei Gu
 * @ create 2021-01-14-13.54
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description
 * @ Version
 */
public class PlaylistInfoOperation extends PlaylistInfoList {

    public void addToPlaylist(Music  selectedMusic, String selectedPlaylist){
        if(selectedMusic == null || selectedPlaylist == null) {
            System.out.println(" There is no music or playlist be selected!! ");
            return;
        }

        addMusic(selectedMusic.getId(), new PlayList().nameToId(selectedPlaylist));
    }

    public void deleteFromPlaylist(Music selectedMusic , String selectedPlaylist){
        if(selectedMusic == null || selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }

        deleteMusic(findID(selectedMusic.getId(),selectedPlaylist));
    }

}
