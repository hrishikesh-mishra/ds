package com.hrishikesh.practices.graph;


import java.util.LinkedList;
import java.util.Queue;

import static com.hrishikesh.practices.graph.SnakeLadder.getNumberOfMove;

/**
 * Problem:
 * Snake and Ladder Problem
 * Given a snake and ladder board, find the minimum number of dice throws required to reach
 * the destination. And suppose you have control over outcome of dice throw.
 * ;
 * If you gets ladder you will climb up, but in case of snake you will be moved down.
 * ;
 * Algorithm:
 * - Create a visited boolean array to hold visited vertices info
 * - Create an Class name Entry to hold following attributes' value:
 * - - vertex
 * - - moves
 * - this entry will be enqueued and dequeued from queue.
 * - Create a queue which will hold above above
 * - Create an object of Entry with value vertex=0 and moves=0
 * - Iterate till queue is not empty
 * - - entry = queue.dequeue
 * - - If entry.vertex == goalVertex then
 * - - - return entry.moves
 * - - Iterate from entry.vertex +1 to entry.vertex+ 6
 * - - - if vertex is not visited then
 * - - - - Create and new entry with  vertex=vertex and moves=entry.moves+1
 * - - - - queue.enqueue(entry)
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=19tY6Y90TP0
 * @link http://hrishikeshmishra.com/snake-ladder-problem/
 */
public class SnakeLadder {

    public static class Entry {
        private Integer vertex;
        private int moves;

        public Entry(Integer vertex, int moves) {
            this.vertex = vertex;
            this.moves = moves;
        }
    }

    public static int getNumberOfMove(int[] moves, int goal) {
        boolean[] visited = new boolean[goal];
        Queue<Entry> queue = new LinkedList<>();

        Entry queueEntry = new Entry(0, 0);
        queue.add(queueEntry);

        while (!queue.isEmpty()) {
            queueEntry = queue.remove();

            if (queueEntry.vertex == goal - 1) {
                return queueEntry.moves;
            }

            visited[queueEntry.vertex] = true;

            /** Compute all six possibilities from current vertex + 1 to current vertex + 6 **/
            for (int nextVertex = queueEntry.vertex + 1;
                 nextVertex < queueEntry.vertex + 6 && nextVertex < goal; nextVertex++) {

                if (!visited[nextVertex]) {

                    /** Move is either snake of ladder **/
                    if (isSnakeOrLadderMove(moves, nextVertex)) {
                        queue.add(new Entry(moves[nextVertex], queueEntry.moves + 1));
                    } else {
                        /** Normal moves **/
                        queue.add(new Entry(nextVertex, queueEntry.moves + 1));

                    }
                }
            }

        }

        /** It should not never reach here **/
        return Integer.MAX_VALUE;
    }

    private static boolean isSnakeOrLadderMove(int[] moves, int nextVertex) {
        return moves[nextVertex] != -1;
    }
}

class SnakeLadderTest {
    public static void main(String[] args) {

        int boardSize = 30;
        int[] moves = getMoves(boardSize);

        /**  Ladders moves **/
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        /** Snakes moves **/
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println("Number move required : " + getNumberOfMove(moves, boardSize));

    }

    private static int[] getMoves(int boardSize) {
        int[] moves = new int[boardSize];

        /** It required to fill with other number from board numbers **/
        /** Suppose we haven't filled with as it is, so default value would be zero  **/
        /** But in that you will not able to identify whether its actual zero or not filled **/
        /** Because there could a ladder from zero or snake can down could be zero **/
        for (int i = 0; i < boardSize; i++) {
            moves[i] = -1;
        }
        return moves;
    }

}
