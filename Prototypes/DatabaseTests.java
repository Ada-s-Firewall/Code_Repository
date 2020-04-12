package Prototypes;

/**
 * This class is specifically for conducting tests on the Database classes.
 * Last Updated: 4/12/2020
 * @author Fernando Villarreal
 */

import Database.*;
import Models.DBInfoRequest;
import Objects.NewRating;
import Objects.UserObject;
import Objects.NewUser;
import Objects.RecordObjectList;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class DatabaseTests {

    /**
     * Main method for doing tests
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        System.out.println("Database Test 01:\n\n");
        //DBTest01();
        System.out.println("Database Test 02:\n\n");
        DBTest02();
    }

    public static void DBTest01() throws Exception {
        // Create a new DBInfoRequest
        DBInfoRequest dbRequest = new DBInfoRequest();
        // Create new users and ratings
        NewUser user01 = new NewUser("JackMan93", "jackword", "jkessler@email.com", "Jack", "Kessler");
        NewUser user02 = new NewUser("FVilla", "village5", "fvil@email.com", "Francis", "Villagran");
        NewUser user03 = new NewUser("jdoe012", "password01", "jdoe1@email.com", "John", "Doe");
        NewUser user04 = new NewUser("jmiller", "password02", "jamil@email.com", "Jane", "Miller");
        NewRating rating01 = new NewRating("FVilla", 9.0, "37394IP6uhnjIpsawpMu4l", "artist");
        NewRating rating02 = new NewRating("JackMan93", 8.5, "2ye2Wgw4gimLv2eAKyk1NB", "artist");
        NewRating rating03 = new NewRating("FVilla", 9.5, "278ZYwGhdK6QTzE3MFePnP", "artist");
        // Record the new users and ratings
        dbRequest.createUserRecord(user01);
        dbRequest.createUserRecord(user02);
        dbRequest.createUserRecord(user03);
        dbRequest.createUserRecord(user04);
        dbRequest.createUserRating(rating01);
        dbRequest.createUserRating(rating02);
        dbRequest.createUserRating(rating03);
    }

    public static void DBTest02() throws Exception {
        // Create a new DBInfoRequest
        DBInfoRequest dbRequest = new DBInfoRequest();
        // Read and get a UserObject with username FVilla
        String username = "FVilla";
        UserObject user01 = dbRequest.readUserRecord(username);
        System.out.println("User01:\n\n" + user01.toString());
        // Read and get FVilla's ratings
        RecordObjectList ratings = dbRequest.readUsersRatings(username);
        System.out.println("User01's Ratings:\n\n" + ratings.toString());
    }
}
