package Model;

import DataBase.Opration.Music;
import DataBase.Opration.PlayList;
import DataBase.Opration.PlaylistInfoList;


/**
 * This class is used for adding or deleting music from an existing playlist.
 * @author Fei Gu
 */
public class PlaylistInfoOperation extends PlaylistInfoList {

    /**
     * Adds a selected song to a selected playlist
     * @param selectedMusic selected music
     * @param selectedPlaylist selected playlist
     */
    public void addToPlaylist(Music  selectedMusic, String selectedPlaylist){
        /*
          If selected music or selected playlist is null, then it is not
          possible to add the selected music the selected playlist
         */
        if(selectedMusic == null || selectedPlaylist == null) {
            System.out.println(" There is no music or playlist be selected!! ");
            return;
        }

        addMusic(selectedMusic.getId(), new PlayList().nameToId(selectedPlaylist)); // add music to selected playlist
    }

    /**
     * Deletes a selected music from the selected playlist
     * @param selectedMusic selected music
     * @param selectedPlaylist selected playlist
     */
    public void deleteFromPlaylist(Music selectedMusic , String selectedPlaylist){
        /*
        Not possible to delete from a selected playlist if the selected music or
        selected playlist is null
         */
        if(selectedMusic == null || selectedPlaylist == null) {
            System.out.println(" there is no playlist be selected !");
            return;
        }

        deleteMusic(findID(selectedMusic.getId(),selectedPlaylist));    // delete selected music from playlist
    }
}
