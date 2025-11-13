//Реализовать поиск в дереве (Iterative deepening depth-first search)

package lab4;
import java.util.*;

public class IterativeDeepeningDFS {

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

        System.out.println("Iterative Deepening DFS");

        TreeNode root = buildTree(scanner);

        System.out.print("Введите значение для поиска: ");
        int target = scanner.nextInt();

        TreeNode result = iterativeDeepeningDFS(root, target);
        printResult(result, target);

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

    private static TreeNode iterativeDeepeningDFS(TreeNode root, int target) {
        int depth = 0;

        while (true) {
            System.out.println("\n Глубина " + depth + " ");
            Set<TreeNode> visited = new HashSet<>();
            TreeNode result = depthLimitedDFS(root, target, depth, visited, 0);

            if (result != null) {
                return result;
            }

            if (isTreeFullyExplored(root, depth, new HashSet<>())) {
                System.out.println("Дерево полностью на глубине " + depth);
                return null;
            }

            depth++;
        }
    }

    private static TreeNode depthLimitedDFS(TreeNode node, int target, int limit, Set<TreeNode> visited, int currentDepth) {
        if (node == null || visited.contains(node)) {
            return null;
        }

        String indent = "  ".repeat(currentDepth);
        System.out.println(indent + "Посещаем узел: " + node.value + " (глубина: " + currentDepth + ")");

        if (node.value == target) {
            System.out.println(indent + "НАЙДЕН на глубине " + currentDepth + "!");
            return node;
        }

        if (currentDepth >= limit) {
            System.out.println(indent + "Достигнут предел глубины");
            return null;
        }

        visited.add(node);

        for (TreeNode child : node.children) {
            TreeNode result = depthLimitedDFS(child, target, limit, visited, currentDepth + 1);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private static boolean isTreeFullyExplored(TreeNode node, int maxDepth, Set<TreeNode> visited) {
        if (node == null || visited.contains(node)) {
            return true;
        }

        visited.add(node);

        if (!node.children.isEmpty() && maxDepth > 0) {
            for (TreeNode child : node.children) {
                if (!isTreeFullyExplored(child, maxDepth - 1, visited)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printResult(TreeNode result, int target) {
        System.out.println("\n=== РЕЗУЛЬТАТ ===");
        if (result != null) {
            System.out.println("Найден узел: " + target);
        } else {
            System.out.println("Узел " + target + " не найден в дереве");
        }
    }
}