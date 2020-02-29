package Models;

/**
 * This class acts as a superclass for Music Objects such as Tracks, Artists, and Albums.
 * It defines the basic properties that these objects should have such as name, ID, etc.
 * Last Updated: 2/28/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class MusicObject {

    protected String name;
    protected String id;
    protected ArrayList<String> genres;

    //================= CONSTRUCTORS ===============

    public MusicObject(String _name, String _id, ArrayList<String> _genres) {
        this.name = _name;
        this.id = _id;
        this.genres = _genres;
    }

    public MusicObject(String _name, String _id, String _genre) {
        this.name = _name;
        this.id = _id;
        this.genres = new ArrayList<>();
        this.genres.add(_genre);
    }

    //================= GETTERS ===============

    /**
     * Return the name of the music object.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the ID of the music object.
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Return an ArrayList of Strings of the genres associated with the music object.
     * @return genres
     */
    public ArrayList<String> getGenres() {
        return this.genres;
    }

    @Override
    public String toString() {
        String objectInfo = "Object Name: " + this.name + "\nID: " + this.id + "\nGenres: " + genres.toString();
        return objectInfo;
    }
}
