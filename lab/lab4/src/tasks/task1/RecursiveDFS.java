package tasks.task1;
import java.util.ArrayList;
import java.util.List;

public class RecursiveDFS {

    //Рекурсивный обход дерева в глубину
    public static List<Integer> dfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfsHelper(root, result);
        return result;
    }

    //Вспомогательный рекурсивный метод для обхода дерева
    private static void dfsHelper(TreeNode node, List<Integer> result) {
        // Базовый случай: достигнут конец ветки
        if (node == null) return;

        // посещаем узел до рекурсивных вызовов
        result.add(node.value);

        // Рекурсивно обходим всех детей слева направо
        for (TreeNode child : node.children) {
            dfsHelper(child, result);
        }
    }

    //Рекурсивный поиск узла по значению
    public static TreeNode search(TreeNode root, int target) {
        // Базовый случай: узел не существует
        if (root == null) return null;

        // Если нашли нужный узел, возвращаем его
        if (root.value == target) return root;

        // Рекурсивно ищем в каждом дочернем узле
        for (TreeNode child : root.children) {
            TreeNode result = search(child, target);
            // Если нашли в одном из поддеревьев, возвращаем результат
            if (result != null) return result;
        }

        // Не нашли в этом поддереве
        return null;
    }
}