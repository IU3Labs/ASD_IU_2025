package tasks.task1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class IterativeDFS {

    //Итеративный обход дерева в глубину с использованием стека
    public static List<Integer> dfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // Обрабатываем случай пустого дерева
        if (root == null) return result;

        // Используем Deque как стек (LIFO)
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        // Пока стек не пуст, продолжаем обход
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            result.add(currentNode.value);

            // Добавляем детей в стек в обратном порядке
            // чтобы сохранить порядок слева направо при обходе
            for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                stack.push(currentNode.children.get(i));
            }
        }

        return result;
    }

    // Итеративный поиск узла по значению
    public static TreeNode search(TreeNode root, int target) {
        if (root == null) return null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();

            // Проверяем текущий узел
            if (currentNode.value == target) {
                return currentNode;
            }

            // Добавляем детей в стек для дальнейшего поиска
            for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                stack.push(currentNode.children.get(i));
            }
        }

        // Узел не найден
        return null;
    }
}