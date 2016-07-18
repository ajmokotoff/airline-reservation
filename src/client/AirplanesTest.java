package client;

import http.Accessor;

import java.util.HashMap;

public class AirplanesTest {
    public static void main(String[] arg){
        //test content is correct first
        DataFactory dataFactory = new DataFactory();
        Accessor accessor = Accessor.get_instance();
        HashMap<String, Airplane> a=Airplanes.get(),b=dataFactory.xml2Airplanes(accessor.getAirplanes());
        System.out.println(a.keySet());
        System.out.println(b.keySet());


        //test access time will not increase after initialized until refresh called
            // has already been accessed twice
        System.out.println(accessor.getTimes());
            // won't change times
        Airplanes.get();
        System.out.println(accessor.getTimes());
            // increased
        Airplanes.refresh();
        System.out.println(accessor.getTimes());
    }
}
