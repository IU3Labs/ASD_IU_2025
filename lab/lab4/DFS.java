/*
Реализовать поиск в дереве в глубину двумя способами.
 */

import java.util.Scanner;
import java.util.Stack;

public class DFS {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static boolean dfsRecursive(Node root, int target) {
        if (root == null) {
            return false;
        }

        if (root.value == target) {
            return true;
        }

        if (dfsRecursive(root.left, target)) {
            return true;
        }

        return dfsRecursive(root.right, target);
    }

    static boolean dfsIterative(Node root, int target) {
        if (root == null) {
            return false;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (current.value == target) {
                return true;
            }

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.print("Введите значение для поиска: ");
        int target = scan.nextInt();

        boolean foundRec = dfsRecursive(root, target);
        System.out.println("Рекурсивный DFS: найдено = " + foundRec);

        boolean foundIter = dfsIterative(root, target);
        System.out.println("Итеративный DFS (стек): найдено = " + foundIter);

        scan.close();
    }
}
