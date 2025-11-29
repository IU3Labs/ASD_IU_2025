package groupa;

import java.util.*;

public class IterativeDeepeningDFS {

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

        boolean DLS(int source, int target, int depth) {
            if (source == target) return true;
            if (depth <= 0) return false;

            for (int neighbor : adjList.get(source)) {
                if (DLS(neighbor, target, depth - 1)) {
                    return true;
                }
            }
            return false;
        }

        boolean IDDFS(int source, int target, int maxDepth) {
            for (int depth = 0; depth <= maxDepth; depth++) {
                if (DLS(source, target, depth)) {
                    System.out.println("Цель найдена на глубине: " + depth);
                    return true;
                }
            }
            return false;
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
        int source = scanner.nextInt();

        System.out.print("Введите целевую вершину: ");
        int target = scanner.nextInt();

        System.out.print("Введите максимальную глубину: ");
        int maxDepth = scanner.nextInt();

        System.out.println("Поиск пути от " + source + " до " + target + " с макс. глубиной " + maxDepth);
        boolean found = graph.IDDFS(source, target, maxDepth);
        System.out.println("Результат: " + (found ? "Найдено" : "Не найдено"));
        System.out.println("Сложность: O(b^d) - b фактор ветвления, d глубина цели");

        scanner.close();
    }
}