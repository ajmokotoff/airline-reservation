/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package search;

/**
 *
 * 
 */
public class FlightPlanRoundTrip extends FlightPlan {
    
    FlightPlanOneWay departingFlightPlan;
    FlightPlanOneWay returningFlightPlan;

    public FlightPlanRoundTrip(FlightPlanOneWay departingPlan, FlightPlanOneWay returningPlan)
    {
        departingFlightPlan = departingPlan;
        returningFlightPlan = returningPlan;
        coachPrice = departingPlan.getCoachPrice() + returningPlan.getCoachPrice();
        firstClassPrice = departingPlan.getFirstClassPrice() + returningPlan.getFirstClassPrice();
        travelTime = departingPlan.getTravelTime() + returningPlan.getTravelTime();
    }
    
    public FlightPlanOneWay getDepartingFlightPlan() {
        return departingFlightPlan;
    }
    
    public FlightPlanOneWay getReturningFlightPlan() {
        return returningFlightPlan;
    }

    @Override
    public boolean canReserveCoach() {
        return departingFlightPlan.canReserveCoach() && returningFlightPlan.canReserveCoach();
    }

    @Override
    public boolean canReserveFirstClass() {
        return departingFlightPlan.canReserveFirstClass() && returningFlightPlan.canReserveFirstClass();
    }
    
}
