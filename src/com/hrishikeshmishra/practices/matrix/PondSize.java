package com.hrishikeshmishra.practices.matrix;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikeshmishra.practices.matrix.PondSize.getPonds;

/**
 * Problem:
 * Pond Size
 * You have an integer matrix representing a plot of land, where hte value at the location represents the height
 * above sea level. A value of zero indicates water. A pond is a region of water connected vertically, horizontally
 * or diagonally. The size of pond is the total number of connected water cells. Implement a method to return
 * sizes of all ponds in the matrix.
 * ;
 * Solution:
 * - Traverse DFS over area
 * ;
 * ;
 * Algorithm:
 * - Iterate all cells of matrix one by one
 * - - If its not visited and cell has water call DFS to count connected ponds
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/pond-size/
 */
public class PondSize {

    public static List<Integer> getPonds(int[][] area) {
        List<Integer> ponds = new ArrayList<>();
        boolean[][] visited = new boolean[area.length][area[0].length];

        for (int row = 0; row < area.length; row++) {
            for (int col = 0; col < area[0].length; col++) {
                if (hasWater(area, row, col) && isNotVisited(visited, row, col)) {
                    ponds.add(computePondArea(area, visited, row, col));
                }
            }
        }
        return ponds;
    }

    private static Integer computePondArea(int[][] area, boolean[][] visited, int row, int col) {
        int size = 1;
        int maxRow = area.length;
        int maxCol = area[0].length;

        visited[row][col] = true;

        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {

                int tempRow = r + row;
                int tempCol = c + col;

                if (isValidCell(tempRow, tempCol, maxRow, maxCol) &&
                        hasWater(area, tempRow, tempCol) &&
                        isNotVisited(visited, tempRow, tempCol)) {

                    size += computePondArea(area, visited, tempRow, tempCol);

                }
            }
        }
        return size;
    }

    public static boolean isValidCell(int row, int col, int maxRow, int maxCol) {
        return ((row >= 0 && row < maxRow) &&
                (col >= 0 && col < maxCol));
    }

    public static boolean hasWater(int[][] area, int row, int col) {
        return area[row][col] == 0;
    }

    public static boolean isNotVisited(boolean[][] visited, int row, int col) {
        return !visited[row][col];
    }

}

class PondSizeTest {
    public static void main(String[] args) {
        int area[][] = {
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        };

        System.out.println("Ponds of size: " + getPonds(area));

    }
}