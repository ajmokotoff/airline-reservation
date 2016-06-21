package client;

public class Airport {
    private String name, code;
    private double lat, lon;

    public Airport(){}

    public Airport(String name, String code, double lat, double lon){
        this.name=name;
        this.code=code;
        this.lat=lat;
        this.lon=lon;
    }

    public Airport(String name, String code, String lat, String lon){
        this(name,code,Double.parseDouble(lat),Double.parseDouble(lon));
    }

    public String toString(){
        return "< "+ name+", "+code+", lat:"+lat+", lon:"+lon+ " >";
    }
}
