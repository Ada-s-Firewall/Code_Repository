package API;

/**
 * This API Translator class makes API requests to the Spotify API.
 * NOTE 1: You must provide your own Spotify Developer Credentials encoded in Base64 in order to use this Translator.
 * NOTE 2: DO NOT leave your Developer Credentials in the MusicAPIAdapter file if you are uploading it to a public repository.
 * NOTE 3: This class requires cURL 7.68.0 or later to be installed on the device to make requests to the Spotify API.
 * NOTE 4: Do not call this class directly; route all requests through the MusicAPIAdapter class.
 * Last Updated: 3/31/2020
 * @author Fernando Villarreal
 */

import Objects.MusicObjectList;
import Objects.MusicObject;
import Objects.ArtistObject;
import Objects.TrackObject;
import Objects.AlbumObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class SpotifyAPITranslator implements MusicAPIInterface {

    //================= CLASS VARIABLES =================

    private final String developerCredentials;
    private String accessToken;
    private final String apiUrlString = "\"https://api.spotify.com";

    //================= CONSTRUCTOR ===============

    public SpotifyAPITranslator(String _encodedDeveloperCredentials) {
        this.developerCredentials = _encodedDeveloperCredentials;
    }

    //================= PUBLIC METHODS ===============

    @Override
    public MusicObjectList search(String _keyword, String _type, int _limit) {
        try {
            // Request authorization
            this.accessToken = this.requestAuthorization();
            // Clean the _keyword and _type for the urlString
            String keyword = _keyword.replace(" ", "+").replace(",", "%2C").trim();
            String type = _type.replace(" ", "").replace(",", "%2C").trim();
            // Create a curl command with the access token
            String curlStringChunk1 = "curl -X \"GET\"";
            String urlSearchString = "/v1/search";
            String urlString = this.apiUrlString + urlSearchString;
            String query = "?q=" + keyword + "&type=" + type + "&limit=" + _limit + "\"";
            String curlStringChunk2 = "-H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer";
            String curlCommand = curlStringChunk1 + " " + urlString + query + " " + curlStringChunk2 + " " + this.accessToken;
            // Perform a process with the curl command and get a JSONObject for the search results
            JSONObject obj = this.performProcess(curlCommand);
            // If there are errors in the JSONObject, throw an exception
            if (obj.has("error")) {
                throw new Exception("ERROR: " + obj.getJSONObject("error").getString("message"));
            }
            // Convert the JSONObject into a MusicObjectList and return it
            ArrayList<MusicObject> arrayResults = this.convertSearchResults(obj, _type);
            String listName = "Search Results for \"" + _keyword + "\" in \"" + _type + "\"";
            MusicObjectList results = new MusicObjectList(listName, "search results", arrayResults);
            return results;
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArtistObject loadArtistById(String _id) {
        try {
            // Load the ArtistObject and return it
            ArtistObject artist = (ArtistObject)this.loadMusicObjectByID(_id, MusicAPIInterface.ARTIST);
            return artist;
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public AlbumObject loadAlbumById(String _id) {
        try {
            // Load the ArtistObject and return it
            AlbumObject album = (AlbumObject)this.loadMusicObjectByID(_id, MusicAPIInterface.ALBUM);
            return album;
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public TrackObject loadTrackById(String _id) {
        try {
            // Load the ArtistObject and return it
            TrackObject track = (TrackObject)this.loadMusicObjectByID(_id, MusicAPIInterface.TRACK);
            return track;
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public AlbumObject loadAlbumWithTracks(AlbumObject _album) {
        try {
            // Request authorization
            this.accessToken = this.requestAuthorization();
            // Create a curl command
            String curlStringChunk1 = "curl -X \"GET\"";
            String urlEndpoint = "/v1/" + _album.getType() + "s/" + _album.getId() + "/tracks\"";
            String urlString = this.apiUrlString + urlEndpoint;
            String curlStringChunk2 = "-H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer";
            String curlCommand = curlStringChunk1 + " " + urlString + " " + curlStringChunk2 + " " + this.accessToken;
            // Perform a process with the curl command and get a JSONObject
            JSONObject jsonTracksObject = this.performProcess(curlCommand);
            // Create an ArrayList of MusicObjects to put the TrackObjects in
            ArrayList<MusicObject> tracksArray = new ArrayList<>();
            JSONArray items = jsonTracksObject.getJSONArray("items");
            int itemsLength = items.length();
            // Get the attributes for each TrackObject
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonTrack = items.getJSONObject(index);
                String trackName = jsonTrack.getString(MusicAPIInterface.NAME);
                String trackID = jsonTrack.getString(MusicAPIInterface.ID);
                String trackType = jsonTrack.getString(MusicAPIInterface.TYPE);
                String trackArtist = this.convertJSONArrayofArtists(jsonTrack.getJSONArray("artists"));
                ArrayList<String> trackGenres = new ArrayList<>();
                // Create a TrackObject and add it to the ArrayList
                TrackObject track = new TrackObject(trackName, trackID, trackType, trackGenres, trackArtist, _album.getName(), _album.getYear());
                tracksArray.add(track);
            }
            // Create a MusicObjectList with the Tracks and return a new AlbumObject with the Tracks
            String tracksListName = "Tracks for \"" + _album.getName() + "\"";
            MusicObjectList tracks = new MusicObjectList(tracksListName, "album tracks", tracksArray);
            return new AlbumObject(_album, tracks);
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //================= PRIVATE METHODS ===============

    /**
     * Request authorization to access the Spotify API. If authorization is granted, an access token is received.
     * @return accessToken : Access token to be used to make other requests to the Spotify API.
     * @throws Exception
     */
    private String requestAuthorization() throws Exception {
        // Create curl command to request authorization
        String baseURL = "https://accounts.spotify.com";
        String endpoint = "/api/token";
        String urlString = baseURL + endpoint;
        String curlStringChunk1 = "curl -X \"POST\" -H \"Authorization: Basic";
        String curlStringChunk2 = "\" -d grant_type=client_credentials";
        String curlCommand = curlStringChunk1 + " " + this.developerCredentials + curlStringChunk2 + " " + urlString;
        // Perform a process with the curl command
        JSONObject obj = this.performProcess(curlCommand);
        // Get the acccess token or throw an error if not found
        if (obj.has("error")) {
            throw new Exception("ERROR: The provided credentials are invalid and/or in the incorrect format.");
        }
        String accessToken = obj.getString("access_token");
        return accessToken;
    }

    /**
     * Performs a process with the given curl command.
     * @param _curlCommand : The command to be performed.
     * @return obj : A JSONObject representation of the InputStream of the Process.
     * @throws Exception
     */
    private JSONObject performProcess(String _curlCommand) throws Exception {
        // Perform a process with the given curl command
        ProcessBuilder processBuilder = new ProcessBuilder(_curlCommand.split(" "));
        processBuilder.directory();
        Process process;
        process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        // Convert the InputStream of the process into a JSONObject
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = bfReader.readLine()) != null) {
            content.append(inputLine);
        }
        process.waitFor();
        process.destroy();
        inputStream.close();
        // Return the JSONObject
        JSONObject obj = new JSONObject(content.toString());
        return obj;
    }

    /**
     * Load and return a MusicOject of a specific type using the given ID.
     * Helper method for the public "load by ID" methods above.
     * @param _id : The ID to be used to load the MusicObject.
     * @param _type : The type of MusicObject to load.
     * @return MusicObject
     */
    private MusicObject loadMusicObjectByID(String _id, String _type) {
        try {
            // Request authorization
            this.accessToken = this.requestAuthorization();
            // Create a curl command
            String curlStringChunk1 = "curl -X \"GET\"";
            String urlEndpoint = "/v1/" + _type + "s/";
            String id = _id + "\"";
            String urlString = this.apiUrlString + urlEndpoint + id;
            String curlStringChunk2 = "-H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer";
            String curlCommand = curlStringChunk1 + " " + urlString + " " + curlStringChunk2 + " " + this.accessToken;
            // Perform a process with the curl command and get a JSONObject
            JSONObject jsonMusicObject = this.performProcess(curlCommand);
            // If the JSON Music Object could not be loaded, throw an error
            if (jsonMusicObject.has("error")) {
                throw new Exception("ERROR: The provided ID is either invalid or does not belong to a Music Object of type: " + _type + ".");
            }
            // Convert the JSON Music Object into the specified type of MusicObject and return it
            if (_type.equals(MusicAPIInterface.ARTIST)) {
                ArtistObject artist = this.convertJSONArtistObject(jsonMusicObject);
                return artist;
            }
            if (_type.equals(MusicAPIInterface.ALBUM)) {
                AlbumObject album = this.convertJSONAlbumObject(jsonMusicObject);
                return album;
            }
            if (_type.equals(MusicAPIInterface.TRACK)) {
                TrackObject track = this.convertJSONTrackObject(jsonMusicObject);
                return track;
            }
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Convert the JSONObject of the search results into an ArrayList of MusicObjects.
     * Helper method for the search method.
     * @param _searchResults : The JSONObject representation of the search results.
     * @param _type : The type(s) of MusicObjects initially queried in the search.
     * @return ArrayList<MusicObject>
     * @throws Exception
     */
    private ArrayList<MusicObject> convertSearchResults(JSONObject _searchResults, String _type) throws Exception {
        // Create an empty ArrayList of MusicObjects to fill
        ArrayList<MusicObject> itemsList = new ArrayList<>();
        // Fill the ArrayList with MusicObjects
        if (_type.contains(MusicAPIInterface.ARTIST)) {
            JSONObject resultsOfType = _searchResults.getJSONObject("artists");
            JSONArray items = resultsOfType.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonArtist = items.getJSONObject(index);
                ArtistObject artist = this.convertJSONArtistObject(jsonArtist);
                itemsList.add(artist);
            }
        }
        if (_type.contains(MusicAPIInterface.TRACK)) {
            JSONObject resultsOfType = _searchResults.getJSONObject("tracks");
            JSONArray items = resultsOfType.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonTrack = items.getJSONObject(index);
                TrackObject track = this.convertJSONTrackObject(jsonTrack);
                itemsList.add(track);
            }
        }
        if (_type.contains(MusicAPIInterface.ALBUM)) {
            JSONObject resultsOfType = _searchResults.getJSONObject("albums");
            JSONArray items = resultsOfType.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonAlbum = items.getJSONObject(index);
                AlbumObject album = this.convertJSONAlbumObject(jsonAlbum);
                itemsList.add(album);
            }
        }
        // Return the ArrayList of MusicObjects
        return itemsList;
    }

    /**
     * Convert a JSONArray of Strings into an ArrayList of Strings.
     * @param _jsonArray : A JSONArray containing Strings.
     * @return stringList : An ArrayList of Strings.
     * @throws JSONException
     */
    private ArrayList<String> convertJSONArrayOfStrings(JSONArray _jsonArray) throws JSONException {
        int length = _jsonArray.length();
        ArrayList<String> stringList = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            String itemString = _jsonArray.getString(index);
            stringList.add(itemString);
        }
        return stringList;
    }

    /**
     * Convert a JSONArray of Artist Objects into a String listing the names of the artists.
     * @param _jsonArray : A JSONArray containing JSON Spotify Artist Objects.
     * @return artists : A String listing the names of the artists.
     * @throws JSONException
     */
    private String convertJSONArrayofArtists(JSONArray _jsonArray) throws JSONException{
        int arrayLength = _jsonArray.length();
        String artists = "";
        // Get the name of each JSON Artist Object
        for (int index = 0; index < arrayLength; index++) {
            JSONObject jsonArtist = _jsonArray.getJSONObject(index);
            String artistName = jsonArtist.getString(MusicAPIInterface.NAME);
            artists += artistName;
            if (index < (arrayLength - 1)) {
                artists += ", ";
            }
        }
        return artists;
    }

    //================= PRIVATE METHODS FOR: Converting Spotify JSON Music Objects into MusicObjects ===============

    /**
     * Convert a JSON Spotify Artist Object into an ArtistObject.
     * Helper method for the convertSearchResults method.
     * @param _jsonArtist
     * @return ArtistObject
     */
    private ArtistObject convertJSONArtistObject(JSONObject _jsonArtist) throws JSONException {
        // Get the name, ID, type, and url
        String artistName = _jsonArtist.getString(MusicAPIInterface.NAME);
        String artistID = _jsonArtist.getString(MusicAPIInterface.ID);
        String artistType = _jsonArtist.getString(MusicAPIInterface.TYPE);
        String artistUrl = _jsonArtist.getJSONObject("external_urls").getString("spotify");
        // Get and convert the genres
        JSONArray jsonGenres = _jsonArtist.getJSONArray(MusicAPIInterface.GENRES);
        ArrayList<String> artistGenres = this.convertJSONArrayOfStrings(jsonGenres);
        // Create and return the ArtistObject
        ArtistObject artist = new ArtistObject(artistName, artistID, artistType, artistGenres, artistUrl);
        return artist;
    }

    /**
     * Convert a JSON Spotify Track Object into a TrackObject.
     * Helper method for the convertSearchResults method.
     * @param _jsonTrack
     * @return TrackObject
     * @throws JSONException
     */
    private TrackObject convertJSONTrackObject(JSONObject _jsonTrack) throws JSONException {
        // Get the name, ID, and type
        String trackName = _jsonTrack.getString(MusicAPIInterface.NAME);
        String trackID = _jsonTrack.getString(MusicAPIInterface.ID);
        String trackType = _jsonTrack.getString(MusicAPIInterface.TYPE);
        // Spotify does not provide genres for tracks
        ArrayList<String> trackGenres = new ArrayList<>();
        // Get the artist(s), album, and year
        String trackArtist = this.convertJSONArrayofArtists(_jsonTrack.getJSONArray("artists"));
        String trackAlbum = _jsonTrack.getJSONObject(MusicAPIInterface.ALBUM).getString(MusicAPIInterface.NAME);
        String trackYear = _jsonTrack.getJSONObject(MusicAPIInterface.ALBUM).getString("release_date");
        // Create and return the TrackObject
        TrackObject track = new TrackObject(trackName, trackID, trackType, trackGenres, trackArtist, trackAlbum, trackYear);
        return track;
    }

    /**
     * Convert a JSON Spotify Album Object into an AlbumObject.
     * Helper method for the convertSearchResults method.
     * @param _jsonAlbum
     * @return AlbumObject
     * @throws JSONException
     */
    private AlbumObject convertJSONAlbumObject(JSONObject _jsonAlbum) throws JSONException {
        // Get the name, ID, and type
        String albumName = _jsonAlbum.getString(MusicAPIInterface.NAME);
        String albumID = _jsonAlbum.getString(MusicAPIInterface.ID);
        String albumType = _jsonAlbum.getString(MusicAPIInterface.TYPE);
        // Get the year and artist
        String albumYear = _jsonAlbum.getString("release_date");
        String albumArtist = this.convertJSONArrayofArtists(_jsonAlbum.getJSONArray("artists"));
        // Spotify does not provide genres for albums
        ArrayList<String> albumGenres = new ArrayList<>();
        // Create and return the AlbumObject
        AlbumObject album = new AlbumObject(albumName, albumID, albumType, albumGenres, albumArtist, albumYear, null);
        return album;
    }
}
