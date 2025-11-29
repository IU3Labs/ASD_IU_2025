package com.iu3;

import java.util.Stack;

public class DFS {

    // Рекурсивный DFS
    public boolean searchRecursive(TreeNode node, int target) {
        if (node == null) return false;
        if (node.val == target) return true;
        return searchRecursive(node.left, target) ||
                searchRecursive(node.right, target);
    }

    // Итеративный DFS (с использованием стека)
    public boolean searchIterative(TreeNode root, int target) {
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
