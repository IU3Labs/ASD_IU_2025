// 2. Реализовать поиск в дереве (Iterative deepening depth-first search)

package com.iu3;

public class IDDFS {

    public boolean search(TreeNode root, int target) {
        if (root == null) return false;

        int maxDepth = getHeight(root); // максимальная глубина дерева
        for (int depth = 0; depth <= maxDepth; depth++) {
            if (dfs(root, target, depth)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(TreeNode node, int target, int depth) {
        if (node == null) return false;
        if (node.val == target) return true;
        if (depth == 0) return false; // достигли лимита глубины

        return dfs(node.left, target, depth - 1) ||
                dfs(node.right, target, depth - 1);
    }

    private int getHeight(TreeNode node) {
        if (node == null) return -1; // чтобы лист имел глубину 0
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}
