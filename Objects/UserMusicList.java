package Objects;

/*
* Object for the user's full music list.
*
* @author: Will Higdon
* Last Updated: April 2, 2020
*/

import Objects.AlbumObject;
import Objects.ArtistObject;
import Objects.TrackObject;
import Objects.MusicObject;
import Objects.MusicObjectList;
import Objects.RatingObject;
import Objects.RecordObject;
import Objects.UserObject;
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


    //=================  CONSTRUCTORS =================

    public UserMusicList(int _usersAlbumsCompleted, int _usersAlbumsPlanToListen, ArrayList<String> _usersFavoriteAlbums, ArrayList<String> _usersFavoriteArtists, ArrayList<String> _usersFavoriteTracks) {
        this.usersAlbumsCompleted = usersAlbumsCompleted;
        this.usersAlbumsPlanToListen = usersAlbumsPlanToListen;
        this.usersFavoriteAlbums = usersFavoriteAlbums;
        this.usersFavoriteArtists = usersFavoriteArtists;
        this.usersFavoriteTracks = usersFavoriteTracks;
    }

    //=================  GETTERS ==================

    public int getUsersAlbumsCompleted() {
        return this.usersAlbumsCompleted;
    }
    public int getUsersAlbumsPlanToListen() {
        return this.usersAlbumsPlanToListen;
    }
    public ArrayList<String> getUsersFavoriteAlbums() {
        return this.usersFavoriteAlbums;
    }
    public ArrayList<String> getUsersFavoriteArtists() {
        return this.usersFavoriteArtists;
    }
    public ArrayList<String> getUsersFavoriteTracks() {
        return this.usersFavoriteTracks;
    }

    //=================  SETTERS =================

    public void setUsersAlbumsCompleted(int _usersAlbumsCompleted) {
        this.usersAlbumsCompleted = usersAlbumsCompleted;
    }
    public void setUsersAlbumsPlanToListen(int _usersAlbumsPlanToListen) {
        this.usersAlbumsPlanToListen = usersAlbumsPlanToListen;
    }
    public void setUsersFavoriteAlbums(ArrayList<String> usersFavoriteAlbums) {
        this.usersFavoriteAlbums = usersFavoriteAlbums;
    }
    public void setUsersFavoriteArtists(ArrayList<String> usersFavoriteArtists) {
        this.usersFavoriteArtists = usersFavoriteArtists;
    }
    public void setUsersFavoriteTracks(ArrayList<String> usersFavoriteTracks) {
        this.usersFavoriteTracks = usersFavoriteTracks;
    }
}
