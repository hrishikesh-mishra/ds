package com.hrishikesh.ns.recur;

/**
 *
 * Given an array, check whether the array is in sorted order with recursion
 * Created by hrishikesh.mishra
 */
public class ArraySortChecker {

    public static boolean isSorted(int [] array, int index){
        if(index <= 1)
            return true;
        return (array[index - 1] < array[index - 2])? false : isSorted(array, index-1);
    }

    public static void main(String[] args) {
        int array1 [] = {2, 3, 4, 6, 6, 6,  34, 123, 567};
        int array2 [] = {2, 3, 4, 6, 2, 34, 123, 567};
        System.out.println("Is array1 sorted: " + isSorted(array1, array1.length));
        System.out.println("Is array2 sorted: " + isSorted(array2, array2.length));

    }
}
