package lab4.bellmanFord;

import java.util.*;

public class BellmanFord {
    static class Edge {
        int from, to;
        long dist;
        Edge(int from, int to, long dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    static class ResultBellmanFord{
        List<Integer> path;
        long dist;
        boolean hasNegativeCycle;
        ResultBellmanFord(){
            path = new ArrayList<>();
            dist = 0L;
        }
        public String toString() {
            return path.toString();
        }
    }

    public static ResultBellmanFord bellmanFord(int countNodes, List<Edge> edges, int start, int finish) {
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[countNodes];
        int[] parent = new int[countNodes];
        Arrays.fill(dist,INF);
        Arrays.fill(parent,-1);

        dist[start]=0;


        for (int i = 1; i < countNodes; i++) {

            boolean any = false;
            for (Edge e : edges) {
                if (dist[e.from] != INF && dist[e.from] + e.dist < dist[e.to]) {
                    dist[e.to] = dist[e.from] + e.dist;
                    parent[e.to] = e.from;
                    any = true;
                }
            }
            if (!any) break;
        }

        if (dist[finish] == INF){
            return new ResultBellmanFord();
        }
        ResultBellmanFord result = new ResultBellmanFord();
        result.path.addFirst(finish);
        result.dist = dist[finish];
        while(result.path.getFirst() != start){
            int newElem = parent[result.path.getFirst()];
            if (result.path.contains(newElem)){
                result.hasNegativeCycle=true;
                break;
            }else {
                result.path.addFirst(newElem);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int countNodes = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, -5));
        edges.add(new Edge(0, 2, -4));
        edges.add(new Edge(1, 3, -3));
        edges.add(new Edge(2, 1, -6));
        edges.add(new Edge(3, 2, -2));

        ResultBellmanFord result = bellmanFord(countNodes, edges, 0, 3);

        System.out.println("Дистанция 0->3");
        if (result.hasNegativeCycle){
            System.out.println("Найден цикл");
        }else {
            System.out.println("Путь: " + result.toString());
        }


//        System.out.println("Дистанция 0->3: " + result.dist); // 8
//        System.out.println("Путь: " + result.toString()); // [0, 1, 3]

//        result = bellmanFord(countNodes, edges, 0, 0);
//        System.out.println("Дистанция 0->0: " + result.dist); // 0
//        System.out.println("Путь: " + result.toString()); // [0]
//
//        result = bellmanFord(countNodes, edges, 3, 0);
//        System.out.println("Дистанция 0->0: " + result.dist); // 0
//        System.out.println("Путь: " + result.toString()); // [] - путь не найден
    }
}
