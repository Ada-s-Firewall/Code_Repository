package Prototypes;

/**
 * The sole purpose of this class is to directly test the API classes in the package API.
 * Last Updated: 3/31/2020
 * @author Fernando Villarreal
 */

import API.MusicAPIAdapter;
import API.MusicAPIInterface;
import Models.MusicRequest;
import Objects.*;
import java.util.ArrayList;

public class APITests {

    /**
     * Main method for running tests on the API classes
     * @param args
     */
    public static void main(String[] args){
        // Perform API Test 01
        System.out.println("API TEST 01:\n");
        APITests.test01();
        // Perform API Test 02
        System.out.println("\n\nAPI TEST 02:\n");
        APITests.test02();
        // Perform API Test 03
        System.out.println("\n\nAPI TEST 03:\n");
        APITests.test03();
        // Perform API Test 04
        //System.out.println("\n\nAPI TEST 04:\n");
        //APITests.test04();
    }

    /**
     * Perform API Test 01
     */
    public static void test01() {
        // Create a new MusicAPIAdapter
        System.out.println("MusicAPIAdapter and SpotifyAPITranslator Tests:\n");
        MusicAPIAdapter adapter = new MusicAPIAdapter();
        // Use the adapter to perform a search
        String types = "";
        types += MusicAPIInterface.ALL_MUSIC_OBJECT_TYPES;
        //types += MusicAPIVariables.ARTIST;
        //types += "," + MusicAPIVariables.ALBUM;
        //types += "," + MusicAPIVariables.TRACK;
        MusicObjectList searchResults = adapter.search("stone", types, 10);
        // Print the searchResults
        searchResults.printList();
        // Set the genres for the first music object
        String[] genres = {"rock, pop, country, everything"};
        MusicObject obj1 = searchResults.get(1);
        obj1.setGenres(genres);
        System.out.println("\nMusic Object with Genres Set:\n\n" + obj1.toString());
        // Use the ID of obj to test MusicAPIAdapter.loadArtistByID
        String objID1 = obj1.getId();
        MusicObject obj2 = adapter.loadArtistById(objID1);
        System.out.println("\nloadMusicObjectByID Tests:\n\n" + obj2.toString());
    }

    /**
     * Perform API Test 02
     */
    public static void test02() {
        // Create a new MusicRequest object
        MusicRequest request = new MusicRequest();
        // Use the MusicRequest object
        MusicObjectList searchResults = request.search("led zeppelin", "artists, tracks, albums", 10);
        searchResults.printList();
    }

    /**
     * Perform API Test 03
     */
    public static void test03() {
        // Write your own tests here
    }

    /**
     * Perform API Test 04
     */
    public static void test04() {
        // Create a new MusicRequest object
        MusicRequest request = new MusicRequest();
        // Perform a search and get the first album and its tracks
        MusicObjectList searchResults = request.search("metallica");
        searchResults.printList();
        MusicObjectList albumList = searchResults.getAllAlbumObjects();
        AlbumObject album = (AlbumObject)albumList.get(1);
        System.out.println("\nTracks Loaded?: " + album.tracksLoaded() + "\n");
        album = request.loadAlbumWithTracks(album);
        System.out.println("\nTracks Loaded?: " + album.tracksLoaded() + "\n");
        album.getTracks().printList();
    }
}
