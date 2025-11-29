import java.util.*;
//5 В ориентированном графе найти эйлеров путь, проходящий через каждое ребро графа один раз.
public class EulerPath {

    static List<String> findEulerPath(Map<String, List<String>> graph) {
        Stack<String> stack = new Stack<>();
        List<String> path = new ArrayList<>();

        String start = graph.keySet().iterator().next();
        stack.push(start);

        Map<String, Iterator<String>> iters = new HashMap<>();
        for (String v : graph.keySet())
            iters.put(v, graph.get(v).iterator());

        while (!stack.isEmpty()) {
            String v = stack.peek();
            Iterator<String> it = iters.get(v);

            if (it.hasNext()) {
                String to = it.next();
                stack.push(to);
            } else {
                path.add(stack.pop());
            }
        }

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", new ArrayList<>(List.of("B")));
        graph.put("B", new ArrayList<>(List.of("C")));
        graph.put("C", new ArrayList<>(List.of("A")));

        List<String> path = findEulerPath(graph);
        System.out.println("Euler path: " + path);
    }
}