import java.util.*;

public class EulerPath {

    static List<String> findEulerPathWithTarget(Map<String, List<String>> graph, String target) {
        Stack<String> stack = new Stack<>();
        List<String> path = new ArrayList<>();
        boolean[] foundTarget = {false}; // флаг, нашли ли вершину

        String start = graph.keySet().iterator().next();
        stack.push(start);

        Map<String, Iterator<String>> iters = new HashMap<>();
        for (String v : graph.keySet())
            iters.put(v, graph.get(v).iterator());

        while (!stack.isEmpty()) {
            String v = stack.peek();
            if (v.equals(target)) {
                foundTarget[0] = true; // нашли target
            }

            Iterator<String> it = iters.get(v);
            if (it.hasNext()) {
                String to = it.next();
                stack.push(to);
            } else {
                path.add(stack.pop());
            }
        }

        Collections.reverse(path);
        System.out.println("Target " + target + " found: " + foundTarget[0]);
        return path;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", new ArrayList<>(List.of("B")));
        graph.put("B", new ArrayList<>(List.of("C")));
        graph.put("C", new ArrayList<>(List.of("A")));

        String target = "E";
        List<String> path = findEulerPathWithTarget(graph, target);
        System.out.println("Euler path: " + path);
    }
}