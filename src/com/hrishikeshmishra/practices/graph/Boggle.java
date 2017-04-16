package com.hrishikeshmishra.practices.graph;

import static com.hrishikeshmishra.practices.graph.Boggle.findWords;

/**
 * Problem:
 * Boggle
 * Find all possible words in a board of characters
 * Given a dictionary which is form of M x N board where every cell has one character.
 * Find all possible words that can be formed by a sequence of adjacent charactersNote that we can move to any
 * of 8 adjacent characters, but a word should not have multiple instances of same cell.
 * ;
 * ;
 * Algorithm:
 * - Create visited matrix
 * - Iterate row from 0 to M - 1
 * - - Iterate col from 0 to N - 1
 * - - - If each cel call modified DFS
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-possible-words-board-characters-2/
 */
public class Boggle {

    public static void findWords(char[][] boggle, String[] dictionary) {

        boolean[][] visited = new boolean[boggle.length][boggle[0].length];

        for (int i = 0; i < boggle.length; i++) {
            for (int j = 0; j < boggle[i].length; j++) {
                findWords(boggle, dictionary, i, j, visited, "");
            }
        }

    }

    public static void findWords(char[][] boggle, String[] dictionary, int r, int c,
                                 boolean[][] visited, String word) {

        visited[r][c] = true;
        word = word + boggle[r][c];

        if (isWordExistsInDictionary(word, dictionary)) {
            System.out.println(word);
        }

        for (int row = r - 1; row <= r + 1 && row < boggle.length; row++) {
            for (int col = c - 1; col <= col + 1 && col < boggle[0].length; col++) {
                if (row >= 0 && col >= 0 && !visited[row][col]) {
                    findWords(boggle, dictionary, row, col, visited, word);
                }
            }
        }

        visited[r][c] = false;

    }


    private static boolean isWordExistsInDictionary(String word, String[] dictionary) {
        for (String w : dictionary) {
            if (word.equals(w)) {
                return true;
            }
        }

        return false;
    }

}


class BoggleTest {

    public static void main(String[] args) {
        char[][] boggle = {
                {'A', 'B', 'C'},
                {'M', 'N', 'O'},
                {'X', 'Y', 'Z'}
        };
        String dictionary[] = {"AMXY", "CON", "AZ"};

        findWords(boggle, dictionary);
    }


}