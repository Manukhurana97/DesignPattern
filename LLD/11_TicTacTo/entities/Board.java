package entities;

import enums.Symbol;
import exception.InvalidMoveException;

public class Board {
    private final int size;
    private int moveCount;
    private Cell[][] cell;

    public Board(int size) {
        this.size = size;
        this.moveCount = 0;
        this.cell = new Cell[size][size];
        this.initializeBoard();
    }

    public void initializeBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cell[r][c] = new Cell(Symbol.EMPTY);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean full() {
        return size*size == moveCount;
    }

    public Cell getCell(int r, int c) {
        if (r < 0 || c < 0 || r >= size || c >= size) {
            return null;
        }
        return cell[r][c];
    }

    public void placeCell(int r, int c, Symbol symbol) {
        if (r < 0 || c < 0 || r >= this.size || c >= this.size) {
            throw new InvalidMoveException("Invalid position: out of bound.");
        }
        if (cell[r][c].getSymbol() != Symbol.EMPTY) {
            throw new InvalidMoveException("Invalid position: cell is already occupied.");
        }

        cell[r][c].setSymbol(symbol);
        this.moveCount++;
    }

    public void printBoard() {
        System.out.println("-".repeat(10));

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.printf("%s ", cell[r][c].getSymbol() == Symbol.EMPTY ? "_" : cell[r][c].getSymbol().name());
            }
            System.out.println();
        }

        System.out.println("-".repeat(10));
    }
}