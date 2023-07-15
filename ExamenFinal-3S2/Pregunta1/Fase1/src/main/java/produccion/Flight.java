package produccion;

public class Flight {
    private String flightType;

    public Flight(String flightType){
        this.flightType = flightType;
    }

    public String getFlightType() {
        return flightType;
    }

    public boolean addPassenger(Passenger passenger){
        if (flightType.equals("Economica")) return true;
        else if (flightType.equals("Negocios")) {
            if (passenger.getIfVip()) return true;
            else return false;
        }
        else throw new IllegalArgumentException();
    }

    public boolean removePassenger(Passenger passenger){
        if (passenger.getIfVip()) return false;
        else return true;
    }
}
