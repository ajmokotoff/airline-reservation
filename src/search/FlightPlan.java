/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import client.Flight;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 *
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

    public double getCoachPrice() {
        return coachPrice;
    }

    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    public long getTravelTime() {
        return travelTime;
    }
    
    abstract public boolean canReserveCoach();

    abstract public boolean canReserveFirstClass();
    
    static Comparator<FlightPlan> getCoachPriceComparator() {
        return new Comparator<FlightPlan>() {

            @Override
            public int compare(FlightPlan o1, FlightPlan o2) {
                return Double.compare(o1.getCoachPrice(), o2.getCoachPrice());
            }
        };
    }

    static Comparator<FlightPlan> getFirstClassPriceComparator() {
        return new Comparator<FlightPlan>() {

            @Override
            public int compare(FlightPlan o1, FlightPlan o2) {
                return Double.compare(o1.getFirstClassPrice(), o2.getFirstClassPrice());
            }
        };
    }

    static Comparator<FlightPlan> getTimeComparator() {
        return new Comparator<FlightPlan>() {

            @Override
            public int compare(FlightPlan o1, FlightPlan o2) {
                return (int) (o1.getTravelTime() - o2.getTravelTime());
            }
        };
    }

}
