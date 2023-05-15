package preg2.prueba;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import preg2.produccion.TicTacToe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTicTacToe {
    private static TicTacToe game;
    @BeforeAll
    public static void setUp(){
        game = new TicTacToe();
    }
    @Test
    public void testJugarColumnaValida(){
        assertThrows(RuntimeException.class, () -> game.jugar(-1,2) );
    }
    @Test
    public void testJugarColumnaInvalida(){
        assertThrows(RuntimeException.class, () -> game.jugar(2,-1));
    }

    @Test
    public void testJugarEspacioOcupado(){
        game.jugar(2,2);
        assertThrows(RuntimeException.class, () -> game.jugar(2,2));
    }

    //Escribimos la prueba para el REQUISITO 2: soporte para 2 jugadores.
    //La prueba falla al principio porque no hemos hecho que el metodo jugar cambie turnos.
    //Luego de agregar una instruccion para cambiar el turno en jugar(), el metodo pasa.
    @Test
    public void turnoInicial(){
        TicTacToe gameTemp = new TicTacToe();
        assertEquals('X', gameTemp.getTurn());
    }

    @Test
    public void turnoDespuesDeO(){
        game.jugar(1,1);
        assertEquals('X', game.getTurn());
    }

    @Test
    public void turnoDespuesDeX(){
        game.jugar(0,0);
        assertEquals('O', game.getTurn());

    }

    //La prueba predeterminada, si no se cumple ninguna WinCondition, entonces no hay ganador

    @Test
    public void testNoWinner(){
        assertEquals(false, game.winCondition());
    }

    public void testHorizontalLine(){
        game.jugar(0,1);
        game.jugar(0,2);
        assertEquals(true, game.winCondition());
    }
}
