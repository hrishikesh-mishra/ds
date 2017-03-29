package com.hrishikesh.practices.mixed;


import java.util.ArrayList;
import java.util.List;

import static com.hrishikesh.practices.mixed.TicTac.*;
import static com.hrishikesh.practices.mixed.TicTac.Cell.O;
import static com.hrishikesh.practices.mixed.TicTac.Cell.X;

/**
 * Problem:
 * Tic Tac Game Winner Finder
 * Find the winner of tic tac game.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/tic-tac-game-winner-finder/
 */
public class TicTac {

    /**
     * Cell type
     **/
    public enum Cell {
        _,
        X,
        O
    }

    /**
     * Cell Iterator
     **/
    private class CellIterator {
        private int x;
        private int y;
        private int size;
        private int xIncrement;
        private int yIncrement;

        public CellIterator(int x, int y, int size, int xIncrement, int yIncrement) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.xIncrement = xIncrement;
            this.yIncrement = yIncrement;
        }

        private boolean hasNext() {
            return (x >= 0 && x < size) &&
                    (y >= 0 && y < size);
        }

        private void next() {
            x = x + xIncrement;
            y = y + yIncrement;
        }

    }

    public Cell findWinner(Cell[][] board) {

        /** Check is valid board **/
        if (!isValidBoard(board)) {
            throw new RuntimeException("Invalid board");
        }

        int boardSize = board.length;

        /** Add all rows **/
        List<CellIterator> list = new ArrayList<>();
        for (int row = 0; row < boardSize; row++) {
            list.add(new CellIterator(row, 0, boardSize, 1, 0));
        }

        /** Add all cols **/
        for (int col = 0; col < boardSize; col++) {
            list.add(new CellIterator(0, col, boardSize, 1, 0));
        }

        /** Add all diagonals **/
        list.add(new CellIterator(0, 0, boardSize, 1, 1));
        list.add(new CellIterator(boardSize - 1, 0, boardSize, -1, 1));

        return iterateCell(list, board);
    }

    private Cell iterateCell(List<CellIterator> list, Cell[][] board) {
        int boardSize = board.length;

        /** Iterate all cell iterator  **/
        for (CellIterator cellIterator : list) {

            Cell firstCell = board[cellIterator.x][cellIterator.y];
            cellIterator.next();

            int counter = 1;

            /** Iterate all cell of a iterator **/
            while (cellIterator.hasNext()) {
                Cell nextCell = board[cellIterator.x][cellIterator.y];
                if (firstCell != nextCell) {
                    break;
                }
                counter++;
                cellIterator.next();
            }

            if (counter == boardSize) {
                return firstCell;
            }

        }

        return Cell._;
    }

    private boolean isValidBoard(Cell[][] board) {
        return board.length == board[0].length;
    }
}


class TicTacTest {
    public static void main(String[] args) {
        Cell[][] board1 = {
                {X, X, X},
                {O, X, O},
                {O, X, X},
        };

        Cell[][] board2 = {
                {X, O, X},
                {O, O, X},
                {X, O, O},
        };

        Cell[][] board3 = {
                {X, O, O},
                {O, X, O},
                {O, X, X},
        };

        Cell[][] board4 = {
                {X, O, O},
                {O, O, X},
                {O, X, X},
        };


        TicTac ticTac = new TicTac();
        System.out.println("Tic Tac Winner: " + ticTac.findWinner(board1));
        System.out.println("Tic Tac Winner: " + ticTac.findWinner(board2));
        System.out.println("Tic Tac Winner: " + ticTac.findWinner(board3));
        System.out.println("Tic Tac Winner: " + ticTac.findWinner(board4));

    }
}