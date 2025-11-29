/*4 Реализовать поиск в дереве в ширину двумя способами.*/


import java.util.*;

public class TreeBFS {

    // Классический BFS с использованием очереди
    public static void bfsQueue(TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустоеоооо");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");

            for (TreeNode child : current.children) {
                queue.offer(child);
            }
        }
        System.out.println();
    }

    // BFS с использованием двух списков (уровень за уровнем)
    public static void bfsLevelByLevel(TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустоеоооо");
            return;
        }


        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        int level = 0;

        while (!currentLevel.isEmpty()) {
            System.out.print("Уровень " + level + ": ");
            List<TreeNode> nextLevel = new ArrayList<>();

            for (TreeNode node : currentLevel) {
                System.out.print(node.val + " ");
                nextLevel.addAll(node.children);
            }
            System.out.println();

            currentLevel = nextLevel;
            level++;
        }
    }

    // BFS с подсчетом количества узлов на каждом уровне
    public static void bfsWithLevelInfo(TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустоооооо");
            return;
        }


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int totalNodes = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Уровень " + (totalNodes == 0 ? 0 : (int)(Math.log(totalNodes + 1) / Math.log(2))) + " (" + levelSize + " узлов): ");

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");
                totalNodes++;

                for (TreeNode child : current.children) {
                    queue.offer(child);
                }
            }
            System.out.println();
        }
        System.out.println("Всего узлов: " + totalNodes);
    }

    public static void main(String[] args) {
        // Создаем дерево
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





        bfsQueue(root);


        bfsLevelByLevel(root);

        bfsWithLevelInfo(root);

        // Дополнительный тест с другим деревом

        System.out.println("ТЕСТ С ПУСТЫМ ДЕРЕВОМ");

        bfsQueue(null);


        System.out.println("ТЕСТ С ОДНИМ УЗЛОМ");

        TreeNode singleNode = new TreeNode(100);
        bfsLevelByLevel(singleNode);
    }
}