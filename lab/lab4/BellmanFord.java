import java.util.*;

public class BellmanFord {
    public static void findShortestPath(Graph graph, int source, int destination) {
        ArrayList<ArrayList<Graph.Edge>> edgesList = graph.getEdgeList();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] predecessors = new int[graph.getVertices()];
        int[] paths = new int[graph.getVertices()];
        boolean[] inQueue = new boolean[graph.getVertices()];

        inQueue[source] = true;
        Arrays.fill(paths, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        paths[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int vertex = queue.pop();
            inQueue[vertex] = false;

            for (int i = 0; i < edgesList.get(vertex).size(); i++) {
                int target = edgesList.get(vertex).get(i).destination;
                int weight = edgesList.get(vertex).get(i).weight;

                if (paths[vertex] + weight < paths[target]) {
                    paths[target] = paths[vertex] + weight;
                    predecessors[target] = vertex;

                    if (!inQueue[target]) {
                        queue.add(target);
                        inQueue[target] = true;
                    }
                }
            }
        }

        printPath(predecessors, destination, paths[destination]);
    }

    private static void printPath(int[] predecessors, int destination, int distance) {
        if (distance == Integer.MAX_VALUE) {
            System.out.println("Путь не существует");
            return;
        }

        ArrayList<Integer> path = new ArrayList<>();
        for (int i = destination; i != -1; i = predecessors[i]) {
            path.add(i);
        }

        Collections.reverse(path);
        System.out.print("Путь: ");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i) + " -> ");
        }
        System.out.println(path.get(path.size() - 1));
        System.out.println("Расстояние: " + distance);
    }
}