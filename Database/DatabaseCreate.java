package Database;

import Objects.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds the methods for creating a record in the database.
 * Last updated: 04.17.2020
 * @author Quinn Tjin-A-Soe, Fernando Villarreal
 */
public class DatabaseCreate {

    /**
     * This method creates a record that is stored in the database. It takes an
     * ArrayList of Strings and writes it onto a text file.
     *
     * @param _file
     * @param _record
     * @throws java.io.IOException
     */
    public static void createRecord(File _file, ArrayList<String> _record) throws IOException {
        String stringRecord = "";
        int recordLength = _record.size();

        for (int index = 0; index < recordLength; index++) {
            //add every thing from the arraylist to the string
            stringRecord += _record.get(index);
            //tab over each time
            if (index < (recordLength - 1)) {
                stringRecord += "\t";
            }
        }

        stringRecord += "\t" + DatabaseInterface.active + "\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(_file, true));
        writer.write(stringRecord);
        writer.close();

    }

    /**
     * Creates and records for a new user to the datastore.
     * @param _user
     * @throws Exception
     */
    public static void createUserRecord(UserObject _user) throws Exception {
        try {
            // Check for an existing user record before creating
            UserObject existingUser = DatabaseRead.readUserRecord(_user.getUserName());
            String existingUsername = existingUser.getUserName();
            // Throw a DatabaseException if the User already exists
            if (existingUsername.equals(_user.getUserName())) {
                throw new DatabaseException("User with username '" + existingUsername + "' already exists.");
            }
            // Create a record in UserInfo.txt
            DatabaseCreate.createRecord(DatabaseInterface.userInfoFile, _user.toArrayList());
            // Create a record in UserLogin.txt
            ArrayList<String> userLogin = new ArrayList<>();
            userLogin.add(_user.getUserName());
            userLogin.add(_user.getUserPassword());
            DatabaseCreate.createRecord(DatabaseInterface.userLoginFile, userLogin);
        // Catch DatabaseExceptions
        } catch (DatabaseException exception) {
            System.out.println(exception.getMessage() + "\nNew User not created.");
        }
    }

    /**
     * Creates and records a new rating to the datastore.
     * @param _rating
     * @throws Exception
     */
    public static void createUserRating(RatingObject _rating) throws Exception {
        try {
            // Check for existing rating records before creating
            RecordObjectList existingRatings = DatabaseRead.readUsersRatings(_rating.getUsername());
            for (RecordObject existingRecord : existingRatings.getList()) {
                RatingObject existingRating = (RatingObject)existingRecord;
                String existingUsername = existingRating.getUsername();
                String existingSpotifyID = existingRating.getSpotifyId();
                // Throw a DatabaseException if the Rating already exists
                if (existingUsername.equals(_rating.getUsername()) && existingSpotifyID.equals(_rating.getSpotifyId())) {
                    throw new DatabaseException("Rating with username '" + existingUsername + "' and Spotify ID '"
                    + existingSpotifyID + "' already exists.");
                }
            }
            // Create a record in UserRating.txt
            DatabaseCreate.createRecord(DatabaseInterface.userRatingFile, _rating.toArrayList());
        // Catch DatabaseExceptions
        } catch (DatabaseException exception) {
            System.out.println(exception.getMessage() + "\nNew Rating not created.");
        }
    }

    /**
     * Creates and records a new plan-to-listen record.
     * @param _planToListen
     * @throws Exception
     */
    public static void createUserPlanToListen(PlanToListenObject _planToListen) throws Exception {
        try {
            // Check for existing plan-to-listen records before creating
            RecordObjectList existingPlanToListens = DatabaseRead.readUsersPlanToListen(_planToListen.getUsername());
            for (RecordObject existingRecord : existingPlanToListens.getList()) {
                PlanToListenObject existingPlanToListen = (PlanToListenObject)existingRecord;
                String existingUsername = existingPlanToListen.getUsername();
                String existingSpotifyID = existingPlanToListen.getSpotifyId();
                // Throw a DatabaseException if a record already exists
                if (existingUsername.equals(_planToListen.getUsername()) && existingSpotifyID.equals(_planToListen.getSpotifyId())) {
                    throw new DatabaseException("Record with username '" + existingUsername + "' and Spotify ID '"
                    + existingSpotifyID + "' already exists.");
                }
            }
            // Create a record in UserPlanToListen.txt
            DatabaseCreate.createRecord(DatabaseInterface.userPlanToListen, _planToListen.toArrayList());
        // Catch DatabaseExceptions
        } catch (DatabaseException exception) {
            System.out.println(exception.getMessage() + "\nNew Record not created.");
        }
    }
}
