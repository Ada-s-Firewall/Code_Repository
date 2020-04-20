package Prototypes;

import static Database.DatabaseInterface.userInfoFile;
import static Database.DatabaseInterface.userLoginFile;
/**
 * This class is specifically for conducting tests on the Database classes. Last
 * Updated: 4/17/2020
 *
 * @author Fernando Villarreal
 */

import Models.DBInfoRequest;
import Objects.PlanToListenObject;
import Objects.RatingObject;
import Objects.UserObject;
import Objects.RecordObjectList;
import java.util.ArrayList;

public class DatabaseTests {

    /**
     * Main method for doing tests
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Database Test 01:\n\n");
        //DBTest01();
        System.out.println("Database Test 02:\n\n");
        //DBTest02();
        System.out.println("Database Test 03:\n\n");
        //DBTest03();
        System.out.println("Database Test 04:\n\n");
        //DBTest04();
    }

    public static void DBTest01() throws Exception {
        // Create a new DBInfoRequest
        DBInfoRequest dbRequest = new DBInfoRequest();
        // Create new users and ratings
        UserObject user01 = new UserObject("JackMan93", "jackword", "jkessler@email.com", "Jack", "Kessler");
        UserObject user02 = new UserObject("FVilla", "village5", "fvil@email.com", "Francis", "Villagran");
        UserObject user03 = new UserObject("jdoe012", "password01", "jdoe1@email.com", "John", "Doe");
        UserObject user04 = new UserObject("jmiller", "password02", "jamil@email.com", "Jane", "Miller");
        RatingObject rating01 = new RatingObject("FVilla", 9.0, "37394IP6uhnjIpsawpMu4l", "artist");
        RatingObject rating02 = new RatingObject("JackMan93", 8.5, "2ye2Wgw4gimLv2eAKyk1NB", "artist");
        RatingObject rating03 = new RatingObject("FVilla", 9.5, "278ZYwGhdK6QTzE3MFePnP", "artist");
        RatingObject rating04 = new RatingObject("JackMan93", 8.8, "36QJpDe2go2KgaRleHCDTp", "artist");
        RatingObject rating05 = new RatingObject("jdoe012", 8.3, "2wOqMjp9TyABvtHdOSOTUS", "artist");
        // Record the new users and ratings
        dbRequest.createUserRecord(user01);
        dbRequest.createUserRecord(user02);
        dbRequest.createUserRecord(user03);
        dbRequest.createUserRecord(user04);
        dbRequest.createUserRating(rating01);
        dbRequest.createUserRating(rating02);
        dbRequest.createUserRating(rating03);
        dbRequest.createUserRating(rating04);
        dbRequest.createUserRating(rating05);
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
        System.out.println("\nUser01's Ratings:\n\n" + ratings.toString());
    }

    public static void DBTest03() throws Exception {
        //Write your tests here
        DBInfoRequest dbRequest = new DBInfoRequest();
        UserObject user02 = new UserObject("FVilla", "village5", "fvil@email.com", "Francis", "Villagran");
        UserObject user03 = new UserObject("Real", "Person", "realperson@email.com", "Real", "Person");
        //dbRequest.createUserRecord(user03);
        dbRequest.deleteUserRecord(userInfoFile, user03);
    }

    public static void DBTest04() throws Exception {
        // Create a database adapter
        DBInfoRequest dbRequest = new DBInfoRequest();
        // Create new plan-to-listen records
        PlanToListenObject planToListen01 = new PlanToListenObject("FVilla", "78lgmZwycJ3nzsdgmPPGNx", "track");
        PlanToListenObject planToListen02 = new PlanToListenObject("FVilla", "5CQ30WqJwcep0pYcV4AMNc", "track");
        PlanToListenObject planToListen03 = new PlanToListenObject("FVilla", "0hCB0YR03f6AmQaHbwWDe8", "track");
        // Record the objects
        dbRequest.createUserPlanToListen(planToListen01);
        dbRequest.createUserPlanToListen(planToListen02);
        dbRequest.createUserPlanToListen(planToListen03);
    }
}
