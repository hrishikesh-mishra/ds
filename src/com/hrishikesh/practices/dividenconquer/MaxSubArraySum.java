package com.hrishikesh.practices.dividenconquer;

import java.util.Arrays;

import static com.hrishikesh.practices.dividenconquer.MaxSubArraySum.findMaximumSubArray;

/**
 * Problme:
 * Maximum sub-array sum :
 * Given a array find maximum sub-array sum.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/maximum-sub-array-sum/
 */
public class MaxSubArraySum {

    public static class Result {
        int low;
        int high;
        int sum;

        public Result(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }
    }

    public static Result findMaximumSubArray(int[] array, int low, int high) {

        if (high == low) {
            return new Result(low, high, array[low]);
        } else {
            int mid = (low + high) / 2;

            Result leftResult = findMaximumSubArray(array, low, mid);
            Result rightResult = findMaximumSubArray(array, mid + 1, high);
            Result crossResult = findMaxInCrossSubArray(array, low, mid, high);

            if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossResult.sum) {
                return new Result(leftResult.low, leftResult.high, leftResult.sum);
            } else if (rightResult.sum >= leftResult.sum && rightResult.sum >= crossResult.sum) {
                return new Result(rightResult.low, rightResult.high, rightResult.sum);
            } else {
                return new Result(crossResult.low, crossResult.high, crossResult.sum);
            }
        }
    }

    private static Result findMaxInCrossSubArray(int[] array, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxleft = mid;

        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxleft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid + 1;
        for (int i = mid + 1; i <= high; i++) {
            sum += array[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new Result(maxleft, maxRight, leftSum + rightSum);
    }

}

class MaxSubArraySumTest {

    public static void main(String[] args) {
        int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        System.out.println("Array: " + Arrays.toString(array));
        MaxSubArraySum.Result result = findMaximumSubArray(array, 0, array.length - 1);
        System.out.println("SubArray: " + Arrays.toString(getSubArray(array, result.low, result.high)));
        System.out.println("Sum: " + result.sum);

    }

    private static int[] getSubArray(int[] array, int start, int end) {
        int[] subArray = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            subArray[i - start] = array[i];
        }
        return subArray;
    }


}
