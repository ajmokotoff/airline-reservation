/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

/**
 * SearchResultRoundTrip class
 * <p>
 *     Contains Result info for a Round Trip search query.
 * </p>
 *
 * @author Mike
 */
public class SearchResultRoundTrip extends SearchResult {


    public SearchResultRoundTrip(SearchResultOneWay departingResults, SearchResultOneWay returningResult) {
        super();
    
        for (FlightPlan departingFlightPlan : departingResults.getCompleteFlightPlanList()) {
            for (FlightPlan returningFlightPlan : returningResult.getCompleteFlightPlanList()) {
                addFlightPlan(new FlightPlanRoundTrip((FlightPlanOneWay)departingFlightPlan, (FlightPlanOneWay)returningFlightPlan));
            }
        }

        initializeFilteredList();
    }


}
