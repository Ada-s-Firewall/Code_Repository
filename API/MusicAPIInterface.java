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
     * @param _keyword: The keyword to use to search the API.
     * @param _type: The type of music object to search for: either an artist, a track, or an album.
     * @return
     */
    public ArrayList<MusicObject> search(String _keyword, String _type);

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
