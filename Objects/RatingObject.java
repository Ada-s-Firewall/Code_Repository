package Objects;

import java.util.ArrayList;

/**
 * This is an object for the user's rating of a particular album or song.
 *
 * @authors: Quinn Tjin-A-Soe, Will Higdon | Modified: Fernando Villarreal
 * Last Updated: April 14, 2020
 */

public class RatingObject extends RecordObject {
    /*
    * Variables for the user's rating.
    */
    private String username;
    private double usersRating;
    private String spotifyID;
    private String musicObjectType;

    //=================  CONSTRUCTORS ===================

    public RatingObject(String _name, int _id, double _usersRating, String _spotifyID, String _musicObjectType) {
        super(_name, _id);
        this.usersRating = _usersRating;
        this.spotifyID = _spotifyID;
        this.musicObjectType = _musicObjectType;
    }

    public RatingObject(String _uuid, String _name, int _id, double _usersRating, String _spotifyID, String _musicObjectType) {
        super(_uuid, _name, _id);
        this.usersRating = _usersRating;
        this.spotifyID = _spotifyID;
        this.musicObjectType = _musicObjectType;
    }

    // The constructors below do not use the id and name variables.

    public RatingObject(String _username, double _usersRating, String _spotifyID, String _musicObjectType) {
        super();
        this.username = _username;
        this.usersRating = _usersRating;
        this.spotifyID = _spotifyID;
        this.musicObjectType = _musicObjectType;
    }

    public RatingObject(String _uuid, String _username, double _usersRating, String _spotifyID, String _musicObjectType) {
        super(_uuid);
        this.username = _username;
        this.usersRating = _usersRating;
        this.spotifyID = _spotifyID;
        this.musicObjectType = _musicObjectType;
    }

    // The constructors below do not require a usersRating value to be constructed.

    public RatingObject(String _username, String _spotifyID, String _musicObjectType) {
        super();
        this.username = _username;
        this.spotifyID = _spotifyID;
        this.musicObjectType = _musicObjectType;
    }

    public RatingObject(String _uuid, String _username, String _spotifyID, String _musicObjectType) {
        super(_uuid);
        this.username = _username;
        this.spotifyID = _spotifyID;
        this.musicObjectType = _musicObjectType;
    }

    // This constrcutors is for Null RatingObjects

    public RatingObject(String _nullValue) {
        super(_nullValue);
        this.username = _nullValue;
        this.spotifyID = _nullValue;
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
        String ratingInfo = super.toString() + "\nRating: " + this.usersRating + "\nSpotify ID: " + this.spotifyID
                + "\nMusic Object Type: " + this.musicObjectType;
        return ratingInfo;
    }

    //=================  GETTERS ========================

    public String getUsername() {
        return this.username;
    }

    public double getUsersRating() {
        return this.usersRating;
    }

    public String getSpotifyId() {
        return this.spotifyID;
    }

    public String getMusicObjectType() {
        return this.musicObjectType;
    }

    //=================  SETTERS ========================

    public void setUsername(String _username) {
        this.username = _username;
    }

    public void setUsersRating(double _usersRating) {
        this.usersRating = _usersRating;
    }

    public void setSpotifyID(String _spotifyID) {
        this.spotifyID = _spotifyID;
    }

    public void setUUID(String _uuid) {
        this.uuid = _uuid;
    }

    public void setMusicObjectTupe(String _musicObjectType) {
        this.musicObjectType = _musicObjectType;
    }
}
