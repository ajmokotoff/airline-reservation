package reserve;


import client.Flight;
import http.Accessor;
import search.FlightPlan;
import search.FlightPlanOneWay;
import search.FlightPlanRoundTrip;
import java.util.HashMap;

/**
 * Reserving class
 * <p>
 *     This class will invoke post API to lock db, reserve tickets, and unlock db.
 *     One important assumption here is we assumed after checking seat available is OK,
 *     all reserving actions will be successful between lock and unlock action,
 *     in other words, no failures of reserving will happen as long as db is locked.
 *     If some reserving failed in a flight plan, we can't cancel or roll back
 *     because there is no cancel api provided.
 * </p>
 * @author vincent
 * @since 07/13/2016
 */
public class Reserver {
    /**
     * singleton accessor class for retrieving data from server.
     */
    private Accessor accessor;

    /**
     * constructor, initialize accessor.
     */
    public Reserver(){
        accessor = Accessor.get_instance();
    }

    /**
     * check availabilities of all flight in a flight plan
     * @param plan either a one way plan or a round trip plan
     * @param isOneway indicate plan is one way or not(round trip)
     * @return if all seats are good to reserve.
     */
    private boolean checkAvailable(FlightPlan plan,boolean isOneway){
        if(isOneway) return checkOneWayAvailable((FlightPlanOneWay) plan);
        return checkRoundTripAvailable((FlightPlanRoundTrip) plan);
    }

    /**
     * check if flights' seats are good to go in an one way flight plan
     * @param plan one way plan to check
     * @return if all seats are available
     */
    private boolean checkOneWayAvailable(FlightPlanOneWay plan){
        boolean result = true;
        for(Flight f:plan.getFlightList()){
            if(plan.coachSeatingSelected(f)) result&=(f.checkCoachLeft()>0);
            else result&=(f.checkFirstLeft()>0);
        }
        return result;
    }

    /**
     * check if flight's seat are available in round trip plan
     * @param plan round trip flight plan
     * @return if all seats are available
     */
    private boolean checkRoundTripAvailable(FlightPlanRoundTrip plan){
        return checkOneWayAvailable(plan.getDepartingFlightPlan())
                &&checkOneWayAvailable(plan.getReturningFlightPlan());
    }

    /**
     * reserve a seat of flight with seat class
     * @param flight flight to reserve
     * @param isCoach seat classs is coach or not
     * @return if reserving is successfull
     */
    private boolean reserveOneFlight(Flight flight, boolean isCoach){
        return accessor.reserveSeat(flight.getFlightNo(),isCoach);
    }

    /**
     * Reserve only one flight with locking and unlocking action.
     * @param flight flight needs to be reserved
     * @param isCoach saet class is coach or not
     * @return reserve result class
     */
    public ReserveResult reserveFlight(Flight flight, boolean isCoach){
        HashMap<Flight, Boolean> result=new HashMap<>();
        boolean isSucceed=true;

        // lock db first
        if(!accessor.lockDB()) {
            System.out.println("Failed to lock");
            return new ReserveResult(result,false,false,false,false);
        }

        // make sure all seats are available
        if(isCoach&&flight.checkCoachLeft()<1 || !isCoach&&flight.checkFirstLeft()<1){
            System.out.println("seat not available");
            return new ReserveResult(result,true,false,false,accessor.unlockDB());
        }

        // reserve all flights according to plan type, one way or round trip
        isSucceed = reserveOneFlight(flight,isCoach);
        result.put(flight,isSucceed);

        // finished, and unlock. If here failed, server need some time to unlock by itself
        // and no reserving can be done during this period.
        if(!accessor.unlockDB()){
            System.out.println("Failed to unlock");
            return new ReserveResult(result,true,true,isSucceed,false);
        }

        return new ReserveResult(result,true,true,isSucceed,true);
    }

    /**
     * reserve all the seats given a flight plan. plan can be one way or round trip.
     * @param flightplan flight plan the user selected
     * @param isOneWay indicating it is an one way or round trip.
     * @return if reserving plan is successful
     */
    public ReserveResult reservePlan(FlightPlan flightplan, boolean isOneWay){
        HashMap<Flight, Boolean> result=new HashMap<>();
        boolean all=true;

        // lock db first
        if(!accessor.lockDB()) {
            System.out.println("Failed to lock");
            return new ReserveResult(result,false,false,false,false);
        }

        // make sure all seats are available
        if(!checkAvailable(flightplan,isOneWay)){
            System.out.println("Not all seats available");
            return new ReserveResult(result,true,false,false,accessor.unlockDB());
        }

        // start to reserve all flights according to plan type, one way or round trip
        if(isOneWay){
            FlightPlanOneWay plan = (FlightPlanOneWay) flightplan;
            for (Flight f : plan.getFlightList()) {
                boolean succeed = reserveOneFlight(f,plan.coachSeatingSelected(f));
                result.put(f, succeed);
                all&=succeed;
            }
        }else{
            FlightPlanRoundTrip plan = (FlightPlanRoundTrip) flightplan;
            FlightPlanOneWay dep=plan.getDepartingFlightPlan(),ret=plan.getReturningFlightPlan();
            for(Flight f:dep.getFlightList()){
                boolean succeed = reserveOneFlight(f,dep.coachSeatingSelected(f));
                result.put(f, succeed);
                all&=succeed;
            }
            for(Flight f:ret.getFlightList()){
                boolean succeed = reserveOneFlight(f,ret.coachSeatingSelected(f));
                result.put(f, succeed);
                all&=succeed;
            }
        }

        // finished, and unlock. If here failed, server need some time to unlock by itself
        // and no reserving can be done during this period.
        if(!accessor.unlockDB()){
            System.out.println("Failed to unlock");
            return new ReserveResult(result,true,true,all,false);
        }

        return new ReserveResult(result,true,true,all,true);
    }

}
