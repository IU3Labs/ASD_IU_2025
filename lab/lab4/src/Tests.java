public class Tests {
    public static void main(String[] args) {

        System.out.println("Bellman-Ford Algorithm Test");
        int V = 5;
        int E = 8;
        BellmanFord graph = new BellmanFord(V, E);

        // Edge 0-1
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = -1;

        // Edge 0-2
        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 4;

        // Edge 1-2
        graph.edge[2].source = 1;
        graph.edge[2].destination = 2;
        graph.edge[2].weight = 3;

        // Edge 1-3
        graph.edge[3].source = 1;
        graph.edge[3].destination = 3;
        graph.edge[3].weight = 2;

        // Edge 1-4
        graph.edge[4].source = 1;
        graph.edge[4].destination = 4;
        graph.edge[4].weight = 2;

        // Edge 3-2
        graph.edge[5].source = 3;
        graph.edge[5].destination = 2;
        graph.edge[5].weight = 5;

        // Edge 3-1
        graph.edge[6].source = 3;
        graph.edge[6].destination = 1;
        graph.edge[6].weight = 1;

        // Edge 4-3
        graph.edge[7].source = 4;
        graph.edge[7].destination = 3;
        graph.edge[7].weight = -3;

        graph.BellmanFordAlgorithm(0);


        System.out.println("\nIDDFS test: ");
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);

        int target = 6, maxDepth = 3, src = 0;
        if (g.IDDFS(src, target, maxDepth))
            System.out.println("Достижима из этой точки");
        else
            System.out.println("НЕ достижима из это точки");

        System.out.println("\nПоиск эйлерового пути");
        int[][] graphEuler = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0}
        };

        if (GfG.eulerPath(graphEuler) == 1) {
            System.out.println("Да");
            GfG.printPath(graphEuler);

        } else
            System.out.println("Нет");
    }
}
