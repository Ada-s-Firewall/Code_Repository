package Objects;

/**
 * This class defines an Album Object and its properties and methods.
 * Last Updated: 3/13/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class AlbumObject extends MusicObject {

    protected String artist;
    protected String year;
    protected MusicObjectList tracks;

    //================= CONSTRUCTORS ===============

    public AlbumObject(String _name, String _id, String _type, ArrayList<String> _genres, String _artist, String _year) {
        super(_name, _id, _type, _genres);
        this.year = _year;
        this.artist = _artist;
        tracks = new MusicObjectList(this.name, "album tracks");
    }

    public AlbumObject(String _name, String _id, String _type, String _genre, String _artist, String _year) {
        super(_name, _id, _type, _genre);
        this.year = _year;
        this.artist = _artist;
        tracks = new MusicObjectList(this.name, "album tracks");
    }

    public AlbumObject(String _name, String _id, String _type, String[] _genres, String _artist, String _year) {
        super(_name, _id, _type, _genres);
        this.year = _year;
        this.artist = _artist;
        tracks = new MusicObjectList(this.name, "album tracks");
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

    //================= SETTERS ===============

    /**
     * Sets the tracks for the album.
     * @param _tracks
     */
    public void setTracks(MusicObjectList _tracks) {
        this.tracks = _tracks;
    }

    /**
     * Sets the tracks for the album.
     * @param _tracks
     */
    public void setTracks(ArrayList<MusicObject> _tracks) {
        this.tracks.setList(_tracks);
    }
}
