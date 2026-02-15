import java.util.*;

public class DFSexampli {
    public static void main(String[] args) {
        // Строим дерево
        Node root = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(4);
        Node d = new Node(5);

        root.children.add(a);
        root.children.add(b);
        a.children.add(c);
        a.children.add(d);

        int target = 5;

        Node foundRecursive = dfsRecursiveSearch(root, target);
        System.out.println("DFS Recursive found: " + (foundRecursive != null ? foundRecursive.value : "not found"));

        Node foundIterative = dfsIterativeSearch(root, target);
        System.out.println("DFS Iterative found: " + (foundIterative != null ? foundIterative.value : "not found"));
    }
    static class Node {
        int value;
        List<Node> children = new ArrayList<>();
        Node(int value) { this.value = value; }
    }

    static Node dfsRecursiveSearch(Node node, int target) {
        if (node == null) return null;
        if (node.value == target) return node;

        for (Node child : node.children) {
            Node found = dfsRecursiveSearch(child, target);
            if (found != null) return found; // нашли в поддереве
        }
        return null;
    }

    static Node dfsIterativeSearch(Node root, int target) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.value == target) return node;

            List<Node> children = node.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return null;
    }
}