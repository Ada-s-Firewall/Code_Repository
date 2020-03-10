package API;

/**
 * This API Translator class makes API requests to the Spotify API.
 * NOTE 1: You must provide your own Spotify Developer Credentials encoded in Base64 in order to use this Translator.
 * NOTE 2: DO NOT leave your Developer Credentials in the MusicAPIAdapter file if you are uploading it to a public repository.
 * NOTE 3: This class requires cURL 7.68.0 to be installed on the machine to make requests to the Spotify API.
 * NOTE 4: Do not call this class directly; route all requests through the MusicAPIAdapter class.
 * Last Updated: 3/9/2020
 * @author Fernando Villarreal
 */

import Models.AlbumObject;
import Models.ArtistObject;
import Models.MusicObject;
import Models.TrackObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class SpotifyAPITranslator implements MusicAPIInterface {

    private final String developerCredentials;
    private String accessToken;
    private final String apiUrlString = "\"https://api.spotify.com";

    //================= CONSTRUCTOR ===============

    public SpotifyAPITranslator(String _encodedDeveloperCredentials) {
        this.developerCredentials = _encodedDeveloperCredentials;
    }

    //================= PUBLIC METHODS ===============

    @Override
    public ArrayList<MusicObject> search(String _keyword, String _type, int _limit) {
        try {
            // Check the validity of the limit value
            if (_limit <= 0) {
                throw new Exception("The limit must be an integer greater than or equal to 1.");
            }
            // Request Authorization to the Spotify API and get an access token
            this.accessToken = this.requestAuthorization();
            // Clean the _keyword and _type for the urlString
            _keyword = _keyword.replace(" ", "+").replace(",", "%2C").trim();
            _type = _type.replace(" ", "").replace(",", "%2C").trim();
            // Create a curl command with the access token
            String curlStringChunk1 = "curl -X \"GET\"";
            String urlSearchString = "/v1/search";
            String urlString = this.apiUrlString + urlSearchString;
            String query = "?q=" + _keyword + "&type=" + _type + "&limit=" + _limit + "\"";
            String curlStringChunk2 = "-H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer";
            String curlCommand = curlStringChunk1 + " " + urlString + query + " " + curlStringChunk2 + " " + this.accessToken;
            // Perform a process with the curl command and get a JSON Object for the search results
            JSONObject obj = this.performProcess(curlCommand);
            // Get the types of the Music Objects in the searchResults
            String types = "";
            if (obj.has("artists")) {
                types += "artists, ";
            }
            if (obj.has("albums")) {
                types += "albums, ";
            }
            if (obj.has("tracks")) {
                types += "tracks";
            }
            // Convert the JSON Object into an ArrayList of MusicObjects and return it
            ArrayList<MusicObject> results = this.convertSearchResults(obj, types);
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
            ArtistObject artist = (ArtistObject)this.loadMusicObjectByID(_id, "artist");
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
            AlbumObject album = (AlbumObject)this.loadMusicObjectByID(_id, "album");
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
            TrackObject track = (TrackObject)this.loadMusicObjectByID(_id, "track");
            return track;
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //================= PRIVATE METHODS ===============

    /**
     * Request authorization to access the Spotify API.
     * @return accessToken
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
        // Get the Acccess Token
        String accessToken = obj.getString("access_token");
        return accessToken;
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
     * Load and return a MusicOject of _type using the given _id.
     * Helper method for the public load_By_ID methods above.
     * @param _id
     * @param _type
     * @return MusicObject of either artist, album, or track
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
            // Convert the JSON Music Object into the required type of MusicObject and return it
            if (_type.equals("artist")) {
                ArtistObject artist = this.convertJSONArtistObject(jsonMusicObject);
                return artist;
            }
            if (_type.equals("album")) {
                AlbumObject album = this.convertJSONAlbumObject(jsonMusicObject);
                return album;
            }
            if (_type.equals("track")) {
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
     * @param _searchResults
     * @param _type
     * @return
     * @throws Exception
     */
    private ArrayList<MusicObject> convertSearchResults(JSONObject _searchResults, String _type) throws Exception {
        // Create an empty ArrayList<MusicObject> to fill
        ArrayList<MusicObject> itemsList = new ArrayList<>();
        // Fill the ArrayList with MusicObjects
        if (_type.contains("artist")) {
            JSONObject resultsOfType = _searchResults.getJSONObject("artists");
            JSONArray items = resultsOfType.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonArtist = items.getJSONObject(index);
                ArtistObject artist = this.convertJSONArtistObject(jsonArtist);
                itemsList.add(artist);
            }
        }
        if (_type.contains("track")) {
            JSONObject resultsOfType = _searchResults.getJSONObject("tracks");
            JSONArray items = resultsOfType.getJSONArray("items");
            int itemsLength = items.length();
            for (int index = 0; index < itemsLength; index++) {
                JSONObject jsonTrack = items.getJSONObject(index);
                TrackObject track = this.convertJSONTrackObject(jsonTrack);
                itemsList.add(track);
            }
        }
        if (_type.contains("album")) {
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
        String artistName = _jsonArtist.getString("name");
        String artistID = _jsonArtist.getString("id");
        String artistType = _jsonArtist.getString("type");
        String artistUrl = _jsonArtist.getJSONObject("external_urls").getString("spotify");
        // Get and convert the genres
        JSONArray jsonGenres = _jsonArtist.getJSONArray("genres");
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
        String trackName = _jsonTrack.getString("name");
        String trackID = _jsonTrack.getString("id");
        String trackType = _jsonTrack.getString("type");
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
        String albumName = _jsonAlbum.getString("name");
        String albumID = _jsonAlbum.getString("id");
        String albumType = _jsonAlbum.getString("type");
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
