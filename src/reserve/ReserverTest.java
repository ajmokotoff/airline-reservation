package reserve;

import client.DataFactory;
import client.Flight;
import http.Accessor;
import search.FlightPlanOneWay;
import java.util.HashMap;



public class ReserverTest {
    public static void main(String[] args){
        Reserver reserver = new Reserver();
        DataFactory dataFactory = new DataFactory();
        Accessor accessor=Accessor.get_instance();

        HashMap<String,Flight> hash = dataFactory.xml2Flights(accessor.getDepartingFlights("BOS",2016,5,10));
        Object[] keys =  hash.keySet().toArray();
        Flight a = hash.get(keys[0]),b=hash.get(keys[1]),c=hash.get(keys[2]);
        FlightPlanOneWay plan1 = new FlightPlanOneWay(a,b,c);
        plan1.setFlightSeatingToFirstClass(b);

        System.out.println(reserver.reservePlan(plan1,true));
    }
}
