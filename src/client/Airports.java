package client;

import http.Accessor;
import java.util.HashMap;

/**
 * Airports class
 * <p>
 *     This class is designed to cache airport info. since this kind of info. is not frequently changed and this saves times to access server
 * </p>
 * @author vincent
 * @since 07/11/2016
 */
public class Airports {
    /**
     * class instance, singleton pattern
     */
    private static Airports _instance = null;

    /**
     * hash table of airports
     */
    private HashMap<String,Airport> airports;

    /**
     * fetch airports hash table
     * @return hash table of airplanes
     */
    public static HashMap<String,Airport> get(){
        if (_instance==null){
            _instance=new Airports();
        }
        return _instance.airports;
    }

    private Airports(){
        DataFactory dataFactory=new DataFactory();
        Accessor accessor = Accessor.get_instance();
        airports=dataFactory.xml2Airports(accessor.getAirports());
    }

    /**
     * reconnect to serve and refresh the content
     */
    public static void refresh(){
        DataFactory dataFactory=new DataFactory();
        Accessor accessor = Accessor.get_instance();
        if (_instance==null){
            _instance=new Airports();
        }
        _instance.airports=dataFactory.xml2Airports(accessor.getAirports());
    }
}
