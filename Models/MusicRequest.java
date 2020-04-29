package Models;

/**
 * This class acts as a wrapper for the MusicAPIAdapter class.
 * Last Updated: 4/28/2020
 * @author Fernando Villarreal
 */

import API.MusicAPIAdapter;
import API.MusicAPIInterface;
import Objects.AlbumObject;
import Objects.ArtistObject;
import Objects.MusicObjectList;
import Objects.TrackObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicRequest implements MusicAPIInterface {

    //=================== CLASS VARIABLES ===================

    protected final MusicAPIAdapter adapter = new MusicAPIAdapter();
    private final String DEFAULT_SEARCH_TYPE = MusicAPIInterface.ALL_MUSIC_OBJECT_TYPES;
    private final int DEFAULT_QUERY_LIMIT = 20;
    private final int MIN_QUERY_LIMIT = 1;
    private final int MAX_QUERY_LIMIT = 50;

    //=================== PUBLIC METHODS ===================

    @Override
    public MusicObjectList search(String _keyword, String _type, int _limit) {
        try {
            MusicObjectList searchResults = this.searchHelper(_keyword, _type, _limit);
            return searchResults;
        } catch (Exception ex) {
            Logger.getLogger(MusicRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Searches the Music API for MusicObjects using the provided keyword and type.
     * @param _keyword : The keyword to use to search the API.
     * @param _type : The type of music object to search for: artist, track, and/or album.
     * @return MusicObjectList
     */
    public MusicObjectList search(String _keyword, String _type) {
        try {
            MusicObjectList searchResults = this.searchHelper(_keyword, _type, this.DEFAULT_QUERY_LIMIT);
            return searchResults;
        } catch (Exception ex) {
            Logger.getLogger(MusicRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Searches the Music API for MusicObjects using the provided keyword.
     * @param _keyword : The keyword to use to search the API.
     * @return MusicObjectList
     */
    public MusicObjectList search(String _keyword) {
        try {
            MusicObjectList searchResults = this.searchHelper(_keyword, this.DEFAULT_SEARCH_TYPE, this.DEFAULT_QUERY_LIMIT);
            return searchResults;
        } catch (Exception ex) {
            Logger.getLogger(MusicRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArtistObject loadArtistById(String _id) {
        System.out.println("Loading ArtistObject with ID: " + _id + ".");
        return this.adapter.loadArtistById(_id);
    }

    @Override
    public AlbumObject loadAlbumById(String _id) {
        System.out.println("Loading AlbumObject with ID: " + _id + ".");
        return this.adapter.loadAlbumById(_id);
    }

    @Override
    public TrackObject loadTrackById(String _id) {
        System.out.println("Loading TrackObject with ID: " + _id + ".");
        return this.adapter.loadTrackById(_id);
    }

    @Override
    public AlbumObject loadAlbumWithTracks(AlbumObject _album) {
        System.out.println("Loading the TrackObjects for the Album: " + _album.getName() + ".");
        return this.adapter.loadAlbumWithTracks(_album);
    }

    @Override
    public MusicObjectList loadAlbumsOfArtist(ArtistObject _artist, int _limit) {
        System.out.println("Loading the Albums of " + _artist.getName() + ".");
        String artistID = _artist.getId();
        return this.adapter.loadAlbumsOfArtist(artistID, _limit);
    }

    @Override
    public MusicObjectList loadAlbumsOfArtist(String _artistID, int _limit) {
        System.out.println("Loading the Albums of artist with ID: " + _artistID + ".");
        return this.adapter.loadAlbumsOfArtist(_artistID, _limit);
    }

    /**
     * Load and return a MusicObjectList with the AlbumObjects of the given ArtistObject.
     * @param _artist : The ArtistObject
     * @return MusicObjectList
     */
    public MusicObjectList loadAlbumsOfArtist(ArtistObject _artist) {
        return this.loadAlbumsOfArtist(_artist, this.DEFAULT_QUERY_LIMIT);
    }

    /**
     * Load and return a MusicObjectList with the AlbumObjects of an artist using their ID.
     * @param _artistID : The ID of the artist.
     * @return MusicObjectList
     */
    public MusicObjectList loadAlbumsOfArtist(String _artistID) {
        return this.loadAlbumsOfArtist(_artistID, this.DEFAULT_QUERY_LIMIT);
    }

    //=================== PRIVATE METHODS ===================

    /**
     * Helper method for search that checks the validity of the parameters and performs the search with the adapter.
     * @param _keyword
     * @param _type
     * @param _limit
     * @return
     * @throws Exception
     */
    private MusicObjectList searchHelper(String _keyword, String _type, int _limit) throws Exception {
        // Check if the keyword is empty
        if (_keyword.isEmpty()) {
            throw new Exception("ERROR: The value of _keyword is invalid.");
        }
        // Check that the _type is valid and create a new type
        String type = "";
        if (_type.contains(MusicAPIInterface.ARTIST)) {
            type += MusicAPIInterface.ARTIST + ",";
        }
        if (_type.contains(MusicAPIInterface.ALBUM)) {
            type += MusicAPIInterface.ALBUM + ",";
        }
        if (_type.contains(MusicAPIInterface.TRACK)) {
            type += MusicAPIInterface.TRACK;
        }
        // Check if type is empty
        if (type.isEmpty()) {
            throw new Exception("ERROR: The value of _type is invalid.");
        }
        // Trim the trailing comma in type if present
        if (type.endsWith(",")) {
            type = type.substring(0, type.length() - 1);
        }
        // Check that the limit is valid
        if (_limit < this.MIN_QUERY_LIMIT || _limit > this.MAX_QUERY_LIMIT) {
            throw new Exception("ERROR: The limit must be an integer between 1 and 50.");
        }
        // Perform the search using the adapter
        System.out.println("Searching for \"" + _keyword + "\" in \"" + type + "\".");
        return this.adapter.search(_keyword, type, _limit);
    }
}
