package com.hrishikeshmishra.practices.dynamicprogramming;

/**
 * Problem:
 * Minimum Cost Path Finder
 * Given a 2D matrix where each cell has a cost to travel.
 * Find a path from left-top corner to bottom-right corner with minimum travel cost.
 * Constraint: You can move only right or down.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/minimum-cost-path-finder/
 */
public class MinimumCostPathFinder {

    public static int find(int[][] costMatrix) {
        int[][] dpTable = new int[costMatrix.length][costMatrix[0].length];

        /** Top left corner cost **/
        dpTable[0][0] = costMatrix[0][0];

        /** First column cost **/
        for (int i = 1; i < dpTable.length; i++) {
            dpTable[i][0] = dpTable[i - 1][0] + costMatrix[i][0];
        }

        /** First row cost **/
        for (int i = 1; i < dpTable.length; i++) {
            dpTable[0][i] = dpTable[0][i - 1] + costMatrix[0][i];
        }


        for (int i = 1; i < dpTable.length; i++) {
            for (int j = 1; j < dpTable[i].length; j++) {
                dpTable[i][j] = Math.min(dpTable[i][j - 1], dpTable[i - 1][j]) + costMatrix[i][j];
            }
        }

        return dpTable[dpTable.length - 1][dpTable[0].length - 1];
    }
}


class MinimumCostPathFinderTest {
    public static void main(String[] args) {

        int[][] costMatrix = {
                {1, 7, 9, 2},
                {8, 6, 3, 2},
                {1, 6, 7, 8},
                {2, 9, 8, 2}
        };

        printMatrix(costMatrix);

        System.out.println("Minimum Cost : " + MinimumCostPathFinder.find(costMatrix));

    }

    private static void printMatrix(int[][] costMatrix) {
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[0].length; j++) {
                System.out.printf("%d ", costMatrix[i][j]);
            }
            System.out.println();
        }
    }
}
