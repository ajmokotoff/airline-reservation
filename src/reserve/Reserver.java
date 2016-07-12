package reserve;


import client.Flight;
import http.Accessor;
import search.FlightPlan;
import search.FlightPlanOneWay;


public class Reserver {
    private Accessor accessor;

    public Reserver(){
        accessor = Accessor.get_instance();
    }

    private boolean reserveOneFlight(String flightNo, boolean isCoach){
        if(!accessor.reserveSeat(flightNo,isCoach)){
            System.out.println(flightNo+":seat number change failed");
            return false;
        }
        return true;
    }

    public boolean reserveOneFlight(Flight flight, boolean isCoach){
        if (!accessor.lockDB()){
            System.out.println("lock failed");
            return false;
        }
        if (!reserveOneFlight(flight.getFlightNo(),isCoach)){
            System.out.println("reserve failed");
            return false;
        }
        if(!accessor.unlockDB()){
            System.out.println("reserving success but failed to unlock DB");
            return true;
        }
        return true;
    }

    public boolean reservePlan(FlightPlan flightplan, boolean isOneWay){
        return false;
        // unfinished, no specific seat time for each flight in flightplan
        // roundTrip haven't start
        /*
        boolean result;
        if(isOneWay){
            FlightPlanOneWay plan = (FlightPlanOneWay) flightplan;
            for (Flight f: plan.getFlightList()) {
                if (!reserveOneFlight(f, true)){
                    //always coach
                }
            }
        }
        else{

        }
        return result;
        */
    }

}
