package API;

/**
 * This class defines an interface that any Music API class will implement.
 * Last Updated: 3/28/2020
 * @author Fernando Villarreal
 */

import Objects.MusicObjectList;
import Objects.ArtistObject;
import Objects.TrackObject;
import Objects.AlbumObject;

public interface MusicAPIInterface {

    /**
     * Search the Music API for MusicObjects using the provided keyword, type, and limit.
     * @param _keyword : The keyword to use to search the API.
     * @param _type : The type of music object to search for: artist, track, and/or album.
     * @param _limit : The limit for how many music objects to return of each type in the search results.
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
     * @return AlbumObject
     */
    public AlbumObject loadAlbumById(String _id);

    /**
     * Load and return the TrackObject associated with the given ID.
     * @param _id : The ID to use to load the TrackObject.
     * @return TrackObject
     */
    public TrackObject loadTrackById(String _id);

    /**
     * Load the tracks of the given AlbumObject.
     * @param _album : The AlbumObject.
     */
    public void loadAlbumTracks(AlbumObject _album);
}
