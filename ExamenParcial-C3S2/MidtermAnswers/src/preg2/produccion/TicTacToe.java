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

    public boolean winCondition(){
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


}
