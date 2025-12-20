package tasks.task2;
import java.util.ArrayDeque;
import java.util.Deque;

public class IterativeBFS {

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