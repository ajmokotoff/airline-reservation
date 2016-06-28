/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import client.Flight;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 */
public class OneWayFlightPlan extends FlightPlan {

    private List<Flight> flightList;

    public OneWayFlightPlan(Flight... flights) {
        super();

        flightList = Arrays.asList(flights);
        travelTime = flights[flights.length - 1].getArrTime().getTime() - flights[0].getDepTime().getTime();

        for (Flight flight : flights) {
            coachPrice += flight.getCoachPrice();
            firstClassPrice += flight.getFirstPrice();
        }
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public int getNumberOfTransfers() {
        return flightList.size() - 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OneWayFlightPlan)) {
            return false;
        }

        OneWayFlightPlan plan = (OneWayFlightPlan) obj;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash;
        
        for(Flight flight : flightList)
        {
            hash += Objects.hashCode(flight.getFlightNo());
        }
        
        //System.out.println("HERE!!!!");
        return hash;
    }

}
