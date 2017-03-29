package com.hrishikesh.practices.array;

import static com.hrishikesh.practices.array.ShuffleCardOfDeck.shuffle;

/**
 * Problem:
 * Shuffle Card of Desk
 * ;
 * Shuffle card of desk, it must be perfect shuffle, mean each of 52! permutations of the deck has to equally likely.
 * ;
 * ;
 * Algorithm:
 * - Iterate 1 to 52
 * - - During each iterate get random number between 1 to current position
 * - - Swap card of current position with random position
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/shuffle-card-desk/
 */
public class ShuffleCardOfDeck {

    public static void shuffle(int[] card) {
        for (int i = 0; i < card.length; i++) {
            int rand = rand(0, i);
            swap(card, i, rand);
        }
    }

    private static void swap(int[] card, int i, int j) {
        int temp = card[i];
        card[i] = card[j];
        card[j] = temp;
    }

    private static int rand(int minimum, int maximum) {
        return minimum + Double.valueOf(Math.random() * (maximum - minimum)).intValue();
    }

}

class ShuffleCardOfDeckTest {
    public static void main(String[] args) {
        int[] card = getCardOfDeck();
        System.out.println("Before Shuffle: ");
        print(card);

        shuffle(card);
        System.out.println("\n\nAfter Shuffle: ");
        print(card);

    }

    public static int[] getCardOfDeck() {
        int[] card = new int[52];
        for (int i = 0; i < 52; i++) {
            card[i] = i + 1;
        }
        return card;
    }

    public static void print(int[] card) {
        for (int i = 0; i < card.length; i++) {
            System.out.print(card[i] + " ");
            if (i > 0 && i % 13 == 0) {
                System.out.println();
            }
        }
    }
}
