package com.hrishikeshmishra.practices.queue;

import java.util.Arrays;
import java.util.Collections;

/**
 * Problem :
 * Find the largest multiple of 3
 * ;
 * Given an array of non-negative integers. Find the largest multiple of 3 that can be formed from array elements.
 * For example, if the input array is {8, 1, 9}, the output should be “9 8 1”, and
 * if the input array is {8, 1, 7, 6, 0}, output should be “8 7 6 0”.
 * ;
 * Solution:
 * ;
 * Number three has some special property
 * -  A number is multiple of 3 if and only if the sum of digits of number is multiple of 3.
 * For example, let us consider 8760, it is a multiple of 3 because sum of digits is 8 + 7+ 6+ 0 = 21,
 * which is a multiple of 3.
 * -  If a number is multiple of 3, then all permutations of it are also multiple of 3.
 * For example, since 6078 is a multiple of 3, the numbers 8760, 7608, 7068, ….. are also multiples of 3.
 * - We get the same remainder when we divide the number and sum of digits of the number.
 * For example, if divide number 151 and sum of it digits 7, by 3, we get the same remainder 1.
 * ;
 * ;
 * Algorithm:
 * - First sort the array in ascending order
 * - Take three queues,
 * - - 1. Queue0 - To hold digit % 3 == 0
 * - - 2. Queue1 - To hold digit % 3 == 1
 * - - 3. Queue2 - To hold digit % 3 == 2
 * - Iterate array from start to end
 * - - Divide digit by 3 and put in respective queues based on remainder
 * - - And calculate sum of all digits
 * - Calculate remainder = sum % 3
 * - if remainder == 1
 * - - Dequeue one element from Queue1 or two dequeue from Queue2
 * - - (because 5 + 2 = 7, 7 % 3 = 1)
 * - else if remainder == 2
 * - - Dequeue one element from Queue2 or two dequeue from Queue1
 * - Merge Queue0, Queue1 and Queue2 into one list
 * - sort merge list in descending order
 * - return merged sorted order
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-the-largest-multiple-of-3/
 */
public class LargestMultipleOfThree {

    public static int find(int[] array) {

        /** sort in ascending order **/
        Arrays.sort(array);

        /** To hold those digits such that digit % 3 == 0 **/
        Queue<Integer> queue0 = new Queue<>();
        /** To hold those digits such that digit % 3 == 1 **/
        Queue<Integer> queue1 = new Queue<>();
        /** To hold those digits such that digit % 3 == 2 **/
        Queue<Integer> queue2 = new Queue<>();

        /** Separating digits based on their remainder **/
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            switch (array[i] % 3) {
                case 0:
                    queue0.enqueue(array[i]);
                    break;
                case 1:
                    queue1.enqueue(array[i]);
                    break;
                default:
                    queue2.enqueue(array[i]);
                    break;
            }
        }

        // @// TODO: 20/11/16 deep dive

        /** Remove some digit **/
        switch (sum % 3) {
            case 1:
                if (!queue1.isEmpty()) {
                    dequeueX(queue1, 1);
                } else {
                    dequeueX(queue2, 2);
                }
                break;
            case 2:
                if (!queue2.isEmpty()) {
                    dequeueX(queue2, 1);
                } else {

                    dequeueX(queue1, 2);
                }
                break;
        }

        /** Merge all digits from did**/
        Integer[] digits = mergeQueue(queue0, queue1, queue2);

        /** Sort digit in reverse order **/
        Arrays.sort(digits, Collections.reverseOrder());

        return formNumber(digits);

    }

    private static int formNumber(Integer[] digits) {
        StringBuilder sb = new StringBuilder(digits.length);
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }

        return Integer.valueOf(sb.toString());
    }

    private static Integer[] mergeQueue(Queue<Integer> queue0, Queue<Integer> queue1, Queue<Integer> queue2) {

        Integer[] array = new Integer[queue0.getSize() + queue1.getSize() + queue2.getSize()];

        int i = 0;
        while (!queue0.isEmpty()) {
            array[i++] = queue0.dequeue();
        }

        while (!queue1.isEmpty()) {
            array[i++] = queue1.dequeue();
        }
        while (!queue2.isEmpty()) {
            array[i++] = queue2.dequeue();
        }
        return array;
    }

    private static void dequeueX(Queue<Integer> queue, int numberOfTimes) {
        try {
            for (int i = 0; i < numberOfTimes; i++) {
                queue.dequeue();
            }
        } catch (Exception e) {
            throw new RuntimeException("Number not possible ");
        }
    }

}


class LargestMultipleOfThreeTest {
    public static void main(String[] args) {
        int[] digits = {8, 1, 7, 6, 0};
        System.out.println(LargestMultipleOfThree.find(digits));
    }
}
