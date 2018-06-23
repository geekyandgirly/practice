package matrix;

import java.util.HashMap;
import java.util.Random;

/**
 * Connect 4 games.  board size 6 x 7
 */
public class ConnectFour {
    private static int ROWS = 6;
    private static int COLS = 7;

    // 0, 1 or -1
    private int[][] board = new int[ROWS][COLS];
    private HashMap<Integer, Integer> colSpots = new HashMap<>();

    public ConnectFour() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = -1;
            }
        }
    }

    // return winner id (0 or 1) or -1 if no one wins
    public int play(int player, int col) throws Exception {
        Integer row = colSpots.get(col);
        if (row == null) {
            // the column is empty
            row = ROWS - 1;
        } else {
            if (row == -1) {
                throw new Exception("invalid move, col is full");
            }
        }
        colSpots.put(col, row - 1);
        board[row][col] = player;

        if (checkWinner(player, row, col) != -1) {
            System.out.print("Player " + player + " won!");
            return player;
        }
        return -1;
    }

    // return winner id (0 or 1) or -1 if no one wins
    public int checkWinner(int player, int row, int col) {
        // Just check for extra 3 count since current board[row][col] is already counted as 1.
        
        // check horizontal
        int count = getConnectedCount(player, row, -1, col, 0) // left
                + getConnectedCount(player, row, 1, col, 0); // right
        if (count == 3) return player;

        // check vertical
        count = getConnectedCount(player, row, 0, col, -1) // up
                + getConnectedCount(player, row, 0, col, 1); // down
        if (count == 3) return player;

        // check top left to bottom right
        count = getConnectedCount(player, row, -1, col, -1) // top left
                + getConnectedCount(player, row, 1, col, 1); // bottom right
        if (count == 3) return player;

        // check top right to bottom left
        count = getConnectedCount(player, row, -1, col, 1) // top right
                + getConnectedCount(player, row, 1, col, -1); // bottom left
        if (count == 3) return player;

        return -1;
    }

    public int getConnectedCount(int player, int row, int rowInc, int col, int colInc) {
        int rowMin = Math.max(0, row - 3);
        int rowMax = Math.min(ROWS - 1, row + 3);
        int colMin = Math.max(0, col - 3);
        int colMax = Math.min(COLS - 1, col + 3);

        int currRow = row;
        int currCol = col;
        int count = 0;
        while (currRow > rowMin && currRow < rowMax && currCol > colMin && currCol < colMax) {
            currRow += rowInc;
            currCol += colInc;
            if (board[currRow][currCol] == player) {
                count++;
                if (count == 3) {
                    return count;
                }
            } else {
                break;
            }
        }

        return count;
    }

    public void printBoard() {
        System.out.println();
        for (int[] row : board) {
            System.out.print("[");
            for (int col : row) {
                if (col == -1) {
                    System.out.print("x ");
                } else {
                    System.out.print(col + " ");
                }
            }
            System.out.print("]\n");
        }
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();

        try {
            int winner = -1;
            while (winner == -1) {
                int col = new Random().nextInt(7);
                int player = new Random().nextInt(2);
                System.out.println("Generated col=" + col + " player=" + player);
                winner = game.play(player, col);
           }
            game.printBoard();
        } catch (Exception e) {
            e.printStackTrace();
            // ignore invalid move
        }
    }
}
