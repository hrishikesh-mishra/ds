package com.hrishikeshmishra.ns.graph;

import java.util.*;

/**
 * <p>
 *     Cut vertex (Articulation point) Finder
 *
 *     {@link "hrishikeshmishra.com/cut-vertex-articulation-point-finder/"}
 * </p>
 * Created by hrishikesh.mishra
 */
public class CutVertexFinder {

    private int num;
    public List<Integer> find(Graph graph) {
        num = 1;
        Map<Integer, Integer> lowpoint = new HashMap<>();
        Map<Integer, Integer> dfsNum = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        List<Integer> visitedVertices = new ArrayList<>();
        List<Integer> cutVertices = new ArrayList<>();

        parent.put(0, null);
        find(0, lowpoint, dfsNum, parent , graph, visitedVertices, cutVertices);
        return cutVertices;
    }

    private void find(int vertex, Map<Integer, Integer> lowpoint, Map<Integer, Integer> dfsNum, Map<Integer, Integer> parent, Graph graph, List<Integer> visitedVertices, List<Integer> cutVertices) {
        /** Mark vertex as visited **/
        visitedVertices.add(vertex);

        /**
         * dfs number
         * Number of vertices visited from root.
         */
        dfsNum.put(vertex, num);

        /**
         * The minimum of the dfsnum of all neighbors of v
         * (other than the parent of v in the dfs tree) and the low
         * of all children of the v in the DFS tree.
         */
        lowpoint.put(vertex, num);

        /** Increment vertex number **/
        num++;

        boolean isCutVertex = false;
        int childrenCount = 0 ;
        for(Integer adjacentVertex : graph.getAdjacentVertices(vertex)){

            /**
             *  if adjacent vertex is parent of current vertex
             *  In adjacency matrix is possible.
            **/
            if (adjacentVertex.equals(parent.get(vertex))) continue;

            /** if adjacent vertex is not visited earlier **/
            if(!visitedVertices.contains(adjacentVertex)){
                childrenCount++;
                parent.put(adjacentVertex, vertex);
                find(adjacentVertex, lowpoint, dfsNum, parent , graph, visitedVertices, cutVertices);


                if(dfsNum.get(vertex) <= lowpoint.get(adjacentVertex))
                    isCutVertex = true;
                else
                    lowpoint.put(vertex, Math.min(lowpoint.get(vertex), lowpoint.get(adjacentVertex)));
            } else {
                lowpoint.put(vertex, Math.min(lowpoint.get(vertex), dfsNum.get(adjacentVertex)));
            }
        }

        if((Objects.isNull(parent.get(vertex)) && childrenCount >= 2)
                || (!Objects.isNull(parent.get(vertex)) && isCutVertex))
            cutVertices.add(vertex);
    }


    public static class Graph {

        private boolean [] [] matrix;
        private int totalVertices;

        public Graph(int totalVertices) {
            this.totalVertices = totalVertices;
            this.matrix = new boolean[totalVertices] [totalVertices];
        }


        public void addEdge(int sourceVertex, int destinationVertex){
            if(!isValidVertex(sourceVertex) || !isValidVertex(destinationVertex))
                throw new NoSuchElementException("No such vertex. ");
            this.matrix[sourceVertex] [destinationVertex] = true;
            this.matrix[destinationVertex] [sourceVertex] = true;
        }

        public List<Integer> getAdjacentVertices(int vertex){
            List<Integer> adjacentVertices = new ArrayList<>();
            for(int i =0 ; i< matrix[vertex].length ; i++)
                if(matrix[vertex][i]) adjacentVertices.add(i);
            return adjacentVertices;
        }

        public boolean isValidVertex(int vertex){
            return (vertex >= 0 && vertex < totalVertices);
        }

        public void display(){
            for(int i =0 ; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++)
                    System.out.print((matrix[i][j]? " T ":" - ")+ " ");
                System.out.println("");
            }
        }

        public int getTotalVertices() {
            return totalVertices;
        }

    }

}

class  CutVertexFinderTest {
    public static void main(String[] args) {
        CutVertexFinder.Graph graph = new CutVertexFinder.Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 3); graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4); graph.addEdge(3, 5); graph.addEdge(3, 6);
        graph.addEdge(5, 6);

        CutVertexFinder cutVertexFinder =  new CutVertexFinder();
        System.out.println("Cut Vertices: " + cutVertexFinder.find(graph));
    }
}