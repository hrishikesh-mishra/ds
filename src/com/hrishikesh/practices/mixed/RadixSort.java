package com.hrishikesh.practices.mixed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hrishikesh.practices.mixed.RadixSort.sort;

/**
 * Problem:
 * Radix Sort
 * ;
 * complexity : O(kn) for n keys which are integers of word size k.
 * ;
 * Algorithm:
 * - Create buckets (for decimal it would be 10, from 0 to 9 digits)
 * - Iterate all number of array one by one
 * - - Find digit at once place
 * - - Put number in bucket based on digit
 * - Put number back to array from bucket
 * - Iterate same for all digits of all numbers
 *
 * @author hrishikesh.mishra
 * @wiki https://en.wikipedia.org/wiki/Radix_sort
 * @link http://hrishikeshmishra.com/radix-sort/
 */
public class RadixSort {

    /**
     * Radix Buckets Decimals
     **/
    public static final int RADIX_BUCKETS_DECIMALS = 10;

    public static void sort(int[] array) {
        /** Create buckets for radix for decimal numbers **/
        List<Integer>[] buckets = getBuckets(RADIX_BUCKETS_DECIMALS);

        /** Starting place in number (i.e. ones place) **/
        int place = 1;

        /** To check is max digit end place reached or not **/
        boolean isMaxDigitEndPlaceReached = false;

        while (!isMaxDigitEndPlaceReached) {
            isMaxDigitEndPlaceReached = true;

            /** Put all number of respective buckets **/
            for (int i = 0; i < array.length; i++) {
                int element = array[i];
                int temp = element / place;
                int bucketId = temp % RADIX_BUCKETS_DECIMALS;
                buckets[bucketId].add(element);

                if (temp > 0) {
                    isMaxDigitEndPlaceReached = false;
                }
            }

            /** Put all numbers from bucket to array **/
            int counter = 0;
            for (int bucket = 0; bucket < RADIX_BUCKETS_DECIMALS; bucket++) {
                counter = addFromBucketToArray(buckets[bucket], array, counter);
            }

            /** Clear buckets **/
            clearBuckets(buckets);

            /** Clear place **/
            place *= 10;
        }
    }

    private static int addFromBucketToArray(List<Integer> bucket, int[] array, int arrayIndex) {
        if (bucket.isEmpty()) {
            return arrayIndex;
        }

        for (int item : bucket) {
            array[arrayIndex++] = item;
        }
        return arrayIndex;
    }

    private static void clearBuckets(List<Integer>[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i].clear();
        }
    }

    private static List<Integer>[] getBuckets(int size) {
        List<Integer>[] buckets = new ArrayList[RADIX_BUCKETS_DECIMALS];
        for (int i = 0; i < size; i++) {
            buckets[i] = new ArrayList<>();
        }
        return buckets;
    }
}

class RadixSortTest {
    public static void main(String[] args) {
        int[] array = {2, 14, 1211, 32, 422, 54, 2, 11, 23, 1661, 212};
        System.out.println("Before sorting: " + Arrays.toString(array));
        sort(array);
        System.out.println("After sorting: " + Arrays.toString(array));
    }
}