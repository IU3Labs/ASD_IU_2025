package BFS;

public class BFSRecursive {
    public static void bfs (TreeNode root) {
        int height = height(root);
        for (int level = 0; level <= height; level++) {
            printLevel(root, level);
        }
    }

    private static void printLevel(TreeNode node, int level) {
        if (node == null) return;

        if (level == 0) {
            System.out.print(node.value + " ");
        } else {
            for (TreeNode child : node.children) {
                printLevel(child, level - 1);
            }
        }
    }

    private static int height(TreeNode node) {
        if (node == null) return -1;

        int maxHeight = -1;
        for (TreeNode child : node.children) {
            maxHeight = Math.max(maxHeight, height(child));
        }
        return maxHeight + 1;
    }
}
