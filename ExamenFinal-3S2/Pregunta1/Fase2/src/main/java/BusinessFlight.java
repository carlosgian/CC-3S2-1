public class BusinessFlight implements Flight{

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.getIfVip()) return true;
        else return false;
    }
    @Override
    public boolean removePassenger(Passenger passenger) {
        return false;
    }
}
