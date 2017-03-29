package com.hrishikesh.practices.dynamicprogramming;

import static com.hrishikesh.practices.dynamicprogramming.EggDroppingPuzzle.solveByByDP;
import static com.hrishikesh.practices.dynamicprogramming.EggDroppingPuzzle.solveByRecursion;

/**
 * Problem:
 * Egg Dropping Puzzle
 * We need to find minimum number of egg droppings that will work in all cases (including worst-case).
 * ;
 * ;
 * Formula:
 * - This problem is not above from which floor eggs should be dropped, its about to minimize number of drops.
 * - Suppose, we have n eggs and k floors then,
 * - Base Case:
 * - - When floor is 1 then, MinNoOfDrops(n, 1)=1
 * - - And when egg is 1 the, MinNoOfDrops(1, k)=k
 * - Generailsed Solution:
 * - - MinNoOfDrops(n, k) = 1 + min{ max(MinNoOfDrops(n − 1, x − 1), MinNoOfDrops(n,k − x)) } , x = 1, 2, ..., k
 * ;
 * ;
 * ;
 * Dynamic Programming Algorithm:
 * - Create dp table of (totalEggs + 1) X (totalFloors + 1)
 * - Base Case: When egg is zero or one then, set for floor i, table[0][i] = 0; and table[1][i] = i;
 * - Base Case: Floor is zero or one then, set for egg j, table[j][0] = 0 and table[j][1] = 1;
 * - Iterate egg i from 2 to total_eggs
 * - - Iterate floor j from 2 to total_floors
 * - - - Set table[i][j] = INFINITY
 * - - - Iterate floor k from 1 to j
 * - - - - Set maxDrop = 1 + max(table[i-1][k-1], table[i][j-k])
 * - - - - If table[i][j]  > maxDrop  then
 * - - - - -  table[i][j]  =  maxDrop
 *
 * @author hrishikesh.mishra
 * @quora https://www.quora.com/What-is-the-solution-to-the-dropping-eggs-puzzle
 * @stackoverflow http://stackoverflow.com/questions/10177389/generalised-two-egg-puzzle
 * @wiki https://en.wikipedia.org/wiki/Dynamic_programming#Egg_dropping_puzzle
 * @link http://hrishikeshmishra.com/egg-dropping-puzzle/
 */
public class EggDroppingPuzzle {

    /**
     * Not efficient
     **/
    public static int solveByRecursion(int totalEggs, int totalFloors) {

        /** Base Case: When no floor **/
        if (totalFloors == 0) {
            return 0;
        }

        /** Base case: When only one floor **/
        if (totalFloors == 1) {
            return 1;
        }

        /** Base case: When only one eggs, then we have to try it from all floors **/
        if (totalEggs == 1) {
            return totalFloors;
        }

        int minimumDrops = Integer.MAX_VALUE;
        /** Now drop a egg from floor 1 to totalFloors **/
        for (int k = 1; k <= totalFloors; k++) {

            /** When an egg breaks at kth floor **/
            int totalDropWhenEggBreaks = solveByRecursion(totalEggs - 1, k - 1);

            /** When egg doesn't break at kth floor **/
            int totalDropWhenEggNotBreaks = solveByRecursion(totalEggs, totalFloors - k);

            /** Worst between above conditions **/
            int maxDrop = Math.max(totalDropWhenEggBreaks, totalDropWhenEggNotBreaks);


            /** Minimum drops for all floors **/
            if (minimumDrops > maxDrop) {
                minimumDrops = maxDrop;
            }
        }

        return minimumDrops + 1;
    }

    public static int solveByByDP(int totalEggs, int totalFloors) {
        int[][] table = new int[totalEggs + 1][totalFloors + 1];

        /** Base Case: When egg is zero or one **/
        for (int i = 0; i < totalFloors + 1; i++) {
            table[0][i] = 0;
            table[1][i] = i;
        }

        /** Base case: Floor is zero or one **/
        for (int j = 0; j < totalEggs + 1; j++) {
            table[j][0] = 0;
            table[j][1] = 1;
        }

        /** For floor more than 1 and eggs are also more than 1 **/
        for (int i = 2; i < totalEggs + 1; i++) {
            for (int j = 2; j < totalFloors + 1; j++) {

                table[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {

                    /** When an egg breaks at kth floor **/
                    int totalDropWhenEggBreaks = table[i - 1][k - 1];

                    /** When egg doesn't break at kth floor **/
                    int totalDropWhenEggNotBreaks = table[i][j - k];

                    /** Worst between above conditions **/
                    int maxDrop = 1 + Math.max(totalDropWhenEggBreaks, totalDropWhenEggNotBreaks);

                    /** Minimum drops for all floors **/
                    if (maxDrop < table[i][j]) {
                        table[i][j] = maxDrop;
                    }
                }
            }
        }

        return table[totalEggs][totalFloors];
    }
}


class EggDroppingPuzzleTest {
    public static void main(String[] args) {
        int totalFloor = 10;
        int totalEggs = 2;

        System.out.println("Total Floors : " + totalFloor);
        System.out.println("Total Eggs : " + totalEggs);
        System.out.println("Minimum Drops (By DP): " + solveByByDP(totalEggs, totalFloor));
        System.out.println("Minimum Drops (By recursion): " + solveByRecursion(totalEggs, totalFloor));

    }


}