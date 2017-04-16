package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

/**
 * Problem:
 * Maximum subarray product
 * Find the contiguous subarray within an array  which has the largest product.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/maximum-subarray-product/
 */
public class MaximumSubArrayProduct {

    public static int find(int[] array) {

        int[] max = new int[array.length];
        int[] min = new int[array.length];
        Integer maxProduct;
        max[0] = min[0] = maxProduct = array[0];

        for (int i = 1; i < array.length; i++) {

            if (array[i] > 0) {
                max[i] = Math.max(array[i], max[i - 1] * array[i]);
                min[i] = Math.min(array[i], min[i - 1] * array[i]);
            } else {
                max[i] = Math.max(array[i], min[i - 1] * array[i]);
                min[i] = Math.min(array[i], max[i - 1] * array[i]);
            }

            maxProduct = Math.max(maxProduct, max[i]);
        }


        return maxProduct;
    }
}

class MaximumSubArrayProductTest {
    public static void main(String[] args) {
        int[] array = {1, 0, -1, 2, 3, -5, -2};
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Max contiguous product: " + MaximumSubArrayProduct.find(array));
    }
}
