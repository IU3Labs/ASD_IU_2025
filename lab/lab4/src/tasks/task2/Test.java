/**
 * Реализовать поиск в дереве в ширину двумя способами.
 */

package tasks.task2;

import java.util.Scanner;

public class Test {

    /**
     * Создает тестовое дерево для демонстрации работы алгоритмов
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
        System.out.println("_______ ТЕСТИРОВАНИЕ BFS ДЛЯ ДЕРЕВА ________\n");

        TreeNode root = createTestTree();

        System.out.print("Введите искомое значение: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        testSearch(root, number);
        sc.close();

    }

    private static void testSearch(TreeNode root, int target) {
        TreeNode iterativeResult = IterativeBFS.search(root, target);
        TreeNode recursiveResult = RecursiveBFS.search(root, target);

        System.out.println("Поиск " + " (" + target + "):");
        System.out.println("  Итеративный: " + iterativeResult);
        System.out.println("  Рекурсивный: " + recursiveResult);
        System.out.println("  Результаты совпадают: " +
                (iterativeResult == null ? recursiveResult == null : iterativeResult.value == recursiveResult.value));
    }
}