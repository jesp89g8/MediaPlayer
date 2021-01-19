package DataBase.Opration;

/**
 * The interface gives the implementor access to play, pause, stop music
 * Due to late implementation of this interface it does not provide any of
 * the necessary methods to make an implementor playable. Currently the
 * functionality of a playable item is implemented in the MusicOperation and
 * PlaylistOperation class. However, the playable type is still used for testing
 * whether a given object is of type Playlist or Music, example, in the play function.
 */
public interface Playable {
}
