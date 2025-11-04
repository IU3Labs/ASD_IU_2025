import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*1. В ориентированном взвешенном графе с отрицательными и
положительными весами ребер найти кратчайший путь между
двумя вершинами (Bellman--Ford algorithm). */

public class BellmanFordAlgorithm {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "B", -1);
        graph.addEdge("B", "D", 4);
        graph.addEdge("D", "C", -2);
        graph.addEdge("D", "E", -10);

        System.out.println(findShortestPathBetweenTwoVertices(graph, "A", "E"));
    }

    public static <T> ArrayList<T> findShortestPathBetweenTwoVertices(Graph<T> graph, T from, T to) {
        HashMap<T, Integer> distances = new HashMap<>();
        HashMap<T, T> predecessors = new HashMap<>();
        HashMap<T, Boolean> inQueue = new HashMap<>();
        ArrayDeque<T> queue = new ArrayDeque<>();

        for (T vertex : graph.getVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
            inQueue.put(vertex, false);
        }

        distances.put(from, 0);
        predecessors.put(from, from);
        queue.add(from);
        inQueue.put(from, true);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            inQueue.put(current, false);

            for (Graph.Edge edge : graph.getEdgesFrom(current)) {
                T neighbor = (T) edge.to();
                int newDistance = distances.get(current) + edge.weight();

                if (newDistance < distances.get(neighbor)) {

                    distances.put(neighbor, newDistance);
                    predecessors.put(neighbor, current);

                    if (!inQueue.get(neighbor)) {
                        queue.add(neighbor);
                        inQueue.put(neighbor, true);
                    }
                }
            }
        }

        return reconstructPath(predecessors, from, to);
    }

    private static <T> ArrayList<T> reconstructPath(HashMap<T, T> predecessors, T from, T to) {
        ArrayList<T> path = new ArrayList<>();

        if (!predecessors.containsKey(to)) {
            return path;
        }

        T current = to;
        while (!current.equals(from)) {
            path.add(current);
            current = predecessors.get(current);
        }
        path.add(from);

        Collections.reverse(path);
        return path;
    }
}
