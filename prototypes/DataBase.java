package prototypes;

/*
 * Last Update: 02.19.2020
 * This class is a prototype for MyMusicList database which connects to a
 * database service through NetBeans.
 * Contributing @author Quinn Tjin-A-Soe.
 */

/*

*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
        private static Connection connectionObject = null;
        private static Statement statementObject = null;
        private static ResultSet resultObject = null;
        private static ResultSetMetaData metaObject = null;
        private static String query = "select * from ADASFIREWALL.USERACCOUNT";
    public static void main(String[] args) {
        try{
            connectionObject = DriverManager.getConnection("jdbc:derby://localhost:1527/MyMusicListDataBase", "AdasFirewall", "csc340");
            statementObject = connectionObject.createStatement();
            resultObject = statementObject.executeQuery(query);
            metaObject = resultObject.getMetaData();
            int columnNumber = metaObject.getColumnCount();
            for(int i = 1; i <= columnNumber; i++){
                System.out.print(metaObject.getColumnName(i) + "\t");
            }
            System.out.println();
            while(resultObject.next()){
                for(int i = 1; i <= columnNumber; i++){
                    System.out.print(resultObject.getObject(i) + "\t");
                }
                System.out.println();
            }
        }
        catch (SQLException _e){
            _e.printStackTrace();
        }
    }
}