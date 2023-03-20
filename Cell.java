/* 
	File: Board.java
	Author: Mahdeen Ahmed Khan Sameer
	Date: 03/16/2023
    CS231B
    Creates a single cell in a Sudoku game board
*/

import java.awt.Graphics;
import java.awt.Color;


public class Cell {
    private int row;
    private int col;
    private int value;
    private boolean locked;

    public Cell() {
        this.row = 0;
        this.col = 0;
        this.value = 0;
        this.locked = false;
    }
    
    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = false;
    }
    
    public Cell(int row, int col, int value, boolean locked) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = locked;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int newval) {
        this.value = newval;
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public void setLocked(boolean lock) {
        this.locked = lock;
    }
    
    public String toString() {
        return Integer.toString(value);
    }
    
    public void draw(Graphics g, int x, int y, int scale) {
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked() ? Color.BLUE : Color.RED);
        g.drawChars(new char[] { toDraw }, 0, 1, x, y);
    }
    
    public static void main(String[] args) {
        Cell cell1 = new Cell();
        Cell cell2 = new Cell(2, 3, 5);
        Cell cell3 = new Cell(1, 1, 3, true);
    
        // Test default constructor
        assert cell1.getRow() == 0;
        assert cell1.getCol() == 0;
        assert cell1.getValue() == 0;
        assert !cell1.isLocked();
    
        // Test constructor with row, col, and value
        assert cell2.getRow() == 2;
        assert cell2.getCol() == 3;
        assert cell2.getValue() == 5;
        assert !cell2.isLocked();
    
        // Test constructor with row, col, value, and locked
        assert cell3.getRow() == 1;
        assert cell3.getCol() == 1;
        assert cell3.getValue() == 3;
        assert cell3.isLocked();
    
        // Test setting and getting value
        cell1.setValue(7);
        assert cell1.getValue() == 7;
    
        // Test setting and getting locked status
        cell2.setLocked(true);
        assert cell2.isLocked();
    
        // Test toString method
        assert cell1.toString().equals("7");
        assert cell2.toString().equals("5");
        assert cell3.toString().equals("3");
    
        System.out.println("All tests passed!");
    }
}    