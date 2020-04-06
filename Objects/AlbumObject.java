package Objects;

/**
 * This class defines an Album Object and its properties and methods.
 * Last Updated: 3/31/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class AlbumObject extends MusicObject {

    protected final String artist;
    protected final String year;
    protected final MusicObjectList tracks;

    //================= CONSTRUCTORS ===============

    public AlbumObject(String _name, String _id, String _type, ArrayList<String> _genres, String _artist, String _year, MusicObjectList _tracks) {
        super(_name, _id, _type, _genres);
        this.year = _year;
        this.artist = _artist;
        this.tracks = _tracks;
    }

    public AlbumObject(String _name, String _id, String _type, String _genre, String _artist, String _year, MusicObjectList _tracks) {
        super(_name, _id, _type, _genre);
        this.year = _year;
        this.artist = _artist;
        this.tracks = _tracks;
    }

    public AlbumObject(String _name, String _id, String _type, String[] _genres, String _artist, String _year, MusicObjectList _tracks) {
        super(_name, _id, _type, _genres);
        this.year = _year;
        this.artist = _artist;
        this.tracks = _tracks;
    }

    public AlbumObject(AlbumObject _album, MusicObjectList _tracks) {
        super(_album.name, _album.id, _album.type, _album.genres);
        this.year = _album.year;
        this.artist = _album.artist;
        this.tracks = _tracks;
    }

    //================= METHODS ===============

    /**
     * Checks if the tracks for this AlbumObject are loaded.
     * Returns true if the tracks are loaded. Returns false if the tracks are not loaded.
     * @return boolean
     */
    public boolean tracksLoaded() {
        if (this.tracks != null) {
            return true;
        }
        return false;
    }

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

    /**
     * Get a MusicObjectList containing the album's tracks.
     * @return MusicObjectList
     */
    public MusicObjectList getTracks() {
        return this.tracks;
    }

    /**
     * Get an ArrayList containing the album's tracks.
     * @return ArrayList<MusicObject>
     */
    public ArrayList<MusicObject> getTracksInArrayList() {
        return this.tracks.toArrayList();
    }
}
