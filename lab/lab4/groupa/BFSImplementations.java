package groupa;

import java.util.*;

public class BFSImplementations {

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

        // Способ 1: Классический BFS с очередью
        void BFSClassic(int startVertex) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            visited[startVertex] = true;
            queue.offer(startVertex);

            System.out.print("Классический BFS: ");
            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                System.out.print(vertex + " ");

                for (int neighbor : adjList.get(vertex)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // Способ 2: BFS с уровнями
        void BFSWithLevels(int startVertex) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            visited[startVertex] = true;
            queue.offer(startVertex);

            System.out.print("\nBFS с уровнями:\n");
            int level = 0;

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                System.out.print("Уровень " + level + ": ");

                for (int i = 0; i < levelSize; i++) {
                    int vertex = queue.poll();
                    System.out.print(vertex + " ");

                    for (int neighbor : adjList.get(vertex)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
                System.out.println();
                level++;
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

        graph.BFSClassic(startVertex);
        graph.BFSWithLevels(startVertex);

        System.out.println("\nСложность: O(V + E) - V вершин, E ребер");

        scanner.close();
    }
}