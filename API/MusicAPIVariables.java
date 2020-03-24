package API;

/**
 * This class contains a variety of static variables to use with any Music API class.
 * Last Updated: 3/24/2020
 * @author Fernando Villarreal
 */

public class MusicAPIVariables {

    // Types of Music Objects
    public static final String ARTIST = "artist";
    public static final String ALBUM = "album";
    public static final String TRACK = "track";
    public static final String ALL_MUSIC_OBJECT_TYPES = ARTIST + "," + ALBUM + "," + TRACK;

    // Base MusicObject Variables
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String GENRES = "genres";
}
