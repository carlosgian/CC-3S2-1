import org.example.ReservaSistema;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservaSistemaTest {
    ReservaSistema sistema = new ReservaSistema();

    @Test
    public void siSoloUnaReserva(){
        int[] ejemplo = new int[]{1,2};
        sistema.reservar(ejemplo);
        ArrayList<int[]> prueba = new ArrayList<>();
        prueba.add(ejemplo);
        assertEquals(prueba, sistema.reservar(ejemplo));
    }

    @Test
    public void rechazarReservaSiHayCruce() {
        int[] ejemplo = new int[]{1, 3};
        sistema.reservar(ejemplo);
        ArrayList<int[]> prueba = new ArrayList<>();
        prueba.add(new int[]{1, 2});
        assertEquals(prueba, sistema.reservar(ejemplo));
    }
}
