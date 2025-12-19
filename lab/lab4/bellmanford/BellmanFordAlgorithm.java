package bellmanford;

import java.util.*;

public class BellmanFordAlgorithm {
    static class Edge {                             //описание ребра
        int from, to;
        long w;

        Edge(int from, int to, long w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static class Result {                           //результат работы алгоритма
        boolean hasNegativeCycleReachableFromS;
        long distance;
        List<Integer> path;
    }

    static final long INF = Long.MAX_VALUE / 4;

    public static Result bellmanFord(int n, List<Edge> edges, int s, int t) {
        long[] dist = new long[n];
        int[] parent = new int[n];              //массивы расстояния и предков
        Arrays.fill(dist, INF);                 //в начале считаем, что расстояние бесконечны, предков нет, расстояние до стартовой вершины равно 0
        Arrays.fill(parent, -1);
        dist[s] = 0;

        // V-1 релаксаций
        for (int i = 0; i < n - 1; i++) {
            boolean changed = false;
            for (Edge e : edges) {              //проходимся по всем рёбрам
                if (dist[e.from] == INF) continue;          //если вершина from недостижима, то пропускаем ребро
                long nd = dist[e.from] + e.w;               //nd - потенциально новое расстояние до e.to
                if (nd < dist[e.to]) {                      //если наш новый апуть короче, то перезаписываем
                    dist[e.to] = nd;
                    parent[e.to] = e.from;
                    changed = true;
                }
            }
            if (!changed) break; // оптимизация: если улучшений нет — можно остановиться
        }

        // проверка отрицательного цикла (достижимого из s)
        boolean negCycle = false;       //флаг наличия отрицательного цикла
        for (Edge e : edges) {          //снова проход по всем рёбрам
            if (dist[e.from] == INF) continue;     //Цикл должен быть достижим из s, иначе он не влияет на путь.
            long nd = dist[e.from] + e.w;       //*Если после n−1 итераций расстояние всё ещё улучшается:
            // существует отрицательный цикл
            // кратчайший путь не определён*/
            if (nd < dist[e.to]) {
                negCycle = true;
                break;
            }
        }

        Result res = new Result();    //формируем результат
        res.hasNegativeCycleReachableFromS = negCycle;
        res.distance = dist[t];

        // Если пути нет или отрицательный цикл достижим — путь/ответ по задаче может быть неопределим
        if (negCycle || dist[t] == INF) {
            res.path = Collections.emptyList();
            return res;
        }

        // восстановление пути s -> t по parent[]
        ArrayList<Integer> rev = new ArrayList<>();
        for (int cur = t; cur != -1; cur = parent[cur]) rev.add(cur);
        Collections.reverse(rev);

        // если t не достижим от s (на всякий случай)
        if (rev.isEmpty() || rev.get(0) != s) {
            res.path = Collections.emptyList();
        } else {
            res.path = rev;
        }

        return res;
    }
}


