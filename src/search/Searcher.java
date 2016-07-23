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
 * Searcher class
 * <p>
 * Class contains DataFactory and Accessor, used to search for flight options
 * </p>
 *
 * @author Mike
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

    // Performs flight search for given airport codes and date, returns Result
    public Result searchOneWayTrip(String depCode, String arrCode, Date startDate, Date endDate) {

        if (!isValidAirportCode(depCode)) {
            return new ErrorResult("Please select a Departure Airport Code!");
        }

        if (!isValidAirportCode(arrCode)) {
            return new ErrorResult("Please select an Arrival Airport Code!");
        }
        
        if(depCode.equals(arrCode))
        {
            return new ErrorResult("Departure and Arrival locations the same!");
        }

        if (startDate == null) {
            return new ErrorResult("Date is not set!");
        }

        if (endDate == null) {
            return new ErrorResult("Date is not set!");
        }

        if (startDate.after(endDate)) {
            return new ErrorResult("Cannot set start time before end time!");
        }

        flightMap.initialize();

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(startDate);
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

                if (flightWithinTimeConstraints(directFlight, startDate, endDate)) {
                    flightPlanList.add(new FlightPlanOneWay(directFlight));
                }
            }
        }

        // Iterate through airports that departure airport has flights to
        for (String currDepOutboundArrCode : depOutboundArrCodes) {

            // Check if direct flight exists from here to desired destination
            if (flightMap.directFlightExists(currDepOutboundArrCode, arrCode)) {

                // Iterate through flight set depCode -> currDepOutputArrCode
                for (Flight departingFlight : flightMap.getDirectFlights(depCode, currDepOutboundArrCode)) {

                    if (flightWithinTimeConstraints(departingFlight, startDate, endDate)) {

                        // Iterate through flight set currDepOutputArrCode -> arrCode
                        for (Flight connectingFlight : flightMap.getDirectFlights(currDepOutboundArrCode, arrCode)) {

                            if (validConnectingFlightTimeDiff(departingFlight, connectingFlight)) {
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

                                        if (validConnectingFlightTimeDiff(departingFlight, connectingFlight1) && validConnectingFlightTimeDiff(connectingFlight1, connectingFlight2)) {
                                            flightPlanList.add(new FlightPlanOneWay(departingFlight, connectingFlight1, connectingFlight2));
                                        }
                                    }
                                }
                            }

                        }
                    }
                } // end for departing flights
            }

        }
        
        if(flightPlanList.isEmpty())
        {
            return new ErrorResult("No valid flights could be found!");
        }

        return new SearchResultOneWay(flightPlanList);
    }

    // Performs round trip search for provided airport codes and dates.
    // Does this by performing two one-way trip searches, passing valid Result of those searches
    // to create the SearchResultRoundTrip (if either Result is bad, returns error).
    public Result searchRoundTrip(String depCode, String arrCode, Date departDateStart, Date departDateEnd, Date returnDateStart, Date returnDateEnd) {
        if (departDateStart.after(returnDateStart)) {
            
            System.out.println(departDateStart.toString() + " - " + returnDateStart.toString());
            return new ErrorResult("Depart date set after return date!");
        }

        Result departResults = searchOneWayTrip(depCode, arrCode, departDateStart, departDateEnd);

        if (departResults instanceof ErrorResult) {
            return departResults;
        }

        Result returnResults = searchOneWayTrip(arrCode, depCode, returnDateStart, returnDateEnd);

        if (returnResults instanceof ErrorResult) {
            return returnResults;
        }

        return new SearchResultRoundTrip((SearchResultOneWay) departResults, (SearchResultOneWay) returnResults);
    }

    // Check if String is an airport code
    private boolean isValidAirportCode(String str) {
        return airportCodesSet.contains(str.toUpperCase());
    }

    // Given a HashMap of String -> Flight, return set of Arrival airport codes from all flights
    private Set<String> getArrCodesFromFlightMap(HashMap<String, Flight> map) {
        Set<String> codeSet = new HashSet();

        for (Flight flight : map.values()) {
            codeSet.add(flight.getArrCode());
        }

        return codeSet;
    }

    // Given a HashMap of String -> Flight, return set of Departure airport codes from all flights
    private Set<String> getDepCodesFromFlightMap(HashMap<String, Flight> map) {
        Set<String> codeSet = new HashSet();

        for (Flight flight : map.values()) {
            codeSet.add(flight.getDepCode());
        }

        return codeSet;
    }

    // Given two flights, check if their time difference is valid for connecting flights
    private boolean validConnectingFlightTimeDiff(Flight arrivingFlight, Flight departingFlight) {
        //long diff = departingFlight.getDepTime().getTime() - arrivingFlight.getArrTime().getTime();

        //long diffMinutes = diff / (60000) % 60;
        long diffMinutes = departingFlight.getDepTime().getTime() / 60000 - arrivingFlight.getArrTime().getTime() / 60000;

        //System.out.println(diff + " - " + testDiff);
        return diffMinutes >= 30 && diffMinutes <= 300;
    }

    private boolean flightWithinTimeConstraints(Flight flight, Date startTime, Date endTime) {
        return flight.getDepTime().after(startTime) && flight.getDepTime().before(endTime);
    }

}
