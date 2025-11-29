/*Реализовать поиск в дереве (Iterative deepening depth-first search)*/


public class IterativeDeepeningDFS {

    private static boolean depthLimitedSearch(TreeNode node, int target, int depth) {
        if (node == null) return false;

        System.out.print(node.val + " "); // Отслеживаем посещенные узлы

        if (node.val == target) { // достигли цели
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
        if (root == null) {
            System.out.println("Дерево пустое");
            return false;
        }

        int depth = 0;
        int maxDepth = 10; // Добавляем ограничение максимальной глубины

        while (depth <= maxDepth) {
            System.out.println("\nПоиск на глубине: " + depth);
            System.out.print("Посещенные узлы: ");
            if (depthLimitedSearch(root, target, depth)) {
                System.out.println("\n Цель " + target + " найдена на глубине " + depth);
                return true;
            }
            System.out.println("\n Цель " + target + " не найдена на глубине " + depth);
            depth++; // Увеличиваем глубину для следующей итерации
        }

        System.out.println("\n Цель " + target + " не найдена в пределах максимальной глубины " + maxDepth);
        return false;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(100);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);
        node5.addChild(node7);

        // Тест 1: Поиск существующего значения
        int target1 = 7;

        System.out.println("ПОИСК ЗНАЧЕНИЯ " + target1);

        boolean found1 = iterativeDeepeningDFS(root, target1);
        System.out.println("\nФИН РЕЗ: " + (found1 ? "НАЙДЕНО" : "НЕ НАЙДЕНО"));


        // Тест 2: Поиск несуществующего значения
        int target2 = 9;

        System.out.println("ПОИСК ЗНАЧЕНИЯ " + target2);

        boolean found2 = iterativeDeepeningDFS(root, target2);
        System.out.println("\nФИН РЕЗ: " + (found2 ? "НАЙДЕНО" : "НЕ НАЙДЕНО"));


        // Тест 3: Поиск корневого значения
        int target3 = 1;

        System.out.println("ПОИСК ЗНАЧЕНИЯ " + target3);

        boolean found3 = iterativeDeepeningDFS(root, target3);
        System.out.println("\nФИН РЕЗ: " + (found3 ? "НАЙДЕНО" : "НЕ НАЙДЕНО"));
    }
}