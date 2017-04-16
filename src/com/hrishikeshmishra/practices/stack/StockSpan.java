package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * The stock span is a financial problem where we have a series of n daily price quotes for a stock and we need to
 * calculate span of stock’s price for all n days. The span Si of the stock’s price on a given day i is defined as
 * the maximum number of consecutive days just before the given day, for which the price of the stock on the current
 * day is less than or equal to its price on the given day.
 * ;
 * ;
 * For example,
 * if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then
 * the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 * ;
 * ;
 * Algorithm:
 * - We will use stack to store last highest price found till current index
 * - Now create stack and push 0 in it
 * - Create prices array S to hold Si and put S[0] = 1, because it will be always true
 * - Iterate given array from 1 to n (here n = array.length)
 * - - If prices[stack.top] <= prices[i] then
 * - - - stack.pop
 * - - Repeat till stack is not empty or above condition does not break.
 * - - Now at this point there could be two possible states of stack
 * - - - Case 1: When stack is empty that means, all previous prices was smaller than current prices
 * - - - Case 2: When stack is not empty that means, there is atleast one price which higher than current prices.
 * - - If stack is empty then
 * - - - S[i] = i + 1
 * - - Else
 * - - -  S[i] = i - stack.top
 * - - stack.push(i)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/the-stock-span-problem/
 */
public class StockSpan {

    public static int[] calculate(int[] stocks) {

        /** Base case: when array is empty **/
        if (stocks.length == 0) {
            return new int[]{0};
        }

        int[] result = new int[stocks.length];

        /** To hold highest index of stack **/
        Stack<Integer> stack = new Stack<>();

        /** Initial value for index 0, because span for 0th index is always 1 **/
        stack.push(0);
        result[0] = 1;

        /** Iterate all stock from index 1 onward **/
        for (int i = 1; i < stocks.length; i++) {

            /** Remove all index from stack whose stock value is lesser than current stock **/
            while (!stack.isEmpty() && stocks[stack.peek()] <= stocks[i]) {
                stack.pop();
            }

            /** Case 1. When all stock value is smaller than current stock **/
            if (stack.isEmpty()) {
                result[i] = i + 1;
            } else {
                /** Case 2. When there is at-least one previous stock who is higher than current stock **/
                result[i] = (i - stack.peek());
            }

            /** Push current index to stack for further comparision **/
            stack.push(i);
        }
        return result;
    }
}

class StockSpanTest {
    public static void main(String[] args) {
        int[] stocks = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = StockSpan.calculate(stocks);

        for (int i = 0; i < stocks.length; i++) {
            System.out.println(stocks[i] + " - " + spans[i]);
        }
    }
}

