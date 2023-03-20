/* 
	File: Board.java
	Author: Mahdeen Ahmed Khan Sameer
	Date: 03/16/2023
    CS231B
    Tests Board.java
*/

public class BoardTest {

    public static void main(String[] args) {
        testValidValue();
        testValidSolution();
        testNumLocked();
    }

    public static void testValidValue() {
        Board board = new Board();
        board.set(0, 0, 5, true);
        board.set(0, 1, 3, true);
        board.set(1, 0, 6, true);

        boolean test1 = !board.validValue(0, 2, 5); // Same value in the row
        boolean test2 = !board.validValue(2, 0, 6); // Same value in the column
        boolean test3 = !board.validValue(1, 1, 3); // Same value in the 3x3 box
        boolean test4 = board.validValue(0, 2, 1);  // Valid value

        System.out.println("testValidValue: " + (test1 && test2 && test3 && test4));
    }

    public static void testValidSolution() {
        Board board = new Board();
    
        boolean test1 = !board.validSolution(); // Empty board is not a valid solution
    
        // Fill in the board with a valid solution
        int[][] solution = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
    
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                board.set(row, col, solution[row][col], true);
            }
        }
    
        boolean test2 = board.validSolution(); // Valid solution
    
        System.out.println("testValidSolution: " + (test1 && test2));
    }
    

    public static void testNumLocked() {
        Board board = new Board();

        boolean test1 = board.numLocked() == 0; // Initially, no locked cells

        board.set(0, 0, 5, true);

        boolean test2 = board.numLocked() == 1; // 1 locked cell

        board.set(0, 1, 3, false);

        boolean test3 = board.numLocked() == 1; // Still 1 locked cell (new cell is not locked)

        System.out.println("testNumLocked: " + (test1 && test2 && test3));
    }
}
