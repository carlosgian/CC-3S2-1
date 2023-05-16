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
    //Luego de agregar una instruccion para cambiar el turno en jugar(), las pruebas pasan
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
        game.jugar(2,1);
        game.jugar(0,1);
        game.jugar(1,0);
        game.jugar(0,2);
        assertEquals(true, game.winCondition());
    }

    //Escribimos la prueba para que detecte una línea horizontal. Al principio fallará como es lo esperado.
    //Luego implementamos la logica en el metodo winCondition() con otro for.
    @Test
    public void testVerticalLine(){
        TicTacToe gameTemp = new TicTacToe();
        gameTemp.jugar(0,0);
        gameTemp.jugar(0,1);
        gameTemp.jugar(1,0);
        gameTemp.jugar(1,1);
        gameTemp.jugar(2,0);
        assertEquals(true, gameTemp.winCondition());
    }

    //casi para terminar ya, escribimos las pruebas para detectar si se completo alguna diagonal. Hay dos de estas, por lo que se necesitan dos pruebas
    //para implementar la deteccion de esta condicion, simplemente agregamos dos IF.
    @Test
    public void testMainDiagonal(){
        TicTacToe gameTemp = new TicTacToe();
        gameTemp.jugar(0,0);
        gameTemp.jugar(0,1);
        gameTemp.jugar(1,1);
        gameTemp.jugar(1,0);
        gameTemp.jugar(2,2);
        assertEquals(true, gameTemp.winCondition());
    }

    //Para la diagonal secundaria se implementa una prueba similar.
    //La implementacion se hace de manera similar.
    @Test
    public void testSecDiagonal(){
        TicTacToe gameTemp = new TicTacToe();
        gameTemp.jugar(0,2);
        gameTemp.jugar(0,1);
        gameTemp.jugar(1,1);
        gameTemp.jugar(1,0);
        gameTemp.jugar(2,0);
        assertEquals(true, gameTemp.winCondition());
    }

    //Para termianr queremos detectar si el juego queda en empate. Esto es sencillo, pues basta que se haya llenado el tablero y que
    //ninguna condición de victoria se haya cumplido
    //Para esto vamos a implementar un metodo nuevo isDraw
    @Test
    public void testDraw(){
        TicTacToe gameTemp = new TicTacToe();
        gameTemp.jugar(0, 1);
        gameTemp.jugar(0, 0);
        gameTemp.jugar(0, 2);
        gameTemp.jugar(1, 2);
        gameTemp.jugar(1, 0);
        gameTemp.jugar(1, 1);
        gameTemp.jugar(2, 2);
        gameTemp.jugar(2, 0);
        gameTemp.jugar(2, 1);
        
        assertEquals(true, gameTemp.isDraw());
    }
}
