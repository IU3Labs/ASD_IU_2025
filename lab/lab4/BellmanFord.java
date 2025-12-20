import java.util.*;

/**
 * Задача 1 (Группа A): Алгоритм Беллмана-Форда
 * Поиск кратчайшего пути в ориентированном взвешенном графе
 * с отрицательными и положительными весами ребер.
 */
public class BellmanFord {
    static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        List<Edge> edges;

        Graph(int vertices) {
            this.vertices = vertices;
            edges = new ArrayList<>();
        }

        void addEdge(int u, int v, int weight) {
            edges.add(new Edge(u, v, weight));
        }

        int[] bellmanFord(int source) {
            int[] dist = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;

            for (int i = 0; i < vertices - 1; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.u] != Integer.MAX_VALUE &&
                            dist[edge.u] + edge.weight < dist[edge.v]) {
                        dist[edge.v] = dist[edge.u] + edge.weight;
                    }
                }
            }

            for (Edge edge : edges) {
                if (dist[edge.u] != Integer.MAX_VALUE &&
                        dist[edge.u] + edge.weight < dist[edge.v]) {
                    System.out.println("Граф содержит отрицательный цикл!");
                    return null;
                }
            }

            return dist;
        }

        List<Integer> reconstructPath(int source, int target) {
            int[] dist = new int[vertices];
            int[] parent = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            dist[source] = 0;

            for (int i = 0; i < vertices - 1; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.u] != Integer.MAX_VALUE &&
                            dist[edge.u] + edge.weight < dist[edge.v]) {
                        dist[edge.v] = dist[edge.u] + edge.weight;
                        parent[edge.v] = edge.u;
                    }
                }
            }

            List<Integer> path = new ArrayList<>();
            if (dist[target] == Integer.MAX_VALUE) {
                System.out.println("Вершина недостижима");
                return path;
            }

            int current = target;
            while (current != -1) {
                path.add(0, current);
                current = parent[current];
            }

            return path;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        int source = 0;
        int[] distances = graph.bellmanFord(source);

        if (distances != null) {
            System.out.println("Кратчайшие расстояния от вершины " + source + ":");
            for (int i = 0; i < distances.length; i++) {
                System.out.println("До вершины " + i + ": " + distances[i]);
            }

            System.out.println("\nКратчайший путь от 0 до 3:");
            List<Integer> path = graph.reconstructPath(source, 3);
            System.out.println(path);
        }
    }
}