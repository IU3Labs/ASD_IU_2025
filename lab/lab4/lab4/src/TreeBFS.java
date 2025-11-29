/*4 Реализовать поиск в дереве в ширину двумя способами.*/


import java.util.*;

public class TreeBFS {

    // Классический BFS с использованием очереди
    public static void bfsQueue(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");

            for (TreeNode child : current.children) {
                queue.offer(child);
            }
        }
    }

    // BFS с использованием двух списков
    public static void bfsLevelByLevel(TreeNode root) {
        if (root == null) return;

        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();

            for (TreeNode node : currentLevel) {
                System.out.print(node.val + " ");
                nextLevel.addAll(node.children);
            }

            currentLevel = nextLevel;
        }
    }

    public static void main(String[] args) {
        // Создаем дерево
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);

        System.out.println("BFS с очередью:");
        bfsQueue(root);
        System.out.println();

        System.out.println("BFS уровень за уровнем:");
        bfsLevelByLevel(root);
        System.out.println();
    }
}