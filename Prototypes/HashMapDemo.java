package Prototypes;

import java.util.HashMap;

/**
 *
 * @author Quinn Tjin-A-Soe Hash map prototype
 * Last Updated: 03.09.2020
 */
public class HashMapDemo {
    public static void main(String[] args) {
    }
    /**
     * Method takes two strings and creates new (key, value) stored in hash map
     * @param _key
     * @param _value
     */
    public static void pair (String _key, String _value){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(_key, _value);
    }
}
