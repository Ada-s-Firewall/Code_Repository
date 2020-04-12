package Objects;

/**
 * This class handles New Ratings to be added to the datastore.
 * Last Updated: 4/12/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class NewRating extends NewDBObject{

    //================= CLASS VARIABLES =================

    private String username;
    private double usersRating;
    private String spotifyID;
    private String musicObjectType;

    //================= CONSTRUCTORS =================

    public NewRating(String _username, double _usersRating, String _spotifyID, String _musicObjectType) {
        super();
        this.username = _username;
        this.usersRating = _usersRating;
        this.spotifyID = _spotifyID;
        this.musicObjectType = _musicObjectType;
    }

    //================= METHODS =================

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = super.toArrayList();
        list.add(this.username);
        list.add("" + this.usersRating);
        list.add(this.spotifyID);
        list.add(this.musicObjectType);
        return list;
    }

    @Override
    public String toString() {
        String ratingInfo = "\nUsername: " + this.username + "\nRating: " + this.usersRating
                + "\nSpotify ID: " + this.spotifyID + "\nMusic Object TYpe: " + this.musicObjectType;
        return super.toString() + ratingInfo;
    }

    //================= GETTERS =================

    public String getUsername() {
        return this.username;
    }

    public double getUsersRating() {
        return this.usersRating;
    }

    public String getSpotifyID() {
        return this.spotifyID;
    }

    public String getMusicObjectType() {
        return this.musicObjectType;
    }

    //================= SETTERS =================

    public void setUsername(String _username) {
        this.username = _username;
    }

    public void setUsersRating(double _usersRating) {
        this.usersRating = _usersRating;
    }

    public void setSpotifyID(String _spotifyID) {
        this.spotifyID = _spotifyID;
    }

    public void setMusicObjectType(String _musicObjectType) {
        this.musicObjectType = _musicObjectType;
    }
}
