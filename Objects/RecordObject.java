package Objects;

import java.util.UUID;

/**
 * Last updated 04.11.2020
 * @author Quinn Tjin-A-Soe | Modified: Fernando Villarreal
 */
public class RecordObject {

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

// ================================ METHODS ====================================

    public void makeActive() {
        this.active = true;
    }

    public void makeInactive() {
        this.active = false;
    }

    protected static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        String objectText = "Name: " + this.name + "\nID: " + this.id + "\nUUID: " + this.uuid + "\nActive: " + this.active;
        return objectText;
    }

// ================================ GETTERS ====================================
    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // ================================ SETTERS ================================

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
