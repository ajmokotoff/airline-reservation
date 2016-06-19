package http;

public class AccessorTest {

    public static void main(String[] args){
        Accessor accessor=new Accessor();
        System.out.println(accessor.getAirports());
        System.out.println(accessor.getAirplanes());
        System.out.println(accessor.getDepartingFlights("BOS",2016,5,14));
        System.out.println(accessor.getArrivingFlights("BOS",2016,5,14));
        accessor.reset();
        // test if seat reserved
        // test if locked/unlocked
    }
}