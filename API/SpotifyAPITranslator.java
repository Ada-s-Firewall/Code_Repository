package API;

/**
 * This API Translator class makes API requests to the Spotify API.
 * NOTE 1: You must provide your own Spotify Developer Credentials encoded in Base64 in order to use this Translator.
 * NOTE 2: DO NOT leave your Developer Credentials in the MusicAPIAdapter file if you are uploading it to a public repository.
 * NOTE 3: This class requires cURL 7.68.0 to be installed on the machine to make requests to the Spotify API.
 * NOTE 4: Do not call this class directly; route all requests through the MusicAPIAdapter class.
 * Last Updated: 3/13/2020
 * @author Fernando Villarreal
 */

import Models.*;
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
            // Request Authorization to the Spotify API and get an access token
            this.accessToken = this.requestAuthorization();
            // Clean the _keyword and _type for the urlString
            String keyword = _keyword.replace(" ", "+").replace(",", "%2C").trim();
            _type = _type.replace(" ", "").replace(",", "%2C").trim();
            // Create a curl command with the access token
            String curlStringChunk1 = "curl -X \"GET\"";
            String urlSearchString = "/v1/search";
            String urlString = this.apiUrlString + urlSearchString;
            String query = "?q=" + keyword + "&type=" + _type + "&limit=" + _limit + "\"";
            String curlStringChunk2 = "-H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer";
            String curlCommand = curlStringChunk1 + " " + urlString + query + " " + curlStringChunk2 + " " + this.accessToken;
            // Perform a process with the curl command and get a JSON Object for the search results
            JSONObject obj = this.performProcess(curlCommand);
            // Detect Errors in the JSONObject if present and throw an exception
            if (obj.has("error")) {
                throw new Exception("ERROR: " + obj.getJSONObject("error").getString("message"));
            }
            // Convert the JSON Object into an ArrayList of MusicObjects and return it
            ArrayList<MusicObject> arrayResults = this.convertSearchResults(obj, _type);
            String listName = "Search Results for \"" + _keyword + "\"";
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
            ArtistObject artist = (ArtistObject)this.loadMusicObjectByID(_id, MusicAPIVariables.ARTIST);
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
            AlbumObject album = (AlbumObject)this.loadMusicObjectByID(_id, MusicAPIVariables.ALBUM);
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
            TrackObject track = (TrackObject)this.loadMusicObjectByID(_id, MusicAPIVariables.TRACK);
            return track;
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void loadAlbumTracks(AlbumObject _album) {
        try {
            // Request authorization
            this.accessToken = this.requestAuthorization();
            // Create a curl command
            String curlStringChunk1 = "curl -X \"GET\"";
            String urlEndpoint = "/v1/" + _album.getType() + "s/" + _album.getId() + "/tracks\"";
            String urlString = this.apiUrlString + urlEndpoint;
            String curlStringChunk2 = "-H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer";
            String curlCommand = curlStringChunk1 + " " + urlString + " " + curlStringChunk2 + " " + this.accessToken;
            // Perform a Process with the curl command and get a JSONObject
            JSONObject jsonTracksObject = this.performProcess(curlCommand);
            // Get the attributes for each TrackObject
            ArrayList<MusicObject> tracksArray = new ArrayList<>();
            JSONArray items = jsonTracksObject.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonTrack = items.getJSONObject(index);
                String trackName = jsonTrack.getString(MusicAPIVariables.NAME);
                String trackID = jsonTrack.getString(MusicAPIVariables.ID);
                String trackType = jsonTrack.getString(MusicAPIVariables.TYPE);
                String trackArtist = jsonTrack.getJSONArray("artists").getJSONObject(0).getString("name");
                ArrayList<String> trackGenres = new ArrayList<>();
                TrackObject track = new TrackObject(trackName, trackID, trackType, trackGenres, trackArtist, _album.getName(), _album.getYear());
                tracksArray.add(track);
            }
            // Set the tracks for the given album
            _album.setTracks(tracksArray);
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //================= PRIVATE METHODS ===============

    /**
     * Request authorization to access the Spotify API.
     * @return accessToken : Access token to be used to make other requests to the Spotify API.
     * @throws Exception
     */
    private String requestAuthorization() throws Exception {
        // Create cURL Command to Request Authorization
        String baseURL = "https://accounts.spotify.com";
        String endpoint = "/api/token";
        String urlString = baseURL + endpoint;
        String curlStringChunk1 = "curl -X \"POST\" -H \"Authorization: Basic";
        String curlStringChunk2 = "\" -d grant_type=client_credentials";
        String curlCommand = curlStringChunk1 + " " + this.developerCredentials + curlStringChunk2 + " " + urlString;
        // Perform a Process with the curlCommand
        JSONObject obj = this.performProcess(curlCommand);
        // Get the Acccess Token or throw an error if not found
        if (obj.has("access_token")) {
            String accessToken = obj.getString("access_token");
            return accessToken;
        } else {
            throw new Exception("ERROR: The provided credentials are invalid and/or in the incorrect format.");
        }
    }

    /**
     * Performs a Process with the given cURL Command.
     * @param _curlCommand: The command to be performed.
     * @return obj: A JSONObject representation of the InputStream of the Process.
     * @throws Exception
     */
    private JSONObject performProcess(String _curlCommand) throws Exception {
        // Perform a process with the given cURL command
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
     * Helper method for the public load_By_ID methods above.
     * @param _id : The ID to be used to load the MusicObject.
     * @param _type : The type of MusicObject to load.
     * @return MusicObject of either artist, album, or track.
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
            // Perform a Process with the curl command and get a JSONObject
            JSONObject jsonMusicObject = this.performProcess(curlCommand);
            // Throw an error if the JSON Music Object was not loaded
            if (jsonMusicObject.has("error")) {
                throw new Exception("ERROR: The provided ID is either invalid or does not belong to a Music Object of type: " + _type + ".");
            }
            // Convert the JSON Music Object into the required type of MusicObject and return it
            if (_type.equals(MusicAPIVariables.ARTIST)) {
                ArtistObject artist = this.convertJSONArtistObject(jsonMusicObject);
                return artist;
            }
            if (_type.equals(MusicAPIVariables.ALBUM)) {
                AlbumObject album = this.convertJSONAlbumObject(jsonMusicObject);
                return album;
            }
            if (_type.equals(MusicAPIVariables.TRACK)) {
                TrackObject track = this.convertJSONTrackObject(jsonMusicObject);
                return track;
            }
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Given a JSONObject of the search results, return an ArrayList of the MusicObjects.
     * Helper method for the this.search method.
     * @param _searchResults : The JSONObject representation of the search results.
     * @param _type : The type(s) of MusicObjects initially queried in the search.
     * @return itemList : ArrayList of MusicObjects
     * @throws Exception
     */
    private ArrayList<MusicObject> convertSearchResults(JSONObject _searchResults, String _type) throws Exception {
        // Create an empty ArrayList<MusicObject> to fill
        ArrayList<MusicObject> itemsList = new ArrayList<>();
        // Fill the ArrayList with MusicObjects
        if (_type.contains(MusicAPIVariables.ARTIST)) {
            JSONObject resultsOfType = _searchResults.getJSONObject("artists");
            JSONArray items = resultsOfType.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonArtist = items.getJSONObject(index);
                ArtistObject artist = this.convertJSONArtistObject(jsonArtist);
                itemsList.add(artist);
            }
        }
        if (_type.contains(MusicAPIVariables.TRACK)) {
            JSONObject resultsOfType = _searchResults.getJSONObject("tracks");
            JSONArray items = resultsOfType.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonTrack = items.getJSONObject(index);
                TrackObject track = this.convertJSONTrackObject(jsonTrack);
                itemsList.add(track);
            }
        }
        if (_type.contains(MusicAPIVariables.ALBUM)) {
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
     * Convert a JSONArray into an ArrayList of Strings.
     * @param _jsonArray
     * @return finalList
     * @throws JSONException
     */
    private ArrayList<String> convertJSONArray(JSONArray _jsonArray) throws JSONException {
        int length = _jsonArray.length();
        ArrayList<String> finalList = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            String itemString = _jsonArray.getString(index);
            finalList.add(itemString);
        }
        return finalList;
    }

    //================= PRIVATE METHODS FOR: Converting Spotify JSON Music Objects into MusicObjects ===============

    /**
     * Given a Spotify JSON ArtistObject, convert it into an ArtistObject.
     * Helper method for the this.convertSearchResults method.
     * @param _jsonArtist
     * @return artist
     */
    private ArtistObject convertJSONArtistObject(JSONObject _jsonArtist) throws JSONException {
        // Get the name, id, type, and url
        String artistName = _jsonArtist.getString(MusicAPIVariables.NAME);
        String artistID = _jsonArtist.getString(MusicAPIVariables.ID);
        String artistType = _jsonArtist.getString(MusicAPIVariables.TYPE);
        String artistUrl = _jsonArtist.getJSONObject("external_urls").getString("spotify");
        // Get and convert the genres
        JSONArray jsonGenres = _jsonArtist.getJSONArray(MusicAPIVariables.GENRES);
        ArrayList<String> artistGenres = this.convertJSONArray(jsonGenres);
        // Create and return the ArtistObject
        ArtistObject artist = new ArtistObject(artistName, artistID, artistType, artistGenres, artistUrl);
        return artist;
    }

    /**
     * Given a Spotify JSON TrackObject, convert it into a TrackObject.
     * Helper method for the this.convertSearchResults method.
     * @param _jsonTrack
     * @return track
     * @throws JSONException
     */
    private TrackObject convertJSONTrackObject(JSONObject _jsonTrack) throws JSONException {
        // Get the name, id, and type
        String trackName = _jsonTrack.getString(MusicAPIVariables.NAME);
        String trackID = _jsonTrack.getString(MusicAPIVariables.ID);
        String trackType = _jsonTrack.getString(MusicAPIVariables.TYPE);
        // Spotify does not provide genres for tracks
        ArrayList<String> trackGenres = new ArrayList<>();
        // Get the artist, album, and year
        String trackArtist = _jsonTrack.getJSONArray("artists").getJSONObject(0).getString("name");;
        String trackAlbum = _jsonTrack.getJSONObject("album").getString("name");
        String trackYear = _jsonTrack.getJSONObject("album").getString("release_date");
        // Create and return the TrackObject
        TrackObject track = new TrackObject(trackName, trackID, trackType, trackGenres, trackArtist, trackAlbum, trackYear);
        return track;
    }

    /**
     * Given a Spotify JSON AlbumObject, convert it into an AlbumObject.
     * Helper method for the this.convertSearchResults method.
     * @param _jsonAlbum
     * @return album
     * @throws JSONException
     */
    private AlbumObject convertJSONAlbumObject(JSONObject _jsonAlbum) throws JSONException {
        // Get the name, id, and type
        String albumName = _jsonAlbum.getString(MusicAPIVariables.NAME);
        String albumID = _jsonAlbum.getString(MusicAPIVariables.ID);
        String albumType = _jsonAlbum.getString(MusicAPIVariables.TYPE);
        // Get the year and artist
        String albumYear = _jsonAlbum.getString("release_date");
        String albumArtist = _jsonAlbum.getJSONArray("artists").getJSONObject(0).getString("name");
        // Spotify does not provide genres for albums
        ArrayList<String> albumGenres = new ArrayList<>();
        // Create and return the AlbumObject
        AlbumObject album = new AlbumObject(albumName, albumID, albumType, albumGenres, albumArtist, albumYear);
        return album;
    }
}
