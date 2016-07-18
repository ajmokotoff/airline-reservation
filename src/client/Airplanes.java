package client;

import http.Accessor;
import java.util.HashMap;

/**
 * Airplanes class
 * <p>
 *     This class is designed to cache airplane info. since this kind of info. is not frequently changed and this saves times to access server
 * <p/>
 * @author vincent
 * @since 7/12/2016
 */
public class Airplanes {
    /**
     * class instance, singleton pattern
     */
    private static Airplanes _instance=null;

    /**
     * hash table of airplanes
     */
    private HashMap<String,Airplane> airplanes;

    /**
     * fetch airplane hash table
     * @return hash table of airplanes
     */
    public static HashMap<String,Airplane> get(){
        if (_instance==null){
            _instance=new Airplanes();
        }
        return _instance.airplanes;
    }

    private Airplanes(){
        DataFactory dataFactory=new DataFactory();
        Accessor accessor = Accessor.get_instance();
        airplanes=dataFactory.xml2Airplanes(accessor.getAirplanes());
    }

    /**
     * reconnect to serve and refresh the content
     */
    public static void refresh(){
        DataFactory dataFactory=new DataFactory();
        Accessor accessor = Accessor.get_instance();
        if (_instance==null){
            _instance=new Airplanes();
        }
        _instance.airplanes=dataFactory.xml2Airplanes(accessor.getAirplanes());
    }
}
