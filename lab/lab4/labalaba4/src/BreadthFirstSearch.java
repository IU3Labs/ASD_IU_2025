import java.util.*;

public class BreadthFirstSearch {

    public static List<Integer> bfsSimple(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(root.val);
        nodeQueue.offer(root);
        pathQueue.offer(initialPath);

        if (root.val == target) {
            return initialPath;
        }

        while (!nodeQueue.isEmpty()) {
            TreeNode current = nodeQueue.poll();
            List<Integer> currentPath = pathQueue.poll();

            for (TreeNode child : current.children) {
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(child.val);

                if (child.val == target) {
                    return newPath;
                }

                nodeQueue.offer(child);
                pathQueue.offer(newPath);
            }
        }

        return new ArrayList<>(); // не найдено
    }

    public static List<Integer> bfsByLevels(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(root.val);
        nodeQueue.offer(root);
        pathQueue.offer(initialPath);

        if (root.val == target) {
            return initialPath;
        }

        while (!nodeQueue.isEmpty()) {
            // обрабатываем ВЕСЬ текущий уровень
            int levelSize = nodeQueue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = nodeQueue.poll();
                List<Integer> currentPath = pathQueue.poll();

                for (TreeNode child : current.children) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(child.val);

                    if (child.val == target) {
                        return newPath;
                    }

                    nodeQueue.offer(child);
                    pathQueue.offer(newPath);
                }
            }
            // после этого цикла завершён один уровень
            // но если мы нашли target, уже вернулись раньше
        }

        return new ArrayList<>(); // не найдено
    }

    public static void main(String[] args) {
        // Строим дерево:
        //        1
        //      / | \
        //     2  3  4
        //    /      |\
        //   5       6 7
        //            \
        //             8
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        root.addChild(n2);
        root.addChild(n3);
        root.addChild(n4);
        n2.addChild(n5);
        n4.addChild(n6);
        n4.addChild(n7);
        n6.addChild(n8);

        int target = 8;

        List<Integer> simplePath = BreadthFirstSearch.bfsSimple(root, target);
        List<Integer> levelPath = BreadthFirstSearch.bfsByLevels(root, target);

        System.out.println("Цель: " + target);
        System.out.println("BFS (простой): " + simplePath);
        System.out.println("BFS (по уровням): " + levelPath);

        if (simplePath.equals(levelPath)) {
            System.out.println("Пути совпадают!");
        } else {
            System.out.println("Пути не совпадают!");
        }

        // поиск корня
        System.out.println("\nПоиск корня (1):");
        System.out.println("Простой: " + BreadthFirstSearch.bfsSimple(root, 1));
        System.out.println("По уровням: " + BreadthFirstSearch.bfsByLevels(root, 1));

        // отсутствующий элемент
        System.out.println("\nПоиск отсутствующего (99):");
        System.out.println("Простой: " + BreadthFirstSearch.bfsSimple(root, 99));
        System.out.println("По уровням: " + BreadthFirstSearch.bfsByLevels(root, 99));
    }
}