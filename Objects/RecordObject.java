package Objects;

/**
 * Last Updated 04.17.2020
 * @author Quinn Tjin-A-Soe | Modified: Fernando Villarreal
 */

import java.util.ArrayList;
import java.util.UUID;

public class RecordObject {

    //================================ CLASS VARIABLES ================================

    protected String uuid;
    protected String name;
    protected int id;
    protected boolean active = true;

    // ================================ CONSTRUCTORS ====================================

    public RecordObject(String _name, int _id) {
        this.uuid = RecordObject.generateUuid();
        this.name = _name;
        this.id = _id;
    }

    public RecordObject(String _uuid, String _name, int _id) {
        this.uuid = _uuid;
        this.name = _name;
        this.id = _id;
    }

    public RecordObject(String _uuid, String _name) {
        this.uuid = _uuid;
        this.name = _name;
    }

    public RecordObject() {
        this.uuid = RecordObject.generateUuid();
    }

    public RecordObject(String _uuid) {
        this.uuid = _uuid;
    }

    // ================================ METHODS ====================================

    public void makeActive() {
        this.active = true;
    }

    public void makeInactive() {
        this.active = false;
    }

    public boolean isActive() {
        return this.active;
    }

     /**
     * Returns this object's information in an ArrayList of Strings.
     * @return list
     */
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(this.uuid);
        return list;
    }

    protected static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        String objectText = "UUID: " + this.uuid + "\nActive: " + this.active;
        return objectText;
    }

    // ================================ GETTERS ====================================

    public String getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    // ================================ SETTERS ================================

    public void setUuid(String _uuid) {
        this.uuid = _uuid;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void setId(int _id) {
        this.id = _id;
    }
}
