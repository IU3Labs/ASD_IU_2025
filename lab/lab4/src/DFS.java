/*
Реализовать поиск в дереве в глубину двумя способами.
 */

import structures.Node;

import java.util.Stack;

public class DFS {

    public static <T> void recursiveTraverse(Node<T> node) {
        if (node == null) {
            return;
        };
        System.out.printf(node.value + " ");
        for (Node<T> child : node.children) {
            recursiveTraverse(child);
        };
    };

    public static <T> void iterativeTraverse(Node<T> root) {
        if (root == null) {
            return;
        };
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            System.out.printf(currentNode.value + " ");
            for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                stack.push(currentNode.children.get(i));
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

        System.out.println("Рекурсивный обход в глубину:");
        recursiveTraverse(root);
        System.out.println("\nРекурсивный обход в глубину:");
        iterativeTraverse(root);
    };

};
