package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * The maximum matrix rectangle problem
 * ;
 * ;
 * ;
 * Example:
 * 0 1 1 0
 * 1 1 1 1
 * 1 1 1 1
 * 1 1 0 0
 * the max size rectangle is
 * 1 1 1 1
 * 1 1 1 1
 * Area is 4 * 2 = 8
 * ;
 * ;
 * Solution:
 * ;
 * Problem become simple when we use Largest Rectangle <code>LargestRectangle</code>
 * ;
 * ;
 * Algorithm:
 * - Take an array temp of same size of matrix.
 * - Set MaxArea = 0
 * - Iterate matrix row (i) from 0 to n - 1
 * - - Iterate matrix each col (j) for ith row
 * - - - If matrix[i][j] > 0 then
 * - - - - temp[j] += matrix[i][j]
 * - - - Else
 * - - - - temp[j] = 0
 * - - calculate area = LargestRectangle.calculate(temp)
 * - - If MaxArea  <  area then
 * - - - MaxArea  = area
 * - Return MaxArea
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=g8bSdXCG-lA
 * @link http://hrishikeshmishra.com/the-matrix-maximum-rectangle-area/
 */
public class MaxRectangle {

    public static int calculate(int[][] matrix) {

        /** Base case: When matrix is empty **/
        if (matrix.length == 0) {
            return 0;
        }

        int[] temp = new int[matrix[0].length];
        int maxArea = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] != 0) {
                    temp[j] += matrix[i][j];
                } else {
                    temp[j] += 0;
                }
            }

            int area = LargestRectangle.calculate(temp);
            if (maxArea < area) {
                maxArea = area;
            }
        }

        return maxArea;
    }
}


class MaxRectangleTest {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0}
        };

        int area = MaxRectangle.calculate(matrix);
        System.out.println(area);
    }
}