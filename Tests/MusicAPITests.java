package Tests;

/**
 * This class performs various tests on the Music API classes and Music Objects.
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

    /**
     * Main method for running tests.
     * @param args
     */
    public static void main(String[] args) {
        MusicAPITests.testSpotifyAPITranslator();
    }

    /**
     * Tests for the SpotifyAPiTranslator class.
     */
    private static void testSpotifyAPITranslator() {
        // Create a new translator object
        SpotifyAPITranslator translator = new SpotifyAPITranslator("OTg3ZDY5OWZjYjFjNDczMWFjNzUzYTMzYzY0YWJkZDk6NWUxNzhiMjAwNmE0NDRlMDhhNTQ4MTEzYzhhNGE3ZWQ=");
        // Search tests
        MusicObjectList list01 = translator.search("metallica", "artist", 5);
        System.out.println("List 1: " + MusicAPITests.checkSearchResults(list01, "metallica", 3));
        // CONTINUE HERE
        MusicObjectList list02 = translator.search("metallica", "artist", 1);
        translator.search("metallica", "artist, track, album", 10);
        translator.search("metallica", "track", 12);
        translator.search("metallica", MusicAPIInterface.ALL_MUSIC_OBJECT_TYPES, 15);
        translator.search("michael jackson", "artist", 7);
        translator.search("michael jackson", "artist", 1);
        translator.search("michael jackson", "artist, track, album", 9);
        translator.search("michael jackson", "track", 13);
        translator.search("michael jackson", MusicAPIInterface.ALL_MUSIC_OBJECT_TYPES, 20);
    }

    /**
     * Checks if the list of search results has the expected number of occurrences of a keyword.
     * @param _list
     * @param _keyword
     * @param _expectedHits
     * @return boolean
     */
    private static boolean checkSearchResults(MusicObjectList _list, String _keyword, int _expectedHits) {
        int maxDeviation = 5;
        int actualHits = 0;
        // Check if the keyword is present in each MusicObject in the list
        for (MusicObject object : _list.toArrayList()) {
            boolean keywordPresent = MusicAPITests.isKeywordPresent(object, _keyword);
            // If the keyword is present, increment actualHits by one
            if (keywordPresent == true) {
                actualHits++;
            }
        }
        System.out.println("Actual: " + actualHits + ", Expected: " + _expectedHits);
        return Math.abs(_expectedHits - actualHits) <= maxDeviation;
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
