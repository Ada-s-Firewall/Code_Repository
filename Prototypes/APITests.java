package Prototypes;

/**
 * The sole purpose of this class is to directly test the API classes in the package API.
 * Last Updated: 3/23/2020
 * @author Fernando Villarreal
 */

import Objects.MusicObjectList;
import Objects.MusicObject;
import API.MusicAPIAdapter;
import API.MusicAPIVariables;
import Models.*;
import java.util.ArrayList;

public class APITests {

    /**
     * Main method for running tests on the API classes
     * @param args
     */
    public static void main(String[] args){
        // Create a new MusicAPIAdapter
        System.out.println("MusicAPIAdapter and SpotifyAPITranslator Tests:\n");
        MusicAPIAdapter adapter = new MusicAPIAdapter();
        // Use the adapter to perform a search
        String types = "";
        types += MusicAPIVariables.ALL_MUSIC_OBJECT_TYPES;
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

}
