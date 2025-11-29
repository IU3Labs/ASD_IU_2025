package lab4;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    // Итеративный BFS через очередь
    public static boolean bfsIterative(Tree tree, int target) {
        if (tree.root == null) return false;

        Queue<Tree.Node> queue = new LinkedList<>();
        queue.add(tree.root);

        while (!queue.isEmpty()) {
            Tree.Node node = queue.poll();

            if (node.data == target) return true;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        return false;
    }

    // Рекурсивный BFS (по уровням)
    public static boolean bfsRecursive(Tree tree, int target) {
        int height = treeHeight(tree.root);

        for (int level = 0; level <= height; level++) {
            if (searchLevel(tree.root, target, level)) {
                return true;
            }
        }

        return false;
    }

    // Рекурсивная проверка узлов на данном уровне
    private static boolean searchLevel(Tree.Node node, int target, int level) {
        if (node == null) return false;

        if (level == 0) {
            return node.data == target;
        } else {
            return searchLevel(node.left, target, level - 1) ||
                    searchLevel(node.right, target, level - 1);
        }
    }

    // Вычисление высоты дерева
    private static int treeHeight(Tree.Node node) {
        if (node == null) return -1; // глубина пустого дерева -1
        return 1 + Math.max(treeHeight(node.left), treeHeight(node.right));
    }

    public static void main(String[] args) {
        Tree tree = TreeInput.buildTreeFromConsole();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение для поиска:");
        int target = scanner.nextInt();

        // Итеративный BFS
        if (bfsIterative(tree, target)) {
            System.out.println("Итеративный BFS: " + target + " найден!");
        } else {
            System.out.println("Итеративный BFS: " + target + " не найден.");
        }

        // Рекурсивный BFS
        if (bfsRecursive(tree, target)) {
            System.out.println("Рекурсивный BFS: " + target + " найден!");
        } else {
            System.out.println("Рекурсивный BFS: " + target + " не найден.");
        }
    }
}