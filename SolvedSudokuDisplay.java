/* 
	File: Board.java
	Author: Mahdeen Ahmed Khan Sameer
	Date: 03/16/2023
    CS231B
    Creates a new Sudoku object with 20 locked cells, prints the solved Sudoku puzzle to the console
*/

import java.awt.Color;
import java.awt.Graphics;

public class SolvedSudokuDisplay {
    public static void main(String[] args) {
        // Pass the required numLocked parameter while creating a new Sudoku object
        Sudoku sudoku = new Sudoku(20);
        System.out.println("Solved Sudoku puzzle:\n" + sudoku.getBoard());
        LandscapeDisplay display = new LandscapeDisplay(sudoku.getBoard());
    }
}
