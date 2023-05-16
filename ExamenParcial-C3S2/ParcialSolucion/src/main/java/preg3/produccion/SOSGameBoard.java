package preg3.produccion;

import java.util.ArrayList;
import java.util.Random;

public class SOSGameBoard {
    public enum Box {EMPTY, LETTER_S, LETTER_O}

    private int squaresPerSide;

    private char gameType;

    private Box[][] grid;

    public SOSGameBoard() {
        squaresPerSide = 3;
        grid = new Box[squaresPerSide][squaresPerSide];
        gameType = 'S';
        initBoard();
    }
    public SOSGameBoard(int squaresPerSide, char gameType) {
        this.squaresPerSide = squaresPerSide;
        this.gameType = gameType;
        grid = new Box[squaresPerSide][squaresPerSide];
        initBoard();
    }
    public Box getBox(int row, int column) {
        if (row >= 0 && row < squaresPerSide && column >= 0 && column < squaresPerSide) return grid[row][column];
        else {
            return null;
        }
    }
    public void initBoard(){
        for( int row = 0; row < squaresPerSide; row++ ){
            for ( int column = 0; column < squaresPerSide; column++){
                grid[row][column] = Box.EMPTY;
            }
        }
    }

    public void makePlay(int row, int column, Box chosen){
        if ( row >= 0 && row < squaresPerSide && column >= 0 && column < squaresPerSide ) grid[row][column] = chosen;
        else {
            throw new IllegalArgumentException("El argumento debe estar dentro de los limited del tablero");
        }
    }

    boolean detectSOSWhenS(int row, int column, int[] duple){
        return ( getBox(row + duple[0], column + duple[1]) == Box.LETTER_O && getBox(row + 2 * duple[0], column + 2 * duple[1]) == Box.LETTER_S);
    }

    boolean detectSOSWhenO(int row, int column, int[] duple){
        return ( getBox(row + duple[0], column + duple[1]) == Box.LETTER_S && getBox(row - duple[0], column - duple[1]) == Box.LETTER_S);
    }

    public int howManySOS(int row, int column, Box chosen){
        int[][] around = new int[][]{ {-1,-1} , {0,-1} , {1,-1} , {-1,0} , {1,0} , {-1,1} , {0,1} , {1,1} } ;
        int points = 0;
        if ( chosen == Box.LETTER_S ){
            for( int i = 0; i < 8; i++ ){
                if( detectSOSWhenS(row, column, around[i]) ){
                    points++;
                }
            }
        }
        else if ( chosen == Box.LETTER_O ){
            for( int i = 0; i < 4; i++ ){
                if( detectSOSWhenO(row, column, around[i]) ){
                    points++;
                }
            }
        }
        return points;
    }
}