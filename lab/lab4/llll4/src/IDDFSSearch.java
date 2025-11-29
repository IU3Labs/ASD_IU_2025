import java.util.*;

public class IDDFSSearch {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildTree();
        int target = 5;
        boolean found = iddfs(root, target);
        System.out.println("IDDFS — Цель " + target + " найдено?: " + found);
    }

    private static boolean iddfs(TreeNode root, int target) {
        if (root == null) return false;
        int depth = 0;
        while (true) {
            if (dfsAtDepth(root, target, depth)) {
                return true;
            }
            depth++;
        }
    }

    private static boolean dfsAtDepth(TreeNode node, int target, int limit) {
        if (node == null || limit < 0) return false;
        if (node.val == target) return true;
        return dfsAtDepth(node.left, target, limit - 1) ||
                dfsAtDepth(node.right, target, limit - 1);
    }
}