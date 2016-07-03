/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.List;
import java.util.Set;

/**
 * SearchResultOneWay class
 * <p>
 *     Contains Result info for a One Way search query.
 * </p>
 *
 * @author Mike
 */
public class SearchResultOneWay extends SearchResult {

    
    
    public SearchResultOneWay(List<FlightPlan> flightPlanList) {
        super(flightPlanList);   
    }
    
    public SearchResultOneWay(Set<FlightPlanOneWay> flightPlanList) {
        super(flightPlanList);
    }
    
    
    
   
    
    

}
