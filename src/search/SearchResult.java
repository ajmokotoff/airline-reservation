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
 * SearchResult class
 * <p>
 *     Contains Result info for a completed search query.
 * </p>
 *
 * @author Mike
 */
public class SearchResult extends Result {

    // List of all FlightPlans for this search
    private final List<FlightPlan> completeFlightPlanList;

    // List of FlightPlans to provide to user based on filter options
    private final List<FlightPlan> filteredFlightPlanList;
    
    private final List<FlightPlan> coachFlightPlanList;
    private final List<FlightPlan> firstClassFlightPlanList;

    public SearchResult(List<FlightPlan> flightPlanList) {
        completeFlightPlanList = flightPlanList;
        filteredFlightPlanList = new ArrayList(completeFlightPlanList);
        coachFlightPlanList = new ArrayList();
        firstClassFlightPlanList = new ArrayList();
        initializeSeatingLists();
    }

    public SearchResult(Set<FlightPlanOneWay> flightPlanList) {

        completeFlightPlanList = new ArrayList(flightPlanList);
        filteredFlightPlanList = new ArrayList(completeFlightPlanList);
        coachFlightPlanList = new ArrayList();
        firstClassFlightPlanList = new ArrayList();
        initializeSeatingLists();
    }

    protected SearchResult() {
        completeFlightPlanList = new ArrayList();
        filteredFlightPlanList = new ArrayList();
        coachFlightPlanList = new ArrayList();
        firstClassFlightPlanList = new ArrayList();
    }

    // Setup coach and first-class lists
    private void initializeSeatingLists() {
        for (FlightPlan flightPlan : completeFlightPlanList) {
            if (flightPlan.canReserveCoach()) {
                coachFlightPlanList.add(flightPlan);
            }

            if (flightPlan.canReserveFirstClass()) {
                firstClassFlightPlanList.add(flightPlan);
            }
        }
    }

    // Add FlightPlan to complete list and appropriate coach/first-class lists
    protected void addFlightPlan(FlightPlan flightPlan) {
        completeFlightPlanList.add(flightPlan);

        if (flightPlan.canReserveCoach()) {
            coachFlightPlanList.add(flightPlan);
        }

        if (flightPlan.canReserveFirstClass()) {
            firstClassFlightPlanList.add(flightPlan);
        }
    }

    // Reset the filtered list to contain all Flight Plans
    protected void initializeFilteredList() {
        filteredFlightPlanList.clear();
        filteredFlightPlanList.addAll(completeFlightPlanList);
    }
    
    // Reset filtered list to contain input list
    private void initializeFilteredList(List<FlightPlan> list) {
        filteredFlightPlanList.clear();
        filteredFlightPlanList.addAll(list);
    }
    
    // Returns filtered list
    public List<FlightPlan> getFlightPlanList() {
        return filteredFlightPlanList;
    }

    // Results complete list
    protected List<FlightPlan> getCompleteFlightPlanList() {
        return completeFlightPlanList;
    }

    // Sort filtered list by coach price
    public void sortByCoachPrice() {
        initializeFilteredList(coachFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getCoachPriceComparator());
    }

    // Sort filtered list by first class price
    public void sortByFirstClassPrice() {
        initializeFilteredList(firstClassFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getFirstClassPriceComparator());
    }

    // Sort filtered list by coach time
    public void sortByCoachTime() {
        initializeFilteredList(coachFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getTimeComparator());
    }
    
    // Sort filtered list by first class time
    public void sortByFirstClassTime() {
        initializeFilteredList(firstClassFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getTimeComparator());
    }

    
    // Filter list by start/end time
    public void filterByTime(Date start, Date end) {

    }

    // Filter list by start/end time for round-trip
    public void filterByTime(Date startDepart, Date endDepart, Date startReturn, Date endReturn) {

    }

}
