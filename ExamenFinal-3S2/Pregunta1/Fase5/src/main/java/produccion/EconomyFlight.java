package produccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EconomyFlight implements Flight{
    String id;
    Map<String, Passenger> passengers;
    public EconomyFlight(String id){
        this.id = id;
        passengers = new HashMap<>();
    }
    public String getId(){
        return id;
    }
    @Override
    public boolean addPassenger(Passenger passenger) {
        return passengers.put(passenger.getName(),passenger) == null;
    }
    @Override
    public boolean removePassenger(Passenger passenger) {
        if (!passenger.getIfVip()) return passengers.remove(passenger.getName()).equals(passenger);
        return false;
    }
    @Override
    public Map<String, Passenger> getPassengersList() {
        return passengers;
    }
}
