package com.hrishikeshmishra.practices.dynamicprogramming;

import static com.hrishikeshmishra.practices.dynamicprogramming.MatrixChainMultiplication.findByDP;
import static com.hrishikeshmishra.practices.dynamicprogramming.MatrixChainMultiplication.findByRecursion;

/**
 * Problem:
 * Matrix Chain Multiplication
 * Given a sequence of matrices, Find the most efficient way to multiply these matrices.
 *
 * @author hrishikesh.mishra
 * @link https://en.wikipedia.org/wiki/Matrix_chain_multiplication
 * @link hrishikeshmishra.com/matrix-chain-multiplication/
 */
public class MatrixChainMultiplication {

    public static int findByRecursion(int[] matrices) {
        return findByRecursionHelper(matrices, 1, matrices.length - 1);
    }

    public static int findByRecursionHelper(int[] matrices, int i, int j) {
        /** Base case: When only one matrix **/
        if (i == j) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {

            int tempResult = findByRecursionHelper(matrices, i, k) +
                    findByRecursionHelper(matrices, k + 1, j) +
                    matrices[i - 1] * matrices[k] * matrices[j];

            if (tempResult < minCost) {
                minCost = tempResult;
            }
        }

        return minCost;
    }

    /**
     * <p>
     * Here ith Matrix row is in array[i-1] and
     * ith Matrix col is in array[i]
     * </p>
     *
     * @param array
     * @return
     */
    public static int findByDP(int[] array) {

        /** Total number of matrices **/
        int totalNumberOfMatrices = array.length - 1;

        /**
         * Create in table size add one plus in row and col,
         * it helps us to directly access multiplication count,
         * i.e. M(1.1) will store in table[1][1] and
         * M(2,3) will store in table[2][3]
         *
         */
        int[][] table = new int[totalNumberOfMatrices + 1][totalNumberOfMatrices + 1];

        /**
         * Compute multiplication cost length by length starting from 2
         * because cost length of 1 is zero, M(1,1) = 0, M(2,2) = 0 .... M(n,n) = 0, then
         * length 2, i.e. M(1,2), M(2,3) ...  M(n-1,n), then
         * length 3, i.e. M(1,3), M(2,4) ..... M(n-2,n)
         * .........
         * length n, i.e. M(1,n)
         */
        for (int length = 2; length <= totalNumberOfMatrices; length++) {

            for (int i = 1; i <= totalNumberOfMatrices - length + 1; i++) {

                /** End index **/
                int j = i + length - 1;

                /** Default Infinite value **/
                table[i][j] = Integer.MAX_VALUE;

                /** Finding min cost for length n from all n-1 length **/
                for (int k = i; k < j; k++) {
                    int temp = table[i][k] + table[k + 1][j] + (array[i - 1] * array[k] * array[j]);
                    if (temp < table[i][j]) {
                        table[i][j] = temp;
                    }
                }
            }
        }

        printMatrix(table);

        return table[1][totalNumberOfMatrices];
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("\n--------------------------------------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%5d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }
}


class MatrixChainMultiplicationTest {

    public static void main(String[] args) {
        int[] matrices = {1, 2, 3, 4, 3};
        System.out.println("Minimum Cost: " + findByRecursion(matrices));
        System.out.println("Minimum Cost: " + findByDP(matrices));
    }
}