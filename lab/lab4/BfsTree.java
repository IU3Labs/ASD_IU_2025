// Реализовать поиск в дереве в ширину двумя способами.

import java.util.*;

public class BfsTree {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main() {
        System.out.println("Инициализация дерева:");
        TreeNode root = TreeNode.inputTree();

        System.out.println("Значение элемента, который мы ищем:");
        int target = scanner.nextInt();

        System.out.println("Рекурсивный метод: Искомый элемент " +
                (bfsRecursive(root, target) == false ? "не найден" : "найден"));
        System.out.println("Итеративный метод: Искомый элемент " +
                (bfsIterative(root, target) == false ? "не найден" : "найден"));
    }

    public static boolean bfsRecursive(TreeNode root, int target) {
        if (root == null) return false;

        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        return bfsSearchByLevel(currentLevel, target);
    }

    private static boolean bfsSearchByLevel(List<TreeNode> currentLevel, int target) {
        if (currentLevel.isEmpty()) {
            return false;
        }

        List<TreeNode> nextLevel = new ArrayList<>();

        // обработка узлов текущего уровня
        for (TreeNode node : currentLevel) {
            if (node.value == target) {
                return true;
            }

            if (node.left != null) {
                nextLevel.add(node.left);
            }
            if (node.right != null) {
                nextLevel.add(node.right);
            }
        }

        // переходим на следующий уровень (по отдалённости от корня)
        return bfsSearchByLevel(nextLevel, target);
    }

    public static boolean bfsIterative(TreeNode root, int target) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.value == target) {
                return true;
            }

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return false;
    }
}
