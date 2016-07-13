package reserve;


import client.Flight;
import http.Accessor;
import search.FlightPlan;
import search.FlightPlanOneWay;
import search.FlightPlanRoundTrip;
import java.util.ArrayList;
import java.util.HashMap;


public class Reserver {
    private Accessor accessor;

    public Reserver(){
        accessor = Accessor.get_instance();
    }

    private boolean checkAvailable(FlightPlan plan,boolean isOneway){
        if(isOneway) return checkOneWayAvailable((FlightPlanOneWay) plan);
        return checkRoundTripAvailable((FlightPlanRoundTrip) plan);
    }
    private boolean checkOneWayAvailable(FlightPlanOneWay plan){
        boolean result = true;
        for(Flight f:plan.getFlightList()){
            if(plan.flightSeatingIsCoach(f)) result&=(f.checkCoachLeft()>0);
            else result&=(f.checkFirstLeft()>0);
        }
        return result;
    }
    private boolean checkRoundTripAvailable(FlightPlanRoundTrip plan){
        return checkOneWayAvailable(plan.getDepartingFlightPlan())
                &&checkOneWayAvailable(plan.getReturningFlightPlan());
    }

    private boolean reserveOneFlight(Flight flight, boolean isCoach){
        return accessor.reserveSeat(flight.getFlightNo(),isCoach);
    }

    public ReserveResult reservePlan(FlightPlan flightplan, boolean isOneWay){
        HashMap<Flight, Boolean> result=new HashMap<>();
        boolean all=true;

        if(!accessor.lockDB()) return new ReserveResult(result,false,false,false);

        if(!checkAvailable(flightplan,isOneWay)) return new ReserveResult(result,true,false,false);
        if(isOneWay){
            FlightPlanOneWay plan = (FlightPlanOneWay) flightplan;
            for (Flight f : plan.getFlightList()) {
                boolean succeed = reserveOneFlight(f,plan.flightSeatingIsCoach(f));
                result.put(f, succeed);
                all&=succeed;
            }
        }else{
            FlightPlanRoundTrip plan = (FlightPlanRoundTrip) flightplan;
            FlightPlanOneWay dep=plan.getDepartingFlightPlan(),ret=plan.getReturningFlightPlan();
            for(Flight f:dep.getFlightList()){
                boolean succeed = reserveOneFlight(f,dep.flightSeatingIsCoach(f));
                result.put(f, succeed);
                all&=succeed;
            }
            for(Flight f:ret.getFlightList()){
                boolean succeed = reserveOneFlight(f,ret.flightSeatingIsCoach(f));
                result.put(f, succeed);
                all&=succeed;
            }
        }

        if(!accessor.unlockDB()) return new ReserveResult(result,true,all,false);

        return new ReserveResult(result,true,all,true);
    }

}
