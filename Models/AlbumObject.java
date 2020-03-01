package Models;

/**
 * This class defines an Album Object and its properties and methods.
 * Last Updated: 2/29/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class AlbumObject extends MusicObject {

    protected String artist;
    protected String year;

    //================= CONSTRUCTORS ===============

    public AlbumObject(String _name, String _id, String _type, ArrayList<String> _genres, String _artist, String _year) {
        super(_name, _id, _type, _genres);
        this.year = _year;
        this.artist = _artist;
    }

    public AlbumObject(String _name, String _id, String _type, String _genre, String _artist, String _year) {
        super(_name, _id, _type, _genre);
        this.year = _year;
        this.artist = _artist;
    }

    public AlbumObject(String _name, String _id, String _type, String[] _genres, String _artist, String _year) {
        super(_name, _id, _type, _genres);
        this.year = _year;
        this.artist = _artist;
    }

    //================= METHODS ===============

    @Override
    public String toString() {
        String albumInfo = super.toString() + "\nArtist: " + this.artist + "\nYear: " + this.year;
        return albumInfo;
    }

    //================= GETTERS ===============

    /**
     * Return the artist of the album.
     * @return this.artist
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Return the year on which the album was released.
     * @return this.year
     */
    public String getYear() {
        return this.year;
    }
}
