package com.hrishikesh.practices.recursion;

import static com.hrishikesh.practices.recursion.RecursiveMultiplication.multiply;

/**
 * Problem:
 * Recursive Multiplication
 * Implement recursive multiplication that multiply two positive numbers without using * operator.
 * ;
 * ;
 * Algorithm:
 * - Recursively divide smaller number by 2 using bitwise operator till it reaches to 0 to 1
 * - And return double to bigger number
 * - Need take care of special case when half smaller number becomes odd
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/recursive-multiplication/
 */
public class RecursiveMultiplication {

    public static int multiply(int x, int y) {
        int smaller = Math.min(x, y);
        int bigger = Math.max(x, y);
        return multiplyWithOrder(smaller, bigger);
    }

    private static int multiplyWithOrder(int smaller, int bigger) {
        /** When smaller reaches to 0 **/
        if (smaller == 0) {
            return 0;
        }

        /** When smaller reaches to 1 **/
        if (smaller == 1) {
            return bigger;
        }

        /** divide smaller number by 2 **/
        int half = smaller >> 1;

        /** Recursively call **/
        int halfProduct = multiply(half, bigger);

        /** If smaller was even **/
        if (smaller % 2 == 0) {
            return halfProduct + halfProduct;
        } else {
            /** If smaller was odd **/
            /**
             * For example:
             * 5 * 7 = 2 (2, 7) + 5
             *
             */
            return halfProduct + halfProduct + bigger;
        }
    }
}


class RecursiveMultiplicationTest {

    public static void main(String[] args) {

        System.out.println("3 X 4 :" + multiply(3, 4));
        System.out.println("2 X 4 :" + multiply(2, 4));
        System.out.println("13 X 5 :" + multiply(13, 5));
    }
}