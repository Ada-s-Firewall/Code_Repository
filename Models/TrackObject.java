package Models;

/**
 * This class defines a Track (or song) Object and its properties and methods.
 * Last Updated: 2/28/2020
 * @author Fernando
 */

import java.util.ArrayList;

public class TrackObject extends MusicObject {

    protected String artist;
    protected String album;
    protected int year;

    //================= CONSTRUCTORS ===============

    public TrackObject(String _name, String _id, ArrayList<String> _genres, String _artist, String _album, int _year) {
        super(_name, _id, _genres);
        this.artist = _artist;
        this.album = _album;
        this.year = _year;
    }

    public TrackObject(String _name, String _id, String _genre, String _artist, String _album, int _year) {
        super(_name, _id, _genre);
        this.artist = _artist;
        this.album = _album;
        this.year = _year;
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
    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        String trackInfo = super.toString() + "\nArtist: " + this.artist + "\nAlbum: " + this.album + "\nYear: " + this.year;
        return trackInfo;
    }

}
