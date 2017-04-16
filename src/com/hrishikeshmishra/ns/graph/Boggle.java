package com.hrishikeshmishra.ns.graph;

/**
 * D - Problem
 * Find all possible words in a board of characters
 *
 * @author hrishikesh.mishra
 * @link "http://hrishikeshmishra.com/find-all-possible-words-in-a-board-of-characters/"
 */
public class Boggle {

    private void print(char[][] charMap, int rowCount, int colCount, boolean[][] visited, int i, int j, String str) {

        visited[i][j] = true;
        str = str + charMap[i][j];

        System.out.println(str);

        for (int row = i - 1; row <= i + 1 && row < rowCount; row++) {
            for (int col = j - 1; col <= i + 1 && col < colCount; col++) {
                if (row >= 0 && col >= 0 && !visited[row][col])
                    print(charMap, rowCount, colCount, visited, row, col, str);
            }
        }

        visited[i][j] = false;
        str = str.substring(0, str.length() - 1);
    }

    public void print(char[][] charMap) {

        boolean[][] visited = new boolean[charMap.length][charMap[0].length];
        for (int i = 0; i < charMap.length; i++) {
            for (int j = 0; j < charMap[i].length; j++) {
                print(charMap, charMap.length, charMap[i].length, visited, i, j, "");
            }
        }
    }

}

class BoggleTest {
    public static void main(String[] args) {
        char[][] charMap = {
                {'G', 'I'},
                {'K', 'J'},
        };

        Boggle boggle = new Boggle();
        boggle.print(charMap);
    }

}