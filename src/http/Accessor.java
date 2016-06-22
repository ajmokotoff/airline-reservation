package http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Accessing WPI server API to get flight, airport, and airplane in form of XML
 * <p>
 *     Accessor is responsible for generating all kinds of http requests and return XML text.
 *     QueryFactory is an element of Accessor, who is responsible for generating get http string.
 *     Further parsing will be done by DataFactory class. Accessor follows singleton pattern
 * </p>
 * @author vincent
 * @since 06/20/2016
 */
public class Accessor{
    /**
     * singleton instance
     */
    private static Accessor _instance=null;
    private QueryFactory queryFactory;
    /**
     * server url
     */
    private final String prefix="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

    private Accessor(){
        queryFactory=new QueryFactory();
    }
    public static Accessor get_instance(){
        if (_instance==null) _instance=new Accessor();
        return _instance;
    }

    /**
     * Make http get request
     * <p>
     *     Given http get query string, return the XML string.
     *     ResetDB will use this function since it is a GET request, but no string returned.
     *     All get request function will call this function.
     * </p>
     * @param query a string from QueryFactory to request with GET
     * @return request text content
     */
    private String httpGet(String query) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            URL url = new URL(query);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code>=200 && code <300) {
                BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                int result = bis.read();
                while (result != -1) {
                    buf.write((byte) result);
                    result = bis.read();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * Make http post request
     * <p>
     *     Given http post query string, connect with server,
     *     and return if connection succeed.
     *     Lock/unlock DB will call this method.
     * </p>
     * @param params a string from QueryFactory to request with POST
     * @return if connection works
     */
    public boolean httpPost(String params) {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(prefix);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(params);
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();

            System.out.println(("Response Code : " + responseCode));

            if ((responseCode >= 200) && (responseCode <= 299)) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                System.out.println("Response content: "+response.toString());
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Get all airports info. in form of XML.
     * @return XML string
     */
    public String getAirports(){
        return httpGet(prefix+"?"+queryFactory.getAirportString());
    }

    /**
     * Get all airplanes info. in form of XML.
     * @return XML string
     */
    public String getAirplanes(){
        return httpGet(prefix+"?"+queryFactory.getAirplaneString());
    }

    /**
     * Get all flights info. departing from the same airport on the same day.
     * @return XML string
     */
    public String getDepartingFlights(String code, int yyyy, int mm, int dd){
        return httpGet(prefix+"?"+queryFactory.getDepartingString(code,yyyy,mm,dd));
    }

    /**
     * Get all flights info. arriving from the same airport on the same day.
     * @return XML string
     */
    public String getArrivingFlights(String code, int yyyy, int mm, int dd){
        return httpGet(prefix+"?"+queryFactory.getArrivingString(code,yyyy,mm,dd));
    }

    /**
     * reset Team02's database.
     */
    public void reset(){
        httpGet(prefix+"?"+queryFactory.getReset());
    }

    /**
     * lock the DB before reserve the saet
     * @return if the lock operation succeeds.
     */
    public boolean lockDB (){
        System.out.println("\nSending 'POST' to lock database");
        return httpPost(queryFactory.getLock());
    }

    /**
     * unlock the DB after reserve the saet
     * @return if the unlock operation succeeds.
     */
    public boolean unlockDB(){
        System.out.println("\nSending 'POST' to unlock database");
        return httpPost(queryFactory.getUnlock());
    }

    /**
     *
     * @param flightNumber flight number
     * @param isCoach bool value to indicate coach or first
     * @return if reserving succeeds
     */
    public boolean reserveSeat(String flightNumber, boolean isCoach) {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(prefix);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(queryFactory.getReserving(flightNumber,isCoach));
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' to ReserveFlights");
            System.out.println(("Response Code : " + responseCode));
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            System.out.println("Response content: "+response.toString());
            return (responseCode >= 200) && (responseCode <= 299);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

/**
 * Generate http query string
 * <p>
 *     QueryFactory is an element of Accessor, who is responsible for generating get http string.
 * </p>
 * @author vincent
 * @since 06/20/2016
 */
class QueryFactory{
    /**
     * our team is team02
     */
    private String team = "team=Team02";

    QueryFactory(){}//package locally

    String getAirportString(){
        String airportSuffix = "&action=list&list_type=airports";
        return team+ airportSuffix;
    }

    String getAirplaneString(){
        String airplaneSuffix = "&action=list&list_type=airplanes";
        return team+ airplaneSuffix;
    }

    String getDepartingString(String code, int yyyy, int mm, int dd){
        String departingSuffix = "&action=list&list_type=departing";
        return team+ departingSuffix +String.format("&airport=%s&day=%d_%02d_%02d",code,yyyy,mm,dd);
    }

    String getArrivingString(String code, int yyyy, int mm, int dd){
        String arrivingSuffix = "&action=list&list_type=arriving";
        return team+ arrivingSuffix +String.format("&airport=%s&day=%d_%02d_%02d",code,yyyy,mm,dd);
    }

    String getReset(){
        String resetSuffix = "&action=resetDB";
        return team+ resetSuffix;
    }

    String getLock(){
        String lockSuffix = "&action=lockDB";
        return team+ lockSuffix;
    }

    String getUnlock(){
        String unlockSuffix = "&action=unlockDB";
        return team+ unlockSuffix;
    }

    String getReserving(String flightNumber, boolean isCoach){
        String reservingSuffix = "&action=buyTickets";
        return team+ reservingSuffix +"&flightData="+"<Flights>"
                + "<Flight number=\"" + flightNumber + "\" seating=\""+(isCoach?"Coach":"First")+"\"/>"
                + "</Flights>";
    }
}
