package Objects;

/**
 * This class stores lists of RecordObjects.
 * Last Updated: 4/11/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class RecordObjectList {

    //================ CLASS VARIABLES ================

    private ArrayList<RecordObject> list;
    protected String name;
    protected String type;
    protected int length;

    //================ CONSTRUCTORS ================

    public RecordObjectList(String _name, String _type) {
        this.name = _name;
        this.type = _type;
        this.list = new ArrayList<>();
        this.length = this.list.size();
    }

    public RecordObjectList(String _name, String _type, ArrayList<RecordObject> _list) {
        this.name = _name;
        this.type = _type;
        this.list = _list;
        this.length = this.list.size();
    }

    //================ METHODS ================

    /**
     * Returns an ArrayList containing the RecordObjects in this RecordObjectList.
     * @return ArrayList<RecordObject>
     */
    public ArrayList<RecordObject> toArrayList() {
        return this.list;
    }

    /**
     * Adds the given RecordObject to the list.
     * @param _recordObject
     */
    public void add(RecordObject _recordObject) {
        this.list.add(_recordObject);
        this.length = this.list.size();
    }

    /**
     * Removes the RecordObject at the specified index.
     * @param _index
     */
    public void remove(int _index) {
        this.list.remove(_index - 1);
        this.length = this.list.size();
    }

    /**
     * Get the RecordObject at the specified index.
     * @param _index
     * @return RecordObject
     */
    public RecordObject get(int _index) {
        return this.list.get(_index - 1);
    }

    @Override
    public String toString() {
        String listText = "List Name: " + this.name + "\nList Type: " + this.type + "\nNumber of Items: " + this.length;
        listText += "\n\nLIST:\n===========================\n" ;
        for (RecordObject obj : this.list) {
            listText += "\n" + obj.toString() + "\n";
        }
        listText += "\n===========================\nEND OF LIST";
        return listText;
    }

    //================ GETTERS ================

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getLength() {
        return this.length;
    }

    //================ SETTERS ================

    public void setName(String _name) {
        this.name = _name;
    }

    public void setType(String _type) {
        this.type = _type;
    }

    public void setList(ArrayList<RecordObject> _list) {
        this.list = _list;
        this.length = this.list.size();
    }
}
