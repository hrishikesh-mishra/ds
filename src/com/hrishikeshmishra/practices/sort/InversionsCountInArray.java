package com.hrishikeshmishra.practices.sort;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.sort.InversionsCountInArray.getCountByMergeSort;

/**
 * Problem:
 * Inversion Count for an array indicates –
 * how far (or close) the array is from being sorted.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/inversion-count/
 */
public class InversionsCountInArray {

    public static long getCountByBruteForce(int[] a) {
        long inversionCount = 0;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    inversionCount++;
                }
            }
        }

        return inversionCount;
    }

    public static long getCountByMergeSort(int[] a) {
        return mergeSort(a, 0, a.length - 1);
    }

    public static long mergeSort(int[] a, int left, int right) {
        if (right > left) {
            int mid = (left + right) / 2;
            long inversionCount = mergeSort(a, left, mid);
            inversionCount += mergeSort(a, mid + 1, right);

            inversionCount += merge(a, left, mid + 1, right, new int[a.length]);
            return inversionCount;
        }

        return 0;

    }

    private static long merge(int[] a, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid;
        int counter = left;
        long inversionCount = 0;

        while (i < mid && j <= right) {

            if (a[i] <= a[j]) {
                temp[counter++] = a[i++];
            } else {
                temp[counter++] = a[j++];
                inversionCount += (mid - i);
            }
        }


        while (i < mid) {
            temp[counter++] = a[i++];
        }

        while (j <= right) {
            temp[counter++] = a[j++];
        }

        for (int c = left; c <= right; c++) {
            a[c] = temp[c];
        }

        return inversionCount;
    }
}


class InversionsCountInArrayTest {

    public static void main(String[] args) {
        int[] array1 = {1, 1, 1, 2, 2};
        int[] array2 = {2, 1, 3, 1, 2};
        int[] array3 = {1, 1, 1, 2, 2};
        int[] array4 = {2, 1, 3, 1, 2};


        getCountByMergeSort(array2);


        System.out.println("Array: " + Arrays.toString(array1));
        System.out.println("Inversion Count by Brute Force: " + InversionsCountInArray.getCountByBruteForce(array1));
        System.out.println("Inversion Count by Merge Sort: " + InversionsCountInArray.getCountByMergeSort(array1));
        System.out.println();

        System.out.println("Array: " + Arrays.toString(array2));
        System.out.println("Inversion Count by Brute Force: " + InversionsCountInArray.getCountByBruteForce(array2));
        System.out.println("Inversion Count by Merge Sort: " + InversionsCountInArray.getCountByMergeSort(array2));
        System.out.println();

        System.out.println("Array: " + Arrays.toString(array3));
        System.out.println("Inversion Count by Brute Force: " + InversionsCountInArray.getCountByBruteForce(array3));
        System.out.println("Inversion Count by Merge Sort: " + InversionsCountInArray.getCountByMergeSort(array3));
        System.out.println();

        System.out.println("Array: " + Arrays.toString(array4));
        System.out.println("Inversion Count by Brute Force: " + InversionsCountInArray.getCountByBruteForce(array4));
        System.out.println("Inversion Count by Merge Sort: " + InversionsCountInArray.getCountByMergeSort(array4));
        System.out.println();
    }
}