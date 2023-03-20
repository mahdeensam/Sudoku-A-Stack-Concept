/* 
	File: Board.java
	Author: Mahdeen Ahmed Khan Sameer
	Date: 03/16/2023
    CS231B
    Tests Sudoku.java
*/
public class SudokuTest {
    public static void main(String[] args) {
        System.out.println("Test 1: Creating a Sudoku board with 20 locked cells and solving it");
        Sudoku sudoku1 = new Sudoku(20);
        System.out.println("Initial board:\n" + sudoku1.getBoard());
        try {
            if (sudoku1.solve(0)) {
                System.out.println("Sudoku board solved successfully!");
            } else {
                System.out.println("Failed to solve the Sudoku board.");
            }
        } catch (InterruptedException e) {
            System.out.println("Solving interrupted!");
        }
        System.out.println("Final board:\n" + sudoku1.getBoard());

        System.out.println("Test 2: Creating a Sudoku board with 10 locked cells and solving it");
        Sudoku sudoku2 = new Sudoku(10);
        System.out.println("Initial board:\n" + sudoku2.getBoard());
        try {
            if (sudoku2.solve(0)) {
                System.out.println("Sudoku board solved successfully!");
            } else {
                System.out.println("Failed to solve the Sudoku board.");
            }
        } catch (InterruptedException e) {
            System.out.println("Solving interrupted!");
        }
        System.out.println("Final board:\n" + sudoku2.getBoard());
    }
}
