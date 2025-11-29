import java.util.*;

public class BellmanFord {

    static final int INF = Integer.MAX_VALUE;

    // возвращает массив расстояний или кидает исключение, если найден отрицательный цикл
    public static int[] bellmanFord(List<Edge> edges, int V, int start) {
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // v-1 итераций (релаксация)
        for (int i = 0; i < V - 1; i++) {
            boolean changed = false;
            for (Edge e : edges) {
                if (dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[e.from] + e.weight;
                    changed = true;
                }
            }
            if (!changed) break; // оптимизация — досрочно выходим
        }

        // проверка на отрицательные циклы
        for (Edge e : edges) {
            if (dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) {
                throw new RuntimeException("Обнаружен отрицательный цикл");
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 5; // количество вершин

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 3, 7));

        int start = 0;

        try {
            int[] dist = bellmanFord(edges, V, start);

            System.out.println("Кратчайшие расстояния от вершины " + start + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("→ " + i + " = " + (dist[i] == INF ? "∞" : dist[i]));
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
