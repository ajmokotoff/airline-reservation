/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import client.Flight;

/**
 * FlightPlanOneWay class
 * <p>
 * Contains FlightPlan information for Round Trip trip.
 * </p>
 *
 * @author Mike
 */
public class FlightPlanRoundTrip extends FlightPlan {

    FlightPlanOneWay departingFlightPlan;
    FlightPlanOneWay returningFlightPlan;

    public FlightPlanRoundTrip(FlightPlanOneWay departingPlan, FlightPlanOneWay returningPlan) {
        departingFlightPlan = departingPlan;
        returningFlightPlan = returningPlan;
        coachPrice = departingPlan.getCoachPrice() + returningPlan.getCoachPrice();
        firstClassPrice = departingPlan.getFirstClassPrice() + returningPlan.getFirstClassPrice();
        travelTime = departingPlan.getTravelTime() + returningPlan.getTravelTime();
    }

    // Return list of Flights for departing FlightPlan
    public FlightPlanOneWay getDepartingFlightPlan() {
        return departingFlightPlan;
    }

    // Return list of Flights for returning FlightPlan
    public FlightPlanOneWay getReturningFlightPlan() {
        return returningFlightPlan;
    }

    @Override
    public double getPrice() {
        return departingFlightPlan.getPrice() + returningFlightPlan.getPrice();
    }

    // Return if can reserve coach for all Flights
    @Override
    public boolean canReserveCoach() {
        return departingFlightPlan.canReserveCoach() && returningFlightPlan.canReserveCoach();
    }

    // Return if can reserve first class for all Flights
    @Override
    public boolean canReserveFirstClass() {
        return departingFlightPlan.canReserveFirstClass() && returningFlightPlan.canReserveFirstClass();
    }

    @Override
    public void setAllCoachSeating() {
        departingFlightPlan.setAllCoachSeating();
        returningFlightPlan.setAllCoachSeating();
    }

    @Override
    public void setAllFirstClassSeating() {
        departingFlightPlan.setAllFirstClassSeating();
        returningFlightPlan.setAllFirstClassSeating();
    }

    @Override
    public void setSeating(Flight flight, boolean coachSeatingSelected) {
        if(departingFlightPlan.contains(flight))
        {
            departingFlightPlan.setSeating(flight, coachSeatingSelected);
        }
        
        if(returningFlightPlan.contains(flight))
        {
            returningFlightPlan.setSeating(flight, coachSeatingSelected);
        }
    }

}
