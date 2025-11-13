//Реализовать поиск в дереве в глубину двумя способами.

package lab4;
import java.util.*;
import java.util.*;

public class TreeDFS {

    static class TreeNode {
        int value;
        List<TreeNode> children;

        TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            this.children.add(child);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Поиск в глубину (DFS) двумя способами ===");

        TreeNode root = buildTree(scanner);

        System.out.print("Введите значение для поиска: ");
        int target = scanner.nextInt();

        // Рекурсивный DFS
        System.out.println("\n--- Рекурсивный DFS ---");
        TreeNode result1 = dfsRecursive(root, target, new HashSet<>());
        printResult(result1, target);

        // Итеративный DFS
        System.out.println("\n--- Итеративный DFS ---");
        TreeNode result2 = dfsIterative(root, target);
        printResult(result2, target);

        scanner.close();
    }

    private static TreeNode buildTree(Scanner scanner) {
        System.out.print("Введите значение корня: ");
        TreeNode root = new TreeNode(scanner.nextInt());

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print("Сколько детей у узла " + current.value + "? ");
            int count = scanner.nextInt();

            for (int i = 0; i < count; i++) {
                System.out.print("Введите значение ребенка " + (i + 1) + ": ");
                TreeNode child = new TreeNode(scanner.nextInt());
                current.addChild(child);
                queue.add(child);
            }
        }
        return root;
    }

    private static TreeNode dfsRecursive(TreeNode node, int target, Set<TreeNode> visited) {
        if (node == null || visited.contains(node)) return null;

        System.out.println("Посещаем узел: " + node.value);
        if (node.value == target) return node;

        visited.add(node);
        for (TreeNode child : node.children) {
            TreeNode result = dfsRecursive(child, target, visited);
            if (result != null) return result;
        }
        return null;
    }

    private static TreeNode dfsIterative(TreeNode root, int target) {
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (visited.contains(current)) continue;

            System.out.println("Посещаем узел: " + current.value);
            if (current.value == target) return current;

            visited.add(current);
            // Добавляем детей в обратном порядке для правильного обхода
            for (int i = current.children.size() - 1; i >= 0; i--) {
                stack.push(current.children.get(i));
            }
        }
        return null;
    }

    private static void printResult(TreeNode result, int target) {
        if (result != null) {
            System.out.println("Найден узел: " + target);
        } else {
            System.out.println("Узел " + target + " не найден");
        }
    }
}