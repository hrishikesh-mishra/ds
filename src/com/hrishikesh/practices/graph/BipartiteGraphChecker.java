package com.hrishikesh.practices.graph;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import static com.hrishikesh.practices.graph.BipartiteGraphChecker.Graph;
import static com.hrishikesh.practices.graph.BipartiteGraphChecker.isGraphBipartite;

/**
 * Problem:
 * Check whether a given graph is Bipartite or not
 * A Bipartite Graph is a graph whose vertices can be divided into two independent sets,
 * U and V such that every edge (u, v) either connects a vertex from U to V or a vertex from V to U.
 * In other words, for every edge (u, v), either u belongs to U and v to V, or u belongs to V and v to U.
 * We can also say that there is no edge that connects vertices of same set.
 * ;
 * Solution :
 * - It can be easily solve by vertex color algorithm where color is 2
 * - We alternatively color a vertex, it means
 * - - If vertex color is red, its neighbours color would be yellow and neighbours's neighbour color would be red again.
 * - - During painting if we found that color of two adjacent vertices is same the graph is not Bipartite.
 * Note:
 * - It is possible to color a cycle graph with even cycle using two colors.
 * - It is not possible to color a cycle graph with odd cycle using two colors.
 * - We will use BFS for traversing a graph
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=za_BGCGJzSs
 * @link http://hrishikeshmishra.com/check-whether-given-graph-bipartite-not/
 */
public class BipartiteGraphChecker {

    public static class Graph {
        private int numberOfVertices;
        private List<Integer> adjacencyLists[];

        public Graph(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            init(numberOfVertices);
        }

        private void init(int numberOfVertices) {
            adjacencyLists = (List<Integer>[]) Array.newInstance(List.class, numberOfVertices);

            for (int i = 0; i < numberOfVertices; i++) {
                adjacencyLists[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination) {
            adjacencyLists[source].add(destination);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfVertices; i++) {
                sb.append(i + " -> " + adjacencyLists[i] + "\n");
            }
            return sb.toString();
        }
    }

    private enum Color {
        RED,
        YELLOW;

        public static Color getAlternateColor(Color color) {
            return color == RED ? YELLOW : RED;
        }
    }

    public static boolean isGraphBipartite(Graph graph) {
        /** Vertices Color Array **/
        Color[] verticesColor = new Color[graph.numberOfVertices];

        /** Queue to traverse BFS **/
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        /** Start vertex color **/
        verticesColor[0] = Color.RED;

        /** Process till queue is empty **/
        while (!queue.isEmpty()) {
            /** Get fetch first from queue **/
            Integer currentVertex = queue.remove();

            /** Iterate all adjacent vertex of current vertex **/
            for (Integer adjacentVertex : graph.adjacencyLists[currentVertex]) {

                /** If vertex was not colored means it means it discovered first time **/
                if (Objects.isNull(verticesColor[currentVertex])) {
                    /** Color alternate color to source vertex **/
                    verticesColor[currentVertex] = Color.getAlternateColor(verticesColor[currentVertex]);
                    /** add to queue to explore their neighbours **/
                    queue.add(adjacentVertex);
                } else if (verticesColor[currentVertex] == verticesColor[adjacentVertex]) {
                    /** If vertex was explored earlier and color is same **/
                    return false;
                }
            }
        }
        return true;
    }

}


class BipartiteGraphCheckerTest {

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        graph.addEdge(3, 2);
        System.out.println(graph);

        System.out.println("Is Graph Bipartite: " + isGraphBipartite(graph));
    }
}