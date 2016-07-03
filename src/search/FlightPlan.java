/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.Comparator;


/**
 * FlightPlan class
 * <p>
 *     Contains Flight information for a Collection of Flights.
 * </p>
 *
 * @author Mike
 */
public abstract class FlightPlan {

    protected double coachPrice;
    protected double firstClassPrice;
    protected long travelTime;

    protected FlightPlan() {
        coachPrice = 0;
        firstClassPrice = 0;
        travelTime = 0;
    }

    // Return coach price
    public double getCoachPrice() {
        return coachPrice;
    }

    // Return first class price
    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    // Return travel time
    public long getTravelTime() {
        return travelTime;
    }
    
    // Return if can reserve coach for all Flights
    abstract public boolean canReserveCoach();

    // Return if can reserve first class for all Flights
    abstract public boolean canReserveFirstClass();
    
    // Comparator for sorting by coach price
    static Comparator<FlightPlan> getCoachPriceComparator() {
        return new Comparator<FlightPlan>() {

            @Override
            public int compare(FlightPlan o1, FlightPlan o2) {
                return Double.compare(o1.getCoachPrice(), o2.getCoachPrice());
            }
        };
    }

    // Comparator for sorting by first class price
    static Comparator<FlightPlan> getFirstClassPriceComparator() {
        return new Comparator<FlightPlan>() {

            @Override
            public int compare(FlightPlan o1, FlightPlan o2) {
                return Double.compare(o1.getFirstClassPrice(), o2.getFirstClassPrice());
            }
        };
    }

    // Comparator for sorting by time
    static Comparator<FlightPlan> getTimeComparator() {
        return new Comparator<FlightPlan>() {

            @Override
            public int compare(FlightPlan o1, FlightPlan o2) {
                return (int) (o1.getTravelTime() - o2.getTravelTime());
            }
        };
    }

}
