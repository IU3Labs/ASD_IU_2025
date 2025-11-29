import java.util.*;

class GfG {

    static void findEulerPath(int cur, int[][] graph, List<Integer> path) {
        int n = graph.length;

        for (int i = 0; i < n; i++) {
            if (graph[cur][i] == 1) {
                graph[cur][i] = 0;
                graph[i][cur] = 0;

                findEulerPath(i, graph, path);
            }
        }

        path.add(cur);
    }

    static void printPath(int[][] graph) {
        int n = graph.length;

        int start = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    cnt++;
                }
            }
            if (cnt % 2 == 1) {
                start = i;
                break;
            }
        }

        List<Integer> path = new ArrayList<>();

        findEulerPath(start, graph, path);

        for (int ele : path) {
            System.out.print(ele + " ");
        }
    }

    static int eulerPath(int[][] graph) {
        int n = graph.length;
        int odd = 0;

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    cnt++;
                }
            }
            if (cnt % 2 == 1) {
                odd++;
            }
        }

        return (odd > 2) ? 0 : 1;
    }
}