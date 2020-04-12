package Objects;

/**
 * This is an object for the user's rating of a particular album or song.
 *
 * @authors: Quinn Tjin-A-Soe, Will Higdon | Modified: Fernando Villarreal
 * Last Updated: April 12, 2020
 */

public class RatingObject extends RecordObject {
    /*
    * Variables for the user's rating.
    */
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

    //================= METHODS =================

    @Override
    public String toString() {
        String ratingInfo = super.toString() + "\nRating: " + this.usersRating + "\nSpotify ID: " + this.spotifyID
                + "\nMusic Object Type: " + this.musicObjectType;
        return ratingInfo;
    }

    //=================  GETTERS ========================

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
