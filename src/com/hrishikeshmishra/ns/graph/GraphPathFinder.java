package com.hrishikeshmishra.ns.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Problem:
 * Count paths in undirected graph
 * Count paths for a a given graph G has simple path from
 * source s to destination d. Assume that graph is represented
 * using adjacent matrix.
 * ;
 * ;
 * Solution:
 * - Use DFS
 * - When we explored all neighbours of a unvisited vertex then we set
 * visited of that vertex to false so, that we ca n use for next iteration
 * ;
 * ;
 * Algorithm:
 * - Set count = 0
 * - Create boolean visited matrix for each vertex of graph G
 * - Call Count_Path function
 * - In count_path function
 * - - Set visited of source_vertex to true
 * - - If source_vertex == destination_vertex then
 * - - - Increment vertext counter
 * - - - Set visited of destination_vertex to false
 * - - Explore all adjacent_vertices of sources_vertex
 * - - If adjacent_vertex is not visited then
 * - - - Call count_path recursively with adjacent_vertex as sources vertex
 * - - - After exploring all neighbours of adjacent_vertex set visited of this vertex to false
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/count-paths-between-two-vertices-in-undirected-graph/
 */
public class GraphPathFinder {

    private int count;

    public int countPaths(Graph graph, int sourceVertex, int destinationVertex) {
        count = 0;
        boolean[] visitedVertices = new boolean[graph.getTotalVertices()];
        countPaths(graph, sourceVertex, destinationVertex, visitedVertices);
        return count;
    }


    public void countPaths(Graph graph, int sourceVertex, int destinationVertex, boolean[] visitedVertices) {
        visitedVertices[sourceVertex] = true;

        if (sourceVertex == destinationVertex) {
            count++;
            visitedVertices[sourceVertex] = false;
            return;
        }

        for (Integer adjacentVertex : graph.getAdjacentVertices(sourceVertex)) {
            if (!visitedVertices[adjacentVertex]) {
                countPaths(graph, adjacentVertex, destinationVertex, visitedVertices);
                visitedVertices[adjacentVertex] = false;
            }
        }
    }

    public static class Graph {

        private boolean[][] matrix;
        private int totalVertices;

        public Graph(int totalVertices) {
            this.totalVertices = totalVertices;
            this.matrix = new boolean[totalVertices][totalVertices];
        }


        public void addEdge(int sourceVertex, int destinationVertex) {
            if (!isValidVertex(sourceVertex) || !isValidVertex(destinationVertex))
                throw new NoSuchElementException("No such vertex. ");
            this.matrix[sourceVertex][destinationVertex] = true;
            this.matrix[destinationVertex][sourceVertex] = true;
        }

        public List<Integer> getAdjacentVertices(int vertex) {
            List<Integer> adjacentVertices = new ArrayList<>();
            for (int i = 0; i < matrix[vertex].length; i++)
                if (matrix[vertex][i]) adjacentVertices.add(i);
            return adjacentVertices;
        }

        public boolean isValidVertex(int vertex) {
            return (vertex >= 0 && vertex < totalVertices);
        }

        public void display() {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++)
                    System.out.print((matrix[i][j] ? " T " : " - ") + " ");
                System.out.println("");
            }
        }


        public int getTotalVertices() {
            return totalVertices;
        }

    }

}


class GraphPathFinderTest {

    public static void main(String[] args) {
        GraphPathFinder.Graph graph = new GraphPathFinder.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);

        graph.display();

        GraphPathFinder finder = new GraphPathFinder();
        System.out.println("Path Count between 0 to 5 are: " + finder.countPaths(graph, 0, 4));


    }
}