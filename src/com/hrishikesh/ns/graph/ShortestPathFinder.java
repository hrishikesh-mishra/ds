package com.hrishikesh.ns.graph;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 *     Shortest Path Finder for unweighted graph exactly same
 *     BFS the only difference is, instead of using boolean visitedMap
 *     we use distanceMap[v] = -1 for unvisited vertex.
 *
 * </p>
 * Created by hrishikesh.mishra
 */
public class ShortestPathFinder {

    private AdjacencyMatrixGraph graph;
    private DistanceMap distanceMap;



    public ShortestPathFinder(int totalVertices) {
        this.graph = new AdjacencyMatrixGraph(totalVertices);
        this.distanceMap = new DistanceMap();
    }

    public ShortestPathFinder(AdjacencyMatrixGraph graph) {
        this.graph = graph;
        this.distanceMap = new DistanceMap();
    }

    public Map<Integer, Integer> find(int startVertex){
        Queue<Integer> queue = new LinkedQueue<>();
        distanceMap.initializeDistanceMap(graph);

        queue.enQueue(startVertex);
        distanceMap.updateDistance(startVertex, 0);

        while (!queue.isEmpty()){
            Integer vertex = queue.deQueue();
            for(Integer adjacentVertex : graph.getAdjacentVertices(vertex)){
                if(!distanceMap.isDistanceExist(adjacentVertex)){
                    distanceMap.updateDistance(adjacentVertex, distanceMap.getDistance(vertex) + 1);
                    queue.enQueue(adjacentVertex);
                }
            }
        }

        return distanceMap.getMap();
    }

    /**
     * Distance Map
     */
    private static class DistanceMap extends HashMap<Integer, Integer> {
        private static final int DEFAULT_DISTANCE = -1;

        public void initializeDistanceMap(AdjacencyMatrixGraph graph){
            for(int i =0; i< graph.numberOfVertices(); i++)
                this.put(i, DEFAULT_DISTANCE);
        }

        public void updateDistance(Integer vertex, int distance){
            this.put(vertex, distance);
        }

        public void addDistance(Integer vertex, int distance){
            this.put(vertex, this.get(vertex) + distance);
        }

        public boolean isDistanceExist(Integer vertex){
            return (this.get(vertex) != DEFAULT_DISTANCE);
        }

        public Map<Integer, Integer> getMap(){
            return Collections.unmodifiableMap(this);
        }

        public int getDistance(Integer vertex){
            return this.get(vertex);
        }
    }
}

class ShortestPathFinderTest {
    public static void main(String[] args) {
        AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(7);
        adjacencyMatrixGraph.addEdge(0, 1); adjacencyMatrixGraph.addEdge(0, 2);
        adjacencyMatrixGraph.addEdge(1, 3); adjacencyMatrixGraph.addEdge(1, 4);
        adjacencyMatrixGraph.addEdge(3, 2); adjacencyMatrixGraph.addEdge(3, 5);
        adjacencyMatrixGraph.addEdge(4, 3); adjacencyMatrixGraph.addEdge(4, 6);
        adjacencyMatrixGraph.addEdge(5, 2);
        adjacencyMatrixGraph.addEdge(6, 5);

        adjacencyMatrixGraph.display();
        ShortestPathFinder shortestPathFinder = new ShortestPathFinder(adjacencyMatrixGraph);

        Map<Integer, Integer> distanceMap = shortestPathFinder.find(0);
        for(int i=0; i< distanceMap.size(); i++)
            System.out.println("Distance between V(0) and V(" + i+") is " + distanceMap.get(i));

    }
}
