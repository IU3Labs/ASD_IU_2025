package lab4;
import java.util.Scanner;
import java.util.Stack;

public class DFS {
    //Рекурсивный DFS
    public static boolean dfsRecursive(Tree.Node node, int target) {
        if (node == null) return false;

        // Если нашли узел
        if (node.data == target) return true;

        // Сначала левое поддерево, потом правое
        return dfsRecursive(node.left, target) || dfsRecursive(node.right, target);
    }

    //Итеративный DFS через стек
    public static boolean dfsIterative(Tree tree, int target) {
        if (tree.root == null) return false;

        Stack<Tree.Node> stack = new Stack<>();
        stack.push(tree.root);

        while (!stack.isEmpty()) {
            Tree.Node node = stack.pop();

            // Если нашли узел
            if (node.data == target) return true;

            // Сначала правый потом левый, чтобы левый обрабатывался первым
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return false;
    }
    public static void main(String[] args) {
        // Ввод дерева с консоли
        Tree tree = TreeInput.buildTreeFromConsole();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение для поиска:");
        int target = scanner.nextInt();

        // Рекурсивный поиск
        if (dfsRecursive(tree.root, target)) {
            System.out.println("Рекурсивный DFS: " + target + " найден!");
        } else {
            System.out.println("Рекурсивный DFS: " + target + " не найден.");
        }

        // Итеративный поиск
        if (dfsIterative(tree, target)) {
            System.out.println("Итеративный DFS: " + target + " найден!");
        } else {
            System.out.println("Итеративный DFS: " + target + " не найден.");
        }
    }
}