package Database;
/*
 * This Database class returns records.
 * This class uses scanners and reads from a textfile.
 */

/**
 * Last Updated: 04.12.2020
 *
 * @author Quinn Tjin-A-Soe, Fernando Villarreal
 */
import Objects.RatingObject;
import Objects.RecordObject;
import Objects.RecordObjectList;
import Objects.UserObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseRead {

    //=============== CLASS VARIABLES ===============

    // Indexes for items in records
    private static final int indexUUID = 0;
    private static final int indexUsername = 1;
    private static final int indexPassword = 2;
    private static final int indexRating = 2;
    private static final int indexEmail = 3;
    private static final int indexSpotifyID = 3;
    private static final int indexMusicObjectType = 4;
    private static final int indexFirstName = 4;
    private static final int indexLastName = 5;

    // Null value for RecordObjects
    private static final String noSuchRecord = "NoSuchRecord";

    // Active and Inactive record values
    private static final String active = "true";
    private static final String inactive = "false";

    //=============== METHODS ===============

    /**
     * This method returns a specific record from the database.
     *
     * @param _file
     * @param _string
     * @return
     * @throws IOException
     */
    public static ArrayList<String> readRecord(File _file, String _string) throws IOException {
        // Call DatabaseRead.readRecords with the given parameters
        ArrayList<ArrayList<String>> recordsList = DatabaseRead.readRecords(_file, _string);
        // If the record was not found (recordsList is empty), return an empty ArrayList<String>
        if (recordsList.isEmpty()) {
            return new ArrayList<>();
        }
        // Get the first item in recordsList and return it
        int firstItemIndex = 0;
        return recordsList.get(firstItemIndex);
    }

    /**
     * Read and return a list of records.
     * @param _file
     * @param _string
     * @return ArrayList<ArrayList<String>>
     * @throws IOException
     */
    public static ArrayList<ArrayList<String>> readRecords(File _file, String _string) throws IOException {
        // Create an ArrayList of ArrayList<String> to store the records
        ArrayList<ArrayList<String>> recordsList = new ArrayList<>();
        // Scan the specified file for records that contain _string and are active
        Scanner scanner = new Scanner(_file);
        while (scanner.hasNextLine()) {
            String stringRecord = scanner.nextLine();
            if (stringRecord.contains(_string) && stringRecord.contains(DatabaseRead.active)) {
                // Create a new record and add it to recordList
                ArrayList<String> record = new ArrayList<>();
                Scanner scannerRecord = new Scanner(stringRecord);
                scannerRecord.useDelimiter("\t");
                while (scannerRecord.hasNext()) {
                    record.add(scannerRecord.next());
                }
                recordsList.add(record);
            }
        }
        // Return the recordList
        return recordsList;
    }

    /**
     * Reads and return the UserObject using the given username.
     * @param _username
     * @return UserObject
     * @throws java.lang.Exception
     */
    public static UserObject readUserRecord(String _username) throws Exception {
        // Get the ArrayList<String> for the record
        ArrayList<String> recordList = DatabaseRead.readRecord(DatabaseInterface.userInfoFile, _username);
        // If the record was not found, return an empty UserObject
        if (recordList.isEmpty()) {
            return new UserObject(DatabaseRead.noSuchRecord);
        }
        // Create and return a UserObject using the information in recordList
        String uuid = recordList.get(DatabaseRead.indexUUID);
        String username = recordList.get(DatabaseRead.indexUsername);
        String password = recordList.get(DatabaseRead.indexPassword);
        String email = recordList.get(DatabaseRead.indexEmail);
        String firstName = recordList.get(DatabaseRead.indexFirstName);
        String lastName = recordList.get(DatabaseRead.indexLastName);
        return new UserObject(uuid, username, password, email, firstName, lastName);
    }

    /**
     * Reads and returns a list of ratings using the given username.
     * @param _username
     * @return RecordObjectList
     * @throws Exception
     */
    public static RecordObjectList readUsersRatings(String _username) throws Exception {
        // Get the ArrayList of ArrayList<String> for the records
        ArrayList<ArrayList<String>> recordsList = DatabaseRead.readRecords(DatabaseInterface.userRatingFile, _username);
        // Create a new RecordObjectList to store the RatingObjects
        String listName = _username + "'s Ratings";
        RecordObjectList ratingsList = new RecordObjectList(listName, "ratings");
        // If recordsList is empty, return an empty RecordObjectList
        if (recordsList.isEmpty()) {
            return new RecordObjectList("Empty List", "empty");
        }
        // Iterate over recordsList
        for (ArrayList<String> record : recordsList) {
            // Create a RatingObject and add it to ratingsList
            String uuid = record.get(DatabaseRead.indexUUID);
            String username = record.get(DatabaseRead.indexUsername);
            Double rating = new Double(record.get(DatabaseRead.indexRating));
            String spotifyID = record.get(DatabaseRead.indexSpotifyID);
            String musicObjectType = record.get(DatabaseRead.indexMusicObjectType);
            RatingObject ratingObj = new RatingObject(uuid, username, rating, spotifyID, musicObjectType);
            ratingsList.add(ratingObj);
        }
        // Return the list of ratings
        return ratingsList;
    }
}
