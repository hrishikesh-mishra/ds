package com.hrishikeshmishra.ns.recur;

/**
 *
 * Generate all the strings of n bits. Assume A[0..n-1] is any array of size n.
 *
 * Created by hrishikesh.mishra
 */
public class BinaryStringGenerator {

    public static void generateBinary(int [] array, int n){
        if(n<1)
            printArray(array);
        else {
            array [n -1] = 0;
            generateBinary(array, n-1);
            array [n -1] = 1;
            generateBinary(array, n-1);
        }
    }

    public static void printArray(int [] array){
        for (int i : array) {
            System.out.print(i);
        }
        System.out.println();

    }

    public static void main(String[] args) {

        int [] array0 = {1, 1};
        int [] array1 = {1, 1, 1};
        int [] array2 = {1, 1, 1, 1};

        System.out.println("----------------------------");
        System.out.println("\tBinary of length 2");
        System.out.println("----------------------------");
        generateBinary(array0, array0.length);


        System.out.println("----------------------------");
        System.out.println("\tBinary of length 3");
        System.out.println("----------------------------");
        generateBinary(array1, array1.length);

        System.out.println("----------------------------");
        System.out.println("\tBinary of length 4");
        System.out.println("----------------------------");
        generateBinary(array2, array2.length);


    }


}
