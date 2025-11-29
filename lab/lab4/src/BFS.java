/*
Реализовать поиск в дереве в ширину двумя способами.
 */
import structures.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    public static <T> Node<T> recursiveSearch(Node<T> root, T searchValue) {
        if (root == null) {
            return null;
        };
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        return recSearch(queue, searchValue);
    };

    private static <T> Node<T> recSearch(Queue<Node<T>> queue, T searchValue) {
        if (queue.isEmpty()) {
            return null;
        };
        Node<T> currentNode = queue.poll();

        if (currentNode.value.equals(searchValue)) {
            return currentNode;
        }

        for (Node<T> child : currentNode.children) {
            queue.offer(child);
        }

        return recSearch(queue, searchValue);
    };

    public static <T> Node<T> iterativeSearch(Node<T> root, T searchValue) {
        if (root == null) {
            return null;
        };
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            if (currentNode.value.equals(searchValue)) {
                return currentNode;
            }
            for (Node<T> child : currentNode.children) {
                queue.offer(child);
            }
        };

        return null;
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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

        System.out.print("Введите значение, которое нужно найти: ");
        int searchValue = in.nextInt();

        System.out.println("Рекурсивный поиск в ширину:");
        Node<Integer> recResult = recursiveSearch(root, searchValue);
        System.out.println(recResult != null ? "Найдено" : "Не найдено");
        System.out.println("\nИтеративный поиск в ширину:");
        Node<Integer> iterResult = iterativeSearch(root, searchValue);
        System.out.println(iterResult != null ? "Найдено" : "Не найдено");
    };

};
