package produccion;

import java.util.ArrayList;

public class EconomyFlight implements Flight{
    String id;
    ArrayList<Passenger> passengers;
    public EconomyFlight(String id){
        this.id = id;
        passengers = new ArrayList<>();
    }
    public String getId(){
        return id;
    }
    @Override
    public boolean addPassenger(Passenger passenger) {
        return passengers.add(passenger);
    }
    @Override
    public boolean removePassenger(Passenger passenger) {
        if (!passenger.getIfVip()) return passengers.remove(passenger);
        return false;
    }
    @Override
    public ArrayList<Passenger> getPassengersList() {
        return passengers;
    }
}
