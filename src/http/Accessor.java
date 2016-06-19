package http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Post is not finished yet.(lock/unlock/ reserve seat)
 *
 * Accessor is responsible for generating all kinds of http requests and return XML text.
 * QueryFactory is an element of Accessor, who is responsible for generating get http string.
 * Further parsing will be done by DataFactory class.
 *
 */
public class Accessor{
    private QueryFactory queryFactory;

    public Accessor(){
        queryFactory=new QueryFactory();
    }
    // given http get query string, return the XML string.
    // ResetDB will use this function since it is a GET request, but no string returned.
    private String httpGet(String query) {
        try {
            URL url = new URL(query);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //int code = con.getResponseCode();
            BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            return buf.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
    public String getAirports(){
        return httpGet(queryFactory.getAirportString());
    }
    public String getAirplanes(){
        return httpGet(queryFactory.getAirplaneString());
    }
    public String getDepartingFlights(String code, int yyyy, int mm, int dd){
        return httpGet(queryFactory.getDepartingString(code,yyyy,mm,dd));
    }
    public String getArrivingFlights(String code, int yyyy, int mm, int dd){
        return httpGet(queryFactory.getArrivingString(code,yyyy,mm,dd));
    }
    public void reset(){
        httpGet(queryFactory.getReset());
    }
    public int lockDB(){
        return 0;
    }
    public int unlockDB(){
        return 0;
    }
    public int reserveSeat(){
        // first, which seat to reserve
        // second what if no seat available
        return 0;
    }
}

// QueryFactory is an element of Accessor, who is responsible for generating get http string.
class QueryFactory{
    // all the get url strings
    private static String prefix="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?";
    private static String team = "team=Team02";
    private static String reset = "&action=resetDB";
    private static String airportSuffix = "&action=list&list_type=airports";
    private static String airplaneSuffix = "&action=list&list_type=airplanes";
    private static String departingSuffix = "&action=list&list_type=departing";
    private static String arrivingSuffix = "&action=list&list_type=arriving";

    QueryFactory(){//package locally
    }
    String getAirportString(){
        return prefix+team+airportSuffix;
    }
    String getAirplaneString(){
        return prefix+team+airplaneSuffix;
    }
    String getDepartingString(String code, int yyyy, int mm, int dd){
        return prefix+team+departingSuffix+String.format("&airport=%s&day=%d_%02d_%02d",code,yyyy,mm,dd);
    }
    String getArrivingString(String code, int yyyy, int mm, int dd){
        return prefix+team+arrivingSuffix+String.format("&airport=%s&day=%d_%02d_%02d",code,yyyy,mm,dd);
    }
    String getReset(){
        return prefix+team+reset;
    }
}
