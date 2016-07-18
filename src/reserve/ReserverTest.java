package reserve;

import client.DataFactory;
import client.Flight;
import http.Accessor;
import search.FlightPlanOneWay;
import search.FlightPlanRoundTrip;

import java.util.HashMap;



public class ReserverTest {
    public static void main(String[] args){
        // initialize components
        Reserver reserver = new Reserver();
        DataFactory dataFactory = new DataFactory();
        Accessor accessor=Accessor.get_instance();

        // construct test object
        HashMap<String,Flight> hash = dataFactory.xml2Flights(accessor.getDepartingFlights("BOS",2016,5,10));
        Object[] keys =  hash.keySet().toArray();
        Flight a = hash.get(keys[0]),b=hash.get(keys[1]),c=hash.get(keys[2]);
        FlightPlanOneWay plan1 = new FlightPlanOneWay(a,b,c);
        //set flight a as first class
        plan1.setFlightSeatingToFirstClass(b);

        //reset db
        accessor.reset();

        // show be all fine
        ReserveResult result1 = reserver.reservePlan(plan1,true);
        System.out.println("\nAll set:"+result1.isAllSet());
        System.out.println(result1);

        //mute
        accessor.setVerbose(false);

        // fail because of flight a doesn't have enough first seat
        FlightPlanOneWay plan2 = plan1;// here should be deep copy, but now plan1,2 refer to the same object
        plan1.setFlightSeatingToFirstClass(a);
        ReserveResult result2 = reserver.reservePlan(plan2,true);
        System.out.println("\nAll set:"+result2.isAllSet());
        System.out.println(result2);

        // round trip reserving
        plan1.setFlightSeatingToCoach(a);
        FlightPlanRoundTrip plan3 = new FlightPlanRoundTrip(plan1,plan1);
        ReserveResult result3 = reserver.reservePlan(plan3,false);
        System.out.println("\nAll set:"+result3.isAllSet());
        System.out.println(result3);

        // round trip failed because of availability
        plan1.setFlightSeatingToFirstClass(a);
        FlightPlanRoundTrip plan4 = new FlightPlanRoundTrip(plan1,plan1);
        ReserveResult result4 = reserver.reservePlan(plan4,false);
        System.out.println("\nAll set:"+result4.isAllSet());
        System.out.println(result4);

        // check reserve one flight function
        Flight flight1 = plan1.getFlightList().get(0);
        ReserveResult result5 =  reserver.reserveFlight(flight1,false);
        System.out.println("\nAll set:"+result5.isAllSet());
        System.out.println(result5);
    }
}
