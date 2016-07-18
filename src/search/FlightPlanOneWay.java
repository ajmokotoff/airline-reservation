/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import client.Flight;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * FlightPlanOneWay class
 * <p>
 * Contains FlightPlan information for One Way trip.
 * </p>
 *
 * @author Mike
 */
public class FlightPlanOneWay extends FlightPlan {

    private final List<Flight> flightList;
    private final Map<Flight, Boolean> flightSeatingIsCoachMap;

    public FlightPlanOneWay(Flight... flights) {
        super();

        flightList = Arrays.asList(flights);
        travelTime = flights[flights.length - 1].getArrTime().getTime() - flights[0].getDepTime().getTime();

        flightSeatingIsCoachMap = new HashMap();

        for (Flight flight : flights) {
            coachPrice += flight.getCoachPrice();
            firstClassPrice += flight.getFirstPrice();

            // Initialize all flight seatings to coach pricing
            flightSeatingIsCoachMap.put(flight, Boolean.TRUE);
        }

    }

    // Return list of Flights from this FlightPlan
    public List<Flight> getFlightList() {
        return flightList;
    }

    // Return number of transfers in this FlightPlan
    public int getNumberOfTransfers() {
        return flightList.size() - 1;
    }

    public boolean contains(Flight flight) {
        return flightList.contains(flight);
    }

    public void setFlightSeatingToCoach(Flight flight) {
        flightSeatingIsCoachMap.put(flight, Boolean.TRUE);
    }

    public void setFlightSeatingToFirstClass(Flight flight) {
        flightSeatingIsCoachMap.put(flight, Boolean.FALSE);
    }

    public boolean coachSeatingSelected(Flight flight) {
        return flightSeatingIsCoachMap.get(flight);
    }

    @Override
    public double getPrice() {
        double cost = 0;

        for (Flight flight : flightList) {
            cost += flightSeatingIsCoachMap.get(flight) ? flight.getCoachPrice() : flight.getFirstPrice();
        }
        return cost;
    }

    // Return true if all flight numbers are the same
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlightPlanOneWay)) {
            return false;
        }

        FlightPlanOneWay plan = (FlightPlanOneWay) obj;

        if (flightList.size() != plan.getFlightList().size()) {
            return false;
        }

        for (int i = 0; i < flightList.size(); i++) {
            if (!flightList.get(i).getFlightNo().equals(plan.getFlightList().get(i).getFlightNo())) {
                return false;
            }
        }

        return true;

    }

    // Use flight numbers to calculate hash code
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash;

        for (Flight flight : flightList) {
            hash += Objects.hashCode(flight.getFlightNo());
        }

        return hash;
    }

    // Return if can reserve coach for all Flights
    @Override
    public boolean canReserveCoach() {
        for (Flight flight : flightList) {
            if (flight.checkCoachLeft() <= 0) {
                return false;
            }
        }
        return true;
    }

    // Return if can reserve first class for all Flights
    @Override
    public boolean canReserveFirstClass() {
        for (Flight flight : flightList) {
            if (flight.checkFirstLeft() <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setAllCoachSeating() {
        for (Flight flight : flightList) {
            flightSeatingIsCoachMap.put(flight, Boolean.TRUE);
        }
    }

    @Override
    public void setAllFirstClassSeating() {
        for (Flight flight : flightList) {
            flightSeatingIsCoachMap.put(flight, Boolean.FALSE);
        }
    }

    @Override
    public void setSeating(Flight flight, boolean coachSeatingSelected) {
        if(flightList.contains(flight))
        {
            flightSeatingIsCoachMap.put(flight, coachSeatingSelected);
        }
    }

}
