import java.util.*;

public class EulerPathDirectedGraph {

    static class Graph {
        private int V;
        private LinkedList<Integer>[] adj;
        private int[] inDegree;
        private int[] outDegree;

        @SuppressWarnings("unchecked")
        public Graph(int vertices) {
            this.V = vertices;
            adj = new LinkedList[V];
            inDegree = new int[V];
            outDegree = new int[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }


        public void addEdge(int u, int v) {
            adj[u].add(v);
            outDegree[u]++;
            inDegree[v]++;
        }


        public boolean hasEulerPath() {
            int start = 0, end = 0;

            for (int i = 0; i < V; i++) {
                int diff = outDegree[i] - inDegree[i];

                if (Math.abs(diff) > 1) {
                    return false;
                }

                if (diff == 1) {
                    start++;
                } else if (diff == -1) {
                    end++;
                }
            }

            return (start == 0 && end == 0) || (start == 1 && end == 1);
        }


        public int findStartVertex() {
            int start = -1;

            for (int i = 0; i < V; i++) {

                if (outDegree[i] - inDegree[i] == 1) {
                    return i;
                }


                if (outDegree[i] > 0 && start == -1) {
                    start = i;
                }
            }

            return start;
        }


        private void dfs(int u, List<Integer> path) {
            while (!adj[u].isEmpty()) {
                int v = adj[u].removeFirst(); // Удаляем ребро
                dfs(v, path);
            }
            path.add(u);
        }


        public List<Integer> findEulerPath() {
            List<Integer> path = new ArrayList<>();

            if (!hasEulerPath()) {
                System.out.println("Эйлеров путь не существует");
                return path;
            }

            int start = findStartVertex();
            dfs(start, path);
            Collections.reverse(path);

            return path;
        }


        public void printGraph() {
            System.out.println("Граф:");
            for (int i = 0; i < V; i++) {
                if (!adj[i].isEmpty()) {
                    System.out.print(i + " -> ");
                    for (int neighbor : adj[i]) {
                        System.out.print(neighbor + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Пример 1: Граф с эйлеровым путем
        System.out.println("=== Пример 1: Граф с эйлеровым путем ===");
        Graph g1 = new Graph(5);

        // Добавляем ребра
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        g1.addEdge(4, 0);
        g1.addEdge(1, 3);

        g1.printGraph();

        List<Integer> eulerPath1 = g1.findEulerPath();
        System.out.println("Эйлеров путь: " + eulerPath1);

        // Пример 2: Граф с эйлеровым циклом
        System.out.println("\n=== Пример 2: Граф с эйлеровым циклом ===");
        Graph g2 = new Graph(4);

        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(3, 0);
        g2.addEdge(1, 3);
        g2.addEdge(3, 1);

        g2.printGraph();

        List<Integer> eulerPath2 = g2.findEulerPath();
        System.out.println("Эйлеров путь: " + eulerPath2);

        // Пример 3: Граф без эйлерова пути
        System.out.println("\n=== Пример 3: Граф без эйлерова пути ===");
        Graph g3 = new Graph(4);

        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 3);
        g3.addEdge(3, 0);
        g3.addEdge(0, 2);
        g3.addEdge(1, 3);
        g3.addEdge(2, 0);

        g3.printGraph();

        List<Integer> eulerPath3 = g3.findEulerPath();
        System.out.println("Эйлеров путь: " + eulerPath3);
    }
}