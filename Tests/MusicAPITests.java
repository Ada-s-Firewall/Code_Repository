package Tests;

/**
 * This class performs various Music API tests using the MusicAPIAdapter class and Music Objects.
 * NOTE: You must provide your own Spotify Developer credentials as indicated in the
 * API.SpotifyAPITranslator class.
 * Last Updated: 4/14/2020
 * @author Fernando Villarreal
 */

import API.*;
import Objects.MusicObjectList;
import Objects.MusicObject;
import Objects.ArtistObject;
import Objects.AlbumObject;
import Objects.TrackObject;

public class MusicAPITests {

    //====================== PUBLIC MAIN METHOD ======================

    /**
     * Main method for running tests.
     * @param args
     */
    public static void main(String[] args) {
        MusicAPITests.testMusicAPIAdapter();
    }

    //====================== PRIVATE METHODS ======================

    /**
     * Tests for the SpotifyAPiTranslator class.
     */
    private static void testMusicAPIAdapter() {
        // Create a new MusicAPIAdapter object
        MusicAPIAdapter adapter = new MusicAPIAdapter();
        // Keywords used in searches
        String metallica = "metallica";
        String michaelJackson = "michael jackson";
        // Perform and check searches for 'metallica'
        MusicObjectList list01 = adapter.search(metallica, "artist", 5);
        MusicObjectList list02 = adapter.search(metallica, "album, track", 10);
        MusicObjectList list03 = adapter.search(metallica, "artist, track, album", 10);
        MusicObjectList list04 = adapter.search(metallica, "track", 12);
        MusicAPITests.checkSearchResults(list01, metallica, 1);
        MusicAPITests.checkSearchResults(list02, metallica, 15);
        MusicAPITests.checkSearchResults(list03, metallica, 20);
        MusicAPITests.checkSearchResults(list04, metallica, 10);
        // Perform and check searches for 'michael jackson'
        MusicObjectList list05 = adapter.search(michaelJackson, "artist", 3);
        MusicObjectList list06 = adapter.search(michaelJackson, "artist, track, album", 15);
        MusicObjectList list07 = adapter.search(michaelJackson, "track", 30);
        MusicAPITests.checkSearchResults(list05, michaelJackson, 1);
        MusicAPITests.checkSearchResults(list06, michaelJackson, 10);
        MusicAPITests.checkSearchResults(list07, michaelJackson, 25);
    }

    /**
     * Checks if the list of search results has at least the minimum expected number of occurrences of a keyword.
     * @param _list
     * @param _keyword
     * @param _expectedHits
     */
    private static void checkSearchResults(MusicObjectList _list, String _keyword, int _minExpectedHits) {
        int actualHits = 0;
        // Check if the keyword is present in each MusicObject in the list
        for (MusicObject object : _list.toArrayList()) {
            boolean keywordPresent = MusicAPITests.isKeywordPresent(object, _keyword);
            // If the keyword is present, increment actualHits by one
            if (keywordPresent == true) {
                actualHits++;
            }
        }
        // Print the results
        System.out.println("\n=====================================================================");
        System.out.println("\nSearch for '" + _keyword + "' yielded " + actualHits + " related results:");
        System.out.println("\nActual: " + actualHits + ", Min Expected: " + _minExpectedHits);
        // If actualHits > _minExpectedHits, then PASS. Otherwise, FAIL.
        if (actualHits >= _minExpectedHits) {
            System.out.println("\n******** PASS ********");
        } else {
            System.out.println("\n******** FAIL ********");
        }
    }

    /**
     * Checks if the keyword is present in the MusicObject.
     * @param _object
     * @param _keyword
     * @return boolean
     */
    private static boolean isKeywordPresent(MusicObject _object, String _keyword) {
        // Check if the keyword is in the ArtistObject
        if (_object.getType().equals(MusicAPIInterface.ARTIST)) {
            ArtistObject artist = (ArtistObject)_object;
            // Check if the keyword is in the artist name, return false otherwise
            return artist.getName().toLowerCase().contains(_keyword);
        }
        // Check if the keyword is in the AlbumObject
        if (_object.getType().equals(MusicAPIInterface.ALBUM)) {
            AlbumObject album = (AlbumObject)_object;
            // Check if the keyword is in the album name
            if (album.getName().toLowerCase().contains(_keyword)) {
                return true;
            }
            // Check if the keyword is in the artist string, return false otherwise
            return album.getArtist().toLowerCase().contains(_keyword);
        }
        // Check if the keyword is in the TrackObject
        if (_object.getType().equals(MusicAPIInterface.TRACK)) {
            TrackObject track = (TrackObject)_object;
            // Check if the keyword is in the track name
            if (track.getName().toLowerCase().contains(_keyword)) {
                return true;
            }
            // Check if the keyword is in the artist string
            if (track.getArtist().toLowerCase().contains(_keyword)) {
                return true;
            }
            // Check if the keyword is in the album string, return false otherwise
            return track.getAlbum().toLowerCase().contains(_keyword);
        }
        // Return false otherwise
        return false;
    }
}
