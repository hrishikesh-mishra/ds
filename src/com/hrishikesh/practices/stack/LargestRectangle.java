package com.hrishikesh.practices.stack;

/**
 * Problem:
 * ;
 * Find the largest rectangular area possible in a given histogram where the largest rectangle
 * can be made of a number of contiguous bars. For simplicity, assume that all bars have same
 * width and the width is 1 unit.
 * ;
 * ;
 * Solution:
 * ;
 * This is one particular manifestation of the all nearest smaller values
 * (ANSV) (<code>com.hrishikesh.practices.stack.AllNearestSmallerValue</code>) problem.
 * For each bar in the histogram, we want to find the nearest bar on the left which is shorter,
 * and the nearest bar on the right which is shorter---because they determine how far left and how
 * far right we can extend a rectangle with the current bar's full height. Use the O(n) stack-based
 * algorithm for ANSV, and the problem becomes trivial.
 * ;
 * ;
 * Algorithm:
 * - Take 2 arrays (one-one for left and right width of a bar ) and one stack to hold index to calculate ANSV.
 * - Now calculate left width
 * - - Push 0 in add stack
 * - - Set left[0] = 0
 * - - Iterate given histogram array from index 1 to n
 * - - - If histogram[stack.top] >= histogram[i] && stack is not empty then
 * - - - - stack.pop
 * - - - If stack is empty then,
 * - - - - left[i] = i + 1
 * - - - Else
 * - - - - left[i] = i - stack.top()
 * - - - stack.push(i)
 * - - Populate right[i] same way as we did for left area but from nth to 0th index of histogram
 * - - Now calculate Area
 * - - - Set MaxArea = 0
 * - - Iterate given histogram array from index 0 to n
 * - - - width = left[i] + right[i] - 1
 * - - - area = width * histogram[i]
 * - - - If MaxArea < area then
 * - - - - MaxArea = area
 * - Return MaxArea
 *
 * @author hrishikesh.mishra
 * @link https://www.quora.com/What-is-the-algorithmic-approach-to-find-the-maximum-rectangular-area-in-a-histogram
 * @link http://hrishikeshmishra.com/the-largest-rectangular-problem/
 */
public class LargestRectangle {

    public static int calculate(int[] histogram) {

        /** Base case when histogram is empty. **/
        if (histogram.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();

        /** Array to hold shorter the nearest shorter bar **/
        int[] left = new int[histogram.length];
        int[] right = new int[histogram.length];

        /** for 0th index **/
        stack.push(0);
        left[0] = 1;

        /** Iterate array to compute left of width same ANSV **/
        for (int i = 1; i < histogram.length; i++) {

            /**  To find the nearest bar on the left which is shorter **/
            while (!stack.isEmpty() && histogram[i] <= histogram[stack.peek()]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                left[i] = i + 1;
            } else {
                left[i] = i - stack.peek();
            }

            stack.push(i);
        }

        /** Clear stack reuse it for right part computation **/
        stack.clear();

        /** For last index **/
        int lastIndex = histogram.length - 1;
        stack.push(lastIndex);
        right[lastIndex] = 1;

        /** Iterate array to compute right of width same ANSV **/
        for (int i = lastIndex - 1; i >= 0; i--) {

            /**  To find the nearest bar on the right which is shorter **/
            while (!stack.isEmpty() && histogram[i] <= histogram[stack.peek()]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                right[i] = (lastIndex - i) + 1;
            } else {
                right[i] = stack.peek() - i;
            }

            stack.push(i);
        }

        /** Compute area **/
        int width, height, area, maxArea = 0;
        for (int i = 0; i < histogram.length; i++) {
            width = left[i] + right[i] - 1;
            height = histogram[i];
            area = width * height;
            if (maxArea < area) {
                maxArea = area;
            }
        }

        return maxArea;
    }

}

class LargestRactangleTest {

    public static void main(String[] args) {
        int[] histogram = {6, 2, 5, 4, 5, 1, 6};
        int area = LargestRectangle.calculate(histogram);
        System.out.println("Largest Rectangle Area: " + area);

        int[] histogram2 = {2, 3, 3, 2};
        int area2 = LargestRectangle.calculate(histogram2);
        System.out.println("Largest Rectangle Area: " + area2);
    }
}
