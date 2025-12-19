public class Task1 {
    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static final int INF = 1_000_000_000;

    public static void main(String[] args) {

        int n = 5;

        Edge[] edges = createGraph();

        int start = 0;
        int end = 4;

        int[] dist = new int[n];
        int[] parent = new int[n];

        bellmanFord(n, edges, start, dist, parent);

        if (dist[end] == INF) {
            System.out.println("no path");
            return;
        }

        System.out.println("shortest path " + dist[end]);
        printPath(parent, start, end);
    }

    static Edge[] createGraph() {
        return new Edge[]{
                new Edge(0, 1, 6),
                new Edge(0, 2, 7),
                new Edge(1, 2, 8),
                new Edge(1, 3, 5),
                new Edge(1, 4, -4)
        };
    }

    static void bellmanFord(int n, Edge[] edges, int start, int[] dist, int[] parent) {
        initArrays(n, start, dist, parent);

        for (int i = 0; i < n - 1; i++) {
            relaxAllEdges(edges, dist, parent);
        }

        if (hasNegativeCycle(edges, dist)) {
            System.out.println("negative cycle");
        }
    }

    static void initArrays(int n, int start, int[] dist, int[] parent) {
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        dist[start] = 0;
    }

    static void relaxAllEdges(Edge[] edges, int[] dist, int[] parent) {
        for (int i = 0; i < edges.length; i++) {
            Edge e = edges[i];

            if (dist[e.from] == INF) continue;

            int newDist = dist[e.from] + e.weight;
            if (newDist < dist[e.to]) {
                dist[e.to] = newDist;
                parent[e.to] = e.from;
            }
        }
    }

    static boolean hasNegativeCycle(Edge[] edges, int[] dist) {
        for (int i = 0; i < edges.length; i++) {
            Edge e = edges[i];
            if (dist[e.from] == INF) continue;
            if (dist[e.from] + e.weight < dist[e.to]) {
                return true;
            }
        }
        return false;
    }

    static void printPath(int[] parent, int start, int end) {
        int[] path = new int[100];
        int len = 0;

        int cur = end;
        while (cur != -1) {
            path[len++] = cur;
            cur = parent[cur];
        }

        System.out.print("path: ");
        for (int i = len - 1; i >= 0; i--) {
            System.out.print((path[i] + 1));
            if (i > 0) System.out.print(" -> ");
        }
        System.out.println();
    }
}
