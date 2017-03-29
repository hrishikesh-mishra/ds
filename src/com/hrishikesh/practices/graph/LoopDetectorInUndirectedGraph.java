package com.hrishikesh.practices.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.hrishikesh.practices.graph.LoopDetectorInUndirectedGraph.*;

/**
 * Problem:
 * Detect cycle in an undirected graph
 * Given an undirected graph, how to check if there is a cycle in the graph
 * ;
 * ;
 * Solution:
 * - There are two ways to solve this
 * - Using DFS O(V+E) or
 * - Using Disjoint Set with Union-Find algorithm O(ElogV)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/detect-cycle-undirected-graph/
 */
public class LoopDetectorInUndirectedGraph {

    private static class Edge {
        private int x;
        private int y;

        public Edge(int x, int y) {
            /** Simple hack to make to edge equal (x -> y & y->x) **/
            this.x = Math.min(x, y);
            this.y = Math.max(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;

            Edge edge = (Edge) o;

            if (x != edge.x) return false;
            return y == edge.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "(" + x + " <-> " + y + ')';
        }
    }

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
            adjacencyLists[destination].add(source);
        }

        public List<Edge> getEdges() {
            List<Edge> edges = new ArrayList<>();
            for (int sourceVertex = 0; sourceVertex < numberOfVertices; sourceVertex++) {
                for (int destinationVertex : adjacencyLists[sourceVertex]) {
                    Edge edge = new Edge(sourceVertex, destinationVertex);

                    /** Hack to insert only one edge for (x -> y & y -> x ) **/
                    if (!edges.contains(edge)) {
                        edges.add(edge);
                    }
                }
            }
            return edges;
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

    private static class DisjointSet {
        private int[] parents;

        public DisjointSet(int size) {
            init(size);
        }

        private void init(int size) {
            this.parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = -1;
            }
        }

        public int find(int i) {

            if (parents[i] == -1) {
                return i;
            }
            return find(parents[i]);
        }

        private void union(int x, int y) {
            int xSet = find(x);
            int ySet = find(y);

            if (xSet != ySet) {
                parents[xSet] = ySet;
            }
        }
    }

    /**
     * Finding loop using disjoint set
     *
     * @param graph
     * @return
     */
    public static boolean findLoopUsingDisjointSet(Graph graph) {
        DisjointSet disjointSet = new DisjointSet(graph.numberOfVertices);
        List<Edge> edges = graph.getEdges();

        for (Edge edge : edges) {
            int xSet = disjointSet.find(edge.x);
            int ySet = disjointSet.find(edge.y);

            if (xSet == ySet) {
                return true;
            }

            disjointSet.union(edge.x, edge.y);
        }

        return false;

    }

    /**
     * Using DFS
     *
     * @param graph
     * @return
     */
    public static boolean findLoopUsingDFS(Graph graph) {
        boolean[] visited = new boolean[graph.numberOfVertices];

        for (int currentVertex = 0; currentVertex < graph.numberOfVertices; currentVertex++) {

            /** If node was not visited earlier **/
            if (!visited[currentVertex]) {
                int parentVertex = -1;
                boolean isLoopFound = findLoopUsingDFS(graph, visited, currentVertex, parentVertex);
                if (isLoopFound) {
                    return true;
                }
            }
        }

        return false;

    }

    private static boolean findLoopUsingDFS(Graph graph, boolean[] visited,
                                            int currentVertex, int parentVertex) {
        /** If vertex was earlier visited **/
        if (visited[currentVertex]) {
            return false;
        }

        /** Mark current vertex as visited **/
        visited[currentVertex] = true;

        /** Iterate all adjacent vertex **/
        for (Integer adjacentVertex : graph.adjacencyLists[currentVertex]) {

            /** If adjacent vertex is already visited and adjacent vertex is not parent of current vertex **/
            if (visited[adjacentVertex] && !adjacentVertex.equals(parentVertex)) {
                return true;
            }


            /** recursively call for adjacent vertex **/
            if (findLoopUsingDFS(graph, visited, adjacentVertex, currentVertex)) {
                return true;
            }
        }
        /** Else no loop found **/
        return false;
    }

}


class LoopDetectorInUndirectedGraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(3, 6);
       // graph.addEdge(6, 4);
        graph.addEdge(4, 5);
      //  graph.addEdge(5, 3);


        System.out.println(graph);
        System.out.println("Loop found (using DFS): " + findLoopUsingDFS(graph));
        System.out.println("Loop found (using Disjoint-Set ): " + findLoopUsingDisjointSet(graph));

    }
}