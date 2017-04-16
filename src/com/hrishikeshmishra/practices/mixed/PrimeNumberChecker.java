package com.hrishikeshmishra.practices.mixed;

/**
 * Problem:
 * Prime Number Checker
 * Given a number N, you have to print “Prime” if its a prime number or “Not Prime” if its not
 *
 * @author hrishikesh.mishra
 */
public class PrimeNumberChecker {

    public static boolean isPrime(int n) {

        if (n < 2) {
            return false;
        } else if (n == 2) {
            return true;
        }

        int m = (int) Math.sqrt(n) + 1;

        for (int i = 2; i <= m; i++) {

            if (n % i == 0) {
                return false;
            }
        }

        return true;

    }
}


class PrimeNumberCheckerTest {
    public static void main(String[] args) {
        System.out.printf("%d Prime? %b\n", 2, PrimeNumberChecker.isPrime(2));
        System.out.printf("%d Prime? %b\n", 3, PrimeNumberChecker.isPrime(3));
        System.out.printf("%d Prime? %b\n", 4, PrimeNumberChecker.isPrime(4));
        System.out.printf("%d Prime? %b\n", 7, PrimeNumberChecker.isPrime(7));
        System.out.printf("%d Prime? %b\n", 19, PrimeNumberChecker.isPrime(19));
        System.out.printf("%d Prime? %b\n", 21, PrimeNumberChecker.isPrime(21));
    }
}