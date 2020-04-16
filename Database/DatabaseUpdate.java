package Database;

/**
 * This class holds all of the methods for updating a file.
 *
 * Last Updated: 04.14.2020.
 *
 * @authors Quinn Tjin-A-Soe, Will Higdon
 */

import static Database.DatabaseCreate.createUserRecord;
import static Database.DatabaseCreate.createUserRating;
import static Database.DatabaseInterface.userLoginFile;
import static Database.DatabaseInterface.userInfoFile;
import static Database.DatabaseInterface.userLoginFile;
import Objects.UserObject;
import Objects.RatingObject;
import Objects.UserMusicList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class DatabaseUpdate {

    /**
     * This method updates a user password, but it does not work yet.
     *
     * @param _file
     * @param _userObject
     * @param _newPassword
     * @throws FileNotFoundException
     */
    public void updateUserPassword(UserObject _userObject, String _newPassword) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(userLoginFile);
        String userName = _userObject.getName();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //This updates the password of the userObject.
        _userObject.setUserPassword(_newPassword);

        while (scanner.hasNext()) {
            //If this record exists in the file, then the password will be updated.
            if (scanner.hasNext(userName)) {
                stringRecord += _userObject.getUuid() + "\t";
                stringRecord += _userObject.getName() + "\t";
                stringRecord += _userObject.getUserPassword() + "\t";
                stringRecord += DatabaseInterface.active + "\n";
            }
            if (scanner.hasNext("\n")) {
                stringRecord += "\n";
            } //This else statement adds the rest of the records that do not match.
            else {
                stringRecord += scanner.next() + "\t";
            }
        }
        //This second parameter is made to be false in order to overwrite the file.
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userLoginFile, false));
        writer.write(stringRecord);
        writer.close();
    }

    /**
     * This method updates a user's username, but it does not work yet.
     *
     * @param _file
     * @param _userObject
     * @param _newUserName
     * @throws FileNotFoundException
     */
    public void updateUserName(UserObject _userObject, String _newUserName) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(userLoginFile);
        String userName = _userObject.getName();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //This updates the password of the userObject.
        _userObject.setUserName(userName);

        while (scanner.hasNext()) {
            //If this record exists in the file, then the username will be updated.
            if (scanner.hasNext(userName)) {
                stringRecord += _userObject.getUuid() + "\t";
                stringRecord += _userObject.getName() + "\t";
                stringRecord += _userObject.getUserPassword() + "\t";
                stringRecord += DatabaseInterface.active + "\n";
            }
            if (scanner.hasNext("\n")) {
                stringRecord += "\n";
            } //This else statement adds the rest of the records that do not match.
            else {
                stringRecord += scanner.nextLine() + "\n";
            }
        }
        //This second parameter is made to be false in order to overwrite the file.
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userLoginFile, false));
        writer.write(stringRecord);
        writer.close();
    }

    /**
     * This method updates a user's email
     *
     * @param _file
     * @param _userObject
     * @param _newUserEmail
     * @throws FileNotFoundException
     */
    public void updateUserEmail(UserObject _userObject, String _newUserEmail) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(userLoginFile);
        String userName = _userObject.getName();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //This updates the password of the userObject.
        _userObject.setUserEmail(_newUserEmail);

        while (scanner.hasNext()) {
            //If this record exists in the file, then the password will be updated.
            if (scanner.hasNext(userName)) {
                stringRecord += _userObject.getUuid() + "\t";
                stringRecord += _userObject.getName() + "\t";
                stringRecord += _userObject.getUserPassword() + "\t";
                stringRecord += DatabaseInterface.active + "\n";
            }
            if (scanner.hasNext("\n")) {
                stringRecord += "\n";
            } //This else statement adds the rest of the records that do not match.
            else {
                stringRecord += scanner.next() + "\t";
            }
        }
        //This second parameter is made to be false in order to overwrite the file.
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userLoginFile, false));
        writer.write(stringRecord);
        writer.close();
    }

    /**
     * This method updates a user's rating
     *
     * @param _file
     * @param _ratingObject
     * @param _newUsersRating
     * @throws FileNotFoundException
     */
    public void updateUsersRating(File _userRatingFile,RatingObject _ratingObject, double _newUsersRating) throws FileNotFoundException {
        Scanner scanner = new Scanner(_userRatingFile);
        String userName = _ratingObject.getName();
        double usersRating = _ratingObject.getUsersRating();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //This will update the user's rating
        _ratingObject.setUsersRating(_newUsersRating);

        while (scanner.hasNext()) {
            //If this record exists in the file, then the rating will be updated.
            if (scanner.hasNext(userName)) {
                stringRecord += _ratingObject.getUuid() + "\t";
                stringRecord += _ratingObject.getName() + "\t";
                stringRecord += _ratingObject.getUsersRating() + "\t";
                stringRecord += DatabaseInterface.active + "\n";
            }
            if (scanner.hasNext("\n")) {
                stringRecord += "\n";
            } //This else statement adds the rest of the records that do not match.
            else {
                stringRecord += scanner.next() + "\t";
            }
        }
        
    }

    /**
     * This method updates the user's list of albums they've completed or plan
     * to listen
     *
     * @param _file
     * @param _userMusicList
     * @param _newUsersAlbumsCompleted
     * @param _newUsersAlbumsPlanToListen
     * @throws FileNotFoundException
     */
    public void updateUsersMusicList(UserMusicList _userMusicList, int _newUsersAlbumsCompleted, int _newUsersAlbumsPlanToListen) throws FileNotFoundException {
        Scanner scanner = new Scanner(userInfoFile);
        String userName = _userMusicList.getUserName();
        int usersAlbumsCompleted = _userMusicList.getUsersAlbumsCompleted();
        int usersAlbumsPlanToListen = _userMusicList.getUsersAlbumsPlanToListen();

        //Updates the users number of albums completed in their music list
        _userMusicList.setUsersAlbumsCompleted(_newUsersAlbumsCompleted);
        //Updates the user's number of albums that they plan to listen in their music list
        _userMusicList.setUsersAlbumsPlanToListen(_newUsersAlbumsPlanToListen);
    }

    /**
     * This method updates a user's list of favorite albums, arists, and tracks
     *
     * @param _file
     * @param _userMusicList
     * @param _newUsersFavoriteAlbums
     * @param _newUsersFavoriteArtists
     * @param _newUsersFavoriteTracks
     * @throws FileNotFoundException
     */
    public void updateUsersMusicList(UserMusicList _userMusicList, ArrayList<String> _newUsersFavoriteAlbums, ArrayList<String> _newUsersFavoriteArtists, ArrayList<String> _newUsersFavoriteTracks) throws FileNotFoundException {
        Scanner scanner = new Scanner(userInfoFile);
        String userName = _userMusicList.getUserName();
        ArrayList<String> usersFavoriteAlbums = _userMusicList.getUsersFavoriteAlbums();
        ArrayList<String> usersFavoriteArtists = _userMusicList.getUsersFavoriteArtists();
        ArrayList<String> usersFavoriteTracks = _userMusicList.getUsersFavoriteTracks();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //Adds a selected album to the user's list of favorite albums
        _userMusicList.setUsersFavoriteAlbums(_newUsersFavoriteAlbums);
        //Adds a selected artist to the user's list of favorite artists
        _userMusicList.setUsersFavoriteArtists(_newUsersFavoriteArtists);
        //Adds a selected track to the user's list of favorite tracks
        _userMusicList.setUsersFavoriteTracks(_newUsersFavoriteTracks);

    }
}
