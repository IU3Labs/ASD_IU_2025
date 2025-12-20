/**
 * Реализовать поиск в дереве в глубину двумя способами.
 */

package tasks.task1;

import tasks.helper.CreateTree;

import java.util.Scanner;

import tasks.helper.CreateTree;
import tasks.helper.TreeNode;


public class Test {
    public static void main() {
        System.out.println("_________ТЕСТИРОВАНИЕ DFS ДЛЯ ДЕРЕВА _________\n");

        // Создаем тестовое дерево
        Scanner sc = new Scanner(System.in);


        TreeNode root = null;
        root = CreateTree.createTreeFromKeyboard(sc);
        System.out.println("\nСтруктура созданного дерева:");
        CreateTree.printTree(root, "", true);

        System.out.print("Введите искомое значение: ");
        int target = sc.nextInt();

        // Тестирует поиск элемента обоими методами
        TreeNode recursiveResult = RecursiveDFS.search(root, target);
        TreeNode iterativeResult = IterativeDFS.search(root, target);
        System.out.println("Поиск " + " (" + target + "):");
        System.out.println("  Рекурсивный: " + recursiveResult);
        System.out.println("  Итеративный: " + iterativeResult);
        System.out.println("  Результаты совпадают: " +
                (recursiveResult == null ? iterativeResult == null : recursiveResult.value == iterativeResult.value));


        sc.close();
    }

}
