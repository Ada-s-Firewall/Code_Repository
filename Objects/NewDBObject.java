package Objects;

/**
 * This class...
 * Last Updated: 4/8/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;
import java.util.UUID;

public class NewDBObject {

    //=============== CLASS VARIABLES ===============

    private final String id;

    //=============== CONSTRUCTORS ===============

    public NewDBObject() {
        this.id = UUID.randomUUID().toString();
    }

    //=============== METHODS ===============

    /**
     * Returns this object's information in an ArrayList of Strings.
     * @return list
     */
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(this.id);
        return list;
    }

    @Override
    public String toString() {
        return "NewDBObject:" + "\nID: " + this.id;
    }

    //=============== GETTERS ===============

    /**
     * Get the the ID of this object.
     * @return id
     */
    public String getID() {
        return this.id;
    }

}
