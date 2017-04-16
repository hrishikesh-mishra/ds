package com.hrishikeshmishra.ns.stack;

/**
 * Problem:
 * Given an array A the span S[i] of A[j] is the maximum number
 * of consecutive elements A[j] immediately A[i] and such that
 * A[j] <= A[j+1]
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/array-consecutive-element-counter/
 */
public class ArrayConsecutiveFinder {

    public int[] createSpanOfConsecutive(int[] array) {
        int[] spanArray = new int[array.length];
        Stack<Integer> stack = new LinkedStack<>();

        for (int i = 0; i < array.length; i++) {
            if (stack.isEmpty())
                stack.push(1);
            else if (array[i] > array[i - 1])
                stack.push(stack.top() + 1);
            else
                stack.push(1);
            spanArray[i] = stack.top();
        }
        return spanArray;
    }

}

class ArrayConsecutiveFinderTest {
    public static void main(String[] args) {
        int[] spanOfConsecutiveElements, array = {6, 3, 4, 5, 2};
        ArrayConsecutiveFinder consecutiveFinder = new ArrayConsecutiveFinder();
        spanOfConsecutiveElements = consecutiveFinder.createSpanOfConsecutive(array);
        int i;

        System.out.print("\nArray : ");
        for (i = 0; i < array.length; i++)
            System.out.print(array[i] + ", ");

        System.out.print("\nSpan : ");
        for (i = 0; i < spanOfConsecutiveElements.length; i++)
            System.out.print(spanOfConsecutiveElements[i] + ", ");


    }
}