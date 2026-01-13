//Реализовать поиск в дереве в глубину двумя способами.
import java.util.*;

public class DFS {
// Рекурсивный DFS
    public static List<Integer> dfsRecursive(TreeNode root, int target) {
        List<Integer> path = new ArrayList<>();
        boolean found = dfsHelper(root, target, path);
        return found ? path : new ArrayList<>();
    }

    private static boolean dfsHelper(TreeNode node, int target, List<Integer> path) {
        if (node == null) return false;

        path.add(node.value);

        if (node.value == target) {
            return true;
        }

        for (TreeNode child : node.children) {
            if (dfsHelper(child, target, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
// Итеративный DFS
    public static List<Integer> dfsIterative(TreeNode root, int target) {
        if (root == null) return new ArrayList<>();

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<List<Integer>> pathStack = new Stack<>();

        nodeStack.push(root);
        pathStack.push(Arrays.asList(root.value));

        while (!nodeStack.isEmpty()) {
            TreeNode current = nodeStack.pop();
            List<Integer> currentPath = pathStack.pop();

            if (current.value == target) {
                return currentPath;
            }

            List<TreeNode> children = current.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                TreeNode child = children.get(i);
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(child.value);
                nodeStack.push(child);
                pathStack.push(newPath);
            }
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(" ПОИСК В ГЛУБИНУ (DFS) \n");

        TreeNode root = TreeNode.createTestTree();

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nДоступные узлы: " + TreeNode.getAllValues(root));
        System.out.print("Введите значение для поиска: ");
        int target = scanner.nextInt();

        System.out.println("\n--- Способ 1: Рекурсивный DFS ---");
        List<Integer> path1 = dfsRecursive(root, target);
        if (!path1.isEmpty()) {
            System.out.println("Найденный путь: " + path1);
            System.out.println("Длина пути: " + path1.size());
        } else {
            System.out.println("Узел не найден");
        }

        System.out.println("\n--- Способ 2: Итеративный DFS ---");
        List<Integer> path2 = dfsIterative(root, target);
        if (!path2.isEmpty()) {
            System.out.println("Найденный путь: " + path2);
            System.out.println("Длина пути: " + path2.size());
        } else {
            System.out.println("Узел не найден");
        }

        System.out.println("\nПорядок обхода DFS: " + TreeNode.dfsTraversal(root));

        scanner.close();
    }
}