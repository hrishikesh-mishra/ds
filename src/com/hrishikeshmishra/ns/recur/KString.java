package com.hrishikeshmishra.ns.recur;

/**
 * Problem:
 * Generate all the strings of length n drawn from 0..k-1
 * ;
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/generate-strings-length-n-drawn-0-k-1/
 */
public class KString {

    public static void kString(int[] array, int n, int k) {
        if (n < 1)
            printArray(array);
        else {
            for (int j = 0; j < k; j++) {
                array[n - 1] = j;
                kString(array, n - 1, k);
            }
        }
    }

    public static void printArray(int[] array) {
        for (int i : array)
            System.out.print(i);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 1};

        System.out.println("----------------------------");
        System.out.println("\tKString ");
        System.out.println("----------------------------");
        kString(array, array.length, 1);


        System.out.println("----------------------------");
        System.out.println("\tKString ");
        System.out.println("----------------------------");
        kString(array, array.length, 2);


        System.out.println("----------------------------");
        System.out.println("\tKString ");
        System.out.println("----------------------------");
        kString(array, array.length, 3);

    }

}
