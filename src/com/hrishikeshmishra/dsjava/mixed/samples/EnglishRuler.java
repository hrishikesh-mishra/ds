package com.hrishikeshmishra.dsjava.mixed.samples;

/**
 * English Ruler base Recursion
 * <p>
 * ---- 0
 * -
 * --
 * -
 * ---
 * -
 * --
 * -
 * ---- 1
 * -
 * --
 * -
 * ---
 * -
 * --
 * -
 * ---- 1
 * ;
 * Algorithm:
 * In general, an interval with a central tick length L >=1 is composed of:
 * - An interval with a central tick length L - 1
 * - A single tick with Length L
 * - An interval with a central tick length L - 1
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/english-ruler-base-recursion/
 */
public class EnglishRuler {

    public static void main(String[] args) {
        drawRuler(2, 4);
    }

    public static void drawRuler(int nInches, int majorLength) {

        drawLine(majorLength, 0);
        for (int j = 1; j <= nInches; j++) {
            drawInterval(majorLength - 1);
            drawLine(majorLength, j);
        }
    }

    private static void drawInterval(int centralLength) {
        if (centralLength >= 1) {
            drawInterval(centralLength - 1);
            drawLine(centralLength);
            drawInterval(centralLength - 1);
        }
    }

    private static void drawLine(int tickLength, int tickLabel) {
        for (int j = 0; j < tickLength; j++)
            System.out.print("-");

        if (tickLabel >= 0)
            System.out.print(" " + tickLabel);

        System.out.print("\n");
    }

    private static void drawLine(int tickLength) {
        drawLine(tickLength, -1);
    }


}
