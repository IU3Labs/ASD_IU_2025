import java.util.Stack;

public class DFSIterative {

    public static boolean dfs(TreeNode root, int target) {
        if (root == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.val == target) return true;

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);

        System.out.println(dfs(root, 7)); // true
        System.out.println(dfs(root, 9)); // false
    }
}
