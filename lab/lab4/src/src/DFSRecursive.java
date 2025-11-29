public class DFSRecursive {
    public static boolean dfs(TreeNode root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        return dfs(root.left, target) || dfs(root.right, target); // рекурсия на обоих поддеревах
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
