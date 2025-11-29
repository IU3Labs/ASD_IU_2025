package groupa;

import java.util.*;

public class BellmanFord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество вершин: ");
        int vertices = scanner.nextInt();

        System.out.print("Введите количество ребер: ");
        int edgeCount = scanner.nextInt();

        List<Edge> edges = new ArrayList<>();
        System.out.println("Введите ребра в формате: источник назначение вес");
        for (int i = 0; i < edgeCount; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            edges.add(new Edge(source, destination, weight));
        }

        System.out.print("Введите стартовую вершину: ");
        int source = scanner.nextInt();

        bellmanFord(edges, vertices, source);
        System.out.println("\nСложность: O(V*E) - V вершин, E ребер");

        scanner.close();
    }

    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int vertices, int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (distance[edge.source] != Integer.MAX_VALUE &&
                        distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[edge.source] + edge.weight;
                }
            }
        }

        for (Edge edge : edges) {
            if (distance[edge.source] != Integer.MAX_VALUE &&
                    distance[edge.source] + edge.weight < distance[edge.destination]) {
                System.out.println("Граф содержит цикл отрицательного веса!");
                return;
            }
        }

        System.out.println("Кратчайшие расстояния от вершины " + source + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Вершина " + i + ": " +
                    (distance[i] == Integer.MAX_VALUE ? "∞" : distance[i]));
        }
    }


}