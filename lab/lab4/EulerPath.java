/*5. В ориентированном графе найти эйлеров путь, проходящий через
каждое ребро графа один раз.*/

public class EulerPath {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge("1", "0", 1);
        graph.addEdge("0", "3", 1);
        graph.addEdge("3", "4", -1);
        graph.addEdge("4", "0", 4);
        graph.addEdge("0", "2", -2);
        graph.addEdge("2", "1", -10);

        System.out.println(graph.getEulerPath());
    }
}
