import java.util.ArrayDeque;

/**
 * 4. Реализовать поиск в дереве в ширину двумя способами.
 **/

public class TreeBFS {
    public static TreeNode iterationSearch(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            if (node.value == target) {
                return node;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return null;
    }

    public static TreeNode recursiveSearch(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        return recursiveHelper(queue, target);
    }

    private static TreeNode recursiveHelper(ArrayDeque<TreeNode> queue, int target) {
        if (queue.isEmpty()) return null;
        TreeNode node = queue.poll();
        if (node.value == target) {
            return node;
        }
        if (node.left != null) {
            queue.offer(node.left);
        }
        if (node.right != null) {
            queue.offer(node.right);
        }
        return recursiveHelper(queue, target);
    }
}