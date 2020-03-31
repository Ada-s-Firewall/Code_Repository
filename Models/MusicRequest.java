package Models;

/**
 * This class acts as a wrapper for the MusicAPIAdapter class.
 * Last Updated: 3/31/2020
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
    private final int DEFAULT_SEARCH_LIMIT = 10;

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
            MusicObjectList searchResults = this.searchHelper(_keyword, _type, this.DEFAULT_SEARCH_LIMIT);
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
            MusicObjectList searchResults = this.searchHelper(_keyword, this.DEFAULT_SEARCH_TYPE, this.DEFAULT_SEARCH_LIMIT);
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

    /**
     * Call this method for information on how to use this class/object.
     */
    public void help() {
        System.out.println(
            "================================ HELP ================================\n"
            + "\n"
            + "MusicRequest.java" + "\n"
                + "This class acts as wrapper for the MusicAPIAdapter class (API.MusicAPIAdapter.java).\n"
                + "MusicAPIAdapter uses a Translator class called apiTranslator. Please make sure you know how to use \n"
                + "the translator before using this class. Requirements for different APIs and their translators vary.\n"
                + "\n"
                + "To use this class:\n"
                + "Import the class and create a new MusicRequest object.\n"
                + "\n"
                + "The methods available for use are:\n"
                + "search(String _keyword, String _type, int _limit) returns a MusicObjectList.\n"
                + "search(String _keyword, String _type) returns a MusicObjectList.\n"
                + "search(String _keyword) returns a MusicObjectList.\n"
                + "loadArtistById(String _id) returns an ArtistObject.\n"
                + "loadAlbumById(String _id) returns an AlbumObject.\n"
                + "loadTrackById(String _id) returns a TrackObject.\n"
                + "loadAlbumTracks(AlbumObject _album), this is a void method.\n"
                + "help() displays this message.\n"
                + "\n"
                + "The search method parameters:\n"
                + "_keyword: The keyword can be any string.\n"
                + "_type: The type or types of MusicObjects you are searching for. These objects can be artists, albums, \n"
                + "\tand/or tracks. Please use the variables from API.MusicAPIInterface.java for this paremeter. You \n"
                + "\tcan simply append them together into one string and pass that as the parameter as well.\n"
                + "\tFor example: String type = MusicAPIInterface.ALBUM + \", \" + MusicAPIInterface.TRACK\n"
                + "_limit: The limit for how many MusicObjects of each type you want in the search results. For \n"
                + "\texample: if the limit is 5, you will receive up to 5 ArtistObjects, up to 5 AlbumObjects, and \n"
                + "\tup to 5 TrackObjects in the search results (up to 15 MusicObjects total).\n"
                + "\n"
                + "MusicObject IDs: The IDs of MusicObjects are specific to the Music API that is being used.\n"
                + "\n"
                + "Please consult the author of this class if you have anymore questions.\n"
            + "\n"
            + "====================================================================="
        );
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
        if (_limit <= 0) {
            throw new Exception("ERROR: The limit must be greater than zero.");
        }
        // Perform the search using the adapter
        System.out.println("Searching for \"" + _keyword + "\" in \"" + type + "\".");
        return this.adapter.search(_keyword, type, _limit);
    }
}
