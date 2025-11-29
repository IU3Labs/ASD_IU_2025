package lab4;

import java.util.ArrayList;

public class Graph {

    private final int vertices;
    private final ArrayList<ArrayList<Edge>> edgeList;

    public int getVertices() {
        return vertices;
    }

    public ArrayList<ArrayList<Edge>> getEdgeList() {
        return edgeList;
    }

    public static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public Graph(int vertices) {
        this.vertices = vertices;
        edgeList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            edgeList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        edgeList.get(source).add(new Edge(destination, weight));
    }
}