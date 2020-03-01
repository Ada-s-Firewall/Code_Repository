package API;

/**
 * This class defines an interface that any Music API class will implement.
 * Last Updated: 2/28/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;
import Models.MusicObject;
import Models.ArtistObject;
import Models.TrackObject;
import Models.AlbumObject;

public interface MusicAPIInterface {

    /**
     * Search the API for a music object using a keyword and a type.
     * @param _keyword : The keyword to use to search the API.
     * @param _type : The type of music object to search for: either an artist, a track, or an album.
     * @param _limit : The limit for how many objects to return in the search results.
     * @return : The search results as a list of Music Objects.
     */
    public ArrayList<MusicObject> search(String _keyword, String _type, int _limit);

    /**
     * Given an ID, return the Artist Object associated with the ID.
     * @param _id
     * @return
     */
    public ArtistObject loadArtistById(String _id);

    /**
     * Given an ID, return the Album Object associated with the ID.
     * @param _id
     * @return
     */
    public AlbumObject loadAlbumById(String _id);

    /**
     * Given an ID, return the Track Object associated with the ID.
     * @param _id
     * @return
     */
    public TrackObject loadTrackById(String _id);
}
