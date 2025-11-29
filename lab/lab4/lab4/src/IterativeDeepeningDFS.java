/*Реализовать поиск в дереве (Iterative deepening depth-first search)*/


public class IterativeDeepeningDFS {

    private static boolean depthLimitedSearch(TreeNode node, int target, int depth) {
        if (node == null) return false;

        System.out.print(node.val + " "); // Отслеживаем посещенные узлы


        if (node.val == target) { // достигли
            return true;
        }

        // Достигли максимальной глубины - останавливаемся
        if (depth <= 0) {
            return false;
        }

        // Ищем в детях с уменьшенной глубиной
        for (TreeNode child : node.children) {
            if (depthLimitedSearch(child, target, depth - 1)) {
                return true;
            }
        }

        return false;
    }


    public static boolean iterativeDeepeningDFS(TreeNode root, int target) {
        if (root == null) return false;

        int depth = 0;
        while (true) {
            System.out.println("\nПоиск на глубине: " + depth);
            if (depthLimitedSearch(root, target, depth)) {
                return true;
            }
            depth++; // Увеличиваем глубину для следующей итерации
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);
        node5.addChild(node7);

        int target = 7;
        System.out.println("Поиск значения " + target + " с помощью IDDFS:");
        boolean found = iterativeDeepeningDFS(root, target);
        System.out.println("\nРезультат: " + found);

    }
}