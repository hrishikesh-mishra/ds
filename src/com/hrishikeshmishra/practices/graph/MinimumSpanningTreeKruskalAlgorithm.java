package com.hrishikeshmishra.practices.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.hrishikeshmishra.practices.graph.MinimumSpanningTreeKruskalAlgorithm.*;

/**
 * Problem:
 * Minimum Spanning Tree Using Kruskal's algorithm
 * Given a weighted, undirected and connected graph. Find the sum of weights of the edges of the Minimum Spanning Tree
 * ;
 * ;
 * Spanning Tree - A tree that spans all the vertices of a connected and undirected graph .
 * Spanning Tree  Properties
 * - for graph G(V, E), it contains exact (V-1) edges
 * - Remove (E-V+1) edges
 * - Maximally acyclic - Means after adding an edge will be cycle
 * - Minimally connected - means removable of an edge from spinning tree it will give more than one components
 * - maximum possible spinning tree is V ^ (V-2)
 * ;
 * Solution:
 * - In Kruskal's algorithm use disjoint set with find - union operation
 * - its greedy
 * -
 * ;
 * ;
 * ;
 * Algorithm:
 * - Take all edges
 * - Sort edges in ascending order
 * - Iterate all edges one by one
 * - - find disjoint set of source vertex of edge
 * - - find disjoint set of destination vertex of edge
 * - - if both sets are different then,
 * - - - add edge to output list
 * - - - union both sets
 * - return output list of edge
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=fAuF0EuZVCk
 * @vide https://www.youtube.com/watch?v=8fJgkVpxbQg
 * @link http://hrishikeshmishra.com/minimum-spanning-tree-using-kruskals-algorithm/
 */
public class MinimumSpanningTreeKruskalAlgorithm {


    public static class Edge implements Comparable<Edge> {
        private int x;
        private int y;
        private int w;

        public Edge(int x, int y, int w) {
            /** Some hack to for undirected graph **/
            this.x = Math.min(x, y);
            this.y = Math.max(x, y);
            this.w = w;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getW() {
            return w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }

        @Override
        public String toString() {
            return "(" + x + "<" + w + ">" + y + ')' + '\n';
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
    }


    private static class DisjointSet {
        private int[] parents;

        public DisjointSet(int size) {
            make(size);
        }

        private void make(int size) {
            parents = new int[size];

            for (int i = 0; i < size; i++) {
                parents[i] = -1;
            }
        }

        public int find(int element) {

            if (parents[element] == -1) {
                return element;
            }
            return find(parents[element]);
        }

        public void union(int x, int y) {
            int xSet = find(x);
            int ySet = find(y);

            if (xSet != ySet) {
                parents[ySet] = xSet;
            }
        }

    }


    public static List<Edge> getMSTUsingKruskalAlgorithm(int[][] graph) {
        List<Edge> edges = getEdges(graph);
        Collections.sort(edges);
        DisjointSet disjointSet = new DisjointSet(graph.length);
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : edges) {
            int xSet = disjointSet.find(edge.x);
            int ySet = disjointSet.find(edge.y);

            if (xSet != ySet) {
                disjointSet.union(edge.x, edge.y);
                mstEdges.add(edge);
            }
        }

        return mstEdges;
    }

    private static List<Edge> getEdges(int[][] graph) {
        List<Edge> edges = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {

                if (graph[i][j] != 0) {
                    Edge edge = new Edge(i, j, graph[i][j]);
                    if (!edges.contains(edge)) {
                        edges.add(edge);
                    }

                }
            }
        }
        return edges;
    }

}

class MinimumSpanningTreeKruskalAlgorithmTest {
    public static void main(String[] args) {
        int graph[][] = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        List<Edge> mstEdges = getMSTUsingKruskalAlgorithm(graph);

        System.out.println("Minimum Spanning Tree Edges: ");
        for (Edge edge : mstEdges) {
            System.out.println(edge.getX() + " - " + edge.getY() + " --> " + edge.getW());
        }
    }
}
