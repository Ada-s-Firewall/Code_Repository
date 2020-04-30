package Tests;

/**
 * This class performs various Music API tests using the MusicAPIAdapter class and Music Objects.
 * NOTE: You must provide your own Spotify Developer credentials as indicated in the
 * API.SpotifyAPITranslator class.
 * Last Updated: 4/30/2020
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
        // Create a new MusicAPIAdapter object.
        MusicAPIAdapter adapter = new MusicAPIAdapter();
        // Keywords used in searches.
        String metallica = "metallica";
        String michaelJackson = "michael jackson";
        // Test the search method by searching for 'metallica'.
        MusicObjectList list01 = adapter.search(metallica, "artist", 5);
        MusicObjectList list02 = adapter.search(metallica, "album, track", 10);
        MusicObjectList list03 = adapter.search(metallica, "artist, track, album", 10);
        MusicObjectList list04 = adapter.search(metallica, "track", 12);
        MusicAPITests.checkForKeywordPresence(list01, metallica, 1);
        MusicAPITests.checkForKeywordPresence(list02, metallica, 15);
        MusicAPITests.checkForKeywordPresence(list03, metallica, 20);
        MusicAPITests.checkForKeywordPresence(list04, metallica, 10);
        // Test the search method by searching for 'michael jackson'.
        MusicObjectList list05 = adapter.search(michaelJackson, "artist", 3);
        MusicObjectList list06 = adapter.search(michaelJackson, "artist, track, album", 10);
        MusicObjectList list07 = adapter.search(michaelJackson, "track", 30);
        MusicAPITests.checkForKeywordPresence(list05, michaelJackson, 1);
        MusicAPITests.checkForKeywordPresence(list06, michaelJackson, 25);
        MusicAPITests.checkForKeywordPresence(list07, michaelJackson, 25);
        // Test the load-MusicObject-By-ID methods.
        MusicAPITests.testloadMusicObjectByID(list03);
        MusicAPITests.testloadMusicObjectByID(list06);
    }

    /**
     * Checks if the MusicObjectList has at least the minimum number of expected occurrences of a keyword.
     * @param _list : The MusicObjectList.
     * @param _keyword : The keyword to look for.
     * @param _expectedHits : The minimum number of times the keyword is expected to appear.
     */
    private static void checkForKeywordPresence(MusicObjectList _list, String _keyword, int _minExpectedHits) {
        int actualHits = 0;
        // Check if the keyword is present in each MusicObject in the list.
        for (MusicObject object : _list.toArrayList()) {
            boolean keywordPresent = MusicAPITests.isKeywordPresent(object, _keyword);
            // If the keyword is present, increment actualHits by one.
            if (keywordPresent == true) {
                actualHits++;
            }
        }
        // Print the results.
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
        // Check if the keyword is in the ArtistObject.
        if (_object.getType().equals(MusicAPIInterface.ARTIST)) {
            ArtistObject artist = (ArtistObject)_object;
            // Check if the keyword is in the artist name, return false otherwise.
            return artist.getName().toLowerCase().contains(_keyword);
        }
        // Check if the keyword is in the AlbumObject.
        if (_object.getType().equals(MusicAPIInterface.ALBUM)) {
            AlbumObject album = (AlbumObject)_object;
            // Check if the keyword is in the album name.
            if (album.getName().toLowerCase().contains(_keyword)) {
                return true;
            }
            // Check if the keyword is in the artist string, return false otherwise.
            return album.getArtist().toLowerCase().contains(_keyword);
        }
        // Check if the keyword is in the TrackObject.
        if (_object.getType().equals(MusicAPIInterface.TRACK)) {
            TrackObject track = (TrackObject)_object;
            // Check if the keyword is in the track name.
            if (track.getName().toLowerCase().contains(_keyword)) {
                return true;
            }
            // Check if the keyword is in the artist string.
            if (track.getArtist().toLowerCase().contains(_keyword)) {
                return true;
            }
            // Check if the keyword is in the album string, return false otherwise.
            return track.getAlbum().toLowerCase().contains(_keyword);
        }
        // Return false otherwise.
        return false;
    }

    /**
     * Test the "Load Music Object By ID" methods on every object in the given MusicObjectList.
     * @param _list
     */
    private static void testloadMusicObjectByID(MusicObjectList _list) {
        // Print introduction.
        System.out.println("\n======================================================");
        System.out.println("\nLoad-MusicObject-By-ID Tests:");
        System.out.println("\nName of Original Object | Name of Loaded Object | PASS/FAIL\n");
        // Print and load names of MusicObjects.
        MusicAPIAdapter adapter = new MusicAPIAdapter();
        for (MusicObject obj : _list.toArrayList()) {
            String originalName = obj.getName();
            System.out.print(originalName + " | ");
            // The object is an artist.
            if (obj.getType().equals(MusicAPIInterface.ARTIST)) {
                ArtistObject artist = adapter.loadArtistById(obj.getId());
                String loadedName = artist.getName();
                System.out.print(loadedName);
                // Check if the names are the same.
                MusicAPITests.areNamesTheSame(originalName, loadedName);
            }
            // The object is an album.
            if (obj.getType().equals(MusicAPIInterface.ALBUM)) {
                AlbumObject album = adapter.loadAlbumById(obj.getId());
                String loadedName = album.getName();
                System.out.print(loadedName);
                // Check if the names are the same.
                MusicAPITests.areNamesTheSame(originalName, loadedName);
            }
            // The object is a track.
            if (obj.getType().equals(MusicAPIInterface.TRACK)) {
                TrackObject track = adapter.loadTrackById(obj.getId());
                String loadedName = track.getName();
                System.out.print(loadedName);
                // Check if the names are the same.
                MusicAPITests.areNamesTheSame(originalName, loadedName);
            }
            System.out.println();
        }
    }

    /**
     * Prints 'PASS' if the names are the same or 'FAIL' if not.
     * @param _name1
     * @param _name2
     */
    private static void areNamesTheSame(String _name1, String _name2) {
        if (_name1.equals(_name2)) {
            System.out.print(" | *** PASS ***");
        } else {
            System.out.print(" | *** FAIL ***");
        }
    }
}
