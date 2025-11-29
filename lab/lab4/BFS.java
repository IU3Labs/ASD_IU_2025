/*
Реализовать поиск в дереве в ширину двумя способами.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static boolean bfsIterative(Node root, int target) {
        if (root == null) {
            return false;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value == target) {
                return true;
            }

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return false;
    }

    static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftH = height(root.left);
        int rightH = height(root.right);
        return Math.max(leftH, rightH) + 1;
    }

    static boolean processLevel(Node root, int level, int target) {
        if (root == null) {
            return false;
        }
        if (level == 1) {
            return root.value == target;
        }
        boolean foundLeft = processLevel(root.left, level - 1, target);
        if (foundLeft) {
            return true;
        }
        return processLevel(root.right, level - 1, target);
    }

    static boolean bfsRecursive(Node root, int target) {
        int h = height(root);
        for (int lvl = 1; lvl <= h; lvl++) {
            if (processLevel(root, lvl, target)) {
                return true;
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

        boolean foundIter = bfsIterative(root, target);
        System.out.println("Итеративный BFS (очередь): найдено = " + foundIter);

        boolean foundRec = bfsRecursive(root, target);
        System.out.println("Рекурсивный BFS (по уровням): найдено = " + foundRec);

        scan.close();
    }
}
