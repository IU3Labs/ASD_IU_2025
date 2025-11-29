/*
Реализовать поиск в дереве в глубину двумя способами.
 */

import structures.Node;

import java.util.Scanner;
import java.util.Stack;

public class DFS {

    public static <T> Node<T> recursiveSearch(Node<T> node, T valueToFind) {
        if (node == null) {
            return null;
        }
        if (node.value.equals(valueToFind)) {
            return node;
        }
        for (Node<T> child : node.children) {
            Node<T> result = recursiveSearch(child, valueToFind);
            if (result != null) {
                return result;
            }
        }
        return null;
    };

    public static <T> Node<T> iterativeSearch(Node<T> root, T valueToFind) {
        if (root == null) {
            return null;
        }

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            if (currentNode.value.equals(valueToFind)) {
                return currentNode;
            }
            for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                stack.push(currentNode.children.get(i));
            }
        }

        return null;
    }

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

        Scanner in = new Scanner(System.in);
        System.out.print("Введите значение, которое нужно найти: ");
        int searchValue = in.nextInt();

        System.out.println("Рекурсивный поиск в глубину:");
        Node<Integer> recResult = recursiveSearch(root, searchValue);
        System.out.println(recResult != null ? "Найдено" : "Не найдено");
        System.out.println("\nИтеративный поиск в глубину:");
        Node<Integer> iterResult = iterativeSearch(root, searchValue);
        System.out.println(iterResult != null ? "Найдено" : "Не найдено");
    };

};
