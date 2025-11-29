//Реализовать поиск в дереве в ширину двумя способами.
import java.util.*;

public class BSF {

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

        void connect(int from, int to) {
            connections.get(from).add(to);
        }

        void BFSLevels(int startNode) {
            boolean[] seen = new boolean[nodes];
            Queue<Integer> queue = new LinkedList<>();

            seen[startNode] = true;
            queue.offer(startNode);

            System.out.print("BFS по глубине:\n");
            int currentLevel = 0;

            while (!queue.isEmpty()) {
                int elementsInLevel = queue.size();
                System.out.print("Уровень " + currentLevel + ": ");

                for (int i = 0; i < elementsInLevel; i++) {
                    int currentNode = queue.poll();
                    System.out.print(currentNode + " ");

                    for (int linkedNode : connections.get(currentNode)) {
                        if (!seen[linkedNode]) {
                            seen[linkedNode] = true;
                            queue.offer(linkedNode);
                        }
                    }
                }
                System.out.println();
                currentLevel++;
            }
        }

        void BFSStandart(int startNode) {
            boolean[] visited = new boolean[nodes];
            Queue<Integer> queue = new LinkedList<>();

            visited[startNode] = true;
            queue.offer(startNode);

            System.out.print("Стандартный BFS: ");
            while (!queue.isEmpty()) {
                int node = queue.poll();
                System.out.print(node + " ");

                for (int adjacent : connections.get(node)) {
                    if (!visited[adjacent]) {
                        visited[adjacent] = true;
                        queue.offer(adjacent);
                    }
                }
            }
        }
    }

    public static void main() {
        Graph graph = new Graph(7);
        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(1, 3);
        graph.connect(1, 4);
        graph.connect(2, 5);
        graph.connect(2, 6);
        graph.connect(3, 4);

        graph.BFSLevels(0);
        graph.BFSStandart(0);
    }
}