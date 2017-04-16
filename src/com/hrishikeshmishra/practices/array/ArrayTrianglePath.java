package com.hrishikeshmishra.practices.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * Array Triangle Path From Top to Bottom
 * Find the minimum path sum from top to bottom, each step you can only move adjacent indices of below row.
 * For example:
 * <pre>
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 *
 * </pre>
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/array-triangle-path-top-bottom/
 */
public class ArrayTrianglePath {

    public static int getMinimumSum(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[] minimumSum = new int[rows];

        int columnsOfLastRow = rows;

        /** Last row's column **/
        for (int i = 0; i < columnsOfLastRow; i++) {
            minimumSum[i] = triangle.get(rows - 1).get(i);
        }

        /** Iterating from 2nd last row to first row **/
        for (int row = rows - 2; row >= 0; row--) {

            /** Finding the min of between to adjacent column of ith row **/
            for (int j = 0; j < triangle.get(row).size(); j++) {
                minimumSum[j] = triangle.get(row).get(j) + Math.min(minimumSum[j], minimumSum[j + 1]);
            }
        }

        return minimumSum[0];
    }
}


class ArrayTrianglePathTest {
    public static void main(String[] args) {
        List<Integer> row0 = new ArrayList<Integer>() {
            {
                add(2);
            }
        };

        List<Integer> row1 = new ArrayList<Integer>() {
            {
                add(3);
                add(4);
            }
        };

        List<Integer> row2 = new ArrayList<Integer>() {
            {
                add(6);
                add(5);
                add(7);
            }
        };

        List<Integer> row3 = new ArrayList<Integer>() {
            {
                add(4);
                add(1);
                add(8);
                add(3);
            }
        };


        List<List<Integer>> triangle = new ArrayList<List<Integer>>() {
            {
                add(row0);
                add(row1);
                add(row2);
                add(row3);
            }
        };

        System.out.println("Triangle: " + triangle);
        System.out.println("Minimum Path: " + ArrayTrianglePath.getMinimumSum(triangle));
    }


}