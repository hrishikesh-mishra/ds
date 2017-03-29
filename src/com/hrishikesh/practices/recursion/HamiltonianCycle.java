package com.hrishikesh.practices.recursion;

import java.util.StringJoiner;

import static com.hrishikesh.practices.recursion.HamiltonianCycle.checkCycle;

/**
 * Problem:
 * Hamiltonian Cycle
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/hamiltonian-cycle/
 */
public class HamiltonianCycle {

    private static final int DEFAULT_VALUE = -1;

    public static void checkCycle(int[][] graph) {
        int[] path = new int[getTotalVertices(graph)];
        initialize(path);
        path[0] = 0;

        boolean isPathFound = isPathFound(graph, path, 1);

        if (isPathFound) {
            printCycle(path, getTotalVertices(graph));
        } else {
            System.out.println("Cycle not found");
        }
    }

    private static void printCycle(int[] path, int totalNumberOfVertices) {

        StringJoiner joiner = new StringJoiner( " -> ", "Cycle: ", ".");
        for (int i = 0; i < totalNumberOfVertices; i++) {
            joiner.add(String.valueOf(path[i]));
        }

        System.out.println(joiner);

    }

    private static boolean isPathFound(int[][] graph, int[] path, int position) {


        if (position == getTotalVertices(graph)) {
            int source = path[position - 1];
            int destination = path[0];
            return isPathExist(source, destination, graph);
        }


        for (int currentVertex = 1; currentVertex < getTotalVertices(graph); currentVertex++) {

            if (isSafe(currentVertex, graph, path, position)) {
                path[position] = currentVertex;

                if (isPathFound(graph, path, position + 1)) {
                    return true;
                }

                path[position] = DEFAULT_VALUE;
            }
        }

        return false;
    }

    private static boolean isSafe(int currentVertex, int[][] graph, int[] path, int position) {
        int source = path[position - 1];

        return isPathExist(source, currentVertex, graph) &&
                !isAlreadyVisited(currentVertex, path, position);
    }

    private static boolean isPathExist(int source, int destination, int[][] graph) {
        return graph[source][destination] != 0;
    }

    private static boolean isAlreadyVisited(int currentVertex, int[] path, int position) {
        for (int i = 0; i < position; i++) {
            if (path[i] == currentVertex) {
                return true;
            }
        }
        return false;
    }

    private static void initialize(int[] path) {
        for (int i = 0; i < path.length; i++) {
            path[i] = DEFAULT_VALUE;
        }
    }

    private static int getTotalVertices(int[][] graph) {
        return graph.length;
    }
}

class HamiltonianCycleTest {
    public static void main(String[] args) {
        int graph1[][] = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };

        int graph2[][] = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };

        System.out.println("\nGraph1:\n");
        checkCycle(graph1);
        System.out.println("\nGraph2:\n");
        checkCycle(graph2);
    }


}
