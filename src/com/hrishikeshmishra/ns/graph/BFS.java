package com.hrishikeshmishra.ns.graph;

import com.hrishikeshmishra.ns.queue.LinkedQueue;
import com.hrishikeshmishra.ns.queue.Queue;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Breadth first search implementation
 * ;
 * Applications:
 * - Finding all connected components in a graph
 * - Finding  all nodes within one connected component
 * - Finding the shotest path between two nodes
 * - Testing a graph for bipartiteness
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/breadth-first-search-traversal-bfs/
 */
public class BFS {

    private AdjacencyMatrixGraph graph;
    boolean[] visitedVertex;

    public BFS(int totalVertices) {
        this.graph = new AdjacencyMatrixGraph(totalVertices);
        this.visitedVertex = new boolean[totalVertices];
    }

    public BFS(AdjacencyMatrixGraph graph) {
        this.graph = graph;
        this.visitedVertex = new boolean[this.graph.numberOfVertices()];
    }

    public void doBFS(int startVertex) {
        if (!graph.isValidVertex(startVertex))
            throw new NoSuchElementException("Vertex not found .");

        clearVisitedVertex();

        Queue<Integer> queue = new LinkedQueue<>();
        queue.enQueue(startVertex);

        markVisited(startVertex);

        System.out.print("\nBFS non-recursive : " + startVertex + " ");

        while (!queue.isEmpty()) {
            List<Integer> adjacentVertices = graph.getAdjacentVertices(queue.deQueue());
            for (Integer neighbourVertex : adjacentVertices) {
                if (!isVisitedNode(neighbourVertex)) {
                    markVisited(neighbourVertex);
                    System.out.print(neighbourVertex + " ");
                    queue.enQueue(neighbourVertex);
                }
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

class BFSTest {

    public static void main(String[] args) {
        AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(4);
        adjacencyMatrixGraph.addEdge(0, 1);
        adjacencyMatrixGraph.addEdge(0, 3);
        adjacencyMatrixGraph.addEdge(1, 2);
        adjacencyMatrixGraph.addEdge(2, 3);
        adjacencyMatrixGraph.addEdge(2, 0);

        BFS bfs = new BFS(adjacencyMatrixGraph);
        bfs.doBFS(0);

    }

}