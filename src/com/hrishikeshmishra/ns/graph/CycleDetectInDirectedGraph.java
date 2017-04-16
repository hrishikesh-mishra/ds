package com.hrishikeshmishra.ns.graph;

import java.util.*;

/**
 * Problem:
 * Detect Cycle in a Directed Graph
 * ;
 * Solution:
 * Algorithm is based on 3 different buckets (whiteset, grayset and blackset)
 * ;
 * ;
 * Algorithm:
 * - Create three lists (buckets)
 * - - White Bucket - means vertices are not visited
 * - - Gray Bucket - means vertices are under process
 * - - Black Bucket - means vertices are processed
 * - Add all vertices to White Buckets
 * - Iterate till white bucket is not empty
 * - - Call Modified DFS
 * - In Modified DFS Function
 * - - Move vertex from white bucket to gray bucket
 * - - Iterate all adjacent_vertices of current vertex
 * - - - If adjacent_vertex is in back bucket then
 * - - - - don't do anything
 * - - - Else if adjacent_vertex is in gray bucket then
 * - - - - return true (means there is loop)
 * - - - Else
 * - - - - recursively call modified DFS for adjacent_vertex
 * - - Move vertex from gray bucket to black bucket
 * - - Return false
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/detect-cycle-in-a-directed-graph/
 */
public class CycleDetectInDirectedGraph {


    public boolean findCycle(Graph graph) {
        if (graph.isEmpty()) throw new RuntimeException("Graph is empty.");

        /** Unvisited vertices **/
        List<Integer> whiteSet = new ArrayList<>();
        whiteSet.addAll(graph.getVertices());

        /** Under-process vertices **/
        List<Integer> graySet = new ArrayList<>();

        /** Visited including adjacent vertices & children **/
        List<Integer> blackSet = new ArrayList<>();

        /** Call DSF of white set element **/
        while (!whiteSet.isEmpty())
        /** Call dfs to explore vertex and find loop **/
            if (dfs(whiteSet.get(0), whiteSet, graySet, blackSet, graph)) return true;

        return false;
    }

    private boolean dfs(Integer vertex, List<Integer> whiteSet, List<Integer> graySet, List<Integer> blackSet, Graph graph) {

        /** move vertex from white set to gray set, because, we are now processing this vertex**/
        moveVertex(vertex, whiteSet, graySet);

        /** explore adjacent vertices **/
        for (Integer adjacentVertex : graph.getAdjacentVertices(vertex)) {

            /** if already explored do nothing **/
            if (blackSet.contains(adjacentVertex)) continue;

            /** Found in gray set, mean it has loop **/
            if (graySet.contains(adjacentVertex)) return true;

            /** explore adjacent vertex to check loop in children node. **/
            if (dfs(adjacentVertex, whiteSet, graySet, blackSet, graph)) return true;
        }

        /** move vertex from gray set to black set, because we have explored all children and adjacent vertices of this vertex**/
        moveVertex(vertex, graySet, blackSet);
        return false;
    }

    private void moveVertex(Integer vertex, List<Integer> bucket1, List<Integer> bucket2) {
        bucket1.remove(vertex);
        if (!bucket2.contains(vertex)) bucket2.add(vertex);
    }

    public static class Graph {
        private int totalVertices;
        private List<Integer>[] adjacentVertices;

        public Graph(int totalVertices) {
            if (totalVertices <= 0) throw new RuntimeException("Number vertices must be positive value.");
            this.totalVertices = totalVertices;
            init();
        }

        private void init() {
            adjacentVertices = (List<Integer>[]) new List[totalVertices];
            for (int i = 0; i < totalVertices; i++) adjacentVertices[i] = new ArrayList<>();
        }

        public void addEdge(int from, int to) {
            if (!isValidVertex(from)) throw new NoSuchElementException("No such vertex " + from);
            if (!isValidVertex(to)) throw new NoSuchElementException("No such vertex " + to);
            if (adjacentVertices[from].contains(to))
                throw new RuntimeException("Edge already exists between " + from + ", " + to);
            adjacentVertices[from].add(to);
        }

        public boolean isValidVertex(int vertex) {
            return (vertex >= 0 && vertex < totalVertices);
        }

        public List<Integer> getVertices() {
            List<Integer> vertices = new ArrayList<>();
            for (int i = 0; i < totalVertices; i++) vertices.add(i);
            return vertices;
        }

        public List<Integer> getAdjacentVertices(int vertex) {
            if (!isValidVertex(vertex)) throw new NoSuchElementException("No such vertex " + vertex);
            return adjacentVertices[vertex];
        }

        public boolean isEmpty() {
            return totalVertices == 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Integer vertex : getVertices())
                sb.append("V(" + vertex + "), Adjacent vertices: " + getAdjacentVertices(vertex) + "\n");
            return sb.toString();
        }
    }

}

class CycleDetectInDirectedGraphTest {

    public static void main(String[] args) {
        CycleDetectInDirectedGraph.Graph graph = new CycleDetectInDirectedGraph.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println(graph);
        CycleDetectInDirectedGraph cycleDetector = new CycleDetectInDirectedGraph();
        System.out.println("Is loop found: " + cycleDetector.findCycle(graph));


    }

}