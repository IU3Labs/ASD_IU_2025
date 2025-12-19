package bellmanford;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 5;
        int s = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());;
        List<BellmanFordAlgorithm.Edge> edges = List.of(
                new BellmanFordAlgorithm.Edge(0, 1, 6),
                new BellmanFordAlgorithm.Edge(0, 2, 7),
                new BellmanFordAlgorithm.Edge(1, 2, 8),
                new BellmanFordAlgorithm.Edge(1, 3, 5),
                new BellmanFordAlgorithm.Edge(1, 4, -4),
                new BellmanFordAlgorithm.Edge(2, 3, -3),
                new BellmanFordAlgorithm.Edge(2, 4, 9),
                new BellmanFordAlgorithm.Edge(3, 1, -2),
                new BellmanFordAlgorithm.Edge(4, 3, 7),
                new BellmanFordAlgorithm.Edge(4, 0, 2)
        );

        BellmanFordAlgorithm.Result r = BellmanFordAlgorithm.bellmanFord(n, edges, s, t);
        if (r.hasNegativeCycleReachableFromS) {
            System.out.println("Есть отрицательный цикл, достижимый из s. Кратчайший путь может не существовать.");
        } else if (r.path.isEmpty()) {
            System.out.println("Пути нет.");
        } else {
            System.out.println("dist = " + r.distance);
            System.out.println("path = " + r.path);
        }
    }
}

