import java.util.Scanner;
import java.util.HashSet;

public class IterativeDeepeningSearch {
    public static boolean findWithIterativeDeepening(Tree.TreeNode root, int targetValue) {
        if (root == null) {
            return false;
        }

        int currentDepth = 0;
        final int MAX_DEPTH = 100; // Защита от бесконечного цикла

        // Храним посещенные узлы для каждой итерации глубины
        while (currentDepth <= MAX_DEPTH) {
            System.out.println("\nПоиск на глубине: " + currentDepth);

            // Создаем новый set для отслеживания посещенных узлов на этой итерации
            HashSet<Tree.TreeNode> visited = new HashSet<>();
            boolean found = depthLimitedSearch(root, targetValue, currentDepth, visited);

            if (found) {
                System.out.println("Элемент " + targetValue + " найден на глубине " + currentDepth);
                return true;
            }
            currentDepth++;
        }

        System.out.println("Элемент " + targetValue + " не найден в пределах максимальной глубины " + MAX_DEPTH);
        return false;
    }

    private static boolean depthLimitedSearch(Tree.TreeNode currentNode,
                                              int targetValue,
                                              int remainingDepth,
                                              HashSet<Tree.TreeNode> visited) {
        // Проверяем базовые случаи
        if (currentNode == null) {
            return false;
        }

        // Проверяем, не посещали ли мы уже этот узел на текущей итерации
        if (visited.contains(currentNode)) {
            System.out.println("  Обнаружен цикл! Пропускаем узел: " + currentNode.value);
            return false;
        }

        // Добавляем текущий узел в посещенные
        visited.add(currentNode);

        System.out.println("  Проверка узла: " + currentNode.value);

        // Проверяем, не нашли ли целевое значение
        if (currentNode.value == targetValue) {
            return true;
        }

        // Если достигли максимальной глубины - прекращаем углубляться
        if (remainingDepth == 0) {
            return false;
        }

        // Рекурсивно ищем в левом и правом поддеревьях
        boolean foundInLeft = false;
        boolean foundInRight = false;

        // Проверяем левого потомка
        if (currentNode.leftChild != null) {
            foundInLeft = depthLimitedSearch(currentNode.leftChild,
                    targetValue,
                    remainingDepth - 1,
                    new HashSet<>(visited)); // Передаем копию для левой ветви
        }

        // Если уже нашли в левой ветви, не ищем в правой
        if (foundInLeft) {
            return true;
        }

        // Проверяем правого потомка
        if (currentNode.rightChild != null) {
            foundInRight = depthLimitedSearch(currentNode.rightChild,
                    targetValue,
                    remainingDepth - 1,
                    new HashSet<>(visited)); // Передаем копию для правой ветви
        }

        return foundInRight;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Построение дерева из пользовательского ввода
        Tree tree = TreeHelper.buildTreeFromUserInput(scanner);

        // Отображение структуры дерева
        TreeHelper.displayTreeStructure(tree.getRoot());

        // Запрос целевого значения для поиска
        int target = TreeHelper.getSearchValue(scanner);

        // Выполнение поиска
        boolean found = findWithIterativeDeepening(tree.getRoot(), target);

        if (!found) {
            System.out.println("Элемент не найден");
        }

        scanner.close();
    }
}
