package Models;

/*
* Model for the user's full music list.
*
* @author: Will Higdon
* Last Updated: March 31, 2020
*/

import Objects.AlbumObject;
import Objects.ArtistObject;
import Objects.TrackObject;
import Objects.MusicObject;
import Objects.MusicObjectList;
import java.util.ArrayList;

public class UserMusicList {
    /*
    * Variables for the user's music list.
    */
    private int usersAlbumsCompleted;
    private int usersAlbumsPlanToListen;
    ArrayList<String> usersFavoriteAlbums = new ArrayList<String>();
    ArrayList<String> usersFavoriteArtists = new ArrayList<String>();
    ArrayList<String> usersFavoriteTracks = new ArrayList<String>();
    
}
