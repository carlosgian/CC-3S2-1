package produccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PremiumFlight implements Flight{
    String id;
    Map<String, Passenger> passengers;
    public PremiumFlight(String id){
        this.id = id;
        passengers = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map<String, Passenger> getPassengersList() {
        return passengers;
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if(passenger.getIfVip()) return passengers.put(passenger.getName(),passenger) == null;
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (passenger.getIfVip()) return passengers.remove(passenger.getName()).equals(passenger);
        return false;
    }
}
