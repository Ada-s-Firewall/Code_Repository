package API;

/**
 * This API Translator class makes API requests to the Spotify API.
 * NOTE 1: You must provide your own Spotify Developer Credentials encoded in Base64 in order to use this Translator.
 * NOTE 2: DO NOT leave your Developer Credentials in the MusicAPIAdapter file if you are uploading it to a public repository.
 * NOTE 3: This class requires cURL 7.68.0 to be installed on the machine to make requests to the Spotify API.
 * NOTE 4: Do not call this class directly; route all requests through the MusicAPIAdapter class.
 * Last Updated: 2/29/2020
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
            // Convert the JSON Object into an ArrayList of MusicObjects and return it
            ArrayList<MusicObject> results = this.convertSearchResults(obj, _type);
            return results;
        } catch (Exception ex) {
            Logger.getLogger(SpotifyAPITranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArtistObject loadArtistById(String _id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AlbumObject loadAlbumById(String _id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TrackObject loadTrackById(String _id) {
        throw new UnsupportedOperationException("Not supported yet.");
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
     * Given a JSONObject of the search results, return an ArrayList of the MusicObjects.
     * Helper method for the this.search method.
     * @param _searchResults
     * @param _type
     * @return
     * @throws Exception
     */
    private ArrayList<MusicObject> convertSearchResults(JSONObject _searchResults, String _type) throws Exception{
        // Get the JSONArray of items of _type
        String type = _type + "s";
        JSONObject resultsOfType = (JSONObject)_searchResults.get(type);
        JSONArray items = (JSONArray)resultsOfType.get("items");
        int itemLength = items.length();
        ArrayList<MusicObject> itemsList = new ArrayList<>();
        if (type.equals("artists")) {
            for (int index = 0; index < itemLength; index++) {
                JSONObject jsonArtist = (JSONObject)items.get(index);
                ArtistObject artist = this.convertJSONArtistObject(jsonArtist);
                itemsList.add(artist);
            }
            return itemsList;
        }
        if (type.equals("tracks")) {
            for (int index = 0; index < itemLength; index++) {
                JSONObject jsonTrack = (JSONObject)items.get(index);
                TrackObject track = this.convertJSONTrackObject(jsonTrack);
                itemsList.add(track);
            }
            return itemsList;
        }
        if (type.equals("albums")) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        // Return the list of Music Objects
        return itemsList;
    }

    /**
     * Convert a JSONArray into an ArrayList of Strings.
     * @param _jsonArray
     * @return
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

    /**
     * Given a Spotify JSON ArtistObject, convert it into an ArtistObject.
     * Helper method for the this.convertSearchResults method.
     * @param _jsonArtist
     * @return artist
     */
    private ArtistObject convertJSONArtistObject(JSONObject _jsonArtist) throws JSONException {
        String artistName = _jsonArtist.getString("name");
        String artistID = _jsonArtist.getString("id");
        String artistType = _jsonArtist.getString("type");
        JSONObject jsonUrl = (JSONObject)_jsonArtist.get("external_urls");
        String artistUrl = (String)jsonUrl.getString("spotify");
        JSONArray jsonGenres = (JSONArray)_jsonArtist.get("genres");
        ArrayList<String> artistGenres = this.convertJSONArray(jsonGenres);
        ArtistObject artist = new ArtistObject(artistName, artistID, artistType, artistGenres, artistUrl);
        return artist;
    }

    /**
     * Given a Spotify JSON TrackObject, convert it into a TrackObject.
     * @param _jsonTrack
     * @return
     * @throws JSONException
     */
    private TrackObject convertJSONTrackObject(JSONObject _jsonTrack) throws JSONException {
        String trackName = _jsonTrack.getString("name");
        String trackID = _jsonTrack.getString("id");
        String trackType = _jsonTrack.getString("type");
        // Spotify does not provide genres for tracks
        ArrayList<String> trackGenres = new ArrayList<>();
        // Get the artist
        String trackArtist = _jsonTrack.getJSONArray("artists").getJSONObject(0).getString("name");;
        // Get the album
        String trackAlbum = _jsonTrack.getJSONObject("album").getString("name");
        // Get the year
        String trackYear = _jsonTrack.getJSONObject("album").getString("release_date");
        // Create and return the TrackObject
        TrackObject track = new TrackObject(trackName, trackID, trackType, trackGenres, trackArtist, trackAlbum, trackYear);
        return track;
    }
}
