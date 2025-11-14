//В ориентированном взвешенном графе с отрицательными и
//положительными весами ребер найти кратчайший путь между
//двумя вершинами (Bellman--Ford algorithm).
import java.util.*;

public class BellmanFord {
    //ребро графа
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

    public static List<Integer> findShortestPath(List<Edge> edges, int vertexCount, int start, int end) {
        int[] distances = new int[vertexCount];
        int[] predecessors = new int[vertexCount];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[start] = 0;

        for (int i = 0; i < vertexCount - 1; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] != Integer.MAX_VALUE &&
                        distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                    predecessors[edge.destination] = edge.source;
                }
            }
        }

        // Проверка на наличие циклов отрицательного веса
        for (Edge edge : edges) {
            if (distances[edge.source] != Integer.MAX_VALUE &&
                    distances[edge.source] + edge.weight < distances[edge.destination]) {
                throw new RuntimeException("Ошибка");
            }
        }

        if (distances[end] == Integer.MAX_VALUE) {
            return Collections.emptyList();
        }

        // Восстановление пути из массива предшественников
        LinkedList<Integer> path = new LinkedList<>();
        int currentVertex = end;
        while (currentVertex != -1) {
            path.addFirst(currentVertex);
            currentVertex = predecessors[currentVertex];
        }

        return path;
    }

    public static void main() {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 4),
                new Edge(0, 2, 5),
                new Edge(1, 2, -2),
                new Edge(1, 3, 6),
                new Edge(2, 3, 1),
                new Edge(2, 1, 3)
        );

        List<Integer> shortestPath = findShortestPath(edges, 4, 0, 3);
        System.out.println("Кратчайший путь: " + shortestPath);
    }
}