package com.hrishikeshmishra.practices.mixed;

import static com.hrishikeshmishra.practices.mixed.SwapTwoNumber.swap;

/**
 * Problem:
 * Swap two number with temporary variable or arithmetic operations.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/swap-two-number/
 */
public class SwapTwoNumber {

    public static void swap(int a, int b) {
        System.out.println("Before swap - a:" + a + ", b:" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After swap - a:" + a + ", b:" + b);

    }
}


class SwapTwoNumberTest {
    public static void main(String[] args) {
        swap(10, 4);
        swap(100, 200);
        swap(-10, 4);
        swap(100, -200);
        swap(-10, -4);
        swap(-100, -200);
    }
}