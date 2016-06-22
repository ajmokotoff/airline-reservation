package client;

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
    private String depTime;
    /**
     * arriving time
     */
    private String arrTime;
    /**
     * travel time for this single trip
     */
    private String flightTime;
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
    private double coachPrie;

    public Flight(){}

    // the time need to transferring in the future, now they are saved as string

    /**
     * constructor for integer type seat number and double type seat price
     */
    public Flight(String flightNo, String depCode, String arrCode,
                  String depTime, String arrTime, String flightTime, String model, int first, int coach, double firstPrice, double coachPrice){
        this.flightNo=flightNo;
        this.depCode=depCode;
        this.arrCode=arrCode;
        this.depTime=depTime;
        this.arrTime=arrTime;
        this.flightTime=flightTime;
        this.model=model;
        this.first=first;
        this.firstPrice=firstPrice;
        this.coach=coach;
        this.coachPrie=coachPrice;
    }

    /**
     * constructor for all string
     */
    public Flight(String flightNo, String depCode, String arrCode,
                  String depTime, String arrTime, String flightTime, String model,
                  String first, String coach, String  firstPrice, String coachPrice){
        // regex is to remove all the irrelevant char
        this(flightNo,depCode,arrCode,depTime,arrTime,flightTime,model,Integer.parseInt(first),Integer.parseInt(coach),
                Double.parseDouble(firstPrice.replaceAll("[^0-9\\.]","")),Double.parseDouble(coachPrice.replaceAll("[^0-9\\.\\s]","")));
    }

    /**
     * override toString to smart print the airport class
     * @return flight number, dep/arr airports' code, airplane model, number of seats reserved
     */
    public String toString(){
        // won't print all the attributes
        return "< "+flightNo+", "+depCode+"->"+arrCode+", Model:"+model+", seat reserved (first/coach):"+first+"/"+coach+" >";
    }

}
