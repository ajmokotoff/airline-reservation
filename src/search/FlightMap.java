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
 *
 *
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
    
    public boolean directFlightExists(String depCode, String arrCode)
    {
        return map.containsKey(depCode) ? map.get(depCode).containsKey(arrCode) : false;
    }
    
    public Set<Flight> getDirectFlights(String depCode, String arrCode)
    {
        return directFlightExists(depCode, arrCode) ? map.get(depCode).get(arrCode) : null;
    }
    
    

}
