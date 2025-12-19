package BFS;

public class Task3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        root.addChild(n2);
        root.addChild(n3);
        n2.addChild(n5);
        n2.addChild(n4);

        System.out.println("BFS через очередь:");
        BFSQueue.bfs(root);

        System.out.println("\nBFS рекурсивно по уровням:");
        BFSRecursive.bfs(root);
    }
}
