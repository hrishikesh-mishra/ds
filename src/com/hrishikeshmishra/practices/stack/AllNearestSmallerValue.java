package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * All nearest smaller values (ANSV)
 * The all nearest smaller values problem is the following task: for each position in a sequence of numbers,
 * search among the previous positions for the last position that contains a smaller value.
 * ;
 * Example:
 * Input =   {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}.
 * Output =  {—, 0, 0,  4, 0,  2, 2,  6, 0, 1, 1,  5, 1,  3, 3,  7}
 * ;
 * Algorithm:
 * - We need stack to hold all smaller that found till particular position in array
 * - Now take an stack and push 0
 * - Take array S for nearest smaller values and put S[0] = -∞
 * - Iterate input array from index 1 to n-1
 * - - If stack.top > array[i] then,
 * - - - stack.pop
 * - - Repeat this process till stack is not empty or above condition doesn't break
 * - - At this point, we have two cases
 * - - - Case 1: When stack is empty - means no smaller value than current one
 * - - - Case 2: When stack is not empty - means top of stack has nearest smaller values for this element
 * - - If stack is empty then
 * - - - S[i] =  -∞
 * - - Else
 * - - - S[i] = stack.top
 * - - stack.push(array[i])
 *
 * @author hrishikesh.mishra
 * @link https://en.wikipedia.org/wiki/All_nearest_smaller_values
 * @link http://hrishikeshmishra.com/all-nearest-smaller-values-ansv/
 */
public class AllNearestSmallerValue {

    public static int[] findANSV(int[] array) {

        /** Base case : When array is empty **/
        if (array.length == 0) {
            return new int[]{Integer.MIN_VALUE};
        }

        int[] result = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(array[0]);
        result[0] = Integer.MIN_VALUE;

        for (int i = 1; i < array.length; i++) {

            while (!stack.isEmpty() && stack.peek() >= array[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = Integer.MIN_VALUE;
            } else {
                result[i] = stack.peek();
            }
            stack.push(array[i]);
        }
        return result;
    }
}


class AllNearestSmallerValueTest {

    public static void main(String[] args) {
        int[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int[] output = AllNearestSmallerValue.findANSV(input);

        System.out.println("Input\t\tANSV");
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i] + "\t\t\t" + ((i == 0) ? "-" : output[i]));
        }
    }
}
