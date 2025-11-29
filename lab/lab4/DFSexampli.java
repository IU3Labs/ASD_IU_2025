import java.util.*;
//3 Реализовать поиск в дереве в глубину двумя способами.
public class DFSexampli {

    static class Node {
        int value;
        List<Node> children = new ArrayList<>();
        Node(int value) { this.value = value; }
    }

    static void dfsRecursive(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        for (Node child : node.children) {
            dfsRecursive(child);
        }
    }

    static void dfsIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.value + " ");

            List<Node> children = node.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(4);
        root.children.add(a);
        root.children.add(b);
        a.children.add(c);

        System.out.println("DFS Recursive:");
        dfsRecursive(root);

        System.out.println("\nDFS Iterative:");
        dfsIterative(root);
    }
}