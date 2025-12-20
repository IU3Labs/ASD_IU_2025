/**
 * Реализовать поиск в дереве в ширину двумя способами.
 */

package tasks.task2;

import java.util.Scanner;

import tasks.helper.CreateTree;
import tasks.helper.TreeNode;

public class Test {

    public static void main() {
        System.out.println("_______ ТЕСТИРОВАНИЕ BFS ДЛЯ ДЕРЕВА ________\n");
        Scanner sc = new Scanner(System.in);


        TreeNode root = null;
        root = CreateTree.createTreeFromKeyboard(sc);
        System.out.println("\nСтруктура созданного дерева:");
        CreateTree.printTree(root, "", true);

        System.out.print("Введите искомое значение: ");
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
