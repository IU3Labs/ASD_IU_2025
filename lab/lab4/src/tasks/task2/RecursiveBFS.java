package tasks.task2;
import java.util.ArrayList;
import java.util.List;

public class RecursiveBFS {

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