public class EconomyFlight implements Flight {
    @Override
    public boolean addPassenger(Passenger passenger) {
        return true;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (passenger.getIfVip()) return false;
        else return true;
    }
}

