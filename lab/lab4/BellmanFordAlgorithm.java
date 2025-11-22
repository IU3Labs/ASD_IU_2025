package lab4;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;

/**
 * 1. В ориентированном взвешенном графе с отрицательными и
 * положительными весами ребер найти кратчайший путь между
 * двумя вершинами (Bellman--Ford algorithm).
 **/

public class BellmanFordAlgorithm {
    public static void findShortestPath(Graph graph, int source, int destination) {
        ArrayList<ArrayList<Graph.Edge>> edgesList = graph.getEdgeList();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] predecessors = new int[graph.getVertices()];
        int[] paths = new int[graph.getVertices()];
        boolean[] inQueue = new boolean[graph.getVertices()];
        inQueue[source] = true;
        Arrays.fill(paths, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        paths[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            int vertex = queue.pop();
            inQueue[vertex] = false;
            for (int i = 0; i < edgesList.get(vertex).size(); i++) {
                int target = edgesList.get(vertex).get(i).destination;
                int weight = edgesList.get(vertex).get(i).weight;
                if (paths[vertex] + weight < paths[target]) {
                    paths[target] = paths[vertex] + weight;
                    predecessors[target] = vertex;
                    if (!inQueue[target]) {
                        queue.add(target);
                        inQueue[target] = true;
                    }
                }
            }
        }
        ArrayList<Integer> pathToDestination = new ArrayList<>();
        for (int i = destination; i != -1; i = predecessors[i]) {
            pathToDestination.add(i);
        }
        System.out.print("Путь: ");
        Collections.reverse(pathToDestination);
        for (int i = 0; i < pathToDestination.size() - 1; i++) {
            System.out.print(pathToDestination.get(i) + " -> ");
        }
        System.out.println(pathToDestination.getLast());
        System.out.println("Расстояние: " + paths[destination]);
    }
}
