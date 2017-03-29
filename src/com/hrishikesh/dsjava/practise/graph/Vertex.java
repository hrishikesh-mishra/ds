package com.hrishikesh.dsjava.practise.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrishikesh.mishra
 */
public class Vertex {

    private List<Edge> neighborhood;
    private String label;

    public Vertex(String label) {
        this.label = label;
        this.neighborhood = new ArrayList<>();
    }

    public List<Edge> getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(List<Edge> neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addNeighbor(Edge e){
        if(containNeighbor(e))
            return;
        neighborhood.add(e);
    }

    public boolean containNeighbor(Edge e){
        return neighborhood.contains(e);
    }

    public void removeNeighbor(Edge e){
        neighborhood.remove(e);
    }

    public int getNeighborCount(){
        return neighborhood.size();
    }

    public List<Edge> getNeighbors(){
        return neighborhood;
    }

    @Override
    public String toString() {
        return "Vertex(" +
                '\'' + label + '\'' +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        if (!label.equals(vertex.label)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return  label.hashCode();
    }
}
