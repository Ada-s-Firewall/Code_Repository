package API;

/**
 * This API Adapter class uses a given API Translator to make requests to a particular API.
 * Last Updated: 2/28/2020
 * @author Fernando Villarreal
 */

import Models.AlbumObject;
import Models.ArtistObject;
import Models.MusicObject;
import Models.TrackObject;
import java.util.ArrayList;

public class MusicAPIAdapter implements MusicAPIInterface {

    protected final static MusicAPIInterface apiTranslator = new SpotifyAPITranslator("PTg7ZiNDczxxxxxxxxxxx=");

    @Override
    public ArrayList<MusicObject> search(String _keyword, String _type) {
        return MusicAPIAdapter.apiTranslator.search(_keyword, _type);
    }

    @Override
    public ArtistObject loadArtistById(String _id) {
        return MusicAPIAdapter.apiTranslator.loadArtistById(_id);
    }

    @Override
    public AlbumObject loadAlbumById(String _id) {
        return MusicAPIAdapter.apiTranslator.loadAlbumById(_id);
    }

    @Override
    public TrackObject loadTrackById(String _id) {
        return MusicAPIAdapter.apiTranslator.loadTrackById(_id);
    }

}
