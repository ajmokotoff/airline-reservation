package client;

/**
 * Airplane class
 * <p>
 *     The class contains all the info. for a single airplane from XML query results.
 * </p>
 *
 * @author vincent
 * @since 06/20/2016
 */
public class Airplane {
    /**
     * model model of airport
     */
    private String model;
    /**
     * manf manufacturer of airplane
     */
    private String manf;
    /**
     * total number of first class seats
     */
    private int first;
    /**
     * total number of coach class seats
     */
    private int coach;

    public Airplane(){}

    /**
     * constructor with integer seat number input
     */
    public Airplane(String model, String manf, int first, int coach){
        this.model=model;
        this.manf=manf;
        this.first=first;
        this.coach=coach;
    }

    /**
     * constructor with integer seat number input
     */
    public Airplane(String model, String manf, String first, String coach){
        this(model,manf,Integer.parseInt(first),Integer.parseInt(coach));
    }

    /**
     * override toString to smart print the airplane class
     * @return a string included model, manufacturer, and seat number within <>
     */
    public String toString(){
        return "< "+model+", "+manf+", seat(first/coach):"+first+"/"+coach+" >";
    }
}
