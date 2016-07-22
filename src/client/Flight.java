package client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Flight class
 * <p>
 *     The class contain the detail info. of the flight
 * </p>
 * @author vincent
 * @since 06/20/2016
 */
public class Flight {
    /**
     * flight number, assuming unique
     */
    private String flightNo;
    /**
     * departure airport code
     */
    private String depCode;
    /**
     * arrival airport code
     */
    private String arrCode;
    /**
     * departing time
     */
    private Date depTime;
    /**
     * arriving time
     */
    private Date arrTime;
    /**
     * travel time for this single trip
     */
    private int flightTime;
    /**
     * airplane model
     */
    private String model;
    /**
     * number of first class seats reserved
     */
    private int first;
    /**
     * number of coach class seats reserved
     */
    private int coach;
    /**
     * the price of first class seat
     */
    private double firstPrice;
    /**
     * the price of coach class seat
     */
    private double coachPrice;

    // Used to format the departure and arrival time Strings into Date objects
    private static DateFormat dateFormatter = new SimpleDateFormat("yyyy MMMM dd HH:mm Z");
    
    
    public Flight(){}

    // the time need to transferring in the future, now they are saved as string

    /**
     * constructor for integer type seat number and double type seat price
     */
    public Flight(String flightNo, String depCode, String arrCode,
                  String depTime, String arrTime, int flightTime, String model, int first, int coach, double firstPrice, double coachPrice) throws ParseException{
        
        //dateFormatter.setTimeZone(Airports.get().get(depCode).getTimezone());
        
        Calendar cal = Calendar.getInstance();
        
        //Date temp = dateFormatter.parse(depTime);
        cal.setTime(dateFormatter.parse(depTime));
        cal.setTimeZone(Airports.get().get(depCode).getTimezone());
        this.depTime = cal.getTime();
        
        cal.setTime(dateFormatter.parse(arrTime));
        cal.setTimeZone(Airports.get().get(depCode).getTimezone());
        this.arrTime = cal.getTime();
        
        this.flightNo=flightNo;
        this.depCode=depCode;
        this.arrCode=arrCode;
        //this.depTime=dateFormatter.parse(depTime);
        //this.arrTime=dateFormatter.parse(arrTime);
        this.flightTime=flightTime;
        this.model=model;
        this.first=first;
        this.firstPrice=firstPrice;
        this.coach=coach;
        this.coachPrice=coachPrice;
    }

    /**
     * constructor for all string
     */
    public Flight(String flightNo, String depCode, String arrCode,
                  String depTime, String arrTime, String flightTime, String model,
                  String first, String coach, String  firstPrice, String coachPrice) throws ParseException{

        this(flightNo,depCode,arrCode,depTime,arrTime,
                Integer.parseInt(flightTime),model,Integer.parseInt(first),Integer.parseInt(coach),
                Double.parseDouble(firstPrice.replaceAll("[^0-9\\.]","")),Double.parseDouble(coachPrice.replaceAll("[^0-9\\.\\s]","")));
    }

    /**
     * override toString to smart print the airport class
     * @return flight number, dep/arr airports' code, airplane model, number of seats reserved
     */
    public String toString(){
        // won't print all the attributes
        return "["+flightNo+","+depCode+"=>"+arrCode+",Model:"+model+",seat reserved(first|coach):"
                +first+"/"+((Airplane)Airplanes.get().get(model)).getFirst()+"|"
                +coach+"/"+((Airplane)Airplanes.get().get(model)).getCoach()+"]";
    }


    /**
     * check how many coach seats left
     * @return left number of seat
     */
    public int checkCoachLeft(){
        int total = ((Airplane)Airplanes.get().get(model)).getCoach();
        return total-coach;
    }

    /**
     * check how many first class seat left
     * @return left number of seat
     */
    public int checkFirstLeft(){
        int total = ((Airplane)Airplanes.get().get(model)).getFirst();
        return total-first;
    }

    public String getFlightNo()
    {
        return flightNo;
    }
    
    public String getDepCode()
    {
        return depCode;
    }
    
    public String getArrCode()
    {
        return arrCode;
    }
    
    public Date getDepTime()
    {
        return depTime;
    }
    
    public Date getArrTime()
    {
        return arrTime;
    }
    
    public int getFlightTime()
    {
        return flightTime;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public int getFirst() {
        return first;
    }
    
    public int getCoach() {
        return coach;
    }
    
    public double getFirstPrice()
    {
        return firstPrice;
    }
    
    public double getCoachPrice()
    {
        return coachPrice;
    }

}
