/**
 * Реализовать поиск в дереве (Iterative deepening depth-first search)
 */
package tasks.task0;


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

    /**
     * Демонстрация работы алгоритма на примере
     */
    public static void main() {
        System.out.println("_____ ДЕМОНСТРАЦИЯ IDDFS ДЛЯ ДЕРЕВА _____\n");

        // Создаем тестовое дерево:
        // Уровень 0:         1
        //                   /|\
        // Уровень 1:       2 3 4
        //                 /| | \
        // Уровень 2:     5 6 7  8
        //               /      / \
        // Уровень 3:   9      10 11
        //             /
        // Уровень 4: 12

        TreeNode root = new TreeNode(1);

        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        root.addChildren(node2, node3, node4);

        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node2.addChildren(node5, node6);

        TreeNode node7 = new TreeNode(7);
        node3.addChild(node7);

        TreeNode node8 = new TreeNode(8);
        node4.addChild(node8);

        TreeNode node9 = new TreeNode(9);
        node5.addChild(node9);

        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        node8.addChildren(node10, node11);

        TreeNode node12 = new TreeNode(12);
        node9.addChild(node12);

        // Выводим информацию о дереве
        int maxDepth = calculateTreeDepth(root);
        System.out.println("Дерево:");
        System.out.println("Корневой узел: " + root.value);
        System.out.println("Максимальная глубина: " + maxDepth);
        System.out.println("Всего узлов: " + countNodes(root));

        System.out.print("Введите искомое значение: ");
        Scanner sc = new Scanner(System.in);
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