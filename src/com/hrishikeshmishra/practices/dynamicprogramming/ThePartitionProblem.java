package com.hrishikeshmishra.practices.dynamicprogramming;

/**
 * Problem:
 * The Partition Problem
 * Minimum Sum Of Maximums Of Partitions
 *
 * @author hrishikesh.mishra
 * @Todo
 */
public class ThePartitionProblem {

    public void partition(int[] s, int k) {
        int n = s.length;

        /** DP table for value **/
        int[][] m = new int[n + 1][k + 1];

        /** DP table for dividers **/
        int[][] d = new int[n + 1][k + 1];

        /** prefix sum array **/
        int[] prefixSum = new int[n + 1];

        int cost, i, j, x;

        prefixSum[0] = 0;

        /** Constructing prefix sum **/
        for (i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + s[i];
        }

        /** Bounder conditions **/
        for (i = 1; i <= n; i++) {
            m[i][1] = prefixSum[i];
        }

        for (j = 1; j <= k; j++) {
            m[1][j] = s[i];
        }


        for (i = 2; i <= n; i++) {
            for (j = 2; j <= k; j++) {
                m[i][j] = Integer.MAX_VALUE;

                for (x = 1; x <= (i - 1); x++) {
                    cost = Math.max(m[x][j - 1], prefixSum[i] - prefixSum[x]);

                    if (m[i][j] > cost) {
                        m[i][j] = cost;
                        d[i][j] = x;
                    }
                }
            }
        }
    }

}
