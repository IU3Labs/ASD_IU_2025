public class BFSRecursive {
    public static boolean bfsLevel(TreeNode node, int target, int level) {
        if (node == null) return false;

        if (level == 1) {
            return node.val == target;
        } else {
            return bfsLevel(node.left, target, level - 1)
                    || bfsLevel(node.right, target, level - 1);
        }
    }

    public static boolean bfs(TreeNode root, int target) {
        int h = height(root);
        for (int level = 1; level <= h; level++) {
            if (bfsLevel(root, target, level)) {
                return true;
            }
        }
        return false;
    }

    public static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);

        System.out.println(bfs(root, 7)); // true
        System.out.println(bfs(root, 9)); // false
    }
}