/*Группа А, задание 1: В ориентированном взвешенном графе с отрицательными и
положительными весами ребер найти кратчайший путь между
двумя вершинами (Bellman--Ford algorithm).*/

import java.util.*;

public class BellmanFordAlgorithm {

    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void findShortestPath(List<Edge> edges, int vertices, int source, int destination) {
        int[] distance = new int[vertices];
        int[] predecessor = new int[vertices];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(predecessor, -1);
        distance[source] = 0;

        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (distance[edge.source] != Integer.MAX_VALUE &&
                        distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[edge.source] + edge.weight;
                    predecessor[edge.destination] = edge.source;
                }
            }
        }

        for (Edge edge : edges) {
            if (distance[edge.source] != Integer.MAX_VALUE &&
                    distance[edge.source] + edge.weight < distance[edge.destination]) {
                System.out.println("Граф содержит отрицательный цикл!");
                return;
            }
        }

        printShortestPath(distance, predecessor, source, destination);
    }

    private static void printShortestPath(int[] distance, int[] predecessor, int source, int destination) {
        if (distance[destination] == Integer.MAX_VALUE) {
            System.out.println("Путь из " + source + " в " + destination + " не существует");
            return;
        }

        System.out.println("Кратчайшее расстояние из " + source + " в " + destination + ": " + distance[destination]);

        List<Integer> path = new ArrayList<>();
        for (int v = destination; v != -1; v = predecessor[v]) {
            path.add(v);
        }
        Collections.reverse(path);

        System.out.println("Путь: " + path);
    }

    public static void testBellmanFord() {

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 5));
        edges.add(new Edge(1, 2, -2));
        edges.add(new Edge(1, 3, 6));
        edges.add(new Edge(2, 3, 1));
        edges.add(new Edge(2, 1, 3));

        int vertices = 4;
        int source = 0;
        int destination = 3;

        findShortestPath(edges, vertices, source, destination);
    }

    public static void main(String[] args) {
        testBellmanFord();
    }
}