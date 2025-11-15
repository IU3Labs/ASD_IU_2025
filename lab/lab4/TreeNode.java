import java.util.*;

public class TreeNode {
    static Scanner scanner = new Scanner(System.in);

    int value;
    TreeNode left; // левый потомок
    TreeNode right; // правый потомок

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public static TreeNode inputTree() {
        System.out.println("Введите значение корневого узла:");
        int rootValue = scanner.nextInt();
        TreeNode root = new TreeNode(rootValue);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            System.out.println("Введите левого потомка для узла " + current.value + " (или -1 если потомка нет):");
            int leftValue = scanner.nextInt();
            if (leftValue != -1) {
                current.left = new TreeNode(leftValue);
                queue.offer(current.left);
            }

            System.out.println("Введите правого потомка для узла " + current.value + " (или -1 если потомка нет):");
            int rightValue = scanner.nextInt();
            if (rightValue != -1) {
                current.right = new TreeNode(rightValue);
                queue.offer(current.right);
            }
        }

        return root;
    }
}
