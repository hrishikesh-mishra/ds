package com.hrishikesh.practices.bitmanipulation;

/**
 * Problem:
 * Add two numbers without using add operator (i.e. Plus Sign)
 * ;
 * Add two number without using + operator.
 * ;
 * ;
 * Algorithm:
 * - Iterate till second operand is not zero (0)
 * - - compute sum_without_carry by xor operator
 * - - compute carry by and operator and one left shift
 * - - replace operand1 with sum_without_carry and operand2 with carry
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/add-two-numbers-without-using-add-operator-plus-sign/
 */
public class AddWithoutAddOperator {

    public static int addWithoutPlus(int x, int y) {

        while (y != 0) {
            int sumWithoutCarry = x ^ y;
            int carry = (x & y) << 1;
            x = sumWithoutCarry;
            y = carry;
        }

        return x;
    }
}

class AddWithAddOperatorTest {
    public static void main(String[] args) {

        System.out.println("3 + 4 = " + AddWithoutAddOperator.addWithoutPlus(3, 4));
        System.out.println("0 + 4 = " + AddWithoutAddOperator.addWithoutPlus(0, 4));
        System.out.println("2 + 4 = " + AddWithoutAddOperator.addWithoutPlus(2, 4));
        System.out.println("3 + 7 = " + AddWithoutAddOperator.addWithoutPlus(3, 7));
        System.out.println("(-3) + (-7) = " + AddWithoutAddOperator.addWithoutPlus(-3, -7));
        System.out.println("3 + (-7) = " + AddWithoutAddOperator.addWithoutPlus(3, -7));
        System.out.println("(-3) + 7 = " + AddWithoutAddOperator.addWithoutPlus(-3, 7));
    }
}

