package Objects;

/**
 * This is an object for the user's rating of a particular album or song.
 *
 * @authors: Quinn Tjin-A-Soe, Will Higdon
 * Last Updated: April 6, 2020
 */

import java.util.UUID;

public class RatingObject extends RecordObject {
    /*
    * Variables for the user's rating.
    */
    private double usersRating;
    private String spotifyID;
    protected String uuid;

    //=================  CONSTRUCTORS ===================

    public RatingObject() {
        this.setUuid(RatingObject.generateUuid());
    }
    public RatingObject(double _usersRating, String _spotifyID, String _uuid) {
        this.usersRating = usersRating;
        this.spotifyID = spotifyID;
        this.uuid = uuid;
    }

    //=================  GETTERS ========================

    public double getUsersRating() {
        return this.usersRating;
    }
    public String getSpotifyId() {
        return this.spotifyID;
    }
    public String getUUID() {
        return this.uuid;
    }

    //=================  SETTERS ========================

    public void setUsersRating(double _usersRating) {
        this.usersRating = usersRating;
    }
    public void setSpotifyID(String _spotifyID) {
        this.spotifyID = spotifyID;
    }
    public void setUUID(String _uuid) {
        this.uuid = uuid;
    }
}
