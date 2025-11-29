public class Demo {

    static void main() {
        System.out.println("Алгоритм Беллмана-Форда");
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 1, -1);
        graph.addEdge(3, 2, -2);
        graph.addEdge(3, 4, -10);
        graph.addEdge(5, 4, 7);
        BellmanFord.findShortestPath(graph, 0, 4);

        System.out.println("\nПоиск в глубину");
        TreeNode leaf4 = new TreeNode(4);
        TreeNode leaf5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, leaf4, leaf5);
        TreeNode node3 = new TreeNode(3);
        TreeNode root = new TreeNode(1, node2, node3);
        System.out.println("Поиск рекурсивным способом: " + TreeDFS.recursiveSearch(root, 3));
        System.out.println("Поиск итеративным способом: " + TreeDFS.iterationSearch(root, 3));

        System.out.println("\nПоиск в ширину");
        System.out.println("Поиск рекурсивным способом: " + TreeBFS.recursiveSearch(root, 3));
        System.out.println("Поиск итеративным способом: " + TreeBFS.iterationSearch(root, 3));
    }
}