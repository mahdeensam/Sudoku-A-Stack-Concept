import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.awt.*;

public class Sudoku {
    public Board board;
    public LandscapeDisplay ld;
    
    public Sudoku(int numLocked) {
        board = new Board(numLocked);
        ld = new LandscapeDisplay(board);
    }

    public Board getBoard() {
        return board;
    }
    
    public Sudoku(int numLocked, int delay) {
        this(numLocked);
        try {
            solve(delay);
        } catch (InterruptedException e) {
            System.out.println("Solving interrupted!");
        }
    }


    public int findNextValue(Cell cell) {
        for (int value = 1; value <= Board.SIZE; value++) {
            if (board.validValue(cell.getRow(), cell.getCol(), value)) {
                return value;
            }
        }
        return 0;
    }
        // Helper method to count the valid values for a cell
        private int countValidValues(int row, int col) {
            int count = 0;
            for (int value = 1; value <= Board.SIZE; value++) {
                if (board.validValue(row, col, value)) {
                    count++;
                }
            }
            return count;
        }
    
        // Updated findNextCell method that chooses the cell with the fewest valid values
        public Cell findNextCell() {
            Cell bestCell = null;
            int minValidValues = Integer.MAX_VALUE;
    
            for (int row = 0; row < Board.SIZE; row++) {
                for (int col = 0; col < Board.SIZE; col++) {
                    Cell cell = board.get(row, col);
                    if (cell.getValue() == 0) {
                        int validValuesCount = countValidValues(row, col);
                        if (validValuesCount < minValidValues) {
                            minValidValues = validValuesCount;
                            bestCell = cell;
                        }
                    }
                }
            }
            return bestCell;
        }
    
    // public Cell findNextCell() {
    //     for (int row = 0; row < Board.SIZE; row++) {
    //         for (int col = 0; col < Board.SIZE; col++) {
    //             Cell cell = board.get(row, col);
    //             if (cell.getValue() == 0) {
    //                 int value = findNextValue(cell);
    //                 if (value != 0) {
    //                     cell.setValue(value);
    //                     return cell;
    //                 } else {
    //                     return null;
    //                 }
    //             }
    //         }
    //     }
    //     return null;
    // }

    public boolean solve(int delay) throws InterruptedException {
        Deque<Cell> stack = new ArrayDeque<>();
        Cell next = findNextCell();
        while (next != null || !stack.isEmpty()) {
            if (next == null) {
                next = stack.pop();
                next.setValue(0);
            } else {
                int value = findNextValue(next);
                if (value != 0) {
                    next.setValue(value);
                    stack.push(next);
                }
                next = findNextCell();
            }
            if (delay > 0) {
                Thread.sleep(delay);
            }
            if (ld != null) {
                ld.repaint();
            }
        }
        return board.validSolution();
    }
    

    

    public static void main(String[] args) {
        Board board = new Board("/Users/mahdeenkhan/Desktop/Project05/board1.txt");
        LandscapeDisplay display = new LandscapeDisplay(board);
    
        Sudoku sudoku = new Sudoku(0); // create a Sudoku instance with 0 numLocked
        try {
            boolean solved = sudoku.solve(0); // solve the Sudoku with 0 delay
    
            if (solved) {
                System.out.println("Final board:");
                System.out.println(sudoku.getBoard()); // print the final board
            } else {
                System.out.println("Sudoku could not be solved.");
            }
        } catch (InterruptedException e) {
            System.out.println("Solving was interrupted.");
        }
    
        display.repaint();
    }
    
}

