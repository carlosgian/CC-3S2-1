import org.junit.jupiter.api.Test;

public class FacturaDaoIntegracionTest {
    @Test
    public void testGuardar(){
        FacturaDAO ejemplo = new FacturaDAO();
        Factura nuevaFactura = new Factura("Dodo",23);
        ejemplo.guardar(nuevaFactura);

    }

}
