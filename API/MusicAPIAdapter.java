package API;

/**
 * This API Adapter class uses a given API Translator to make requests to a particular API.
 * Last Updated: 3/24/2020
 * @author Fernando Villarreal
 */

import Objects.MusicObjectList;
import Objects.ArtistObject;
import Objects.TrackObject;
import Objects.AlbumObject;

public class MusicAPIAdapter implements MusicAPIInterface {

    protected final MusicAPIInterface apiTranslator = new SpotifyAPITranslator("Yzc5MDA0Y2FmMmEyNDk5Nzg1ZGUxZWE4NmJmMDRlODM6ZDRjNDhmZGNlYzI1NDJjYmFmYmU5ZGFlN2FiNTVkY2I=");

    @Override
    public MusicObjectList search(String _keyword, String _type, int _limit) {
        return this.apiTranslator.search(_keyword, _type, _limit);
    }

    @Override
    public ArtistObject loadArtistById(String _id) {
        return this.apiTranslator.loadArtistById(_id);
    }

    @Override
    public AlbumObject loadAlbumById(String _id) {
        return this.apiTranslator.loadAlbumById(_id);
    }

    @Override
    public TrackObject loadTrackById(String _id) {
        return this.apiTranslator.loadTrackById(_id);
    }

    @Override
    public void loadAlbumTracks(AlbumObject _album) {
        this.apiTranslator.loadAlbumTracks(_album);
    }

}
