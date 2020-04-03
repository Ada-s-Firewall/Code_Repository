package Objects;

import java.util.UUID;

/**
 *
 * @author Quinn Tjin-A-Soe Last updated 04.02.2020
 */
public class RecordObject {

    protected String uuid;
    protected String name;
    protected String id;
    protected boolean active = true;
// ================================ CONSTRUCTORS ====================================

    public RecordObject(String uuid, String name, String id) {
        this.uuid = uuid;
        this.name = name;
        this.id = id;
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

// ================================ GETTERS ====================================
    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    // ================================ SETTERS ================================

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
