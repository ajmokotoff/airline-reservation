/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import client.Flight;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * FlightMap class
 * <p>
 *     Contains HashMap mapping Departing Airport -> Arriving Airport -> Set of Flights.
 * </p>
 *
 * @author Mike
 */
public class FlightMap {

    private final Map<String, Map<String, Set<Flight> > > map;
    
    public FlightMap() {
        map = new HashMap();
    }
    
    public void initialize()
    {
        map.clear();
    }
    
    // Add Flights from given map to HashMap
    public void addFlightNo2FlightMap(Map<String, Flight> fn2fMap)
    {
        String depCode;
        String arrCode;
        Map<String, Set<Flight>> currentMap;
        Set<Flight> currentSet;
        
        for(Flight flight : fn2fMap.values())
        {
            depCode = flight.getDepCode();
            arrCode = flight.getArrCode();
            currentMap = map.containsKey(depCode) ? map.get(depCode) : new HashMap();
            currentSet = currentMap.containsKey(arrCode) ? currentMap.get(arrCode) : new HashSet();
            currentSet.add(flight);
            currentMap.put(arrCode, currentSet);
            map.put(depCode, currentMap);
        }
    }
    
    
    // Returns if a direct flight exists between given airports
    public boolean directFlightExists(String depCode, String arrCode)
    {
        return map.containsKey(depCode) ? map.get(depCode).containsKey(arrCode) : false;
    }
    
    // Returns set of direct flights from Departing Airport -> Arriving Airport
    public Set<Flight> getDirectFlights(String depCode, String arrCode)
    {
        return directFlightExists(depCode, arrCode) ? map.get(depCode).get(arrCode) : null;
    }
    
    

}
