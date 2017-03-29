package com.hrishikesh.ns.graph;

import com.hrishikesh.ns.stack.LinkedStack;
import com.hrishikesh.ns.stack.Stack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem:
 * Given a dictionary of strings [ strings are in sorted order] you have to find the precedence of characters according to the dictionary..
 * eat
 * bxy
 * e is ranked above b according to the dictionary.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/alien-language-parser/
 */
public class AlienLanguageParser {

    public List<Character> parse(String[] strings) {
        Digraph<Character> digraph = new Digraph<>();
        for (int i = 0; i < strings.length - 1; i++)
            extractAlphabets(strings[i], strings[i + 1], digraph);

        TopologicalSort topologicalSort = new TopologicalSort();
        return topologicalSort.sort(digraph);
    }

    private void extractAlphabets(String str1, String str2, Digraph<Character> digraph) {
        int i = 0,
                length1 = str1.length(),
                length2 = str2.length();

        while (i < length1 && i < length2) {
            /** Characters are same in both string **/
            if (str1.charAt(i) == str2.charAt(i)) {
                i++;
                continue;
            }
            /** Add edge to graph is characters are different **/
            addEdgeToGraph(str1.charAt(i), str2.charAt(i), digraph);
            i++;
            break;

        }
    }

    private void addEdgeToGraph(char fromVertex, char toVertex, Digraph<Character> digraph) {
        addVertexIfNotExist(fromVertex, digraph);
        addVertexIfNotExist(toVertex, digraph);
        digraph.addEdge(fromVertex, toVertex);
    }

    private void addVertexIfNotExist(char vertex, Digraph<Character> digraph) {
        if (!digraph.isVertexExists(vertex)) digraph.addVertex(vertex);
    }

    private static class TopologicalSort {

        public <V> List<V> sort(Digraph<V> digraph) {
            List<V> topologicalOrder = new ArrayList<>();
            Stack<V> stack = new LinkedStack<>();

            getZeroInDegreeVertices(digraph).forEach(stack::push);
            VertexInDegreeCounter vertexInDegreeCounter = new VertexInDegreeCounter(digraph);

            if (stack.isEmpty())
                throw new RuntimeException("Graph has loop.");

            while (!stack.isEmpty()) {
                V vertex = stack.pop();
                topologicalOrder.add(vertex);
                for (V outgoingVertex : digraph.getOutboundNeighbors(vertex)) {
                    vertexInDegreeCounter.decreaseInDegreeCountByOne(outgoingVertex);
                    if (!vertexInDegreeCounter.hasMoreInDegree(outgoingVertex))
                        stack.push(outgoingVertex);

                }
            }

            return topologicalOrder;
        }

        private <V> Set<V> getZeroInDegreeVertices(Digraph<V> digraph) {
            return digraph.getVertices().
                    stream().
                    filter(vertex -> digraph.getInboundNeighbors(vertex).size() == 0).
                    collect(Collectors.toCollection(() -> new LinkedHashSet<>()));
        }

        private static class VertexInDegreeCounter<V> {
            private final Map<V, Integer> vertexInDegreeCountMap;

            private VertexInDegreeCounter(Digraph<V> digraph) {
                vertexInDegreeCountMap = getVertexInDegreeCountMap(digraph);
            }

            public void decreaseInDegreeCountByOne(V vertex) {
                vertexInDegreeCountMap.put(vertex, vertexInDegreeCountMap.get(vertex) - 1);
            }

            public boolean hasMoreInDegree(V vertex) {
                return (vertexInDegreeCountMap.get(vertex) > 0);
            }

            private <V> Map<V, Integer> getVertexInDegreeCountMap(Digraph<V> digraph) {
                Map<V, Integer> vertexInDegreeCountMap = new HashMap<>();
                for (V vertex : digraph.getVertices())
                    vertexInDegreeCountMap.put(vertex, digraph.inDegree(vertex));
                return vertexInDegreeCountMap;
            }

        }
    }
}

class AlienLanguageParserTest {

    public static void main(String[] args) {
        String[] word1 = {"aardvark", "ant", "bee", "cat", "cow", "dog", "horse", "llama", "sheep", "zebra"};
        String[] word2 = {"abc", "acd", "bcc", "bed", "bdc", "dab"};

        AlienLanguageParser languageParser = new AlienLanguageParser();
        System.out.println(languageParser.parse(word1));
        System.out.println(languageParser.parse(word2));
    }
}
