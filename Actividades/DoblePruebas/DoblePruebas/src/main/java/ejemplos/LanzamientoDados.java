package ejemplos;
import java.util.random.*;
public class LanzamientoDados {
    private final int NUMERO_LADOS = 6;
    private final NumerosAleatorios rnd;

    //private final RandomGenerator rnd = RandomGenerator.getDefault();

    // Completa
    LanzamientoDados(NumerosAleatorios r){
        this.rnd = r;
    }
    public String asText() {
        int lanzado = rnd.nextInt(NUMERO_LADOS) + 1;

        return String.format("Sacastes un %d", lanzado);
    }
}
