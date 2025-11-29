/*3 Реализовать поиск в дереве в глубину двумя способами.*/


import java.util.Stack;

public class TreeDFS {

    // Рекурсивный DFS
    public static void dfsRecursive(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + " ");

        for (TreeNode child : root.children) {
            dfsRecursive(child);
        }
    }

    // Итеративный DFS с использованием стека
    public static void dfsIterative(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.val + " ");

            // Добавляем детей в обратном порядке для сохранения порядка обхода
            for (int i = current.children.size() - 1; i >= 0; i--) {
                stack.push(current.children.get(i));
            }
        }
    }

    public static void main(String[] args) {
        // Создаем дерево
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);

        System.out.println("Рекурсивный DFS:");
        dfsRecursive(root);
        System.out.println();

        System.out.println("Итеративный DFS:");
        dfsIterative(root);
        System.out.println();
    }
}

