import java.util.*;

/**
 * Задача 3 (Группа A): Поиск в глубину (DFS) двумя способами
 * 1. Рекурсивная реализация (с использованием стека вызовов)
 * 2. Итеративная реализация (с явным стеком)
 */
public class DepthFirstSearch {
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

    static void dfsRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;

        result.add(node.value);
        System.out.println("Посетили вершину: " + node.value);

        for (TreeNode child : node.children) {
            dfsRecursive(child, result);
        }
    }

    static List<Integer> dfsIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.value);
            System.out.println("Посетили вершину: " + node.value);

            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }

        return result;
    }

    static boolean dfsSearchRecursive(TreeNode node, int target) {
        if (node == null) return false;
        if (node.value == target) return true;

        for (TreeNode child : node.children) {
            if (dfsSearchRecursive(child, target)) {
                return true;
            }
        }

        return false;
    }

    static boolean dfsSearchIterative(TreeNode root, int target) {
        if (root == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.value == target) {
                return true;
            }

            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }

        return false;
    }

    static void performanceComparison(TreeNode root, String name) {
        System.out.println("\n=== " + name + " ===");

        // Рекурсивный DFS
        List<Integer> result1 = new ArrayList<>();
        long startTime = System.nanoTime();
        dfsRecursive(root, result1);
        long recursiveTime = System.nanoTime() - startTime;

        System.out.println("\nРекурсивный DFS: " + result1);
        System.out.println("Время: " + recursiveTime + " нс");

        // Итеративный DFS
        long startTime2 = System.nanoTime();
        List<Integer> result2 = dfsIterative(root);
        long iterativeTime = System.nanoTime() - startTime2;

        System.out.println("\nИтеративный DFS: " + result2);
        System.out.println("Время: " + iterativeTime + " нс");
    }

    public static void main(String[] args) {
        // Построение тестового дерева
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);
        node3.addChild(node7);
        node4.addChild(node8);


        System.out.println("========== РЕКУРСИВНЫЙ DFS ==========");
        List<Integer> result1 = new ArrayList<>();
        dfsRecursive(root, result1);
        System.out.println("Результат: " + result1);

        System.out.println("\n========== ИТЕРАТИВНЫЙ DFS ==========");
        List<Integer> result2 = dfsIterative(root);
        System.out.println("Результат: " + result2);

        System.out.println("\n========== ПОИСК ЗНАЧЕНИЯ ==========");
        System.out.println("Поиск 5 (рекурсивный): " + dfsSearchRecursive(root, 5));
        System.out.println("Поиск 5 (итеративный): " + dfsSearchIterative(root, 5));
        System.out.println("Поиск 10 (рекурсивный): " + dfsSearchRecursive(root, 10));
        System.out.println("Поиск 10 (итеративный): " + dfsSearchIterative(root, 10));

        // Сравнение производительности на большом дереве
        performanceComparison(root, "Сравнение производительности");
    }
}