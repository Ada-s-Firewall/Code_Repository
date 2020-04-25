package Objects;

/**
 * This subclass of RatingObject does not use the usersRating variable. Once an
 * object of this type has been rated and it can be recorded to the Users' Ratings list.
 * Last Updated: 4/17/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class PlanToListenObject extends RatingObject {

    //================== CONSTRUCTORS ==================

    public PlanToListenObject(String _username, String _spotifyID, String _musicObjectType) {
        super(_username, _spotifyID, _musicObjectType);
    }

    public PlanToListenObject(String _uuid, String _username, String _spotifyID, String _musicObjectType) {
        super(_uuid, _username, _spotifyID, _musicObjectType);
    }

    public PlanToListenObject(String _nullValue) {
        super(_nullValue);
        this.setUsername(_nullValue);
        this.setSpotifyID(_nullValue);
    }

    //================== METHODS ==================

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(this.getUuid());
        list.add(this.getUsername());
        list.add(this.getSpotifyId());
        list.add(this.getMusicObjectType());
        return list;
    }
}
