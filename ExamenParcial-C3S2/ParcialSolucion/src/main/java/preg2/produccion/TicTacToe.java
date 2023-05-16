package preg2.produccion;

public class TicTacToe {

    private int[][] grid;

    private char turn = 'X';

    public char getTurn(){return turn;}
    public TicTacToe(){
        grid = new int[3][3];
    }
    public void jugar(int row, int column){
        if(row >= 0 && row < 3 && column >= 0 && column < 3 && grid[row][column] == 0){
            if ( turn == 'X' ){
                grid[row][column] = 1;
            } else { grid[row][column] = 2; }

            turn = turn == 'X' ? 'O':'X';
        }
        else throw new RuntimeException();
    }

    public boolean horizontalLineWin(){
        for (int i = 0; i < 3; i++ ){
            if ( grid[i][0] == 1 && grid[i][1] == 1 && grid[i][2] == 1){
                return true;
            }
            if ( grid[i][0] == 2 && grid[i][1] == 2 && grid[i][2] == 2 ){
                return true;
            }
        }
        return false;
    }

    public boolean verticalLineWin(){
        for (int i = 0; i < 3; i++){
            if ( grid[0][i] == 1 && grid[1][i] == 1 && grid[2][i] == 1){
                return true;
            }
            if ( grid[0][i] == 2 && grid[1][i] == 2 && grid[2][i] == 2 ){
                return true;
            }
        }
        return false;
    }

    public boolean mainDiagonalWin(){
        if ( grid[0][0] == 1 && grid[1][1] == 1 && grid[2][2] == 1){
            return true;
        }
        if ( grid[0][0] == 2 && grid[1][1] == 2 && grid[2][2] == 2){
            return true;
        }
        return false;
    }

    public boolean secDiagonalWin(){
        if ( grid[0][2] == 1 && grid[1][1] == 1 && grid[2][0] == 1 ){
            return true;
        }
        if ( grid[0][2] == 2 && grid[1][1] == 2 && grid[2][0] == 2 ){
            return true;
        }
        return false;
    }

    public boolean winCondition(){
        //Para detectar lineas horizontales
        if (horizontalLineWin()) return true;

        //Para detectar lineas verticales
        if (verticalLineWin()) return true;

        //Para detectar diagonal principal
        if (mainDiagonalWin()) return true;

        //Para detectar diagonal secundaria
        if (secDiagonalWin()) return true;

        return false;
    }

    public boolean isDraw(){
        boolean boardFull = true;
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 3; column++){
                if(grid[row][column] == 0) boardFull = false;
            }
        }
        return boardFull && !winCondition();
    }
}
