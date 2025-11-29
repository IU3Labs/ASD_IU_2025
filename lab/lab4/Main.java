/*
 В ориентированном взвешенном графе с отрицательными и
положительными весами ребер найти кратчайший путь между
двумя вершинами (Bellman--Ford algorithm).
 */

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите количество вершин: ");
        int N = scan.nextInt();

        System.out.print("Введите количество рёбер: ");
        int M = scan.nextInt();

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            System.out.println("Ребро №" + (i + 1));

            System.out.print("  Введите начальную вершину (from, от 0 до "
                    + (N - 1) + "): ");
            int from = scan.nextInt();

            System.out.print("  Введите конечную вершину (to, от 0 до "
                    + (N - 1) + "): ");
            int to = scan.nextInt();

            System.out.print("  Введите вес ребра (может быть отрицательным): ");
            int weight = scan.nextInt();

            edges.add(new Edge(from, to, weight));
        }

        System.out.print("Введите стартовую вершину: ");
        int start = scan.nextInt();

        System.out.print("Введите конечную вершину: ");
        int finish = scan.nextInt();

        int[] dist = new int[N];
        int[] parent = new int[N];

        int INF = Integer.MAX_VALUE / 2;

        for (int i = 0; i < N; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        dist[start] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edges) {
                int u = e.from;
                int v = e.to;
                int w = e.weight;

                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                }
            }
        }

        boolean hasNegativeCycle = false;

        for (Edge e : edges) {
            int u = e.from;
            int v = e.to;
            int w = e.weight;

            if (dist[u] != INF && dist[u] + w < dist[v]) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("В графе существует отрицательный цикл, достижимый из стартовой вершины.");
            System.out.println("Кратчайшие пути не определены (можно уменьшать расстояние бесконечно).");
        } else {
            if (dist[finish] == INF) {
                System.out.println("Пути из вершины "
                        + start + " в вершину "
                        + finish + " не существует.");
            } else {
                System.out.println("Кратчайшее расстояние от "
                        + start + " до "
                        + finish + " равно: "
                        + dist[finish]);

                int[] path = new int[N];
                int len = 0;
                int current = finish;

                while (current != -1) {
                    path[len] = current;
                    len += 1;
                    current = parent[current];
                }

                System.out.print("Путь: ");
                for (int i = len - 1; i >= 0; i--) {
                    System.out.print(path[i]);
                    if (i != 0) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }

        scan.close();
    }
}
