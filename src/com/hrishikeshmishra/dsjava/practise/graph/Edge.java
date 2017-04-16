package com.hrishikeshmishra.dsjava.practise.graph;

/**
 * Created by hrishikesh.mishra
 */
public class Edge implements Comparable<Edge>{

    public static double DEFAULT_WEIGHT = 1.0;

    private Vertex startVertex, endVertex;
    private double weight;

    public Edge(Vertex startVertex, Vertex endVertex) {
        this(startVertex, endVertex, DEFAULT_WEIGHT);
    }

    public Edge(Vertex startVertex, Vertex endVertex, double weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return "\n(" +
                "startVertex=" + startVertex +
                ", endVertex=" + endVertex +
                ", weight=" + weight +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (Double.compare(edge.weight, weight) != 0) return false;
        if (!endVertex.equals(edge.endVertex)) return false;
        if (!startVertex.equals(edge.startVertex)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = startVertex.hashCode();
        result = 31 * result + endVertex.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
