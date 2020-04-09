package Prototypes;

/**
 * This class is specifically for conducting tests on the Database classes.
 * Last Updated: 4/9/2020
 * @author Fernando Villarreal
 */

import Database.*;
import Models.DBInfoRequest;
import Objects.NewRating;
import Objects.UserObject;
import Objects.NewUser;
import java.io.File;
import java.net.URI;

public class DatabaseTests {

    /**
     * Main method for doing tests
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        System.out.println("Database Test 01:\n\n");
        DBTest01();
    }

    public static void DBTest01() throws Exception {
        // Create a new DBInfoRequest
        DBInfoRequest dbRequest = new DBInfoRequest();
        // Create new users and ratings
        NewUser user01 = new NewUser("JackMan93", "jackword", "jkessler@email.com", "Jack", "Kessler");
        NewUser user02 = new NewUser("FVilla", "village5", "fvil@email.com", "Francis", "Villagran");
        NewUser user03 = new NewUser("jdoe012", "password01", "jdoe1@email.com", "John", "Doe");
        NewUser user04 = new NewUser("jmiller", "password02", "jamil@email.com", "Jane", "Miller");
        NewRating rating01 = new NewRating("FVilla", 9.0, "37394IP6uhnjIpsawpMu4l");
        NewRating rating02 = new NewRating("JackMan93", 8.5, "2ye2Wgw4gimLv2eAKyk1NB");
        // Record the new users and ratings
        dbRequest.createUserRecord(user01);
        dbRequest.createUserRecord(user02);
        dbRequest.createUserRecord(user03);
        dbRequest.createUserRecord(user04);
        dbRequest.createUserRating(rating01);
        dbRequest.createUserRating(rating02);
    }
}
