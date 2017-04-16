package com.hrishikeshmishra.ns.graph;

import com.hrishikeshmishra.ns.stack.LinkedStack;
import com.hrishikeshmishra.ns.stack.Stack;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Problem:
 * Depth first search
 * ;
 * Application:
 * - Topological sorting
 * - Finding connected components
 * - Solving puzzles such as mazes
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/graph-depth-first-search-traversal-dfs/
 */
public class DFS {

    private AdjacencyMatrixGraph graph;
    boolean[] visitedVertex;

    public DFS(int totalVertices) {
        this.graph = new AdjacencyMatrixGraph(totalVertices);
        this.visitedVertex = new boolean[totalVertices];
    }

    public DFS(AdjacencyMatrixGraph graph) {
        this.graph = graph;
        this.visitedVertex = new boolean[this.graph.numberOfVertices()];
    }

    public void dfsNonRecursive(int startVertex) {
        if (!graph.isValidVertex(startVertex))
            throw new NoSuchElementException("Vertex not found .");

        clearVisitedVertex();

        Stack<Integer> stack = new LinkedStack<>();
        this.markVisited(startVertex);
        stack.push(startVertex);

        System.out.print("\nDFS non-recursive : ");

        while (!stack.isEmpty()) {

            int vertex = stack.top();
            List<Integer> adjacentVertices = getUnVisitedAdjacentVertices(vertex);

            if (adjacentVertices.isEmpty()) {
                System.out.print(stack.pop() + " ");
            } else {
                for (Integer adjacentVertex : adjacentVertices) {
                    if (!isVisitedNode(adjacentVertex)) {
                        markVisited(adjacentVertex);
                        stack.push(adjacentVertex);
                    }
                }
            }
        }

        return;
    }

    public void dfsRecursive(int startVertex) {
        if (!graph.isValidVertex(startVertex))
            throw new NoSuchElementException("Vertex not found .");

        clearVisitedVertex();
        System.out.print("\nDFS recursion : ");
        doDFSRecursive(startVertex);
        markVisited(startVertex);
        System.out.print(startVertex + " ");

    }

    private void doDFSRecursive(int vertex) {

        /** Mark vertices as visited **/
        markVisited(vertex);

        for (Integer adjacentVertex : graph.getAdjacentVertices(vertex)) {
            if (!isVisitedNode(adjacentVertex)) {
                System.out.print(adjacentVertex + " ");
                doDFSRecursive(adjacentVertex);
            }
        }
    }

    private void clearVisitedVertex() {
        this.visitedVertex = new boolean[this.graph.numberOfVertices()];
    }

    private void markVisited(int vertex) {
        this.visitedVertex[vertex] = true;
    }

    private boolean isVisitedNode(int vertex) {
        return this.visitedVertex[vertex];
    }

    private List<Integer> getUnVisitedAdjacentVertices(int vertex) {
        List<Integer> adjacentVertices = graph.getAdjacentVertices(vertex);
        for (Integer adjacentVertex : graph.getAdjacentVertices(vertex))
            if (isVisitedNode(adjacentVertex)) adjacentVertices.remove(adjacentVertex);

        return adjacentVertices;
    }
}


class DFSTest {

    public static void main(String[] args) {
        AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(4);
        adjacencyMatrixGraph.addEdge(0, 1);
        adjacencyMatrixGraph.addEdge(0, 3);
        adjacencyMatrixGraph.addEdge(1, 2);
        adjacencyMatrixGraph.addEdge(2, 3);
        adjacencyMatrixGraph.addEdge(2, 0);

        DFS dfs = new DFS(adjacencyMatrixGraph);
        dfs.dfsNonRecursive(0);
        dfs.dfsRecursive(0);

    }
}