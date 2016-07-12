package client;


import http.Accessor;
import java.util.HashMap;

public class FlightTest {
    public static void main(String [] args){
        DataFactory dataFactory=new DataFactory();
        Accessor accessor = Accessor.get_instance();
        HashMap<String,Flight> flights=dataFactory.xml2Flights(accessor.getDepartingFlights("BOS",2016,5,10));

        for (String code:flights.keySet()) {
            Flight flight = flights.get(code);
            System.out.print(flight.getModel()+" left(coach/first): ");
            System.out.print(flight.checkCoachLeft()+"/");
            System.out.println(flight.checkFirstLeft());
        }
    }
}
