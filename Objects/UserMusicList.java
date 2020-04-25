package Objects;

/*
* Object for the user's full music list.
*
* @author: Will Higdon
* Last Updated: April 25, 2020
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
    private String userName;
    ArrayList<String> usersAlbumsCompleted;
    ArrayList<String> usersAlbumsPlanToListen;
    ArrayList<String> usersFavoriteAlbums;
    ArrayList<String> usersFavoriteArtists;
    ArrayList<String> usersFavoriteTracks;

    //=================  CONSTRUCTORS =================

    public UserMusicList(String _userName, ArrayList<String> _usersAlbumsCompleted, ArrayList<String> _usersAlbumsPlanToListen, ArrayList<String> _usersFavoriteAlbums, ArrayList<String> _usersFavoriteArtists, ArrayList<String> _usersFavoriteTracks) {
        this.userName = userName;
        this.usersAlbumsCompleted = usersAlbumsCompleted;
        this.usersAlbumsPlanToListen = usersAlbumsPlanToListen;
        this.usersFavoriteAlbums = usersFavoriteAlbums;
        this.usersFavoriteArtists = usersFavoriteArtists;
        this.usersFavoriteTracks = usersFavoriteTracks;
    }

    //=================  GETTERS ==================

    public String getUserName() {
        return this.userName;
    }
    public ArrayList<String> getUsersAlbumsCompleted() {
        return this.usersAlbumsCompleted;
    }
    public ArrayList<String> getUsersAlbumsPlanToListen() {
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

    public void setUserName(String _userName) {
        this.userName = userName;
    }
    public void setUsersAlbumsCompleted(ArrayList<String> _usersAlbumsCompleted) {
        this.usersAlbumsCompleted = usersAlbumsCompleted;
    }
    public void setUsersAlbumsPlanToListen(ArrayList<String> _usersAlbumsPlanToListen) {
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
