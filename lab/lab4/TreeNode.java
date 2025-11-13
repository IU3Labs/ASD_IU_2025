public class TreeNode {
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
}
