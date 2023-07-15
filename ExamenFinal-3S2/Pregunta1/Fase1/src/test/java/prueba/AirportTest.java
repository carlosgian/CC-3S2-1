package prueba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.Flight;
import produccion.Passenger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {
    private Flight vuelo1;
    private Flight vuelo2;
    private Passenger pasajero1;
    private Passenger pasajero2;

    @BeforeEach
    void setup(){
        vuelo1 = new Flight("Economica");
        vuelo2 = new Flight("Negocios");

        pasajero1 = new Passenger("timmy",false);
        pasajero2 = new Passenger("jhon", true);
    }

    @Test
    public void testAddPassenger(){
        assertEquals(true, vuelo1.addPassenger(pasajero1));
        assertEquals(false, vuelo2.addPassenger(pasajero1));
        assertEquals(true, vuelo1.addPassenger(pasajero2));
        assertEquals(true,vuelo2.addPassenger(pasajero2));
    }

    @Test
    public void testRemovePassenger(){
        assertEquals(true, vuelo1.removePassenger(pasajero1));
        assertEquals(true, vuelo2.removePassenger(pasajero1));
        assertEquals(false, vuelo1.removePassenger(pasajero2));
        assertEquals(false,vuelo2.removePassenger(pasajero2));
    }
}
