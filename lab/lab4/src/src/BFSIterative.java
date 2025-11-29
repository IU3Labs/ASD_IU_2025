import java.util.LinkedList;
import java.util.Queue;

public class BFSIterative {

    public static boolean bfs(TreeNode root, int target) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.val == target) return true;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        return false;
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
