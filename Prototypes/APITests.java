package Prototypes;

/**
 * The sole purpose of this class is to directly test the API classes in the package API.
 * Last Updated: 2/29/2020
 * @author Fernando Villarreal
 */

import API.SpotifyAPITranslator;
import Models.MusicObject;
import java.util.ArrayList;

public class APITests {

    /**
     * Main method for running tests on the API classes
     * @param args
     */
    public static void main(String[] args){
        System.out.println("SpotifyAPITranslator Tests:\n");
        SpotifyAPITranslator translator = new SpotifyAPITranslator("PTg7ZDY5HJZjYjUiNDczxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx=");
        ArrayList<MusicObject> searchResults = translator.search("stone", "artist", 10);
        for (MusicObject obj : searchResults) {
            System.out.println("\n" + obj.toString());
        }
    }

}
