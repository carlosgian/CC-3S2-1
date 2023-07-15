package produccion;

import java.util.ArrayList;

public class BusinessFlight implements Flight {
    String id;
    ArrayList<Passenger> passengers;
    public BusinessFlight(String id){
        this.id = id;
        passengers = new ArrayList<>();
    }
    public String getId(){
        return id;
    }
    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.getIfVip()) return passengers.add(passenger);
        return false;
    }
    @Override
    public boolean removePassenger(Passenger passenger) {
        return false;
    }
    @Override
    public ArrayList<Passenger> getPassengersList() {
        return passengers;
    }
}
