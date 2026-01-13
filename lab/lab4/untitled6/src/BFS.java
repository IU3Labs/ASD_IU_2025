//Реализовать поиск в дереве в ширину двумя способами.
import java.util.*;

public class BFS {
// Классический BFS
    public static List<Integer> bfsClassic(TreeNode root, int target) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(Arrays.asList(root.value));

        while (!nodeQueue.isEmpty()) {
            TreeNode current = nodeQueue.poll();
            List<Integer> currentPath = pathQueue.poll();

            if (current.value == target) {
                return currentPath;
            }

            for (TreeNode child : current.children) {
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(child.value);
                nodeQueue.offer(child);
                pathQueue.offer(newPath);
            }
        }

        return new ArrayList<>();
    }
//  BFS с уровнями
    public static List<Integer> bfsWithLevels(TreeNode root, int target) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(Arrays.asList(root.value));

        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = nodeQueue.poll();
                List<Integer> currentPath = pathQueue.poll();

                if (current.value == target) {
                    return currentPath;
                }

                for (TreeNode child : current.children) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(child.value);
                    nodeQueue.offer(child);
                    pathQueue.offer(newPath);
                }
            }
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(" ПОИСК В ШИРИНУ (BFS) \n");

        TreeNode root = TreeNode.createTestTree();

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nДоступные узлы: " + TreeNode.getAllValues(root));
        System.out.print("Введите значение для поиска: ");
        int target = scanner.nextInt();

        System.out.println("\n--- Способ 1: Классический BFS ---");
        List<Integer> path1 = bfsClassic(root, target);
        if (!path1.isEmpty()) {
            System.out.println("Найденный путь: " + path1);
            System.out.println("Длина пути: " + path1.size());
        } else {
            System.out.println("Узел не найден");
        }

        System.out.println("\n--- Способ 2: BFS с уровнями ---");
        List<Integer> path2 = bfsWithLevels(root, target);
        if (!path2.isEmpty()) {
            System.out.println("Найденный путь: " + path2);
            System.out.println("Длина пути: " + path2.size());
        } else {
            System.out.println("Узел не найден");
        }

        System.out.println("\nПорядок обхода BFS: " + TreeNode.bfsTraversal(root));

        scanner.close();
    }
}












