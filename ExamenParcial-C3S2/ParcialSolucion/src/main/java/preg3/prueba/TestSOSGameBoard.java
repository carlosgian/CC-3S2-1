package preg3.prueba;

import org.junit.jupiter.api.Test;
import preg3.produccion.SOSGameBoard;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestSOSGameBoard {
    static Random random = new Random();
    static int rand = random.nextInt(8) + 3;
    public static int sideLength = rand;

    public static char gameType = random.nextInt(2) == 0 ? 'S' : 'G';
    private static SOSGameBoard board = new SOSGameBoard(sideLength, gameType);

    //Criterio de aceptacion 1.1
    @Test
    public void testEmptyBoard() {
        for (int row = 0; row < sideLength; row++) {
            for (int column = 0; column < sideLength; column++) {
                assertEquals(SOSGameBoard.Box.EMPTY, board.getBox(row, column));
            }
        }
    }

    @Test
    public void testInvalidRow() {
        assertEquals(null, board.getBox(sideLength, 1));
    }

    //Criterio de Aceptacion 1.3
    @Test
    public void testInvalidColumn() {
        assertEquals(null, board.getBox(1, sideLength));
    }
    @Test
    public void testMakePlay(){
        int row = random.nextInt(sideLength);
        int column = random.nextInt(sideLength);
        board.makePlay(row,column, SOSGameBoard.Box.LETTER_S);
        assertEquals(SOSGameBoard.Box.LETTER_S, board.getBox(row, column));
    }

    @Test
    public void testMakePlayInvalid(){
        int row = sideLength;
        int column = sideLength;
        assertThrows(IllegalArgumentException.class, ()->board.makePlay(row, column , SOSGameBoard.Box.LETTER_S));
    }

    @Test
    public void testHowManySOS(){
        board.makePlay(0,0, SOSGameBoard.Box.LETTER_S);
        board.makePlay(0,1, SOSGameBoard.Box.LETTER_S);
        board.makePlay(0,2, SOSGameBoard.Box.LETTER_S);
        board.makePlay(1,0, SOSGameBoard.Box.LETTER_S);
        board.makePlay(1,2, SOSGameBoard.Box.LETTER_S);
        board.makePlay(2,0, SOSGameBoard.Box.LETTER_S);
        board.makePlay(2,1, SOSGameBoard.Box.LETTER_S);
        board.makePlay(2,2, SOSGameBoard.Box.LETTER_S);
        int row = 1 ;
        int column = 1;
        assertEquals(4, board.howManySOS(row, column, SOSGameBoard.Box.LETTER_O));
    }

}