/* 
	File: Board.java
	Author: Mahdeen Ahmed Khan Sameer
	Date: 03/16/2023
    CS231B
    Creating a new empty board, a board by reading from a file, and then a board with a given number of cells locked to start the game.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;



public class Board {
    public static final int SIZE = 9;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new Cell(row, col, 0);
            }
        }
    }

    public Board(String filename) {
        this();
        read(filename);
    }

    public Board(int numLocked) {
        this();
        lockCells(numLocked);
    }

    private void lockCells(int numLocked) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < SIZE * SIZE; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        Random random = new Random();
        for (int i = 0; i < numLocked; i++) {
            int index = indices.get(i);
            int row = index / SIZE;
            int col = index % SIZE;
            int value = random.nextInt(9) + 1;
            while (!validValue(row, col, value)) {
                value = random.nextInt(9) + 1;
            }
            set(row, col, value, true);
        }
    }

    public int getRows() {
        return SIZE;
    }

    public int getCols() {
        return SIZE;
    }

    public Cell get(int row, int col) {
        return cells[row][col];
    }

    public boolean isLocked(int row, int col) {
        return cells[row][col].isLocked();
    }

    public int numLocked() {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (cells[row][col].isLocked()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int value(int row, int col) {
        return cells[row][col].getValue();
    }

    public void set(int row, int col, int value) {
        cells[row][col].setValue(value);
    }

    public void set(int row, int col, int value, boolean locked) {
        cells[row][col].setValue(value);
        cells[row][col].setLocked(locked);
    }

    public boolean read(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            int row = 0;
            while (line != null && row < SIZE) {
                String[] values = line.split("[ ]+");
                for (int col = 0; col < SIZE && col < values.length; col++) {
                    int value = Integer.parseInt(values[col]);
                    if (value != 0) {
                        cells[row][col].setValue(value);
                        cells[row][col].setLocked(true);
                    }
                }
                row++;
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        } catch (NumberFormatException ex) {
            System.out.println("Board.read():: invalid value in file " + filename);
        }
        return false;
    }

    public boolean validValue(int row, int col, int value) {
        if (value < 1 || value > SIZE) {
            return false;
        }
        for (int i = 0; i < SIZE; i++) {
            if (cells[row][i].getValue() == value || cells[i][col].getValue() == value) {
                return false;
            }
        }
        int boxRow = row / 3 * 3;
        int boxCol = col / 3 * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (cells[i][j].getValue() == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validSolution() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = cells[row][col].getValue();
                if (value == 0 || !validValue(row, col, value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            if (row > 0 && row % 3 == 0) {
                sb.append("------+-------+------\n");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col > 0 && col % 3 == 0) {
                    sb.append("| ");
                }
                sb.append(cells[row][col]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public void draw(Graphics g, int scale) {
        int blockSize = scale;
        int width = SIZE * blockSize;
        int height = SIZE * blockSize;
        g.setColor(Color.BLACK);
for (int row = 0; row <= SIZE; row++) {
    if (row % 3 == 0) {
        g.fillRect(0, row * blockSize - 1, width, 3);
    } else {
        g.fillRect(0, row * blockSize, width, 1);
    }
}
for (int col = 0; col <= SIZE; col++) {
    if (col % 3 == 0) {
        g.fillRect(col * blockSize - 1, 0, 3, height);
    } else {
        g.fillRect(col * blockSize, 0, 1, height);
    }
}
    
        // draw the board
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int val = cells[row][col].getValue();
                if (val != 0) {
                    g.drawString(Integer.toString(val), col * blockSize + blockSize/3, row * blockSize + 2*blockSize/3);
                }
            }
        }
    }
        
 

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Board <filename>");
            return;
        }
        Board board = new Board(args[0]);
        System.out.println(board);
        System.out.println("Is solved? " + board.validSolution());
    }
}