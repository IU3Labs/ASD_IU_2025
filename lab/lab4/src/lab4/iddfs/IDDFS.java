package lab4.iddfs;

import java.util.*;

public class IDDFS {

    static class Node {
        int value;
        List<Node> children;
        Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
        void addChild(Node child) {
            children.add(child);
        }
    }

    static class SearchResult {
        Node node;
        List<Integer> path;

        SearchResult(Node node, List<Integer> path) {
            this.node = node;
            this.path = path;
        }
    }

    public static SearchResult iddfs(Node root, int target) {
        for (int depthLimit = 0; depthLimit < Integer.MAX_VALUE; depthLimit++) {
            Set<Node> visited = new HashSet<>();
            List<Integer> path = new ArrayList<>();

            SearchResult result = dls(root, target, depthLimit, visited, path);

            if (result != null && result.node != null) {
                return result;
            }

            if (result == null) {
                break;
            }
        }
        return null;
    }

    private static SearchResult dls(Node node, int target, int depthLimit,
                                    Set<Node> visited, List<Integer> path) {
        if (node.value == target) {
            path.add(node.value);
            return new SearchResult(node, new ArrayList<>(path));
        }

        if (depthLimit == 0) {
            return new SearchResult(null, null);
        }

        visited.add(node);
        path.add(node.value);

        boolean moreDepthLimit = false;

        for (Node child : node.children) {
            if (!visited.contains(child)) {
                SearchResult result = dls(child, target, depthLimit - 1, visited, path);

                if (result != null && result.node == null) {
                    moreDepthLimit = true;
                }

                else if (result != null && result.node != null) {
                    return result;
                }
            }
        }

        path.removeLast();

        if (moreDepthLimit) {
            return new SearchResult(null, null);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        root.addChild(n2);
        root.addChild(n3);
        n2.addChild(n4);
        n2.addChild(n5);
        n3.addChild(n6);
        n4.addChild(n7);

        int target = 7;

        System.out.println("Поиск узла " + target + " с помощью IDDFS:");

        SearchResult result = iddfs(root, target);

        if (result != null) {
            System.out.println("Узел найден: " + result.node.value); // 7
            System.out.println("Путь от корня: " + result.path); // [1, 2, 4, 7]
        } else {
            System.out.println("Узел не найден");
        }
    }
}
