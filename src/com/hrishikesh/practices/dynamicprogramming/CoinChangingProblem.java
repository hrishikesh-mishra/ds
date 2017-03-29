package com.hrishikesh.practices.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

import static com.hrishikesh.practices.dynamicprogramming.CoinChangingProblem.getChangeCountRecursive;
import static com.hrishikesh.practices.dynamicprogramming.CoinChangingProblem.getChangeCountWithMemo;

/**
 * Problem:
 * Coin Changing Problem
 * Given an infinite number of 1, 2, 5 rupees coins, calculate the number of ways of
 * representing n rupees
 * ;
 * ;
 * ;
 * Solution :
 * - One simple ways try to make N money with M type of coins this ways
 * - - N money with M-1 types of coins +
 * - - N - Amount[M] with M types of coins
 * ;
 * ;
 * Recursive Algorithm:
 * - If money == 0 then
 * - - return 1 (i.e. only way to make money 0 with any kind of coins)
 * - If index >= total coins then
 * - - return 0
 * - Iterate till computedAmount <= money
 * - - recursively call to make computedAmount with M-1 coins
 * - - remaining amount with with N coins
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=tduLvFbqRXE
 * @video https://www.youtube.com/watch?v=_fgjrs570YE
 * @video https://www.youtube.com/watch?v=sn0DWI-JdNA
 * @link http://hrishikeshmishra.com/coin-changing-problem/
 */
public class CoinChangingProblem {


    public static int getChangeCountRecursive(int[] coins, int money) {
        return getChangeCount(coins, money, 0);
    }

    private static int getChangeCount(int[] coins, int money, int index) {
        if (money == 0) {
            return 1;
        }

        if (index >= coins.length) {
            return 0;
        }

        int ways = 0;
        int amountWithCount = 0;
        while (amountWithCount <= money) {
            int remaining = money - amountWithCount;
            ways += getChangeCount(coins, remaining, index + 1);
            amountWithCount += coins[index];
        }

        return ways;
    }

    public static int getChangeCountWithMemo(int[] coins, int money) {
        return getChangeCountWithMemo(coins, money, 0, new HashMap<>());
    }

    private static int getChangeCountWithMemo(int[] coins, int money, int index, Map<String, Integer> memo) {

        /** Base case : When no money then only one way to make it regardless of coin type **/
        if (money == 0) {
            return 1;
        }

        /** If no coin then zero way to make it .**/
        if (index >= coins.length) {
            return 0;
        }

        /** Key for memo **/
        String key = money + "-" + index;

        /** Checking if it already computed or not. **/
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int ways = 0;
        int amountWithCount = 0;

        /** Iterate till all money not convert **/
        while (amountWithCount <= money) {

            /** Remaining amount **/
            int remaining = money - amountWithCount;

            /** Make N money with M-1 coin  **/
            ways += getChangeCount(coins, remaining, index + 1);

            /** Now make N - coins[index] money with M coins  **/
            amountWithCount += coins[index];
        }

        /** Sorting result for further use **/
        memo.put(key, ways);

        return ways;
    }


}

class CoinChangingProblemTest {
    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        System.out.println("Total ways (recursive): " + getChangeCountRecursive(coins, 5));
        System.out.println("Total ways (with memo): " + getChangeCountWithMemo(coins, 5));
    }
}
