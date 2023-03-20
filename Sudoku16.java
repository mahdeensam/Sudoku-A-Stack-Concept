/* 
	File: Board.java
	Author: Mahdeen Ahmed Khan Sameer
	Date: 03/16/2023
    CS231B
    Creates a random 16x16 Sudoku board and prints it to the console using the toString() method.
*/

import java.util.Random;

public class Sudoku16 {
    private static final int SIZE = 16;
    private int[][] board;

    public Sudoku16() {
        board = new int[SIZE][SIZE];
        randomizeBoard();
    }

    private void randomizeBoard() {
        Random random = new Random();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = random.nextInt(SIZE) + 1;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            if (row % 4 == 0 && row != 0) {
                sb.append("-".repeat(67)).append("\n");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % 4 == 0 && col != 0) {
                    sb.append("| ");
                }
                sb.append(String.format("%02d", board[row][col]));
                if (col < SIZE - 1) {
                    sb.append(" ");
                }
            }
            if (row < SIZE - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Sudoku16 sudoku16 = new Sudoku16();
        System.out.println("Randomized 16x16 Sudoku board:\n" + sudoku16);
    }
}

