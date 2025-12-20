/**
 * Реализовать поиск в дереве (Iterative deepening depth-first search)
 */
package tasks.task0;
import tasks.helper.CreateTree;
import tasks.helper.TreeNode;


import java.util.Scanner;

class IterDepDFSearch {

    public static TreeNode search(TreeNode root, int targetValue) {
        // Если дерево пустое, сразу возвращаем null
        if (root == null) {
            return null;
        }

        int currentDepthLimit = 0; // Текущее ограничение глубины поиска
        TreeNode foundNode = null;  // Переменная для хранения результата

        // Вычисляем максимальную глубину дерева для определения предела поиска
        int maxTreeDepth = calculateTreeDepth(root);

        // Постепенно увеличиваем глубину поиска пока не найдем узел
        // или не превысим максимальную глубину дерева
        while (foundNode == null && currentDepthLimit <= maxTreeDepth) {
            System.out.println("Поиск на глубине: " + currentDepthLimit);

            // Выполняем поиск с ограничением по глубине
            foundNode = depthLimitedSearch(root, targetValue, currentDepthLimit);

            // Увеличиваем глубину для следующей итерации
            currentDepthLimit++;
        }

        return foundNode;
    }

    /**
     * Рекурсивный метод поиска с ограничением глубины
     * Проверяет текущий узел и всех его потомков до указанной глубины
     */
    private static TreeNode depthLimitedSearch(TreeNode currentNode, int targetValue, int remainingDepth) {
        // Выводим отладочную информацию о проверяемом узле
        System.out.println("  Проверка узла: " + currentNode.value +
                " (осталось глубины: " + remainingDepth + ")");

        // Проверяем, совпадает ли значение текущего узла с искомым
        if (currentNode.value == targetValue) {
            return currentNode; // Узел найден!
        }

        // Если достигли предела глубины, не углубляемся дальше
        if (remainingDepth <= 0) {
            return null;
        }

        // Рекурсивно проверяем всех дочерних узлов
        // Порядок проверки: слева направо (в порядке добавления)
        for (TreeNode childNode : currentNode.children) {
            // Вызываем поиск для дочернего узла с уменьшенной глубиной
            TreeNode result = depthLimitedSearch(childNode, targetValue, remainingDepth - 1);

            // Если нашли узел в дочерней ветке, сразу возвращаем результат
            if (result != null) {
                return result;
            }
        }

        // Если ни текущий узел, ни его потомки не содержат искомое значение
        return null;
    }

    /**
     * Вычисляет максимальную глубину дерева
     * Глубина определяется как максимальное количество ребер от корня до самого дальнего листа
     */
    private static int calculateTreeDepth(TreeNode node) {
        // Базовый случай: пустое поддерево имеет глубину 0
        if (node == null) {
            return 0;
        }

        // Если у узла нет потомков, его глубина = 0 (только сам узел)
        if (node.children.isEmpty()) {
            return 0;
        }

        // Вычисляем максимальную глубину среди всех дочерних поддеревьев
        int maxChildDepth = 0;
        for (TreeNode child : node.children) {
            int childDepth = calculateTreeDepth(child);
            if (childDepth > maxChildDepth) {
                maxChildDepth = childDepth;
            }
        }

        // Глубина текущего узла = максимальная глубина потомков + 1
        return maxChildDepth + 1;
    }


    public static void main() {
        System.out.println("_____ ДЕМОНСТРАЦИЯ IDDFS ДЛЯ ДЕРЕВА _____\n");
        Scanner sc = new Scanner(System.in);


        TreeNode root = null;
        root = CreateTree.createTreeFromKeyboard(sc);
        System.out.println("\nСтруктура созданного дерева:");
        CreateTree.printTree(root, "", true);

        System.out.print("Введите искомое значение: ");
        int number = sc.nextInt();
        TreeNode result1 = search(root, number);
        System.out.println("Результат: " +
                (result1 != null ? "Найден узел " + result1.value : "Не найден"));
        sc.close();


    }

    /**
     * Вспомогательный метод для подсчета общего количества узлов в дереве
     */
    private static int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int count = 1; // Текущий узел
        for (TreeNode child : node.children) {
            count += countNodes(child);
        }
        return count;
    }
}
