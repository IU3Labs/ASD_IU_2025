public class TaskA2 {
    // Реализовать поиск в дереве (Iterative deepening depth-first search)

    public static void main(String[] args) {
        TreeNode tree = TreeNode.generateTestTree();
        // Возвращается null, так как глубины поиска не хватает
        System.out.println(iterativeDeepeningDfsSearch(tree, 9, 3));
        // Возвращается нода с искомым значением
        System.out.println(iterativeDeepeningDfsSearch(tree, 9, 4));
    }

    // IDDFS с ограниченной глубиной поиска
    private static TreeNode iterativeDeepeningDfsSearch(TreeNode root, int target, int depthLimit) {
        for (int i = 1; i <= depthLimit; i++) {
            TreeNode node = depthLimitedDfsSearch(root, target, i);
            if (node != null) return node;
        }

        return null;
    }

    // Рекурсивный поиск в глубину с ограничением
    private static TreeNode depthLimitedDfsSearch(TreeNode root, int target, int remainingDepth) {
        if (root == null || remainingDepth < 1) return null;
        if (root.val == target) return root;

        TreeNode leftSearch = depthLimitedDfsSearch(root.left, target, remainingDepth - 1);
        if (leftSearch != null) {
            return leftSearch;
        } else {
            return depthLimitedDfsSearch(root.right, target, remainingDepth - 1);
        }
    }
}

