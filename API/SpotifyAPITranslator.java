package API;

/**
 * This API Translator class makes API requests to the Spotify API.
 * NOTE 1: You must provide your own Spotify Developer Credentials encoded in Base64 in order to use this Translator.
 * NOTE 2: This class requires cURL 7.68.0 to be installed on the machine to make requests to the Spotify API.
 * Last Updated: 2/28/2020
 * @author Fernando Villarreal
 */

import Models.AlbumObject;
import Models.ArtistObject;
import Models.MusicObject;
import Models.TrackObject;
import java.util.ArrayList;

public class SpotifyAPITranslator implements MusicAPIInterface {

    private final String developerCredentials;

    public SpotifyAPITranslator(String _encodedDeveloperCredentials) {
        this.developerCredentials = _encodedDeveloperCredentials;
    }

    @Override
    public ArrayList<MusicObject> search(String _keyword, String _type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArtistObject loadArtistById(String _id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AlbumObject loadAlbumById(String _id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TrackObject loadTrackById(String _id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
