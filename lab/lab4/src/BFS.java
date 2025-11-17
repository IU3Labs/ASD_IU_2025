/*
Реализовать поиск в дереве в ширину двумя способами.
 */
import structures.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static <T> void recursiveTraverse(Node<T> root) {
        if (root == null) {
            return;
        };
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        recTraverse(queue);
    };

    private static <T> void recTraverse(Queue<Node> queue) {
        if (queue.isEmpty()) {
            return;
        };
        Node<T> currentNode = queue.poll();
        System.out.print(currentNode.value + " ");

        for (Node<T> child : currentNode.children) {
            queue.offer(child);
        };

        recTraverse(queue);
    };

    public static <T> void iterativeTraverse(Node<T> root) {
        if (root == null) {
            return;
        };
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            System.out.print(currentNode.value + " ");
            for (Node<T> child : currentNode.children) {
                queue.offer(child);
            };
        };
    };

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node8 = new Node<>(8);
        root.addChild(node2);
        root.addChild(node3);
        root.addChild(node4);
        node2.addChild(node5);
        node2.addChild(node6);
        node3.addChild(node7);
        node4.addChild(node8);

        System.out.println("Рекурсивный обход в ширину:");
        recursiveTraverse(root);
        System.out.println("\nРекурсивный обход в ширину:");
        iterativeTraverse(root);
    };

};
