package com.iu3;

import java.util.*;

public class BFS {

    // Итеративный BFS (стандартный)
    public boolean searchIterative(TreeNode root, int target) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == target) return true;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }

    // Рекурсивный BFS (по уровням)
    public boolean searchRecursive(TreeNode root, int target) {
        if (root == null) return false;
        List<TreeNode> currentLevel = List.of(root);
        return bfsRecursive(currentLevel, target);
    }

    private boolean bfsRecursive(List<TreeNode> level, int target) {
        if (level.isEmpty()) return false;

        List<TreeNode> nextLevel = new ArrayList<>();
        for (TreeNode node : level) {
            if (node.val == target) return true;
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }
        return bfsRecursive(nextLevel, target);
    }
}
