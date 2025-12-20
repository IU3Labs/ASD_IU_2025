import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class DeepSearch {

    TreeNode searchRecursive(TreeNode root, int target) {
        if (root == null || root.val == target) {
            return root;
        }
        if (target < root.val) {
            return searchRecursive(root.left, target);
        }
        return searchRecursive(root.right, target);
    }

    TreeNode searchIterative(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == target) {
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


    TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        DeepSearch tree = new DeepSearch();
        TreeNode root = null;

        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            root = tree.insert(root, val);
        }

        System.out.println("Recursive search for 40: " +
                (tree.searchRecursive(root, 40) != null));
        System.out.println("Iterative search for 40: " +
                (tree.searchIterative(root, 40) != null));
        System.out.println("Recursive search for 90: " +
                (tree.searchRecursive(root, 90) != null));
        System.out.println("Iterative search for 90: " +
                (tree.searchIterative(root, 90) != null));
    }
}

