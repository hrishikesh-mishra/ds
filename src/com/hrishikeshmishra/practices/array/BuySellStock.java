package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.array.BuySellStock.findMaxProfit;

/**
 * Problem:
 * Best Time to Buy and Sell Stock
 * In Given array, the ith element is stock value of ith day. We can only one time buy or sale, find maximum profit.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/best-time-buy-sell-stock/
 */
public class BuySellStock {

    public static int findMaxProfit(int[] prices) {

        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }

        int maxProfit = 0;
        int n = prices.length;
        int[] min = new int[n];
        int[] max = new int[n];

        min[0] = prices[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i - 1], prices[i]);
        }

        max[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], prices[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, max[i] - min[i]);
        }


        return maxProfit;

    }
}

class BuySellStockTest {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Array: " + Arrays.toString(prices));
        System.out.println("Maximum profit : " +  findMaxProfit(prices));
    }
}