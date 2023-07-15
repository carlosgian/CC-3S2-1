package produccion;

import java.util.ArrayList;

public class PremiumFlight implements Flight{
    String id;
    ArrayList<Passenger> passengers;
    public PremiumFlight(String id){
        this.id = id;
        passengers = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public ArrayList<Passenger> getPassengersList() {
        return passengers;
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if(passenger.getIfVip()) return passengers.add(passenger);
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger);
    }
}
