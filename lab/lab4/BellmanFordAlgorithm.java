import java.util.*;
//1 В ориентированном взвешенном графе с отрицательными и
//положительными весами ребер найти кратчайший путь между
//двумя вершинами (Bellman--Ford algorithm ).
public class BellmanFordAlgorithm {

    static class Graph {
        private int vertices;
        private ArrayList<ArrayList<Edge>> edgeList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.edgeList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                edgeList.add(new ArrayList<>());
            }
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

        public void addEdge(int source, int destination, int weight) {
            edgeList.get(source).add(new Edge(source, destination, weight));
        }

        public ArrayList<ArrayList<Edge>> getEdgeList() {
            return edgeList;
        }

        public int getVertices() {
            return vertices;
        }
    }

    public static void findShortestPath(Graph graph, int source, int destination) {
        ArrayList<ArrayList<Graph.Edge>> edgesList = graph.getEdgeList();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] predecessors = new int[graph.getVertices()];
        int[] distances = new int[graph.getVertices()];
        boolean[] inQueue = new boolean[graph.getVertices()];

        // Инициализация
        inQueue[source] = true;
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[source] = 0;
        queue.add(source);

        // Основной цикл алгоритма
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            inQueue[currentVertex] = false;

            // Обход всех исходящих ребер из текущей вершины
            for (Graph.Edge edge : edgesList.get(currentVertex)) {
                int targetVertex = edge.destination;
                int edgeWeight = edge.weight;

                // Релаксация ребра
                if (distances[currentVertex] != Integer.MAX_VALUE &&
                        distances[currentVertex] + edgeWeight < distances[targetVertex]) {

                    distances[targetVertex] = distances[currentVertex] + edgeWeight;
                    predecessors[targetVertex] = currentVertex;

                    // Добавляем вершину в очередь, если ее там нет
                    if (!inQueue[targetVertex]) {
                        queue.add(targetVertex);
                        inQueue[targetVertex] = true;
                    }
                }
            }
        }

        // Проверка на отрицательные циклы
        if (hasNegativeCycle(graph, distances)) {
            System.out.println("Обнаружен отрицательный цикл!");
            return;
        }

        // Восстановление и вывод пути
        printPath(predecessors, source, destination, distances);
    }

    private static boolean hasNegativeCycle(Graph graph, int[] distances) {
        ArrayList<ArrayList<Graph.Edge>> edgesList = graph.getEdgeList();

        // Дополнительный проход для обнаружения отрицательных циклов
        for (int vertex = 0; vertex < graph.getVertices(); vertex++) {
            for (Graph.Edge edge : edgesList.get(vertex)) {
                if (distances[vertex] != Integer.MAX_VALUE &&
                        distances[vertex] + edge.weight < distances[edge.destination]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printPath(int[] predecessors, int source, int destination, int[] distances) {
        if (distances[destination] == Integer.MAX_VALUE) {
            System.out.println("Путь из " + source + " в " + destination + " не существует");
            return;
        }

        // Восстановление пути
        ArrayList<Integer> path = new ArrayList<>();
        for (int vertex = destination; vertex != -1; vertex = predecessors[vertex]) {
            path.add(vertex);
        }
        Collections.reverse(path);

        // Вывод результата
        System.out.print("Кратчайший путь: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("Расстояние: " + distances[destination]);
    }

    // Тестирование
    public static void main(String[] args) {
        System.out.println("Тестирование алгоритма Беллмана-Форда ");

        //Граф с отрицательными весами
        System.out.println("Тест 1: Граф с отрицательными весами");
        Graph graph1 = new Graph(5);
        graph1.addEdge(0, 1, 6);
        graph1.addEdge(0, 2, 7);
        graph1.addEdge(1, 2, 8);
        graph1.addEdge(1, 3, -4);
        graph1.addEdge(1, 4, 5);
        graph1.addEdge(2, 3, 9);
        graph1.addEdge(2, 4, -3);
        graph1.addEdge(3, 0, 2);
        graph1.addEdge(3, 4, 7);
        graph1.addEdge(4, 1, -2);

        findShortestPath(graph1, 0, 3);
        System.out.println();

        // Тест 2 Граф без отрицательных циклов
        System.out.println("Тест 2: Простой граф");
        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1, 1);
        graph2.addEdge(0, 2, 4);
        graph2.addEdge(1, 2, 2);
        graph2.addEdge(1, 3, 5);
        graph2.addEdge(2, 3, 1);

        findShortestPath(graph2, 0, 3);
        System.out.println();

        // Тест 3 Несвязный граф
        System.out.println("Тест 3: Несвязный граф");
        Graph graph3 = new Graph(3);
        graph3.addEdge(0, 1, 2);
        // Вершина 2 не связана

        findShortestPath(graph3, 0, 2);
    }
}