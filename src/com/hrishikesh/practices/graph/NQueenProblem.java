package com.hrishikesh.practices.graph;

import static com.hrishikesh.practices.graph.NQueenProblem.placeQueens;

/**
 * Problem :
 * N Queen Problem Using Backtracking Algorithm
 * Given a chess board of size n*n find all the place n queens so that they don't attach each other .
 * - Attach Conditions, Queen at (i,j) and (x,y)
 * - - i == x (for same row)
 * - - j == y (for same column)
 * - - |i-x| == |j -y| (for same diagonal)
 * ;
 * Solution:
 * - Same as {@link GraphColoring} with the help of backtracking
 * ;
 * ;
 * Algorithm:
 * - Base case: check all queen based or not
 * - If all queens placed then
 * - - return true
 * - Iterate all rows (i) one by one
 * - - Iterate all cols (j)  one by one
 * - - - If safe place queen in cell (i,j) then
 * - - - - reserve cell (i, j) for this queue
 * - - - - recursively call this method to place next queen
 * - - - - If next queen also placed properly then
 * - - - - - return true
 * - - - - Else
 * - - - - - reset reserved cell (i, j)
 * - Return false
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=xouin83ebxE
 * @link http://hrishikeshmishra.com/n-queen-problem-using-backtracking-algorithm/
 */
public class NQueenProblem {

    public static void placeQueens(int[][] board) {

        boolean isPlacedProperly = placeQueens(board, 0, board.length);

        if (isPlacedProperly) {
            System.out.println("Queen - Position");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 1) {
                        System.out.printf("%d\t%s\n", i, "(" + i + "," + j + ")");
                    }
                }
            }
        } else {
            System.out.println("Unable to place queens ");
        }
    }


    private static boolean placeQueens(int[][] board, int numberOfQueenPlaced, int totalNumberOfQueens) {

        /** Base case: when all queens placed to board **/
        if (numberOfQueenPlaced >= totalNumberOfQueens) {
            return true;
        }

        /** Row and columnwise to place queue **/
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                /** Check is safe to place a queen at position i, j cell **/
                if (isSafe(board, i, j)) {

                    /** Reserve cell for queen **/
                    board[i][j] = 1;

                    /** Place next queue */
                    if (placeQueens(board, numberOfQueenPlaced + 1, totalNumberOfQueens)) {
                        return true;
                    }

                    /** If placement of next queen failed then reset reserve cell **/
                    board[i][j] = 0;
                }
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] != 0) {

                    /** In same row **/
                    if (i == row) {
                        return false;
                    }

                    /** In same column **/
                    if (j == col) {
                        return false;
                    }

                    /** In same diagonal **/
                    if (Math.abs(i - row) == Math.abs(j - col)) {
                        return false;
                    }

                }
            }
        }

        return true;
    }
}


class NQueenProblemTest {

    public static void main(String[] args) {
        int[][] chessBoard = new int[4][4];
        placeQueens(chessBoard);

    }
}