import java.util.*;

public class Graph<T> {
    public record Edge<T>(T from, T to, int weight) {}

    private final Map<T, List<Edge<T>>> adjacencyList;
    private final Set<T> vertices;
    private final List<Edge<T>> edges;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.vertices = new HashSet<>();
        this.edges = new ArrayList<>();
    }

    public void addVertex(T vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(T source, T destination, int weight) {
        addVertex(source);
        addVertex(destination);

        Edge<T> edge = new Edge<>(source, destination, weight);
        edges.add(edge);
        adjacencyList.get(source).add(edge);
    }

    public void removeEdge(T source, T destination, int weight) {
        Edge<T> edgeToRemove = new Edge<>(source, destination, weight);

        edges.removeIf(edge -> edge.equals(edgeToRemove));

        List<Edge<T>> sourceEdges = adjacencyList.get(source);
        if (sourceEdges != null) {
            sourceEdges.removeIf(edge -> edge.equals(edgeToRemove));
        }
    }

    public List<T> getNeighbors(T vertex) {
        List<Edge<T>> edges = adjacencyList.get(vertex);
        if (edges == null) return new ArrayList<>();

        List<T> neighbors = new ArrayList<>();
        for (Edge<T> edge : edges) {
            neighbors.add(edge.to());
        }
        return neighbors;
    }

    public List<Edge<T>> getEdgesFrom(T vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public boolean hasVertex(T vertex) {
        return vertices.contains(vertex);
    }

    public boolean hasEdge(T source, T destination, int weight) {
        return edges.contains(new Edge<>(source, destination, weight));
    }

    public Set<T> getVertices() {
        return new HashSet<>(vertices);
    }

    public List<Edge<T>> getEdges() {
        return new ArrayList<>(edges);
    }

    public Graph<T> getTranspose() {
        Graph<T> reversedGraph = new Graph<>();
        for (Edge<T> edge : edges) {
            reversedGraph.addEdge(edge.to(), edge.from(), edge.weight());
        }
        return reversedGraph;
    }

    //Kosaraju’s Algorithm
    public boolean isStronglyConnected() {
        if (vertices.isEmpty()) return true;

        T startVertex = vertices.iterator().next();

        Set<T> reachableFromStart = this.dfs(startVertex);
        if (reachableFromStart.size() != vertices.size()) {
            return false;
        }

        Graph<T> reversed = this.getTranspose();
        Set<T> reachableInReversed = reversed.dfs(startVertex);

        return reachableInReversed.size() == vertices.size();
    }

    private Set<T> dfs(T startVertex) {
        Set<T> visited = new HashSet<>();
        dfsRecursive(startVertex, visited);
        return visited;
    }

    private void dfsRecursive(T current, Set<T> visited) {
        if (!visited.add(current)) return;

        for (Edge<T> edge : getEdgesFrom(current)) {
            if (!visited.contains(edge.to())) {
                dfsRecursive(edge.to(), visited);
            }
        }
    }

    public List<T> getEulerPath() {
        if (!hasEulerPath()) return new ArrayList<>();

        T startVertex = findStartVertexForEulerPath();

        Map<T, List<Edge<T>>> edgesCopy = new HashMap<>();
        for (T vertex : vertices) {
            edgesCopy.put(vertex, new ArrayList<>(getEdgesFrom(vertex)));
        }

        return findEulerianPath(startVertex, edgesCopy);
    }

    /*Слабая связность
    Баланс inDegree и OutDegree*/
    private boolean hasEulerPath() {
        if (!isWeaklyConnected()) {
            return false;
        }

        int startVertices = 0;
        int endVertices = 0;

        for (T vertex : vertices) {
            int inDegree = getInDegree(vertex);
            int outDegree = getOutDegree(vertex);
            int diff = outDegree - inDegree;

            if (diff == 1) startVertices++;
            else if (diff == -1) endVertices++;
            else  if (diff != 0) return false;
        }

        return (startVertices == 0 && endVertices == 0) || (startVertices == 1 && endVertices == 1);
    }

    private boolean isWeaklyConnected() {
        if (vertices.isEmpty()) return true;

        Map<T, Set<T>> undirected = new HashMap<>();
        for (Edge<T> edge : edges) {
            undirected.computeIfAbsent(edge.from(), k -> new HashSet<>()).add(edge.to());
            undirected.computeIfAbsent(edge.to(), k -> new HashSet<>()).add(edge.from());
        }

        Set<T> visited = new HashSet<>();
        dfsUndirected(vertices.iterator().next(), undirected, visited);

        return visited.size() == vertices.size();
    }

    public int getInDegree(T vertex) {
        return (int) edges.stream()
                .filter(edge -> edge.to().equals(vertex))
                .count();
    }

    public int getOutDegree(T vertex) {
        return (int) edges.stream()
                .filter(edge -> edge.from().equals(vertex))
                .count();
    }

    private void dfsUndirected(T current, Map<T, Set<T>> undirected, Set<T> visited) {
        if (!visited.add(current)) return;

        Set<T> neighbors = undirected.get(current);
        if (neighbors != null) {
            for (T neighbor : neighbors) {
                dfsUndirected(neighbor, undirected, visited);
            }
        }
    }

    private T findStartVertexForEulerPath() {
        for (T vertex : vertices) {
            if (getOutDegree(vertex) - getInDegree(vertex) == 1) return vertex;
        }

        return vertices.stream()
                .filter(v -> getOutDegree(v) > 0)
                .findFirst()
                .orElse(vertices.isEmpty() ? null : vertices.iterator().next());
    }

    //Hierholzer's algorithm
    private List<T> findEulerianPath(T startVertex, Map<T, List<Edge<T>>> edgesCopy) {
        List<T> path = new ArrayList<>();
        Stack<T> stack = new Stack<>();
        T current = startVertex;

        while (true) {
            if (edgesCopy.get(current) != null && !edgesCopy.get(current).isEmpty()) {
                stack.push(current);

                Edge<T> nextEdge = edgesCopy.get(current).remove(0);
                current = nextEdge.to();
            } else {
                path.add(current);

                if (stack.isEmpty()) break;

                current = stack.pop();
            }
        }
        Collections.reverse(path);
        return path;
    }
}