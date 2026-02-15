import java.util.*;
//4 Реализовать поиск в дереве в ширину двумя способами.
public class BFSexampli {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(4);
        Node d = new Node(5);

        root.children.add(a);
        root.children.add(b);
        a.children.add(c);
        a.children.add(d);

        int target = 6;

        Node foundQueue = bfsQueueSearch(root, target);
        System.out.println("BFS найдено: " + (foundQueue != null ? foundQueue.value : "не найдено"));

        Node foundManual = bfsManualSearch(root, target);
        System.out.println("BFS найдено: " + (foundManual != null ? foundManual.value : "не найдено"));
    }
    static class Node {
        int value;
        List<Node> children = new ArrayList<>();
        Node(int value) { this.value = value; }
    }

    static Node bfsQueueSearch(Node root, int target) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.value == target) return node;

            for (Node child : node.children)
                queue.add(child);
        }
        return null;
    }

    static Node bfsManualSearch(Node root, int target) {
        List<Node> list = new ArrayList<>();
        list.add(root);

        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (node.value == target) return node;
            list.addAll(node.children);
        }
        return null;
    }
}