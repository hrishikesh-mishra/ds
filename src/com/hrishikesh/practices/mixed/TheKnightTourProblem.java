package com.hrishikesh.practices.mixed;

import static com.hrishikesh.practices.mixed.TheKnightTourProblem.solve;

/**
 * Problem:
 * The Knight’s tour problem
 * ;
 * Solution:
 * - Backtracking
 * ;
 * ;
 * Algorithm:
 * - If move = board.size then
 * - - return true
 * - Iterate all possible 8 positions where Knight can go from current position
 * - - If next position is valid and not visited earlier then
 * - - - Set board location = move
 * - - - Recursively call with move + 1 and with new position
 * - - - If last recursive call was success then
 * - - - - return true
 * - - - else reset location
 * - return false
 *
 * @author hrishikesh.mishra
 * @wiki https://en.wikipedia.org/wiki/Knight%27s_tour
 * @video https://www.youtube.com/watch?v=ab_dY3dZFHM
 * @link http://hrishikeshmishra.com/knights-tour-problem/
 */
public class TheKnightTourProblem {

    private static final int[] POSSIBLE_X_MOVE_OFFSETS = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] POSSIBLE_Y_MOVE_OFFSETS = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int DEFAULT_UNVISITED_VALUE = -1;

    public static void solve(int boardSize) {
        int[][] board = new int[boardSize][boardSize];
        fillBoardWithUnVisited(board);

        board[0][0] = 0;
        boolean isSolved = solveByBacktracking(board, 0, 0, 1);

        if (isSolved) {
            System.out.println("Path Found!! ");

            for (int x = 0; x < boardSize; x++) {
                for (int y = 0; y < boardSize; y++) {
                    System.out.printf("%2d\t", board[x][y]);
                }
                System.out.println();
            }
        } else {
            System.out.println("Path not found!");
        }
    }

    public static void fillBoardWithUnVisited(int[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = DEFAULT_UNVISITED_VALUE;
            }
        }
    }

    private static boolean solveByBacktracking(int[][] board, int xPosition, int yPosition, int move) {
        if (move == board.length * board.length) {
            return true;
        }

        for (int offset = 0; offset < POSSIBLE_X_MOVE_OFFSETS.length; offset++) {

            int nextXMove = xPosition + POSSIBLE_X_MOVE_OFFSETS[offset];
            int nextYMove = yPosition + POSSIBLE_Y_MOVE_OFFSETS[offset];

            if (isNextValidMove(board, nextXMove, nextYMove)) {

                board[nextXMove][nextYMove] = move;
                if (solveByBacktracking(board, nextXMove, nextYMove, move + 1)) {
                    return true;
                }

                board[nextXMove][nextYMove] = DEFAULT_UNVISITED_VALUE;
            }
        }

        return false;
    }

    private static boolean isNextValidMove(int[][] board, int xPosition, int yPosition) {

        return isInsideBoard(board, xPosition, yPosition) &&
                isNotVisited(board, xPosition, yPosition);
    }

    private static boolean isInsideBoard(int[][] board, int xPosition, int yPosition) {
        return xPosition >= 0 && xPosition < board.length &&
                yPosition >= 0 && yPosition < board[xPosition].length;
    }

    public static boolean isNotVisited(int[][] board, int xPosition, int yPosition) {
        return board[xPosition][yPosition] == DEFAULT_UNVISITED_VALUE;
    }
}


class TheKnightTourProblemTest {
    public static void main(String[] args) {
        solve(8);
    }
}