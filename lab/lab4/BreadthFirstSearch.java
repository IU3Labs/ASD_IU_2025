package lab4;

// 4 pеализовать поиск в дереве в ширину двумя способами

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearch {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        Tree tree = Utility.createTree(scanner);
        int target = Utility.inputTarget(scanner);

        Queue<Tree.Node> queue = createLinkedList(tree.root);

        if (!recursiveBFS(queue, target)) {
            System.out.println("Элемент не найден");
        }

        if (!iterativeBFS(tree.root, target)) {
            System.out.println("Элемент не найден");
        }
    }

    public static Queue<Tree.Node> createLinkedList(Tree.Node root) {
        Queue<Tree.Node> queue = new LinkedList<>();
        queue.add(root);
        return queue;
    }

    public static boolean recursiveBFS(Queue<Tree.Node> queue, int target) {
        if (queue.isEmpty()) return false;

        Tree.Node node = queue.poll();

        if (node.data == target) {
            System.out.println("Найден: " + node.data);
            return true;
        }

        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);

        return recursiveBFS(queue, target);
    }

    public static boolean iterativeBFS(Tree.Node root, int target) {
        if (root == null) return false;

        Queue<Tree.Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Tree.Node node = queue.poll();

            if (node.data == target) {
                System.out.println("Найден: " + node.data);
                return true;
            }

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        return false;
    }
}