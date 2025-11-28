import java.util.*;


class Graph {
    private int vertices; // Количество вершин
    private List<Edge> edges; // Список всех ребер

    static class Edge {
        int source;      // Начальная вершина
        int destination; // Конечная вершина
        int weight;      // Вес ребра

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public void bellmanFord(int startVertex, int endVertex) {
        // Шаг 1: Инициализация расстояний
        int[] distances = new int[vertices];
        int[] predecessors = new int[vertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);

        distances[startVertex] = 0;

        for (int i = 1; i < vertices; i++) {
            boolean updated = false;

            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;

                if (distances[u] != Integer.MAX_VALUE &&
                        distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    predecessors[v] = u;
                    updated = true;
                }
            }

            if (!updated) {
                break;
            }
        }

        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;

            if (distances[u] != Integer.MAX_VALUE &&
                    distances[u] + weight < distances[v]) {
                System.out.println("Граф содержит отрицательный цикл!");
                return;
            }
        }

        printResults(startVertex, endVertex, distances, predecessors);
    }

    private void printResults(int start, int end, int[] distances, int[] predecessors) {
        System.out.println("\n=== РЕЗУЛЬТАТЫ АЛГОРИТМА БЕЛЛМАНА-ФОРДА ===");

        if (distances[end] == Integer.MAX_VALUE) {
            System.out.println("Путь из вершины " + start + " в вершину " + end + " не существует!");
            return;
        }

        System.out.println("Кратчайшее расстояние от " + start + " до " + end + ": " + distances[end]);

        List<Integer> path = reconstructPath(start, end, predecessors);
        System.out.println("Кратчайший путь: " + path);

        System.out.println("\nРасстояния от вершины " + start + " до всех вершин:");
        for (int i = 0; i < vertices; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Вершина " + i + ": недостижима");
            } else {
                System.out.println("Вершина " + i + ": " + distances[i]);
            }
        }
    }

    private List<Integer> reconstructPath(int start, int end, int[] predecessors) {
        List<Integer> path = new ArrayList<>();

        int current = end;
        while (current != -1) {
            path.add(current);
            current = predecessors[current];
        }

        Collections.reverse(path);

        if (path.get(0) != start) {
            return new ArrayList<>();
        }

        return path;
    }
}

public class BellmanFordDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(5);


        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, -1);

        System.out.println("=== ДЕМОНСТРАЦИЯ АЛГОРИТМА БЕЛЛМАНА-ФОРДА ===");
        System.out.println("Граф содержит 5 вершин и ребра с различными весами");
        System.out.println("Включая ребро 3→4 с отрицательным весом -1");

        System.out.println("\n--- Тест 1: Путь из 0 в 4 ---");
        graph.bellmanFord(0, 4);

        System.out.println("\n--- Тест 2: Путь из 0 в 3 ---");
        graph.bellmanFord(0, 3);

        System.out.println("\n--- Тест 3: Путь из 1 в 4 ---");
        graph.bellmanFord(1, 4);

        System.out.println("\n--- Тест 4: Путь из 4 в 0 (недостижим) ---");
        graph.bellmanFord(4, 0);

        demonstrateNegativeCycle();
    }

    public static void demonstrateNegativeCycle() {
        System.out.println("\n\n=== ДЕМОНСТРАЦИЯ ОБНАРУЖЕНИЯ ОТРИЦАТЕЛЬНОГО ЦИКЛА ===");

        Graph graphWithCycle = new Graph(4);

        graphWithCycle.addEdge(0, 1, 1);
        graphWithCycle.addEdge(1, 2, 1);
        graphWithCycle.addEdge(2, 3, -3);
        graphWithCycle.addEdge(3, 1, 1);

        System.out.println("Граф содержит отрицательный цикл 1→2→3→1");
        graphWithCycle.bellmanFord(0, 3);
    }
}