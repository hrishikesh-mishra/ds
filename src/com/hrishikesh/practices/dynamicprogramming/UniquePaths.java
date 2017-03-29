package com.hrishikesh.practices.dynamicprogramming;

import static com.hrishikesh.practices.dynamicprogramming.UniquePaths.uniquePathsByBacktracking;

/**
 * Problem:
 * Unique Paths
 * A robot is located at the top-left corner of a m x n grid (marked ‘Start’ in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked ‘Finish’ in the diagram below).
 * How many possible unique paths are there?
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=GO5QHC_BmvM
 * @link http://hrishikeshmishra.com/unique-paths/
 */
public class UniquePaths {

    public static int uniquePathsByBacktracking(int row, int col, int lastRow, int lastCol) {
        if (row == lastRow && col == lastCol) {
            return 1;
        }
        if (row > lastRow || col > lastCol) {
            return 0;
        }

        return uniquePathsByBacktracking(row + 1, col, lastRow, lastCol) +
                uniquePathsByBacktracking(row, col + 1, lastRow, lastCol);
    }

}

class UniquePathsTest {

    public static void main(String[] args) {
        System.out.println("Unique paths by backtracking 4X4 grid: " + uniquePathsByBacktracking(0, 0, 3, 3));
    }
}
