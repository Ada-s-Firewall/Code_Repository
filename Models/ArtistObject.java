package Models;

/**
 * This class defines an Artist Object and its properties and methods.
 * Last Updated: 2/28/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class ArtistObject extends MusicObject {

    protected String url;

    //================= CONSTRUCTORS ===============

    public ArtistObject(String _name, String _id, ArrayList<String> _genres, String _url) {
        super(_name, _id, _genres);
        this.url = _url;
    }

    public ArtistObject(String _name, String _id, String _genre, String _url) {
        super(_name, _id, _genre);
        this.url = _url;
    }

    //================= GETTERS ===============

    /**
     * Return a URL associated with the Artist.
     * @return url
     */
    public String getURL() {
        return this.url;
    }

    @Override
    public String toString() {
        String artistInfo = super.toString() + "\nURL: " + this.url;
        return artistInfo;
    }

}
