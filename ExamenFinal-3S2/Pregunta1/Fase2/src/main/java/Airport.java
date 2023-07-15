public class Airport {
    public static void main(String[] args) {
        Flight vuelo1 = new EconomyFlight();
        Flight vuelo2 = new BusinessFlight();

        Passenger pasajero1 = new Passenger("timmy",false);
        Passenger pasajero2 = new Passenger("jhon", true);

        System.out.println(vuelo1.addPassenger(pasajero1));
        System.out.println(vuelo2.addPassenger(pasajero1));
        System.out.println(vuelo1.addPassenger(pasajero2));
        System.out.println(vuelo2.addPassenger(pasajero2));

        System.out.println(vuelo1.removePassenger(pasajero1));
        System.out.println(vuelo2.removePassenger(pasajero1));
        System.out.println(vuelo1.removePassenger(pasajero2));
        System.out.println(vuelo2.removePassenger(pasajero2));
    }
}

