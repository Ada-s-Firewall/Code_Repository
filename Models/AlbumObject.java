package Models;

/**
 * This class defines an Album Object and its properties and methods.
 * Last Updated: 2/28/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class AlbumObject extends MusicObject {

    protected String artist;
    protected int year;

    //================= CONSTRUCTORS ===============

    public AlbumObject(String _name, String _id, ArrayList<String> _genres, String _artist, int _year) {
        super(_name, _id, _genres);
        this.year = _year;
        this.artist = _artist;
    }

    public AlbumObject(String _name, String _id, String _genre, String _artist, int _year) {
        super(_name, _id, _genre);
        this.year = _year;
        this.artist = _artist;
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
    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        String albumInfo = super.toString() + "\nArtist: " + this.artist + "\nYear: " + this.year;
        return albumInfo;
    }
}
