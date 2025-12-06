import java.util.*;

public class IDDFSSearch {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildTree();
        int target = 6;
        boolean found = iddfs(root, target);
        System.out.println("IDDFS — Цель " + target + " найдено?: " + found);
    }

    private static boolean iddfs(TreeNode root, int target) {
        if (root == null) return false;
        int maxDepth = calculateMaxDepth(root);
        int depth = 0;
        while (depth <= maxDepth) {
            if (dfsAtDepth(root, target, depth)) {
                return true;
            }
            depth++;
        }
        return false;
    }

    private static boolean dfsAtDepth(TreeNode node, int target, int limit) {
        if (node == null || limit < 0) return false;
        if (node.val == target) return true;
        return dfsAtDepth(node.left, target, limit - 1) ||
                dfsAtDepth(node.right, target, limit - 1);
    }

    private static int calculateMaxDepth(TreeNode node){
        if (node == null) return 0;
        return 1 + Math.max(calculateMaxDepth(node.right), calculateMaxDepth(node.left));
    }
}