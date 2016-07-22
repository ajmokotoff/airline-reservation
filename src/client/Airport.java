package client;

import java.util.TimeZone;

/**
 * Airport class
 * <p>
 *     The class contains all the info. for a single airport from XML query results.
 * </p>
 * @author vincent
 * @since 06/20/2016
 */
public class Airport {
    /**
     * airport full name
     */
    private String name;
    /**
     * IATA airport station code
     */
    private String code;
    /**
     * latitude of airport
     */
    private double lat;
    /**
     * longitude of airport
     */
    private double lon;
    /**
     * Time zone
     */
    private TimeZone tz;

    public Airport(){}

    public double getLat(){return lat;}
    public double getLon(){return lon;}
    public TimeZone getTimezone(){return tz;}
    /**
     * Constructor with double lat,lon input
     */
    public Airport(String name, String code, double lat, double lon){
        this.name=name;
        this.code=code;
        this.lat=lat;
        this.lon=lon;
        String tzId = TimeZoneMapper.latLngToTimezoneString(lat,lon);
        this.tz = TimeZone.getTimeZone(tzId);

    }

    /**
     * Constructor with string lat,lon input
     */
    public Airport(String name, String code, String lat, String lon){
        this(name,code,Double.parseDouble(lat),Double.parseDouble(lon));
    }

    /**
     * override toString to smart print the airport class
     * @return a string included name, code, lat and lon within <>
     */
    public String toString(){
        return "< "+ name+", "+code+", lat:"+lat+", lon:"+lon+ " >";
    }
}
