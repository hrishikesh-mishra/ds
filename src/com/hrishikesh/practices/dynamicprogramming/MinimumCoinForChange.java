package com.hrishikesh.practices.dynamicprogramming;


import java.util.HashMap;
import java.util.Map;

import static com.hrishikesh.practices.dynamicprogramming.MinimumCoinForChange.minimumChange;

/**
 * Problem:
 * Minimum number of coin
 * Given an infinite number of different denomination, calculate minimum coins required to
 * representing n amount.
 * ;
 * ;
 * Solution:
 * - It is based on one simple fact
 * - Suppose we have following denomination coins
 * - - c0 < c1 < c2 < c3 ........ <cN-1
 * - - Min Coin required for money M (i.e C(m) )
 * - - Cm = min of {C(m-c0) + C(m-c1) + C(m-c2)+ ....... + C(m-cn-1) } + 1
 * ;
 * ;
 * ;
 * Algorithm:
 * - If money == 0 then
 * - - return 0 because 0 coins required for this.
 * - Set min = INFINITY
 * - Iterate all coins one by one
 * - - If coin value is greater than money then
 * - - - don't any thing for this iteration
 * - - recursively call (money - coin[i]) money
 * - - update min based on recursive call response
 * - If min is not INFINITY then
 * - - min = min + 1
 * - return min
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=Y0ZqKpToTic
 * @video https://www.youtube.com/watch?v=GafjS0FfAC0
 * @link http://hrishikeshmishra.com/minimum-number-coin/
 */
public class MinimumCoinForChange {

    public static int minimumChange(int[] coin, int money) {
        return minimumChangeWithMemo(coin, money, new HashMap<>());
    }

    private static int minimumChangeWithMemo(int[] coin, int money, Map<Integer, Integer> memo) {

        /** Base case : When no money then no way to change it **/
        if (money == 0) {
            return 0;
        }

        /** If already computed **/
        if (memo.containsKey(money)) {
            return memo.get(money);
        }

        int min = Integer.MAX_VALUE;

        /** Iterate all coins **/
        for (int i = 0; i < coin.length; i++) {

            /**
             * If coin is higher denomination than money
             * for example coin = 5 and money = 3
             */
            if (money < coin[i]) {
                continue;
            }

            /** Recursively call to know min coin required for money money - coin[i] **/
            int tempMin = minimumChange(coin, money - coin[i]);

            if (min > tempMin) {
                min = tempMin;
            }
        }

        /** Add 1 to min because new coin added here **/
        min = (min == Integer.MAX_VALUE ? min : min + 1);

        /** Return min **/
        return min;
    }
}


class MinimumCoinForChangeTest {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println("Min Coin required of 1 :" + minimumChange(coins, 1));
        System.out.println("Min Coin required of 5 :" + minimumChange(coins, 5));
        System.out.println("Min Coin required of 7 :" + minimumChange(coins, 7));
        System.out.println("Min Coin required of 17 :" + minimumChange(coins, 17));
    }
}