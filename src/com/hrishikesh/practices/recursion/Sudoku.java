package com.hrishikesh.practices.recursion;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Problem:
 * Sudoku
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sudoku-implementation/
 */
public class Sudoku {

    public static class Cell {
        private int row;
        private int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public boolean solve(int[][] board) {
        if (isFull(board)) {
            return true;
        }

        Cell cell = findFirstEmptyCell(board).get();

        Set<Integer> possibleEntries = getPossibleEntries(board, cell.row, cell.col);

        for (Integer possibleEntry : possibleEntries) {
            board[cell.row][cell.col] = possibleEntry;
            if (solve(board)) {
                return true;
            } else {
                board[cell.row][cell.col] = 0;
            }
        }

        return false;
    }


    private Optional<Cell> findFirstEmptyCell(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    Cell cell = new Cell(row, col);
                    return Optional.of(cell);
                }
            }
        }

        return Optional.empty();
    }

    private Set<Integer> getPossibleEntries(int[][] board, int row, int col) {
        Set<Integer> possibleEntries = new HashSet<Integer>() {
            {
                for (int i = 1; i <= 9; i++) {
                    add(i);
                }
            }
        };

        /** For horizontal entries **/
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0) {
                possibleEntries.remove(board[row][i]);
            }
        }

        /** For vertical entries **/
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != 0) {
                possibleEntries.remove(board[i][col]);
            }
        }

        /** smaller square **/
        int boxRowOffset = (row / 3) * 3;
        int boxColOffset = (col / 3) * 3;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[boxRowOffset + i][boxColOffset + j] != 0) {
                    possibleEntries.remove(board[boxRowOffset + i][boxColOffset + j]);
                }
            }
        }

        return possibleEntries;
    }

    private boolean isFull(int[][] board) {
        Optional<Cell> firstEmptyCell = findFirstEmptyCell(board);
        return !firstEmptyCell.isPresent();
    }
}

class SudokuTest {
    public static void main(String[] args) {
        int board[][] = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        System.out.println("Problem:");
        print(board);
        Sudoku sudoku = new Sudoku();
        sudoku.solve(board);

        System.out.println("\n\nSolution:");
        print(board);
    }


    private static void print(int[][] matrix) {
        for (int i = 0; i < 9; ++i) {
            if (i % 3 == 0) {
                System.out.println(" -----------------------");
            }
            for (int j = 0; j < 9; ++j) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(getValue(matrix, i, j));
                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    private static String getValue(int[][] matrix, int row, int col) {
        return (matrix[row][col] == 0) ? " " : Integer.toString(matrix[row][col]);
    }
}