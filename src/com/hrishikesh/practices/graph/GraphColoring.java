package com.hrishikesh.practices.graph;

import static com.hrishikesh.practices.graph.GraphColoring.doColor;

/**
 * Problem:
 * Graph Vertices Coloring
 * - Assign  a color to each vertex of the graph such that no two adjacent vertices have same color
 * - Minimum number of color used for proper coloring of a graph is known chromatic number of the graph
 * - The chromatic number of graph G denoted χ(G), is the smallest k such that G is K-colorable
 * - Facts
 * - - If a G has n vertices, χ(G) <= n
 * - -  χ(G)= 1, iff G has on edges
 * - - χ (even-cycle) = 2 and χ(odd-cycle) = 3
 * - - χ(complete G) = n
 * - - if H is a subgraph of then χ(G) >= χ(H)
 * - - For G(V,E) - A K-coloring of G partitions the vertex set V into K sets v1, v2 ....... Vk
 * where each vi is an independent set
 * - Application
 * - - Scheduling is one of its application
 * ;
 * ;
 * Solution:
 * - For small problem, backtracking approach works, however the graph  coloring problem is known as
 * NP hard problem.
 * - Backtracking takk O(m^n) where m => number of color and n=> number of vertices
 * ;
 * ;
 * ;
 * Algorithm:
 * - Iterate all color one by one
 * - - Check is it safe to assign current color for vertex v
 * - - If yes then
 * - - - assign color to vertex v
 * - - - Recursively call for next vertex
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=miCYGGrTwFU
 * @link http://hrishikeshmishra.com/graph-vertices-coloring/
 */
public class GraphColoring {

    public enum Color {
        RED,
        GREEN,
        YELLOW,

    }

    public static void doColor(int[][] graph) {

        /** Array to hold vertex color **/
        Color[] vertexColor = new Color[graph.length];

        doColor(graph, 0, vertexColor);

    }

    private static void doColor(int[][] graph, int currentVertex, Color[] vertexColor) {

        for (Color color : Color.values()) {

            if (isValidColorForVertex(graph, currentVertex, color, vertexColor)) {
                vertexColor[currentVertex] = color;

                /** Do coloring for next vertex if they exists **/
                int nextVertex = getNextVertex(currentVertex);

                if (isValidVertex(nextVertex, graph)) {
                    doColor(graph, nextVertex, vertexColor);
                } else {
                    /** Else print assinged colors **/
                    printColor(vertexColor, graph);
                }
            }
        }
    }

    public static void printColor(Color[] vertexColor, int[][] graph) {
        System.out.println("Vertex\tColor");
        for (int vertex = 0; vertex < graph.length; vertex++) {
            System.out.printf("%d\t\t%s\n", vertex, vertexColor[vertex]);
        }
    }

    private static int getNextVertex(int currentVertex) {
        return currentVertex + 1;
    }

    private static boolean isValidVertex(int vertex, int[][] graph) {
        return vertex >= 0 && vertex < graph.length;
    }

    private static boolean isValidColorForVertex(int[][] graph, int currentVertex,
                                                 Color color, Color[] vertexColor) {

        /** Check color is already assigned to adjacent vertex of not **/
        for (int vertex = 0; vertex < graph.length; vertex++) {
            if (isAdjacentVertex(graph, currentVertex, vertex) &&
                    color == vertexColor[vertex]) {
                return false;
            }
        }

        return true;
    }


    private static boolean isAdjacentVertex(int[][] graph, int currentVertex, int adjacentVertex) {
        return graph[currentVertex][adjacentVertex] == 1;
    }


}

class GraphColoringTest {

    public static void main(String[] args) {
        int[][] graph = {
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
        };

        doColor(graph);

    }
}