package Prototypes;

/**
 * This class serves as a brief demo that showcases how to connect to the Spotify API and performs a basic search.
 * NOTE 1: You must have cURL 7.68.0 installed on your machine to run this demo.
 * NOTE 2: You must provide your own Spotify Developer Client Credentials encoded in base64 to request authorization.
 * Last Updated: 2/15/2020
 * @author Fernando Villarreal
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.json.*;
import java.lang.ProcessBuilder;
import java.lang.Process;

public class SpotifyAPIDemo {

    /**
     * Main Method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Spotify API Tests: \n");
        SpotifyAPIDemo.spotifyAPIDemo();
    }

    /**
     * A demo that performs a basic search for an artist on the Spotify API.
     * @throws Exception
     */
    public static void spotifyAPIDemo () throws Exception {
        // Request Authorization to the Spotify API
        String encodedClientCredentials = "PTg7ZDY5HJZjYjUiNDczxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx=";
        String accessToken = SpotifyAPIDemo.requestAuthorization(encodedClientCredentials);
        // Use the access token to search for an artist
        String keyword = "metallica";
        String type = "artist";
        JSONObject results = SpotifyAPIDemo.basicSearch(accessToken, keyword, type);
        // Print the JSONObject representation of the results
        int indentationFactor = 1;
        String jsonText = results.toString(indentationFactor);
        System.out.println(jsonText);
    }

    /**
     * Requests Authorization to the Spotify API with the provided client credentials.
     * @param _encodedClientCredentials: Base64 encoded client credentials.
     * @return accessToken: The access token is used to make other requests to the Spotify API.
     * @throws Exception
     */
    public static String requestAuthorization(String _encodedClientCredentials) throws Exception {
        // Create cURL Command to Request Authorization
        String baseURL = "https://accounts.spotify.com";
        String endpoint = "/api/token";
        String urlString = baseURL + endpoint;
        String prefixCurlString = "curl -X \"POST\" -H \"Authorization: Basic";
        String suffixCurlString = "\" -d grant_type=client_credentials";
        String curlCommand = prefixCurlString + " " + _encodedClientCredentials + suffixCurlString + " " + urlString;
        // Perform a Process with the curlCommand
        JSONObject obj = SpotifyAPIDemo.performProcess(curlCommand);
        // Get the Acccess Token
        String accessToken = obj.getString("access_token");
        return accessToken;
    }

    /**
     * Performs a basic search on the Spotify API using a keyword and a type.
     * @param _accessToken: The access token to be used to access the Spotify API.
     * @param _keyword: The query keyword to be used for the search.
     * @param _type: The types of items to search for: can be either artist, album, playlist, or track.
     * @return
     * @throws Exception
     */
    public static JSONObject basicSearch(String _accessToken, String _keyword, String _type) throws Exception {
        // Clean _keyword and _type for the URL
        _keyword = _keyword.replace(" ", "%20").replace(",", "%2C").trim();
        _type = _type.replace(" ", "").replace(",", "%2C").trim();
        // Use the access token to create a cURL search command
        String prefixCurlString = "curl -X \"GET\"";
        String urlString = "\"https://api.spotify.com/v1/search";
        String query = "?q=" + _keyword + "&type=" + _type + "\"";
        String suffixCurlString = "-H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer";
        String curlCommand = prefixCurlString + " " + urlString + query + " " + suffixCurlString + " " + _accessToken;
        // Perform a process with the cURL command
        JSONObject results = SpotifyAPIDemo.performProcess(curlCommand);
        // Return the results
        return results;
    }

    /**
     * Performs a Process with the given cURL Command
     * @param _curlCommand: The command to be performed.
     * @return obj: A JSONObject representation of the InputStream of the Process.
     * @throws Exception
     */
    public static JSONObject performProcess(String _curlCommand) throws Exception {
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
}
