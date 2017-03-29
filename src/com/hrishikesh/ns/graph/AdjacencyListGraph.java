package com.hrishikesh.ns.graph;

import com.hrishikesh.ns.stack.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Problem:
 * Adjacency List Graph
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/adjacency-list-graph-implementation/
 */
public class AdjacencyListGraph<V> {
    private List<V> vertices;
    private ListNode<V>[] edges;
    private int totalVertices;

    public AdjacencyListGraph(List<V> vertices) {
        this.totalVertices = vertices.size();
        this.vertices = vertices;
        initEdge();
    }

    private void initEdge() {
        this.edges = (ListNode<V>[]) new ListNode[this.totalVertices];

        for (int i = 0; i < totalVertices; i++)
        /** Self node loop **/
            this.edges[i] = new ListNode<V>(this.vertices.get(i));

    }

    /**
     * <p>
     * Add edge between source Vertex and destination vertex
     * </p>
     *
     * @param source
     * @param destination
     */
    public void addEdge(V source, V destination) {
        int sourceIndex = vertices.indexOf(source),
                destinationIndex = vertices.indexOf(destination);

        if (isValidIndex(sourceIndex) || isValidIndex(destinationIndex))
            throw new NoSuchElementException("No such vertices. ");


        if (isExistingNeighbour(this.edges[sourceIndex], destination))
            throw new RuntimeException("edge already exists.");

        addNodeInEdge(sourceIndex, destination);
    }

    public void removeEdge(V source, V destination) {
        int sourceIndex = vertices.indexOf(source),
                destinationIndex = vertices.indexOf(source);

        if (isValidIndex(sourceIndex) || isValidIndex(destinationIndex))
            throw new NoSuchElementException("No such vertices. ");

        if (!isExistingNeighbour(this.edges[sourceIndex], destination))
            throw new RuntimeException("There is no edge between these nodes.");

        removeNodeInEdge(this.edges[sourceIndex], this.edges[destinationIndex]);
    }

    private boolean isValidIndex(int sourceIndex) {
        return sourceIndex == -1;
    }

    private void addNodeInEdge(int edgeIndex, V destinationVertex) {
        this.edges[edgeIndex].setNext(new ListNode<V>(destinationVertex, this.edges[edgeIndex].getNext()));
    }

    private void removeNodeInEdge(ListNode<V> vertex, ListNode<V> neighbour) {
        if (Objects.isNull(vertex) || Objects.isNull(vertex.getNext())) return;

        if (vertex.getNext().getData().equals(neighbour.getData()))
            vertex.setNext(neighbour.getNext());
        else
            removeNodeInEdge(vertex.getNext(), neighbour);

    }

    private boolean isExistingNeighbour(ListNode<V> vertex, V neighbour) {
        if (Objects.isNull(vertex)) return false;
        if (vertex.getData().equals(neighbour)) return true;
        return isExistingNeighbour(vertex.getNext(), neighbour);
    }

    public void display() {

        for (int i = 0; i < this.edges.length; i++) {
            ListNode<V> node = this.edges[i];
            System.out.print("\nNeighbours of :" + node.getData() + " : ");
            node = node.getNext();
            while (!Objects.isNull(node)) {
                System.out.print(node.getData() + ", ");
                node = node.getNext();
            }
        }
    }
}

class AdjacencyListGraphTest {

    public static void main(String[] args) {
        Integer v1 = new Integer(1),
                v2 = new Integer(2),
                v3 = new Integer(3),
                v4 = new Integer(4);

        List<Integer> vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);

        AdjacencyListGraph listGraph = new AdjacencyListGraph(vertices);

        listGraph.addEdge(v1, v2);
        listGraph.addEdge(v1, v4);
        listGraph.addEdge(v2, v3);
        listGraph.addEdge(v3, v1);
        listGraph.addEdge(v3, v4);

        listGraph.display();
    }
}