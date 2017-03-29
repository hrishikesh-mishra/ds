package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.MissingTwoNumbersInArray.findingMissingTwoNumbers;

/**
 * Problem:
 * Missing two numbers in array
 * An array contains all the integer from 0 to n, expect for two numbers which are missing.
 * Find missing number in O(n) in O(1) extra space.
 * ;
 * ;
 * Facts:
 * - Find missing numbers sum
 * - Find missing numbers product
 * - Suppose missing numbers are x and y.
 * - Now, x + y = sum_of_missing_numbers and
 * - x * y = product_of_missing_numbers
 * - Solve this quadratic equation.
 * ;
 * ;
 * ;
 * Algorithm:
 * - Compute sum_of_missing_numbers
 * - Computer product_of_missing_numbers
 * - Set a = 1, b = sum_of_missing_numbers and c = product_of_missing_numbers
 * - Now Solve ax^2 + bx + c = 0
 * - Missing number1 = (-b+√​(b​2​​−4ac​​​​​)) / 2a​​
 * - Missing number2 = (-b-√​(b​2​​−4ac​​​​​)) / 2a​​
 * - Return number1 and number2
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/missing-two-numbers-array/
 */
public class MissingTwoNumbersInArray {

    public static class Result {
        private int number1;
        private int number2;

        public Result(int number1, int number2) {
            this.number1 = number1;
            this.number2 = number2;
        }

        public int getNumber1() {
            return number1;
        }

        public int getNumber2() {
            return number2;
        }

        @Override
        public String toString() {
            return "Missing Numbers {" +
                    "n1=" + number1 +
                    ", n2=" + number2 +
                    '}';
        }
    }

    public static Result findingMissingTwoNumbers(int[] array, int n) {
        int sumOfNNaturalNumber = sumOfNNaturalNumber(n);
        int sumOfArrayElement = sumOfArrayElements(array);
        int sumOfMissingNumbers = sumOfNNaturalNumber - sumOfArrayElement;

        int productOfNNaturalNumber = productOfNaturalNumber(n);
        int productOfArrayElements = productOfArrayElement(array);
        int productOfMissingNumbers = productOfNNaturalNumber / productOfArrayElements;


        int a = 1;
        int b = sumOfMissingNumbers;
        int c = productOfMissingNumbers;

        double part1 = -1 * b;
        double part2 = Math.sqrt(b * b - 4 * a * c);
        double part3 = 2 * a;

        int missingNumber1 = (int) ((part1 + part2) / part3);
        int missingNumber2 = (int) ((part1 - part2) / part3);

        return new Result(missingNumber1, missingNumber2);
    }

    private static int sumOfArrayElements(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static int sumOfNNaturalNumber(int n) {
        return n * (n + 1) / 2;
    }

    private static int productOfNaturalNumber(int n) {
        int product = 1;
        for (int i = 2; i <= n; i++) {
            product *= i;
        }
        return product;
    }

    private static int productOfArrayElement(int[] array) {
        int product = 1;
        for (int i = 0; i < array.length; i++) {
            product *= array[i];
        }
        return product;
    }
}


class MissingTwoNumbersInArrayTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 4, 6, 5};
        int n = 7;

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("N: " + n);
        System.out.println("Missing Number: " + findingMissingTwoNumbers(array, n));
    }
}