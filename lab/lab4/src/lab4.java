import java.util.ArrayList;
import java.util.List;

public class lab4 {

    static class Graph {
        int vertices;
        List<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList[vertices]; //список всех вершин в которые можно перейти из i
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to) { //добавление ребра
            adjList[from].add(to);
        }
    }

    public static boolean findPath(Graph graph, int current, boolean[] visited, List<Integer> path) {
        path.add(current);
        visited[current] = true;

        if (path.size() == graph.vertices) { //длина пути равна вершинам = путь найден
            return true;
        }

        for (int neighbor : graph.adjList[current]) {
            if (!visited[neighbor]) { //если еще не посещен
                if (findPath(graph, neighbor, visited, path)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        visited[current] = false;

        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        boolean[] visited = new boolean[graph.vertices];
        List<Integer> path = new ArrayList<>();

        boolean found = false;
        for (int i = 0; i < graph.vertices; i++) {
            if (findPath(graph, i, visited, path)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("путь найден: " + path);
        } else {
            System.out.println("путь не найден");
        }
    }
}
