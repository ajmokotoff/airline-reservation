/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import client.DataFactory;
import client.Flight;
import http.Accessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 */
public class Searcher {

    private final DataFactory dataFactory;
    private final Accessor accessor;

    private final Set<String> airportCodesSet;

    private final FlightMap flightMap;

    public Searcher() {
        dataFactory = new DataFactory();
        accessor = Accessor.get_instance();

        airportCodesSet = dataFactory.xml2Airports(accessor.getAirports()).keySet();

        flightMap = new FlightMap();
    }

    // Returning null if bad search result for now, should return error result to update gui output
    public Result searchOneWayTrip(String depCode, String arrCode, Date date) {

        if (!isValidAirportCode(depCode)) {
            return new ErrorResult("Invalid departure Airport Code!");
        }

        if (!isValidAirportCode(arrCode)) {
            return new ErrorResult("Invalid arrival Airport Code!");
        }

        if (date == null) {
            return new ErrorResult("Date is not set!");
        }

        flightMap.initialize();

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // For some reason, this returns 0 - 11
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        HashMap<String, Flight> departingFlightMap = dataFactory.xml2Flights(accessor.getDepartingFlights(depCode, year, month, day));
        HashMap<String, Flight> arrivingFlightMap = dataFactory.xml2Flights(accessor.getArrivingFlights(arrCode, year, month, day));

        flightMap.addFlightNo2FlightMap(departingFlightMap);
        flightMap.addFlightNo2FlightMap(arrivingFlightMap);

        // Set of Airport codes that departing airport has flights to
        Set<String> depOutboundArrCodes = getArrCodesFromFlightMap(departingFlightMap);

        // Set of Airport codes that arriving airport has flights from
        Set<String> arrInboundDepCodes = getDepCodesFromFlightMap(arrivingFlightMap);

        for (String currentArrCode : depOutboundArrCodes) {
            flightMap.addFlightNo2FlightMap(dataFactory.xml2Flights(accessor.getDepartingFlights(currentArrCode, year, month, day)));
        }

        Set<FlightPlanOneWay> flightPlanList = new HashSet();
        //List<FlightPlan> flightPlanList = new ArrayList();

        // Begin searching flight options
        // If direct flights exist, add them
        if (flightMap.directFlightExists(depCode, arrCode)) {

            for (Flight directFlight : flightMap.getDirectFlights(depCode, arrCode)) {
                flightPlanList.add(new FlightPlanOneWay(directFlight));
            }
        }

        // Iterate through airports that departure airport has flights to
        for (String currDepOutboundArrCode : depOutboundArrCodes) {

            // Check if direct flight exists from here to desired destination
            if (flightMap.directFlightExists(currDepOutboundArrCode, arrCode)) {

                // Iterate through flight set depCode -> currDepOutputArrCode
                for (Flight departingFlight : flightMap.getDirectFlights(depCode, currDepOutboundArrCode)) {

                    // Iterate through flight set currDepOutputArrCode -> arrCode
                    for (Flight connectingFlight : flightMap.getDirectFlights(currDepOutboundArrCode, arrCode)) {

                        if (validConnectingFlight(departingFlight, connectingFlight)) {
                            flightPlanList.add(new FlightPlanOneWay(departingFlight, connectingFlight));
                        }
                    }

                    // Iterate through airports that arrival airport has flights from
                    for (String currArrInboundDepCode : arrInboundDepCodes) {

                        // Check if direct flight exists from 1st connecting airport to 2nd connecting airport
                        if (flightMap.directFlightExists(currDepOutboundArrCode, currArrInboundDepCode)) {

                            // Iterate through flight set currDepOutputArrCode -> currArrInboundDepCode
                            for (Flight connectingFlight1 : flightMap.getDirectFlights(currDepOutboundArrCode, currArrInboundDepCode)) {

                                // Iterate through flight set currDepOutputArrCode -> arrCode
                                for (Flight connectingFlight2 : flightMap.getDirectFlights(currArrInboundDepCode, arrCode)) {

                                    if (validConnectingFlight(departingFlight, connectingFlight1) && validConnectingFlight(connectingFlight1, connectingFlight2)) {
                                        flightPlanList.add(new FlightPlanOneWay(departingFlight, connectingFlight1, connectingFlight2));
                                    }
                                }
                            }
                        }

                    }

                }
            }

        }

        return new SearchResultOneWay(flightPlanList);
    }

    public Result searchRoundTrip(String depCode, String arrCode, Date departDate, Date returnDate) {
        Result departResults = searchOneWayTrip(depCode, arrCode, departDate);

        if (departResults instanceof ErrorResult) {
            return departResults;
        }
        
        Result returnResults = searchOneWayTrip(arrCode, depCode, returnDate);

        if(returnResults instanceof ErrorResult) {
            return returnResults;
        }
        
        //return null;
        return new SearchResultRoundTrip((SearchResultOneWay)departResults, (SearchResultOneWay)returnResults);
    }

    private boolean isValidAirportCode(String str) {
        return airportCodesSet.contains(str.toUpperCase());
    }

    private Set<String> getArrCodesFromFlightMap(HashMap<String, Flight> map) {
        Set<String> codeSet = new HashSet();

        for (Flight flight : map.values()) {
            codeSet.add(flight.getArrCode());
        }

        return codeSet;
    }

    private Set<String> getDepCodesFromFlightMap(HashMap<String, Flight> map) {
        Set<String> codeSet = new HashSet();

        for (Flight flight : map.values()) {
            codeSet.add(flight.getDepCode());
        }

        return codeSet;
    }

    private boolean validConnectingFlight(Flight arrivingFlight, Flight departingFlight) {
        long diff = departingFlight.getDepTime().getTime() - arrivingFlight.getArrTime().getTime();

        long diffMinutes = diff / (60000) % 60;

        return diffMinutes >= 30 && diffMinutes <= 300;
    }

}
