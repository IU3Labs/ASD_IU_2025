import java.util.*;
//4 Реализовать поиск в дереве в ширину двумя способами.
public class BFSexampli {

    static class Node {
        int value;
        List<Node> children = new ArrayList<>();
        Node(int value) { this.value = value; }
    }

    static void bfsQueue(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");

            for (Node child : node.children)
                queue.add(child);
        }
    }

    static void bfsManual(Node root) {
        List<Node> list = new ArrayList<>();
        list.add(root);

        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            System.out.print(node.value + " ");
            list.addAll(node.children);
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

        System.out.println("BFS Queue:");
        bfsQueue(root);

        System.out.println("\nBFS Manual:");
        bfsManual(root);
    }
}