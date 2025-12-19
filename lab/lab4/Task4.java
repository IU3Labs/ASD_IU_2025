import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 4 Реализовать поиск в дереве в ширину двумя способами.

public class Task4 {

    public static Queue<Tree.Node> createLinkedList(Tree.Node root) {
        Queue<Tree.Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        return queue;
    }

   // Рекурсивный BFS
    public static boolean recursiveBFS(Queue<Tree.Node> queue, int target) {
        if (queue.isEmpty()) {
            return false;
        }

        Tree.Node node = queue.poll();

        if (node.data == target) {
            System.out.println("Элемент найден рекурсивным алгоритмом: " + node.data);
            return true;
        }

        if (node.left != null) {
            queue.add(node.left);
        }
        if (node.right != null) {
            queue.add(node.right);
        }

        return recursiveBFS(queue, target);
    }

    // Итеративный BFS
    public static boolean iterativeBFS(Tree.Node root, int target) {
        if (root == null) {
            return false;
        }
        Queue<Tree.Node> queue = createLinkedList(root);
        while (!queue.isEmpty()) {
            Tree.Node node = queue.poll();

            if (node.data == target) {
                System.out.println("Элемент найден итеративным алгоритмом: " + node.data);
                return true;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree tree = Utils.createTree(scanner);
        System.out.println("Структура дерева:");
        Utils.printInOrder(tree.root);
        System.out.println();

        int target = Utils.inputTarget(scanner);

        Queue<Tree.Node> queue = createLinkedList(tree.root);

        if (!recursiveBFS(queue, target)) {
            System.out.println("Элемент не найден");
        }

        if (!iterativeBFS(tree.root, target)) {
            System.out.println("Элемент не найден");

        }
    }
}
