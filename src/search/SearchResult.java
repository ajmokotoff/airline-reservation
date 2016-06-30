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

    protected void addFlightPlan(FlightPlan flightPlan) {
        completeFlightPlanList.add(flightPlan);

        if (flightPlan.canReserveCoach()) {
            coachFlightPlanList.add(flightPlan);
        }

        if (flightPlan.canReserveFirstClass()) {
            firstClassFlightPlanList.add(flightPlan);
        }
    }

    protected void initializeFilteredList() {
        filteredFlightPlanList.clear();
        filteredFlightPlanList.addAll(completeFlightPlanList);
    }
    
    private void initializeFilteredList(List<FlightPlan> list) {
        filteredFlightPlanList.clear();
        filteredFlightPlanList.addAll(list);
    }

    public List<FlightPlan> getFlightPlanList() {
        return filteredFlightPlanList;
    }

    protected List<FlightPlan> getCompleteFlightPlanList() {
        return completeFlightPlanList;
    }

    public void sortByCoachPrice() {
        initializeFilteredList(coachFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getCoachPriceComparator());
    }

    public void sortByFirstClassPrice() {
        initializeFilteredList(firstClassFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getFirstClassPriceComparator());
    }

    public void sortByCoachTime() {
        initializeFilteredList(coachFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getTimeComparator());
    }
    
    public void sortByFirstClassTime() {
        initializeFilteredList(filteredFlightPlanList);
        Collections.sort(filteredFlightPlanList, FlightPlan.getTimeComparator());
    }

    public void filterByTime(Date start, Date end) {

    }

    public void filterByTime(Date startDepart, Date endDepart, Date startReturn, Date endReturn) {

    }

}
