package BFS;
import java.util.*;


public class BFSQueue {
    public static void bfs (TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.value + " ");

            for (TreeNode child : current.children) {
                queue.add(child);
            }
        }
    }
}
