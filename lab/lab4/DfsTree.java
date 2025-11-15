// Реализовать поиск в дереве в глубину двумя способами.

import java.util.*;

public class DfsTree {
    int value;
    TreeNode left; // левый потомок
    TreeNode right; // правый потомок
    public static final Scanner scanner = new Scanner(System.in);

    public static void main() {
        System.out.println("Инициализация дерева:");
        TreeNode root = TreeNode.inputTree();

        System.out.println("Значение элемента, который мы ищем:");
        int target = scanner.nextInt();
        System.out.println("Рекурсивный метод: Искомый элемент " +
                (dfsRecursive(root, target) == false ? "не найден" : "найден"));
        System.out.println("Итеративный метод: Искомый элемент " +
                (dfsIterative(root, target) == false ? "не найден" : "найден"));

    }

    public static boolean dfsRecursive(TreeNode root, int target) {
        if (root == null) return false;
        if (root.value == target) return true;

        if (dfsRecursive(root.left, target)) return true;
        if (dfsRecursive(root.right, target)) return true;
        return false;
    }

    public static boolean dfsIterative(TreeNode root, int target) {
        if (root == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.value == target) return true;

            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        return false;
    }
}