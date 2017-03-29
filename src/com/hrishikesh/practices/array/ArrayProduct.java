package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.ArrayProduct.getProducts;
import static com.hrishikesh.practices.array.ArrayProduct.getProducts2;

/**
 * Problem
 * Product of array elements except self
 * Given an array of n integers of array size greater than one,
 * return an array result such that result[i] is equal to the product of all the elements except it self index.
 * ;
 * Constraints:
 * - time O(n)
 * - And without divide operation
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/product-array-elements-except-self/
 */
public class ArrayProduct {

    /**
     * In constant memory
     *
     * @param arrays
     * @return
     */
    public static int[] getProducts2(int[] arrays) {
        int n = arrays.length;
        int[] result = new int[n];

        result[0] = 1;

        /** Left portion **/
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * arrays[i - 1];
        }

        /** Right portion **/
        int right = arrays[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            result[j] = right * result[j];
            right *= arrays[j];
        }


        return result;
    }

    public static int[] getProducts(int[] arrays) {
        int n = arrays.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] result = new int[n];

        left[0] = 1;
        right[n - 1] = 1;

        /** Left portion **/
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * arrays[i - 1];
        }

        /** Right portion **/
        for (int j = n - 2; j >= 0; j--) {
            right[j] = right[j + 1] * arrays[j + 1];
        }

        /** Result **/
        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }
}

class ArrayProductTest {
    public static void main(String[] args) {
        int[] array = {9, 0, -2};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Product: " + Arrays.toString(getProducts(array)));
        System.out.println("Product2: " + Arrays.toString(getProducts2(array)));

    }


}
