package Models;

/**
 * This class acts as a superclass for Music Objects such as Tracks, Artists, and Albums.
 * It defines the basic properties that these objects should have such as name, ID, type, etc.
 * Last Updated: 2/29/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class MusicObject {

    protected String name;
    protected String id;
    protected String type;
    protected ArrayList<String> genres;

    //================= CONSTRUCTORS ===============

    public MusicObject(String _name, String _id, String _type, ArrayList<String> _genres) {
        this.name = _name;
        this.id = _id;
        this.type = _type;
        this.genres = _genres;
    }

    public MusicObject(String _name, String _id, String _type, String _genre) {
        this.name = _name;
        this.id = _id;
        this.type = _type;
        this.genres = new ArrayList<>();
        this.genres.add(_genre);
    }

    public MusicObject(String _name, String _id, String _type, String[] _genres) {
        this.name = _name;
        this.id = _id;
        this.type = _type;
        this.genres = new ArrayList<>();
        this.setGenres(_genres);
    }

    //================= METHODS ===============

    /**
     * Adds the given genre to the list of genres.
     * @param _genre : The genre to be added to the list.
     */
    public void addGenre(String _genre) {
        this.genres.add(_genre);
    }

    @Override
    public String toString() {
        String objectInfo = "Name: " + this.name + "\nID: " + this.id + "\nType: " + this.type + "\nGenres: " + this.genres.toString();
        return objectInfo;
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
     * Return the type of the music object.
     * @return this.type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Return an ArrayList of Strings of the genres associated with the music object.
     * @return genres
     */
    public ArrayList<String> getGenres() {
        return this.genres;
    }

    //================= SETTERS ===============

    /**
     * Sets the genres of the music object to the given list of genres. Empties the list before doing this.
     * @param _genres : The new list of genres.
     */
    public void setGenres(ArrayList<String> _genres) {
        this.genres.clear();
        this.genres = _genres;
    }

    /**
     * Sets the genres of the music object to the given list of genres. Empties the list before doing this.
     * @param _genres : The new list of genres.
     */
    public void setGenres(String[] _genres) {
        this.genres.clear();
        for (String genre : _genres) {
            this.addGenre(genre);
        }
    }
}
