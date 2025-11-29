import java.util.ArrayDeque;


public class TreeDFS {
    public static TreeNode recursiveSearch(TreeNode root, int target) {
        if (root == null) return null;
        if (root.value == target) {
            return root;
        }
        TreeNode leftResult = recursiveSearch(root.left, target);
        if (leftResult != null) {
            return leftResult;
        }

        return recursiveSearch(root.right, target);
    }

    public static TreeNode iterationSearch(TreeNode root, int target) {
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