package tasks.task2;
import java.util.ArrayList;
import java.util.List;

public class RecursiveBFS {

    // Рекурсивный обход дерева в ширину
    public static List<Integer> bfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Создаем список для текущего уровня
        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        // Запускаем рекурсивный обход по уровням
        bfsHelper(currentLevel, result);

        return result;
    }

    // Вспомогательный рекурсивный метод для обхода по уровням
    private static void bfsHelper(List<TreeNode> levelNodes, List<Integer> result) {
        // Базовый случай: нет узлов на текущем уровне
        if (levelNodes.isEmpty()) return;

        // Собираем узлы следующего уровня
        List<TreeNode> nextLevel = new ArrayList<>();

        // Обрабатываем все узлы текущего уровня
        for (TreeNode node : levelNodes) {
            result.add(node.value);

            // Добавляем детей для следующего уровня
            nextLevel.addAll(node.children);
        }

        // Рекурсивно обрабатываем следующий уровень
        bfsHelper(nextLevel, result);
    }

    // Рекурсивный поиск узла по значению
    public static TreeNode search(TreeNode root, int target) {
        if (root == null) return null;

        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        return searchHelper(currentLevel, target);
    }

    private static TreeNode searchHelper(List<TreeNode> levelNodes, int target) {
        if (levelNodes.isEmpty()) return null;

        List<TreeNode> nextLevel = new ArrayList<>();

        for (TreeNode node : levelNodes) {
            // Проверяем текущий узел
            if (node.value == target) {
                return node;
            }

            // Собираем детей для следующего уровня
            nextLevel.addAll(node.children);
        }

        // Рекурсивно ищем на следующем уровне
        return searchHelper(nextLevel, target);
    }
}