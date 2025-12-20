import java.util.*;

public class Bellman {

    static class DirectedEdge {
        int fromVertex;
        int toVertex;
        long weight;

        DirectedEdge(int fromVertex, int toVertex, long weight) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }

    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertexCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();
        int startVertex = scanner.nextInt() - 1;
        int finishVertex = scanner.nextInt() - 1;

        DirectedEdge[] edges = new DirectedEdge[edgeCount];
        for (int i = 0; i < edgeCount; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            long weight = scanner.nextLong();
            edges[i] = new DirectedEdge(from, to, weight);
        }

        long[] distance = new long[vertexCount];
        int[] predecessor = new int[vertexCount];
        Arrays.fill(distance, INF);
        Arrays.fill(predecessor, -1);
        distance[startVertex] = 0;

        for (int iteration = 0; iteration < vertexCount - 1; iteration++) {
            boolean changedOnIteration = false;
            for (DirectedEdge edge : edges) {
                if (distance[edge.fromVertex] == INF) continue;
                long candidateDistance = distance[edge.fromVertex] + edge.weight;
                if (candidateDistance < distance[edge.toVertex]) {
                    distance[edge.toVertex] = candidateDistance;
                    predecessor[edge.toVertex] = edge.fromVertex;
                    changedOnIteration = true;
                }
            }
            if (!changedOnIteration) break;
        }

        boolean hasReachableNegativeCycle = false;
        for (DirectedEdge edge : edges) {
            if (distance[edge.fromVertex] == INF) continue;
            long candidateDistance = distance[edge.fromVertex] + edge.weight;
            if (candidateDistance < distance[edge.toVertex]) {
                hasReachableNegativeCycle = true;
                break;
            }
        }

        if (distance[finishVertex] == INF) {
            System.out.println("NO PATH");
            return;
        }

        if (hasReachableNegativeCycle) {
            System.out.println("NEGATIVE CYCLE");
            return;
        }

        System.out.println(distance[finishVertex]);

        ArrayList<Integer> pathVertices = new ArrayList<>();
        int currentVertex = finishVertex;
        while (currentVertex != -1) {
            pathVertices.add(currentVertex);
            if (currentVertex == startVertex) break;
            currentVertex = predecessor[currentVertex];
        }

        if (pathVertices.get(pathVertices.size() - 1) != startVertex) {
            System.out.println("NO PATH");
            return;
        }

        Collections.reverse(pathVertices);
        for (int i = 0; i < pathVertices.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(pathVertices.get(i) + 1);
        }
        System.out.println();
    }
}
