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
public class RoundTripFlightPlan extends FlightPlan {
    
    OneWayFlightPlan departingFlightPlan;
    OneWayFlightPlan returningFlightPlan;

    public RoundTripFlightPlan(OneWayFlightPlan departingPlan, OneWayFlightPlan returningPlan)
    {
        departingFlightPlan = departingPlan;
        returningFlightPlan = returningPlan;
        coachPrice = departingPlan.getCoachPrice() + returningPlan.getCoachPrice();
        firstClassPrice = departingPlan.getFirstClassPrice() + returningPlan.getFirstClassPrice();
        travelTime = departingPlan.getTravelTime() + returningPlan.getTravelTime();
    }
    
    public OneWayFlightPlan getDepartingFlightPlan() {
        return departingFlightPlan;
    }
    
    public OneWayFlightPlan getReturningFlightPlan() {
        return returningFlightPlan;
    }
    
}
