import java.util.*;

public class BellmanFord {

    public static BellmanFordResult findShortestPath(int V, List<Edge> edges, int source, int target) {
        long[] dist = new long[V];
        int[] parent = new int[V];

        // изначально все расстояния бесконечны
        Arrays.fill(dist, Long.MAX_VALUE);
        // предков нет
        Arrays.fill(parent, -1);
        dist[source] = 0;

        // v-1 раз пытаемся улучшить пути
        for (int i = 0; i < V - 1; i++) {
            boolean updated = false;

            for (Edge e : edges) {
                if (dist[e.from] != Long.MAX_VALUE) {
                    long newDist = dist[e.from] + e.weight;
                    if (newDist < dist[e.to]) {
                        dist[e.to] = newDist;
                        parent[e.to] = e.from;
                        updated = true;
                    }
                }
            }

            // если ничего не поменялось — дальше смысла нет
            if (!updated) {
                break;
            }
        }

        // проверяем, есть ли отрицательный цикл
        boolean hasNegativeCycle = false;
        for (Edge e : edges) {
            if (dist[e.from] != Long.MAX_VALUE) {
                if (dist[e.from] + e.weight < dist[e.to]) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }

        if (hasNegativeCycle) {
            return BellmanFordResult.negativeCycle();
        }

        // если до цели так и не добрались
        if (dist[target] == Long.MAX_VALUE) {
            return BellmanFordResult.noPath();
        }

        // восстанавливаем путь через parent
        List<Integer> path = new ArrayList<>();
        int current = target;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);

        return BellmanFordResult.success(dist[target], path);
    }

    public static void main(String[] args) {
        int V1 = 5;
        List<Edge> edges1 = new ArrayList<>();
        edges1.add(new Edge(0, 1, 6));
        edges1.add(new Edge(0, 3, 7));
        edges1.add(new Edge(1, 2, 5));
        edges1.add(new Edge(1, 3, 8));
        edges1.add(new Edge(1, 4, -4));
        edges1.add(new Edge(2, 1, -2));
        edges1.add(new Edge(3, 2, -3));
        edges1.add(new Edge(3, 4, 9));
        edges1.add(new Edge(4, 0, 2));
        edges1.add(new Edge(4, 2, 7));

        int source = 0;
        int target = 2;

        BellmanFordResult result1 = findShortestPath(V1, edges1, source, target);

        if (result1.hasNegativeCycle) {
            System.out.println("Обнаружен отрицательный цикл!");
        } else if (!result1.pathExists) {
            System.out.println("Путь не существует.");
        } else {
            System.out.println("Кратчайшее расстояние: " + result1.distance);
            System.out.println("Путь: " + result1.path);
        }
        //граф с отрицательным циклом
        /*
        // Цикл: 1 -> 2 (вес -3), 2 -> 1 (вес 1) → сумма = -2 → отрицательный цикл
        int V2 = 4;
        List<Edge> edges2 = new ArrayList<>();
        edges2.add(new Edge(0, 1, 1));
        edges2.add(new Edge(1, 2, -3));
        edges2.add(new Edge(2, 1, 1)); // создаёт цикл 1 - 2 с отрицательным весом
        edges2.add(new Edge(2, 3, 2));

        BellmanFordResult result2 = findShortestPath(V2, edges2, 0, 3);

        if (result2.hasNegativeCycle) {
            System.out.println("Обнаружен отрицательный цикл!");
        } else if (!result2.pathExists) {
            System.out.println("Путь не существует.");
        } else {
            System.out.println("Кратчайшее расстояние: " + result2.distance);
            System.out.println("Путь: " + result2.path);
        }
        */
    }
}