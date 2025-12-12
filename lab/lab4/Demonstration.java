package lab4;

public class Demonstration {

    static void main() {
        System.out.println("Беллман-Форд");
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, -4);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 2, 9);
        graph.addEdge(4, 3, -3);
        BellmanFordAlgorithm.findShortestPath(graph, 0, 3);


        System.out.println("\nПоиск в глубину");
        TreeNode leaf4 = new TreeNode(4);
        TreeNode leaf5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, leaf4, leaf5);
        TreeNode node3 = new TreeNode(3);
        TreeNode root = new TreeNode(1, node2, node3);
        System.out.println("Поиск рекурсивным способом: " + DFS.recursiveDFS(root, 3));
        System.out.println("Поиск итеративным способом: " + DFS.iterationDFS(root, 3));

        System.out.println("\nПоиск в ширину");
        System.out.println("Поиск рекурсивным способом: " + BFS.recursiveBFS(root, 3));
        System.out.println("Поиск итеративным способом: " + BFS.iterationBFS(root, 3));
    }
}

