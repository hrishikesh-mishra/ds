package com.hrishikesh.ns.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Problem:
 * Adjacency Matrix Graph implementation
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/adjacency-matrix-graph-implementation/
 */
public class AdjacencyMatrixGraph {

    private boolean[][] matrix;
    private int totalVertices;

    public AdjacencyMatrixGraph(int totalVertices) {
        this.totalVertices = totalVertices;
        this.matrix = new boolean[totalVertices][totalVertices];
    }

    public void addEdge(int sourceVertex, int destinationVertex) {
        if (!isValidVertex(sourceVertex) || !isValidVertex(destinationVertex))
            throw new NoSuchElementException("No such vertex. ");
        this.matrix[sourceVertex][destinationVertex] = true;
    }

    public void removeEdge(int sourceVertex, int destinationVertex) {
        if (!isValidVertex(sourceVertex) || !isValidVertex(destinationVertex))
            throw new NoSuchElementException("No such vertex. ");
        this.matrix[sourceVertex][destinationVertex] = false;
    }

    public void display() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print((matrix[i][j] ? 1 : 0) + " ");
            System.out.println("");
        }
    }

    public boolean isValidVertex(int vertex) {
        return (vertex >= 0 && vertex < totalVertices);
    }

    public int numberOfVertices() {
        return totalVertices;
    }

    public List<Integer> getAdjacentVertices(int vertex) {
        if (!isValidVertex(vertex))
            throw new NoSuchElementException("Vertex not found  (" + vertex + ")");

        List<Integer> adjacentVertices = new ArrayList<>();
        for (int i = 0; i < matrix[vertex].length; i++)
            if (i != vertex && matrix[vertex][i])
                adjacentVertices.add(i);

        return adjacentVertices;
    }
}

class AdjacencyMatrixGraphTest {
    public static void main(String[] args) {

        AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(4);
        matrixGraph.addEdge(0, 1);
        matrixGraph.addEdge(0, 3);
        matrixGraph.addEdge(1, 2);
        matrixGraph.addEdge(2, 3);
        matrixGraph.addEdge(2, 0);


        matrixGraph.display();
    }
}