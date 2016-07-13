package http;

public class AccessorTest {

    public static void main(String[] args) {
        Accessor accessor =Accessor.get_instance();

        System.out.println(accessor.getAirports());
        System.out.println(accessor.getAirplanes());
        System.out.println(accessor.getDepartingFlights("BOS", 2016, 5, 14));
        System.out.println(accessor.getArrivingFlights("BOS", 2016, 5, 14));

        // fail1 must lock db first.
        System.out.println("reserved: " + accessor.reserveSeat("2976", true));


        // plz notice the number of coach seat reserved on flight 2976
        System.out.println("\n"+accessor.getDepartingFlights("BOS", 2016, 5, 14).substring(64,100)
                +"   ...   "+accessor.getDepartingFlights("BOS", 2016, 5, 14).substring(300,360));

        // lock first, than reserving is fine.
        System.out.println("locked: " + accessor.lockDB());
        System.out.println("reserved: " + accessor.reserveSeat("2976", false));
        System.out.println("unlocked: " + accessor.unlockDB());

        // Now seat number increased
        System.out.println("\n"+accessor.getDepartingFlights("BOS", 2016, 5, 14).substring(64,90)
                +"   ...   "+accessor.getDepartingFlights("BOS", 2016, 5, 14).substring(280,360));

        // after resetting, the number is the same as at the beginning.
        accessor.reset();
        System.out.println("\n"+accessor.getDepartingFlights("BOS", 2016, 5, 14).substring(64,90)
                +"   ...   "+accessor.getDepartingFlights("BOS", 2016, 5, 14).substring(280,360));
    }
}