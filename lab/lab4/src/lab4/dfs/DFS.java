package lab4.dfs;

import java.util.Stack;

public class DFS {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static TreeNode dfsRecursiveSearch(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        if (node.value == target) {
            return node;
        }
        TreeNode left = dfsRecursiveSearch(node.left, target);
        TreeNode right = dfsRecursiveSearch(node.right, target);
        return (left != null) ? left : right;
    }

    public static TreeNode dfsIterativeSearch(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.value == target) {
                return node;
            }

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Рекурсивный поиск");
        System.out.print("Поиск по значению 5: ");
        TreeNode result = dfsRecursiveSearch(root, 5);
        if (result == null){
            System.out.println("null");
        } else{
            System.out.println(result.value);
        }

        System.out.print("Поиск по значению 10: ");
        result = dfsRecursiveSearch(root, 10);
        if (result == null){
            System.out.println("null");
        } else{
            System.out.println(result.value);
        }

        System.out.println("Поиск итерациями");
        System.out.print("Поиск по значению 4: ");
        result = dfsIterativeSearch(root, 4);
        if (result == null){
            System.out.println("null");
        } else{
            System.out.println(result.value);
        }

        System.out.print("Поиск по значению 6: ");
        result = dfsIterativeSearch(root, 6);
        if (result == null){
            System.out.println("null");
        } else{
            System.out.println(result.value);
        }
    }
}
