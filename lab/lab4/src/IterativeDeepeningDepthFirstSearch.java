//Реализовать поиск в дереве (Iterative deepening depth-first search)
import java.util.*;

public class IterativeDeepeningDepthFirstSearch {

    static class Graph {
        private int nodes;
        private List<List<Integer>> connections;

        Graph(int nodes) {
            this.nodes = nodes;
            connections = new ArrayList<>();
            for (int i = 0; i < nodes; i++) {
                connections.add(new ArrayList<>());
            }
        }

        void add(int source, int destination) {
            connections.get(source).add(destination);
        }

        boolean DLS(int source, int target, int depth) {
            if (source == target) return true;
            if (depth <= 0) return false;

            for (int neighbor : connections.get(source)) {
                if (DLS(neighbor, target, depth - 1)) {
                    return true;
                }
            }
            return false;
        }

        boolean IDDFS(int source, int target, int maxDepth) {
            for (int depth = 0; depth <= maxDepth; depth++) {
                if (DLS(source, target, depth)) {
                    System.out.println("Уровень искомого элемента: " + depth);
                    return true;
                }
            }
            return false;
        }
    }

    public static void main() {
        Graph graph = new Graph(7);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(1, 3);
        graph.add(1, 4);
        graph.add(2, 5);
        graph.add(2, 6);

        int source = 1, target = 6, maxDepth = 3;

        System.out.println("Ищем путь " + source + " " + target + " с макс. уровнем " + maxDepth);
        boolean found = graph.IDDFS(source, target, maxDepth);
        System.out.println("Результат: " + (found ? "Найдено" : "Не найдено"));
    }
}