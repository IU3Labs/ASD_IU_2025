/*Группа A, задание 3: Реализовать поиск в дереве в глубину двумя способами.*/

import java.util.*;

public class DepthFirstSearch {

    static class TreeNode {
        int value;
        List<TreeNode> children;

        public TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public void addChild(TreeNode child) {
            children.add(child);
        }
    }

    public static void dfsRecursive(TreeNode root) {
        if (root == null) return;

        Set<TreeNode> visited = new HashSet<>();
        System.out.print("Рекурсивный DFS: ");
        dfsRecursiveHelper(root, visited);
        System.out.println();
    }

    private static void dfsRecursiveHelper(TreeNode node, Set<TreeNode> visited) {
        if (node == null || visited.contains(node)) return;

        visited.add(node);
        System.out.print(node.value + " ");

        for (TreeNode child : node.children) {
            dfsRecursiveHelper(child, visited);
        }
    }

    public static void dfsIterative(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();

        stack.push(root);
        visited.add(root);

        System.out.print("Итеративный DFS: ");

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.value + " ");

            for (int i = current.children.size() - 1; i >= 0; i--) {
                TreeNode child = current.children.get(i);
                if (!visited.contains(child)) {
                    stack.push(child);
                    visited.add(child);
                }
            }
        }
        System.out.println();
    }

    public static TreeNode createTestTree() {
        TreeNode root = new TreeNode(1);

        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        root.addChild(node2);
        root.addChild(node3);
        root.addChild(node4);

        node2.addChild(node5);
        node2.addChild(node6);

        node3.addChild(node7);
        node4.addChild(node8);

        return root;
    }

    public static void testDFS() {

        TreeNode testTree = createTestTree();

        dfsRecursive(testTree);
        dfsIterative(testTree);
    }

    public static void main(String[] args) {
        testDFS();
    }
}