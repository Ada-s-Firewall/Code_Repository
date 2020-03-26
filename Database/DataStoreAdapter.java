package Database;
/**
 *  The DataStore class acts as a intermediary between the system and the
 *  specific persistent data base type.
 *  @author ike
 */

import Database.DataFactory;
import Database.DBConnectorInterface;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataStoreAdapter {
    //in place of textFileConnector it was MySQLConnector, need to create TextFileConnector
    private static final DBConnectorInterface connector = new MySQLConnector();

    /**
     * Given a DataObject, this method translates that DataObject into primitive
     * name-value pairs that can be used by a DBConnectorInterface class.
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    public static Boolean createObject(DataObject obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        // Send name-value pairs to the connector class. This class should
        // return a generated id number.
        int id = connector.createObject(obj.getProperties(), obj.getDataTable());
        // Associate this new id number with the object that was just saved.
        obj.setId(id);
        // Return the true if we have an id number. false otherwise.
        return (id != 0);
    }


    /**
     * Given a map of conditional statements, this method returns a single generic
     * DataObject instance with properties set to the returned data.
     * @param _map : The conditional name-value pairs (ie. name='John');
     * @param _table
     * @param _class
     * @return DataObject
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchMethodException
     */
    public static DataObject readObect(Map <String, String> _map, String _table, String _class) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // The Connector object returns a Hashmap of name-value pairs.
        HashMap<String, Object> results = connector.readObject(_map, _table);
        if (results == null) {
            return null;
        }
        try {
            // Here we create an object (cast as a generic DataObject) and return it.
            return (DataObject)DataFactory.objectFactory(_class, results);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DataStoreAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    /**
     * Updates an object already recorded in the database with new property values.
     * @param obj
     * @return Boolean
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws java.lang.NoSuchFieldException
     */
    public static Boolean updateObject(DataObject obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        return connector.updateObject(obj.getProperties(), obj.getUuid(), obj.getDataTable());
    }


    /**
     * Deletes an object from the database.
     * Since we never want to actually delete records, we'll need to add an "active"
     * property to the base DataObject class. This implementation will then set
     * the active value to 0.
     * @param obj
     * @return Boolean
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws java.lang.NoSuchFieldException
     */
    public static Boolean deleteObject(DataObject obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        obj.makeInactive();
        return DataStoreAdapter.updateObject(obj);
    }

}
