package lab4;

import java.util.ArrayDeque;

/**
 * 3. Реализовать поиск в дереве в глубину двумя способами.
 **/

public class DFS {

    // 1. Рекурсивный поиск в глубину
    public static TreeNode recursiveDFS(TreeNode root, int target) {
        if (root == null) return null;
        if (root.value == target) {
            return root;
        }
        TreeNode leftResult = recursiveDFS(root.left, target);
        if (leftResult != null) {
            return leftResult;
        }

        return recursiveDFS(root.right, target);
    }
    // Итеративный поиск в глубину
    public static TreeNode iterationDFS(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.value == target) {
                return node;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return null;
    }
}