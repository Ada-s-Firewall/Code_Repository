package Objects;

/**
 * This class...
 * Last Updated: 4/8/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class NewRating extends NewDBObject{

    //================= CLASS VARIABLES =================

    private String username;
    private double usersRating;
    private String spotifyID;

    //================= CONSTRUCTORS =================

    public NewRating(String _username, double _usersRating, String _spotifyID) {
        super();
        this.username = _username;
        this.usersRating = _usersRating;
        this.spotifyID = _spotifyID;
    }

    //================= METHODS =================

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = super.toArrayList();
        list.add(this.username);
        list.add("" + this.usersRating);
        list.add(this.spotifyID);
        return list;
    }

    @Override
    public String toString() {
        String ratingInfo = "\nUsername: " + this.username + "\nRating: " + this.usersRating
                + "\nSpotify ID: " + this.spotifyID;
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
}
