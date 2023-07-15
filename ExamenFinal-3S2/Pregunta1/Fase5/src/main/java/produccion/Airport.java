package produccion;

public class Airport {
    public static void main(String[] args) {
        Flight economyFlight = new EconomyFlight("1");
        Flight businessFlight = new BusinessFlight("2");

        Passenger pasajero1 = new Passenger("timmy",false);
        Passenger pasajero2 = new Passenger("jhon", true);

        economyFlight.addPassenger(pasajero1);
        economyFlight.addPassenger(pasajero2);
        System.out.println("Lista de pasajeros en el vuelo economico luego de intentar agregar a dos tipos de pasajeros");
        for (Passenger passenger : economyFlight.getPassengersList().values()){
            System.out.println(passenger.getName());
        }

        economyFlight.removePassenger(pasajero1);
        economyFlight.removePassenger(pasajero2);
        System.out.println("Lista de pasajeros en el vuelo economico luego de intentar remover a dos tipos de pasajeros");
        for (Passenger passenger : economyFlight.getPassengersList().values()){
            System.out.println(passenger.getName());
        }

        businessFlight.addPassenger(pasajero1);
        businessFlight.addPassenger(pasajero2);
        System.out.println("Lista de pasajeros en el vuelo de negocios luego de intentar agregar a dos tipos de pasajeros");
        for (Passenger passenger : businessFlight.getPassengersList().values()){
            System.out.println(passenger.getName());
        }

        businessFlight.removePassenger(pasajero1);
        businessFlight.removePassenger(pasajero2);
        System.out.println("Lista de pasajeros en el vuelo de negocios luego intentar remover a dos tipos de pasajeros");
        for (Passenger passenger : businessFlight.getPassengersList().values()){
            System.out.println(passenger.getName());
        }
    }
}
