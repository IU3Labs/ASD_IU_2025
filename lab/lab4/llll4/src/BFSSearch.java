import java.util.*;

public class BFSSearch {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildTree();
        int target = 5;
        System.out.println("BFS по очереди, найдено?: " + bfsQueue(root, target));
        System.out.println("BFS по уровням, найдено?: " + bfsLevel(root, target));
    }

    private static boolean bfsQueue(TreeNode root, int target) {
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

    private static boolean bfsLevel(TreeNode root, int target) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.val == target) return true;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return false;
    }
}