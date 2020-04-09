package Prototypes;

/**
 * This class is specifically for conducting tests on the Database classes.
 * Last Updated: 4/8/2020
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
        // Create and record new users and ratings
        NewUser user01 = new NewUser("JackMan93", "jackword", "jkessler@email.com", "Jack", "Kessler");
        NewUser user02 = new NewUser("FVilla", "village5", "fvil@email.com", "Francis", "Villagran");
        NewRating rating01 = new NewRating("FVilla", 9.0, "37394IP6uhnjIpsawpMu4l");
        DatabaseCreate.createUserRecord(user01);
        DatabaseCreate.createUserRecord(user02);
        DatabaseCreate.createUserRating(rating01);
    }
}
