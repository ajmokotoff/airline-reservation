package client;


import http.Accessor;
import java.util.HashMap;

public class AirportsTest {
    public static void main(String [] args){
        //test content is correct first
        DataFactory dataFactory = new DataFactory();
        Accessor accessor = Accessor.get_instance();
        HashMap<String, Airport> a=Airports.get(),b=dataFactory.xml2Airports(accessor.getAirports());
        System.out.println(a.keySet());
        System.out.println(b.keySet());


        //test access time will not increase after initialized until refresh called
        // has already been accessed twice
        System.out.println(accessor.getTimes());
        // won't change times
        Airports.get();
        System.out.println(accessor.getTimes());
        // increased
        Airports.refresh();
        System.out.println(accessor.getTimes());
    }
}
