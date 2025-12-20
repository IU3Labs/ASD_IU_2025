package tasks.task1;
import java.util.ArrayDeque;
import java.util.Deque;

public class IterativeDFS {

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