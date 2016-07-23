package client;

import java.util.HashMap;

/**
 * Created by vincent on 7/21/16.
 */
public class TimezoneTest {
    public static void main(String [] args){
        HashMap<String, Airport> airports = Airports.get();
        for (String code:airports.keySet()) {
            System.out.println(code+" --- "+airports.get(code).getTimezone());
        }
    }
}
