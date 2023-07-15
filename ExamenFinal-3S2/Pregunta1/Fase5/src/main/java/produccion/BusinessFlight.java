package produccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusinessFlight implements Flight {
    String id;
    Map<String, Passenger> passengers;
    public BusinessFlight(String id){
        this.id = id;
        passengers = new HashMap<>();
    }
    public String getId(){
        return id;
    }
    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.getIfVip()) return passengers.put(passenger.getName(), passenger) == null;
        return false;
    }
    @Override
    public boolean removePassenger(Passenger passenger) {
        return false;
    }
    @Override
    public Map<String, Passenger> getPassengersList() {
        return passengers;
    }
}
