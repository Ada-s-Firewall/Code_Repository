package Prototypes;

/**
 * The sole purpose of this class is to directly test the API classes in the package API.
 * Last Updated: 3/10/2020
 * @author Fernando Villarreal
 */

import API.SpotifyAPITranslator;
import API.MusicAPIAdapter;
import Models.MusicObject;
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
        ArrayList<MusicObject> searchResults = adapter.search("stone", "artist, album, track", 5);
        // Print the searchResults
        for (MusicObject obj : searchResults) {
            System.out.println("\n" + obj.toString());
        }
        // Set the genres for the first music object
        String[] genres = {"rock, pop, country, everything"};
        MusicObject obj1 = searchResults.get(0);
        obj1.setGenres(genres);
        System.out.println("\nMusic Object with Genres Set:\n\n" + obj1.toString());
        // Use the ID of obj to test MusicAPIAdapter.loadArtistByID
        String objID1 = obj1.getId();
        MusicObject obj2 = adapter.loadArtistById(objID1);
        System.out.println("\nloadMusicObjectByID Tests:\n\n" + obj2.toString());
    }

}
