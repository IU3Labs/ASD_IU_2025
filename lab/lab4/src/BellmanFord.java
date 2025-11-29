import java.util.Arrays;


public class BellmanFord {
    static class Edge {
        int source, destination, weight;

        Edge() {
            source = destination = weight = 0;
        }
    }

    int V, E;
    Edge[] edge;

    BellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }

    void printDistance(int[] dist, int v){
        System.out.println("Расстояние от начальной точки: ");
        for (int i = 0; i < v; i++){
            System.out.println(i + "\t\t" +dist[i]);
        }
    }

    void BellmanFordAlgorithm(int source) {
        int V = this.V;
        int E = this.E;
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int s = this.edge[j].source;
                int v = this.edge[j].destination;
                int weight = this.edge[j].weight;
                if (dist[s] != Integer.MAX_VALUE && dist[s] + weight < dist[v])
                    dist[v] = dist[s] + weight;
            }
        }

        for (int j = 0; j < E; j++) {
            int s = this.edge[j].source;
            int v = this.edge[j].destination;
            int weight = this.edge[j].weight;
            if (dist[s] != Integer.MAX_VALUE && dist[s] + weight < dist[v]) {
                System.out.println("Граф содержит цикл отрицательного элемента");
                return;
            }
        }
        printDistance(dist, V);
    }
}
