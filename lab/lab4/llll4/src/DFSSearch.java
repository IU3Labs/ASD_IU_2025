import java.util.*;

public class DFSSearch {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildTree();
        int target = 5;
        System.out.println("Рекурсивный DFS, найдено?: " + dfsRecursive(root, target));
        System.out.println("Итеративный DFS, найдено?: " + dfsIterative(root, target));
    }

    private static boolean dfsRecursive(TreeNode node, int target) {
        if (node == null) return false;
        if (node.val == target) return true;
        return dfsRecursive(node.left, target) || dfsRecursive(node.right, target);
    }

    private static boolean dfsIterative(TreeNode root, int target) {
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
}