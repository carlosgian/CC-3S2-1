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

    //Escribimos la prueba para comprobar que detecte líneas horizontales. Al principio falla porque no hemos agregado la lógica al método winCondition()
    //Implemetamos la primera parte de winCondition para que detecte tan pronto como un jugador detecte la línea horizontal
    @Test
    public void testHorizontalLine(){
        game.jugar(0,1);
        game.jugar(0,2);
        assertEquals(true, game.winCondition());
    }

    //Escribimos la prueba para que detecte una línea horizontal. Al principio fallará como es lo esperado.
    //Luego implementamos la logica en el metodo winCondition() con otro for.
    @Test
    public void testVerticalLine(){
        TicTacToe gameTemp = new TicTacToe();
        gameTemp.jugar(0,0);
        gameTemp.jugar(1,0);
        gameTemp.jugar(2,0);
        assertEquals(true, gameTemp.winCondition());
    }
}
