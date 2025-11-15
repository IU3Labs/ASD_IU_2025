// В ориентированном взвешенном графе с отрицательными и
// положительными весами ребер найти кратчайший путь между
// двумя вершинами (Bellman--Ford algorithm).

import java.util.*;

public class BellmanFord {
    private Map<String, List<Edge>> graph;
    private List<String> nodes;
    private String startVertex;

    public static void main(String[] args) {
        BellmanFord bellmanFord = new BellmanFord();

        bellmanFord.addEdge("A", "B", 8);
        bellmanFord.addEdge("B", "C", -2);
        bellmanFord.addEdge("A", "C", 10);
        bellmanFord.addEdge("C", "D", 1);
        bellmanFord.addEdge("B", "E", 6);
        bellmanFord.addEdge("C", "B", 3);
        bellmanFord.addEdge("A", "D", 14);
        bellmanFord.addEdge("C", "E", 15);
        bellmanFord.addEdge("E", "D", -5);

        boolean success = bellmanFord.findShortestPaths("A");

    }

    public BellmanFord() {
        this.graph = new HashMap<>();
        this.nodes = new ArrayList<>();
    }

    public void addNode(String node) {
        if (!graph.containsKey(node)) {
            graph.put(node, new ArrayList<>());
            nodes.add(node);
        }
    }

    public void addEdge(String start, String end, int weight) {
        addNode(start);
        addNode(end);
        graph.get(start).add(new Edge(start, end, weight));
    }

    public boolean findShortestPaths(String start) {
        this.startVertex = start;

        if (!graph.containsKey(start)) {
            System.out.println("Стартовая вершина не найдена в графе!");
            return false;
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();

        for (String node : nodes) {
            distances.put(node, Integer.MAX_VALUE);
            predecessors.put(node, null);
        }
        distances.put(start, 0);

        for (int i = 0; i < nodes.size() - 1; i++) {
            boolean updated = false;

            for (String node : nodes) {
                if (distances.get(node) == Integer.MAX_VALUE) {
                    continue;
                }

                for (Edge edge : graph.get(node)) {
                    int newDistance = distances.get(edge.getStart()) + edge.getWeight();

                    if (newDistance < distances.get(edge.getEnd())) {
                        distances.put(edge.getEnd(), newDistance);
                        predecessors.put(edge.getEnd(), edge.getStart());
                        updated = true;
                    }
                }
            }

            if (!updated) {
                break;
            }
        }

        for (String node : nodes) {
            for (Edge edge : graph.get(node)) {
                if (distances.get(edge.getStart()) != Integer.MAX_VALUE &&
                        distances.get(edge.getStart()) + edge.getWeight() < distances.get(edge.getEnd())) {
                    System.out.println("Обнаружен отрицательный цикл!");
                    return false;
                }
            }
        }

        printResults(distances, predecessors);
        return true;
    }

    private void printResults(Map<String, Integer> distances, Map<String, String> predecessors) {
        System.out.println("Кратчайшие пути от вершины '" + startVertex + "':");
        System.out.println("Вершина\tРасстояние\tПредшественник");
        System.out.println("----------------------------------------");

        for (String node : nodes) {
            int dist = distances.get(node);
            String pred = predecessors.get(node);
            String distanceStr = (dist == Integer.MAX_VALUE) ? "∞" : String.valueOf(dist);
            String predStr = (pred == null) ? "-" : pred;

            System.out.println(node + "\t\t\t" + distanceStr + "\t\t\t" + predStr);
        }
    }
}