            package API;

/**
 * This class defines an interface that any Music API class will implement.
 * Last Updated: 4/3/2020
 * @author Fernando Villarreal
 */

import Objects.MusicObjectList;
import Objects.ArtistObject;
import Objects.TrackObject;
import Objects.AlbumObject;

public interface MusicAPIInterface {

    //================= CLASS VARIABLES =================

    public static final String ARTIST = "artist";
    public static final String ALBUM = "album";
    public static final String TRACK = "track";
    public static final String ALL_MUSIC_OBJECT_TYPES = ARTIST + "," + ALBUM + "," + TRACK;
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String GENRES = "genres";

    //================= METHODS =================

    /**
     * Search the Music API for MusicObjects using the provided keyword, type, and limit.
     * @param _keyword : The keyword to use to search the API.
     * @param _type : The type of music object to search for: artist, track, and/or album.
     * @param _limit : The limit for how many MusicObjects to return of each type in the search results.
     * @return MusicObjectList: A list of the MusicObjects.
     */
    public MusicObjectList search(String _keyword, String _type, int _limit);

    /**
     * Load and return the ArtistObject associated with the given ID.
     * @param _id : The ID to use to load the ArtistObject.
     * @return ArtistObject
     */
    public ArtistObject loadArtistById(String _id);

    /**
     * Load and return the AlbumObject associated with the given ID.
     * @param _id : The ID to use to load the AlbumObject.
     * @return AlbumObject without TrackObjects
     */
    public AlbumObject loadAlbumById(String _id);

    /**
     * Load and return the TrackObject associated with the given ID.
     * @param _id : The ID to use to load the TrackObject.
     * @return TrackObject
     */
    public TrackObject loadTrackById(String _id);

    /**
     * Reload the given AlbumObject with its tracks.
     * @param _album : The AlbumObject.
     * @return AlbumObject with TrackObjects
     */
    public AlbumObject loadAlbumWithTracks(AlbumObject _album);

    /**
     * Load and return a MusicObjectList with the AlbumObjects of the given ArtistObject.
     * @param _artist : The ArtistObject.
     * @param _limit : The limit for how many AlbumObjects to return in the MusicObjectList.
     * @return MusicObjectList filled with AlbumObjects
     */
    public MusicObjectList loadAlbumsOfArtist(ArtistObject _artist, int _limit);

    /**
     * Load and return a MusicObjectList with the AlbumObjects of an artist using their ID.
     * @param _artistID : The ID of the artist.
     * @param _limit : The limit for how many AlbumObjects to return in the MusicObjectList.
     * @return MusicObjectList filled with AlbumObjects
     */
    public MusicObjectList loadAlbumsOfArtist(String _artistID, int _limit);
}
