import java.util.ArrayDeque;

public class TaskA4 {

    // Реализовать поиск в дереве в ширину двумя способами

    public static void main(String[] args) {
        TreeNode tree = TreeNode.generateTestTree();

        // Проверка итеративного поиска в ширину для разных сценариев
        System.out.println(bfsSearchIterative(tree, 11));
        System.out.println(bfsSearchIterative(null, 11));
        System.out.println(bfsSearchIterative(tree, 7));

        // Проверка рекурсивного поиска в ширину для разных сценариев
        System.out.println(bfsSearchRecursive(tree, 11));
        System.out.println(bfsSearchRecursive(null, 11));
        System.out.println(bfsSearchRecursive(tree, 7));
    }

    // Классический поиск в ширину с использованием очереди
    private static TreeNode bfsSearchIterative(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            if (node.val == target) return node;

            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);
        }

        return null;
    }

    // Поиск в ширину с рекурсивной обработкой уровней
    private static TreeNode bfsSearchRecursive(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        return bfsSearchLevel(queue, target);
    }

    // Рекурсивная обработка уровня
    private static TreeNode bfsSearchLevel(ArrayDeque<TreeNode> level, int target) {
        int size = level.size();
        if (size == 0) return null;

        for (int i = 0; i < size; i++) {
            TreeNode node = level.pop();
            if (node.val == target) return node;

            if (node.left != null) level.addLast(node.left);
            if (node.right != null) level.addLast(node.right);
        }

        return bfsSearchLevel(level, target);
    }
}
