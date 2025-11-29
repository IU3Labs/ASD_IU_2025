package groupa;

import java.util.*;

public class DFSImplementations {

    static class Graph {
        private int vertices;
        private List<List<Integer>> adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int source, int destination) {
            adjList.get(source).add(destination);
        }

        // Способ 1: Рекурсивный DFS
        void DFSRecursive(int vertex, boolean[] visited) {
            visited[vertex] = true;
            System.out.print(vertex + " ");

            for (int neighbor : adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    DFSRecursive(neighbor, visited);
                }
            }
        }

        // Способ 2: Итеративный DFS со стеком
        void DFSIterative(int startVertex) {
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();

            stack.push(startVertex);

            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                if (!visited[vertex]) {
                    visited[vertex] = true;
                    System.out.print(vertex + " ");

                    for (int i = adjList.get(vertex).size() - 1; i >= 0; i--) {
                        int neighbor = adjList.get(vertex).get(i);
                        if (!visited[neighbor]) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество вершин: ");
        int vertices = scanner.nextInt();

        Graph graph = new Graph(vertices);

        System.out.print("Введите количество ребер: ");
        int edgeCount = scanner.nextInt();

        System.out.println("Введите ребра в формате: источник назначение");
        for (int i = 0; i < edgeCount; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        System.out.print("Введите стартовую вершину: ");
        int startVertex = scanner.nextInt();

        System.out.println("Рекурсивный DFS:");
        boolean[] visited1 = new boolean[vertices];
        graph.DFSRecursive(startVertex, visited1);

        System.out.println("\n\nИтеративный DFS:");
        graph.DFSIterative(startVertex);

        System.out.println("\n\nСложность: O(V + E) - V вершин, E ребер");

        scanner.close();
    }
}