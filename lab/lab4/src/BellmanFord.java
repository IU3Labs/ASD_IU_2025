/*
В ориентированном взвешенном графе с отрицательными и
положительными весами ребер найти кратчайший путь между
двумя вершинами (Bellman--Ford algorithm).
 */

import structures.Edge;

import java.util.Scanner;

public class BellmanFord {

    public static void findShortestPaths(int verticesCount, Edge[] edges, int startVertex) {
        int[] distance = new int[verticesCount];

        for (int i = 0; i < verticesCount; i++) {
            distance[i] = Integer.MAX_VALUE;
        };

        distance[startVertex] = 0;

        for (int i = 0; i < verticesCount - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[edge.source] + edge.weight;
                };
            };
        };

        for (Edge edge : edges) {
            if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                return;
            };
        };

        printShortestPaths(distance, startVertex);
    };

    private static void printShortestPaths(int[] distance, int startVertex) {
        System.out.printf("Кратчайшие пути от вершины %d:\n", startVertex);
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.printf("До вершины %d нет пути\n", i);
            } else {
                System.out.printf("До вершины %d - %d\n", i, distance[i]);
            };
        };
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Кол-во вершин: ");
        int verticesCount = in.nextInt();
        System.out.print("Кол-во рёбер: ");
        int edgesCount = in.nextInt();

        Edge[] edges = new Edge[edgesCount];
        for (int i = 0; i < edgesCount; i++) {
            System.out.printf("Ребро №%d: ", i + 1);
            int source = in.nextInt();
            int destination = in.nextInt();
            int weight = in.nextInt();
            edges[i] = new Edge(source, destination, weight);
        };

        System.out.print("Введите начальную вершину: ");
        int startVertex = in.nextInt();
        if (startVertex < 0 || startVertex >= verticesCount) {
            System.out.print("Вершина вне допустимого диапазона");
        } else {
            findShortestPaths(verticesCount, edges, startVertex);
        };
    };

};
