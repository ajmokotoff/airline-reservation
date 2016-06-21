package client;

import http.Accessor;

public class DataFactoryTest {
    public static void main(String[] args){
        DataFactory df= new DataFactory();
        Accessor accessor =Accessor.get_instance();

        //all airports, values, codes, size
        System.out.println("\n"+df.xml2Airports(accessor.getAirports()).values());
        System.out.println(df.xml2Airports(accessor.getAirports()).keySet());
        System.out.println(df.xml2Airports(accessor.getAirports()).size());
        //all airplane models
        System.out.println("\n"+df.xml2Airplanes(accessor.getAirplanes()).values());
        System.out.println(df.xml2Airplanes(accessor.getAirplanes()).keySet());
        System.out.println(df.xml2Airplanes(accessor.getAirplanes()).size());
        //all flight departing from boston on 5/14/2016
        System.out.println("\n"+df.xml2Flights(accessor.getDepartingFlights("BOS",2016,5,14)).values());
        System.out.println(df.xml2Flights(accessor.getDepartingFlights("BOS",2016,5,14)).keySet());
        System.out.println(df.xml2Flights(accessor.getDepartingFlights("BOS",2016,5,14)).size());
        // all flight arriving boston on that day
        System.out.println("\n"+df.xml2Flights(accessor.getArrivingFlights("BOS",2016,5,14)).values());
        System.out.println(df.xml2Flights(accessor.getArrivingFlights("BOS",2016,5,14)).keySet());
        System.out.println(df.xml2Flights(accessor.getArrivingFlights("BOS",2016,5,14)).size());
    }

}