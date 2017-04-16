package com.hrishikeshmishra.ns.stack;

/**
 * Problem Statement:
 * ;
 * Given an array with heights of rectangle (assuming width is 1), we need
 * to find the largest rectangle area possible.
 * ;
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-largest-rectangle-in-histogram/
 */
public class LargestRectangleHistogram {

    /**
     * <p>
     * NonLinear solution
     * <p>
     * Finding Li = O(n^2)
     * Finding Ri = O(n^2)
     * Calculating Ai = O(n)
     * Finding max A(i) = O(n)
     * <p>
     * So, Final complexity is O(n^2) + O(n^2) + O(n) + O(n) = O(n^2)
     * </p>
     *
     * @param a
     * @return
     */
    public int calculateAreaInNonLinearTime(int[] a) {
        int[] Li = new int[a.length],
                Ri = new int[a.length],
                Ai = new int[a.length];

        int i, j, width, height, maxArea = 0;

        /** Calculating Li **/
        for (i = 0; i < a.length; i++) {
            for (j = i - 1; j >= 0; j--)
                if (a[j] < a[i])
                    break;
            Li[i] = i - j - 1;
        }


        /** Calculating Ri **/
        for (i = 0; i < a.length; i++) {
            for (j = i + 1; j < a.length; j++)
                if (a[j] < a[i])
                    break;
            Ri[i] = j - i - 1;
        }

        /** Calculating Area A[i] **/
        for (i = 0; i < a.length; i++) {
            // System.out.println("L " + Li[i] + ", R " + Ri[i] + ", H " + a[i]);
            width = Li[i] + Ri[i] + 1;
            height = a[i];
            Ai[i] = height * width;
        }

        /** Finding maximum area **/
        for (i = 0; i < a.length; i++) {
            if (maxArea < Ai[i])
                maxArea = Ai[i];
        }
        return maxArea;
    }

    /**
     * <p>
     * Linear solution
     * using stack
     * <p>
     * Finding Li = O(n)
     * Finding Ri = O(n)
     * Calculating Ai = O(n)
     * Finding max A(i) = O(n)
     * <p>
     * So, Final complexity is O(n) + O(n) + O(n) + O(n) = O(n)
     * </p>
     *
     * @param a
     * @return
     */
    public int calculateAreaInLinearTime(int[] a) {
        Stack<Integer> stack = new LinkedStack<>();
        int[] Li = new int[a.length],
                Ri = new int[a.length],
                Ai = new int[a.length];
        int i, t, width, height, maxArea = 0;

        /** Calculating Li **/
        for (i = 0; i < a.length; i++) {
            /** move left until we find smaller height **/
            while (!stack.isEmpty()) {
                if (a[i] <= a[stack.top()])
                    stack.pop();
                else {
                    break;
                }
            }

            if (stack.isEmpty())
                t = -1;
            else
                t = stack.top();

            Li[i] = i - t - 1;
            stack.push(i);

        }

        /** Clean stack **/
        while (!stack.isEmpty())
            stack.pop();

        /** Calculating Ri **/
        for (i = a.length - 1; i >= 0; i--) {
            /** move right until we find smaller height **/
            while (!stack.isEmpty()) {
                if (a[i] <= a[stack.top()])
                    stack.pop();
                else
                    break;
            }

            if (stack.isEmpty())
                t = a.length;
            else
                t = stack.top();

            Ri[i] = t - i - 1;
            stack.push(i);
        }

        /** Calculating Area A[i] **/
        for (i = 0; i < a.length; i++) {
            //System.out.println("L " + Li[i] + ", R " + Ri[i] + ", H " + a[i]);
            width = Li[i] + Ri[i] + 1;
            height = a[i];
            Ai[i] = height * width;

            if (maxArea < Ai[i])
                maxArea = Ai[i];
        }

        return maxArea;
    }

    /**
     * @param a
     * @return
     */
    public int calculateArea3(int[] a) {
        Stack<Integer> heightStack = new LinkedStack<>(),
                startIndexStack = new LinkedStack<>();

        int height, width, i,
                maxArea = 0, area,
                startPosition = 0;

        for (i = 0; i < a.length; i++) {
            if (heightStack.isEmpty() || a[i] > heightStack.top()) {
                startIndexStack.push(i);
                heightStack.push(a[i]);
            } else if (a[i] < heightStack.top()) {
                while (!heightStack.isEmpty() && a[i] < heightStack.top()) {
                    height = heightStack.pop();
                    startPosition = startIndexStack.pop();
                    width = (i - startPosition);
                    area = height * width;
                    if (maxArea < area)
                        maxArea = area;
                }

                heightStack.push(a[i]);
                startIndexStack.push(startPosition);
            }
            /**
             * else if a[i] == heightStack.top() then
             * nothing to do because this height and their
             * starting position index already in
             * respective stack.
             */
        }

        while (!heightStack.isEmpty()) {
            height = heightStack.pop();
            startPosition = startIndexStack.pop();
            width = (i - startPosition);
            area = height * width;
            if (maxArea < area)
                maxArea = area;
        }

        return maxArea;
    }
}


class LargestRectangleHistogramTest {

    public static void main(String[] args) {
        int[] histogram1 = {1, 2, 3, 1, 4, 2, 3, 0, 2},
                histogram2 = {1, 2, 3, 1, 4, 3, 3, 0, 2},
                histogram3 = {1, 2, 3, 1, 4, 3, 3, 2, 2};

        LargestRectangleHistogram calculateArea = new LargestRectangleHistogram();

        System.out.println("largest rectangle area1 (non-linear): " + calculateArea.calculateAreaInNonLinearTime(histogram1));
        System.out.println("largest rectangle area2 (linear): " + calculateArea.calculateAreaInLinearTime(histogram1));
        System.out.println("largest rectangle area3 (linear): " + calculateArea.calculateArea3(histogram1));


        System.out.println("\nlargest rectangle area1 (non-linear): " + calculateArea.calculateAreaInNonLinearTime(histogram2));
        System.out.println("largest rectangle area2 (linear): " + calculateArea.calculateAreaInLinearTime(histogram2));
        System.out.println("largest rectangle area3 (linear): " + calculateArea.calculateArea3(histogram2));

        System.out.println("\nlargest rectangle area1 (non-linear): " + calculateArea.calculateAreaInNonLinearTime(histogram3));
        System.out.println("largest rectangle area2 (linear): " + calculateArea.calculateAreaInLinearTime(histogram3));
        System.out.println("largest rectangle area3 (linear): " + calculateArea.calculateArea3(histogram3));

    }
}
