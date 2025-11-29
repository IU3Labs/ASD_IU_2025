/**
 * Реализовать поиск в дереве в глубину двумя способами.
 */

package tasks.task1;
import java.util.List;

public class Test {

    /**
     * Создаем тестовое дерево для демонстрации работы алгоритмов
     * Структура тестового дерева:
     *        1
     *      / | \
     *     2  3  4
     *    / \    |
     *   5   6   7
     *       |
     *       8
     */
    private static TreeNode createTestTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.addChildren(node2, node3, node4);
        node2.addChildren(node5, node6);
        node4.addChild(node7);
        node6.addChild(node8);

        return node1;
    }

    public static void main() {
        System.out.println("_________ТЕСТИРОВАНИЕ DFS ДЛЯ ДЕРЕВА _________\n");

        // Создаем тестовое дерево
        TreeNode root = createTestTree();

        // Тест 1: Обход дерева
        System.out.println("1. ОБХОД ДЕРЕВА:");
        List<Integer> recursiveDFS = RecursiveDFS.dfs(root);
        List<Integer> iterativeDFS = IterativeDFS.dfs(root);

        System.out.println("Рекурсивный DFS: " + recursiveDFS);
        System.out.println("Итеративный DFS: " + iterativeDFS);
        System.out.println("Результаты совпадают: " + recursiveDFS.equals(iterativeDFS));

        // Тест 2: Поиск элементов
        System.out.println("\n2. ПОИСК ЭЛЕМЕНТОВ:");
        testSearch(root, 6, "существующий элемент");
        testSearch(root, 8, "элемент в глубине");
        testSearch(root, 99, "несуществующий элемент");

    }

    // Тестирует поиск элемента обоими методами
    private static void testSearch(TreeNode root, int target, String description) {
        TreeNode recursiveResult = RecursiveDFS.search(root, target);
        TreeNode iterativeResult = IterativeDFS.search(root, target);

        System.out.println("Поиск " + description + " (" + target + "):");
        System.out.println("  Рекурсивный: " + recursiveResult);
        System.out.println("  Итеративный: " + iterativeResult);
        System.out.println("  Результаты совпадают: " +
                (recursiveResult == null ? iterativeResult == null : recursiveResult.value == iterativeResult.value));
    }

}