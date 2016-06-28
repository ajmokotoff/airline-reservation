/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 *
 */
public class SearchResult {

    private final List<FlightPlan> completeFlightPlanList;
    private final List<FlightPlan> filteredFlightPlanList;
    
    public SearchResult(List<FlightPlan> flightPlanList) {

        completeFlightPlanList = flightPlanList;
        filteredFlightPlanList = new ArrayList();
        
    }
    
    public SearchResult(Set<OneWayFlightPlan> flightPlanList) {

        completeFlightPlanList = new ArrayList(flightPlanList);
        filteredFlightPlanList = new ArrayList();
        
    }
    
    public SearchResult(List<OneWayFlightPlan> departingFlightPlanList, List<OneWayFlightPlan> returningFlightPlanList) {

        completeFlightPlanList = new ArrayList();
        for(OneWayFlightPlan departingFlightPlan : departingFlightPlanList) {
            for(OneWayFlightPlan returningFlightPlan : returningFlightPlanList) {
                completeFlightPlanList.add(new RoundTripFlightPlan(departingFlightPlan, returningFlightPlan));
            }
        }
        
        filteredFlightPlanList = new ArrayList();
        
    }
    
    public List<FlightPlan> getCompleteFlightPlanList()
    {
        return completeFlightPlanList;
    }
    
    public void sortByCoachPrice()
    {
        Collections.sort(completeFlightPlanList, FlightPlan.getCoachPriceComparator());
    }
    
    public void sortByFirstClassPrice()
    {
        
    }
    
    public void sortByTime()
    {
        
    }
    
    public void filterByTime(Date start, Date end)
    {
        
    }
    
    public void filterByCoachSeating()
    {
        
    }
    
    public void filterByFirstClassSeating()
    {
        
    }
    
    

}
