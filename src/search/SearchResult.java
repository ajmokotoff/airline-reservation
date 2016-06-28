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
public class SearchResult extends Result {

    private final List<FlightPlan> completeFlightPlanList;
    private final List<FlightPlan> filteredFlightPlanList;
    
    public SearchResult(List<FlightPlan> flightPlanList)
    {
        completeFlightPlanList = flightPlanList;
        filteredFlightPlanList = new ArrayList(completeFlightPlanList);
    }
    
    public SearchResult(Set<FlightPlanOneWay> flightPlanList) {

        completeFlightPlanList = new ArrayList(flightPlanList);
        filteredFlightPlanList = new ArrayList(completeFlightPlanList);    
    }
    
    protected SearchResult()
    {
        completeFlightPlanList = new ArrayList();
        filteredFlightPlanList = new ArrayList();
    }
    
    protected void addFlightPlan(FlightPlan flightPlan)
    {
        completeFlightPlanList.add(flightPlan);
    }
    
    protected void initializeFilteredList() {
        filteredFlightPlanList.clear();
        filteredFlightPlanList.addAll(completeFlightPlanList);
    }
    
     public List<FlightPlan> getFlightPlanList()
    {
        return filteredFlightPlanList;
    }
    
    protected List<FlightPlan> getCompleteFlightPlanList() {
        return completeFlightPlanList;
    }
    
    public void sortByCoachPrice()
    {
        Collections.sort(filteredFlightPlanList, FlightPlan.getCoachPriceComparator());
    }
    
    public void sortByFirstClassPrice()
    {
        Collections.sort(filteredFlightPlanList, FlightPlan.getFirstClassPriceComparator());
    }
    
    public void sortByTime()
    {
        Collections.sort(filteredFlightPlanList, FlightPlan.getTimeComparator());
    }
    
    public void filterByTime(Date start, Date end)
    {
        
    }
    
    public void filterByTime(Date startDepart, Date endDepart, Date startReturn, Date endReturn)
    {
        
    }
    
    public void filterByCoachSeating()
    {
        
    }
    
    public void filterByFirstClassSeating()
    {
        
    }
}
