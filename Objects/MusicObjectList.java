package Objects;

/**
 * This class handles list of MusicObjects.
 * Last Updated: 3/28/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class MusicObjectList {

    //================= CLASS VARIABLES =================

    private ArrayList<MusicObject> list;
    protected String name;
    protected String type;
    protected int length;

    //================= CONSTRUCTORS =================

    public MusicObjectList(String _name, String _type) {
        this.list = new ArrayList<>();
        this.name = _name;
        this.type = _type;
        this.length = this.list.size();
    }

    public MusicObjectList(String _name, String _type, ArrayList<MusicObject> _arrayList) {
        this.list = _arrayList;
        this.name = _name;
        this.type = _type;
        this.length = _arrayList.size();
    }

    //================= METHODS =================

    /**
     * Add a MusicObject to the MusicObjectList.
     * @param _item : The MusicObject to be added.
     */
    public void addItem(MusicObject _item) {
        this.list.add(_item);
        this.length = this.list.size();
    }

    /**
     * Remove a MusicObject from the MusicObjectList.
     * @param _index : The index of the MusicObject to remove; ranges from 1 to length.
     */
    public void removeItem(int _index) {
        this.list.remove(_index-1);
        this.length = this.list.size();
    }

    /**
     * Get a MusicObject from the MusicObjectList.
     * @param _index : The index of the MusicObject; ranges from 1 to length.
     * @return MusicObject
     */
    public MusicObject get(int _index) {
        return this.list.get(_index-1);
    }

    /**
     * Get a new MusicObjectList containing all the ArtistObjects from this MusicObjectList.
     * The type of a MusicObject must be "artist" for it to appear in the list.
     * @return MusicObjectList
     */
    public MusicObjectList getAllArtistObjects() {
        ArrayList<MusicObject> artistList = new ArrayList<>();
        // Iterate over the list and get all ArtistObjects
        for (MusicObject obj : this.list) {
            if (obj.getType().equals("artist")) {
                ArtistObject artist = (ArtistObject)obj;
                artistList.add(artist);
            }
        }
        String listName = "ArtistObjects in \"" + this.name + "\"";
        String listType = "List of ArtistObjects";
        return new MusicObjectList(listName, listType, artistList);
    }

    /**
     * Get a new MusicObjectList containing all the AlbumObjects from this MusicObjectList.
     * The type of a MusicObject must be "album" for it to appear in the list.
     * @return MusicObjectList
     */
    public MusicObjectList getAllAlbumObjects() {
        ArrayList<MusicObject> albumList = new ArrayList<>();
        // Iterate over the list and get all AlbumObjects
        for (MusicObject obj : this.list) {
            if (obj.getType().equals("album")) {
                AlbumObject album = (AlbumObject)obj;
                albumList.add(album);
            }
        }
        String listName = "AlbumObjects in \"" + this.name + "\"";
        String listType = "List of AlbumObjects";
        return new MusicObjectList(listName, listType, albumList);
    }

    /**
     * Get a new MusicObjectList containing all the TrackObjects from this MusicObjectList.
     * The type of a MusicObject must be "track" for it to appear in the list.
     * @return MusicObjectList
     */
    public MusicObjectList getAllTrackObjects() {
        ArrayList<MusicObject> trackList = new ArrayList<>();
        // Iterate over the list and get all TrackObjects
        for (MusicObject obj : this.list) {
            if (obj.getType().equals("track")) {
                TrackObject track = (TrackObject)obj;
                trackList.add(track);
            }
        }
        String listName = "TrackObjects in \"" + this.name + "\"";
        String listType = "List of TrackObjects";
        return new MusicObjectList(listName, listType, trackList);
    }

    /**
     * Clears the MusicObjectList.
     */
    public void clear() {
        this.list.clear();
        this.length = this.list.size();
    }

    /**
     * Get an ArrayList of the MusicObjects in the MusicObjectList.
     * @return ArrayList of MusicObjects
     */
    public ArrayList<MusicObject> toArrayList() {
        return this.list;
    }

    /**
     * Prints the MusicObjectList.
     */
    public void printList() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String listInfo = "List Name: " + this.name + "\nList Type: " + this.type + "\nNumber of Items: " + this.length;
        String beginList = "\n\nLIST:\n================================";
        String listContents = "";
        for (int index = 0; index < this.list.size(); index++) {
            MusicObject obj = this.list.get(index);
            String item = "\n\n" + "#" + (index+1) + ":\n"+ obj.toString();
            listContents += item;
        }
        String endList = "\n\n================================\nEND OF LIST";
        String listText = listInfo + beginList + listContents + endList;
        return listText;
    }

    //================= GETTERS =================

    /**
     * Get the name of the list.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the type of the list.
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the length of the list.
     * @return length
     */
    public int getLength() {
        return this.length;
    }

    //================= SETTERS =================

    /**
     * Set the name of the list.
     * @param _name
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * Set the type of the list.
     * @param _type
     */
    public void setType(String _type) {
        this.type = _type;
    }

    /**
     * Sets the MusicObjectList to the given ArrayList of MusicObjects.
     * @param _newList : An ArrayList of MusicObjects containing the new objects in the list.
     */
    public void setList(ArrayList<MusicObject> _newList) {
        this.list = _newList;
        this.length = this.list.size();
    }
}
