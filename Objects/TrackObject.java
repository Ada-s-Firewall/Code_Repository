package Objects;

/**
 * This class defines a Track (or song) Object and its properties and methods.
 * Last Updated: 2/29/2020
 * @author Fernando
 */

import Objects.MusicObject;
import java.util.ArrayList;

public class TrackObject extends MusicObject {

    protected String artist;
    protected String album;
    protected String year;

    //================= CONSTRUCTORS ===============

    public TrackObject(String _name, String _id, String _type, ArrayList<String> _genres, String _artist, String _album, String _year) {
        super(_name, _id, _type, _genres);
        this.artist = _artist;
        this.album = _album;
        this.year = _year;
    }

    public TrackObject(String _name, String _id, String _type, String _genre, String _artist, String _album, String _year) {
        super(_name, _id, _type, _genre);
        this.artist = _artist;
        this.album = _album;
        this.year = _year;
    }

    public TrackObject(String _name, String _id, String _type, String[] _genres, String _artist, String _album, String _year) {
        super(_name, _id, _type, _genres);
        this.artist = _artist;
        this.album = _album;
        this.year = _year;
    }

    //================= METHODS ===============

    @Override
    public String toString() {
        String trackInfo = super.toString() + "\nArtist: " + this.artist + "\nAlbum: " + this.album + "\nYear: " + this.year;
        return trackInfo;
    }

    //================= GETTERS ===============

    /**
     * Return the artist that performed the track.
     * @return this.artist
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Return the album in which the track appears.
     * @return this.album
     */
    public String getAlbum() {
        return this.album;
    }

    /**
     * Return the year on which the track was released.
     * @return this.year
     */
    public String getYear() {
        return this.year;
    }
}
