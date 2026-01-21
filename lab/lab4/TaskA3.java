import java.util.Stack;

public class TaskA3 {

    // Реализовать поиск в дереве в глубину двумя способами

    public static void main(String[] args) {
        TreeNode tree = TreeNode.generateTestTree();

        // Проверка рекурсивного поиска в глубину для разных сценариев
        System.out.println(dfsSearchRecursive(tree, 11));
        System.out.println(dfsSearchRecursive(null, 11));
        System.out.println(dfsSearchRecursive(tree, 7));

        // Проверка итеративного поиска в глубину для разных сценариев
        System.out.println(dfsSearchIterative(tree, 11));
        System.out.println(dfsSearchIterative(null, 11));
        System.out.println(dfsSearchIterative(tree, 7));
    }

    // Классический рекурсивный поиск в глубину
    private static TreeNode dfsSearchRecursive(TreeNode root, int target) {
        if (root == null) return null;
        if (root.val == target) return root;

        TreeNode leftSearch = dfsSearchRecursive(root.left, target);
        if (leftSearch != null) {
            return leftSearch;
        } else {
            return dfsSearchRecursive(root.right, target);
        }
    }

    // Итеративный поиск в глубину с использованием стека
    private static TreeNode dfsSearchIterative(TreeNode root, int target) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) continue;
            if (node.val == target) return node;

            stack.push(node.right);
            stack.push(node.left);
        }

        return null;
    }
}
