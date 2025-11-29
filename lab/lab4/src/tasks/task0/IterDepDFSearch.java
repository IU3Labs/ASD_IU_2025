/**
 * Реализовать поиск в дереве (Iterative deepening depth-first search)
 */
package tasks.task0;

class TreeNode {
    int data;          // Значение, хранящееся в узле
    TreeNode leftChild;  // Ссылка на левого потомка
    TreeNode rightChild; // Ссылка на правого потомка


    TreeNode(int data) {
        this.data = data;
        // leftChild и rightChild автоматически инициализируются как null
    }
}

public class IterDepDFSearch {

    public static TreeNode findNodeUsingIDDFS(TreeNode startNode, int searchValue) {
        int currentMaxLevel = 0; // Текущая максимальная глубина поиска
        TreeNode foundNode = null; // Переменная для хранения результата

        // Бесконечный цикл, который прервется когда найден узел или достигнута максимальная глубина
        while (foundNode == null) {
            // Выполняем поиск с ограничением по глубине
            foundNode = searchWithDepthLimit(startNode, searchValue, currentMaxLevel);

            // Проверяем, не превысили ли мы максимальную глубину дерева
            // Если да, значит весь дерево проверено и узел не найден
            if (currentMaxLevel > calculateMaxDepth(startNode)) {
                break;
            }

            System.out.println("Поиск на глубине " + currentMaxLevel + " завершен");
            currentMaxLevel++; // Увеличиваем глубину для следующей итерации
        }

        return foundNode;
    }

    private static TreeNode searchWithDepthLimit(TreeNode currentNode, int searchValue, int remainingDepth) {
        // Базовый случай: достигнут нулевой узел (конец ветки)
        if (currentNode == null) {
            return null;
        }

        System.out.println("Проверяем узел: " + currentNode.data + " на глубине " + remainingDepth);

        // Если нашли узел с искомым значением, возвращаем его
        if (currentNode.data == searchValue) {
            return currentNode;
        }

        // Если достигли предела глубины, прекращаем углубляться
        if (remainingDepth <= 0) {
            return null;
        }

        // Рекурсивно ищем в левом поддереве с уменьшенной глубиной
        TreeNode leftSearchResult = searchWithDepthLimit(currentNode.leftChild, searchValue, remainingDepth - 1);

        // Если нашли в левом поддереве, сразу возвращаем результат
        // Это обеспечивает поиск в глубину
        if (leftSearchResult != null) {
            return leftSearchResult;
        }

        // Если в левом поддереве не нашли, ищем в правом
        return searchWithDepthLimit(currentNode.rightChild, searchValue, remainingDepth - 1);
    }

    private static int calculateMaxDepth(TreeNode node) {
        // Базовый случай: пустое дерево имеет глубину 0
        if (node == null) {
            return 0;
        }

        // Рекурсивно вычисляем глубину левого и правого поддеревьев
        int leftSubtreeDepth = calculateMaxDepth(node.leftChild);
        int rightSubtreeDepth = calculateMaxDepth(node.rightChild);

        // Глубина текущего дерева = максимальная глубина поддеревьев + 1 (текущий узел)
        return Math.max(leftSubtreeDepth, rightSubtreeDepth) + 1;
    }

    static void main() {
        // Создаем тестовое дерево:
        //       10
        //      /  \
        //     20   30
        //    / \     \
        //   40  50    60
        TreeNode rootNode = new TreeNode(10);
        rootNode.leftChild = new TreeNode(20);
        rootNode.rightChild = new TreeNode(30);
        rootNode.leftChild.leftChild = new TreeNode(40);
        rootNode.leftChild.rightChild = new TreeNode(50);
        rootNode.rightChild.rightChild = new TreeNode(60);

        int valueToFind = 50;
        TreeNode resultNode = findNodeUsingIDDFS(rootNode, valueToFind);

        if (resultNode != null) {
            System.out.println("✓ Найден узел: " + resultNode.data);
        } else {
            System.out.println("✗ Узел со значением " + valueToFind + " не обнаружен");
        }

        System.out.println("\n--- Поиск несуществующего значения ---");
        TreeNode missingResult = findNodeUsingIDDFS(rootNode, 99);
        if (missingResult == null) {
            System.out.println("✗ Ожидаемо: узел 99 не найден");
        }
    }
}