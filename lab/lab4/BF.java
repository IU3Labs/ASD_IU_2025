/**
 * Группа А. Задание 1.
 * В ориентированном взвешенном графе с отрицательными и
 * положительными весами ребер найти кратчайший путь между
 * двумя вершинами (Bellman--Ford algorithm).
 * */
import java.util.*;
public class BF {
    static class Edge { // класс-структура для ребра графа: откуда, куда и какой вес
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static class Graph {
        private final int vertices; // количество вершин
        private final List<Edge> edges = new ArrayList<>(); // список всех рёбер

        public Graph(int vertices) {
            this.vertices = vertices;
        }

        public void addEdge(int from, int to, int weight) { // добавление ребра в граф
            edges.add(new Edge(from, to, weight));
        }

        public void findShortestPath(int start, int finish) {
            int[] dist = new int[vertices]; // массив расстояний
            int[] parent = new int[vertices]; // массив предков для восстановления пути

            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            dist[start] = 0;

            for (int i = 0; i < vertices - 1; i++) {
                boolean changed = false; // флаг: произошло ли обновление в этой итерации
                for (Edge e : edges) { //если путь через e.from короче, обновляем e.to
                    if (dist[e.from] != Integer.MAX_VALUE && dist[e.from] + e.weight < dist[e.to]) {
                        dist[e.to] = dist[e.from] + e.weight;
                        parent[e.to] = e.from;
                        changed = true; // произошло обновление
                    }
                }
                if (!changed) break;
            }

            for (Edge e : edges) {
                if (dist[e.from] != Integer.MAX_VALUE && dist[e.from] + e.weight < dist[e.to]) {
                    System.out.println("Обнаружен отрицательный цикл — поиск невозможен.");
                    return;
                }
            }
            printResults(start, finish, dist, parent);
        }

        private void printResults(int start, int finish, int[] dist, int[] parent) {
            System.out.println("\nКратчайший путь от вершины " + start + " до " + finish);

            if (dist[finish] == Integer.MAX_VALUE) {
                System.out.println("Пути не существует.");
                return;
            }

            System.out.println("Расстояние: " + dist[finish]);

            List<Integer> path = new ArrayList<>();
            for (int v = finish; v != -1; v = parent[v]) {
                path.add(v);
            }
            Collections.reverse(path);

            System.out.print("Маршрут: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i != path.size() - 1) System.out.print(" -> ");
            }

            System.out.println("\n\nТаблица расстояний:");
            System.out.println("Вершина\tДистанция\tПредок");
            System.out.println("-----------------------------------");
            for (int i = 0; i < vertices; i++) {
                System.out.printf("%d\t\t%s\t\t%d\n",
                        i,
                        dist[i] == Integer.MAX_VALUE ? "∞" : dist[i],
                        parent[i]);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, -2);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 2, 5);
        graph.addEdge(4, 3, -8);

        graph.findShortestPath(0, 3);
    }
}
