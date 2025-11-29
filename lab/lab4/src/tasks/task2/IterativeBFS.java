package tasks.task2;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class IterativeBFS {

    // Итеративный обход дерева в ширину с использованием очереди
    public static List<Integer> bfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Используем Deque как очередь (FIFO)
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            result.add(currentNode.value);

            // Добавляем всех детей в конец очереди
            for (TreeNode child : currentNode.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    // Итеративный поиск узла по значению
    public static TreeNode search(TreeNode root, int target) {
        if (root == null) return null;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            if (currentNode.value == target) {
                return currentNode;
            }

            for (TreeNode child : currentNode.children) {
                queue.offer(child);
            }
        }

        return null;
    }
}