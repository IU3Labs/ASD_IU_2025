import java.util.*;

class BNode {
    int value;
    BNode left, right;

    BNode(int val) {
        value = val;
        left = null;
        right = null;
    }
}

public class Task4 {
    public static void IterativeBFS(BNode root) {
        if (root == null) {
            return;
        }

        Queue<BNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BNode node = queue.poll();
            System.out.print(node.value + " ");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    // Вывод узлов на заданном уровне
    public static void PrintCurrentLevel(BNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(root.value + " ");
        } else if (level > 1) {
            PrintCurrentLevel(root.left, level - 1);
            PrintCurrentLevel(root.right, level - 1);
        }
    }

    // Вычисление высоты дерева
    public static int height(BNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static void RecursiveBFS(BNode root) {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            PrintCurrentLevel(root, i);
        }
    }

    public static void main(String[] args) {
        BNode root = new BNode(1);
        root.left = new BNode(2);
        root.right = new BNode(3);
        root.left.left = new BNode(4);
        root.left.right = new BNode(5);

        System.out.println("BFS (Итеративно):");
        IterativeBFS(root); // Вывод: 1 2 3 4 5
        System.out.println("\nBFS (Рекурсивно):");
        RecursiveBFS(root);
    }
}