/*3 Реализовать поиск в дереве в глубину двумя способами.*/


import java.util.*;

public class TreeDFS {

    private static class TreeNode {
        int val;
        List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        public void addChild(TreeNode child) {
            this.children.add(child);
        }
    }

    // Рекурсивный DFS (pre-order)
    public static void dfsRecursive(TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустое");
            return;
        }

        System.out.print(root.val + " ");

        for (TreeNode child : root.children) {
            dfsRecursive(child);
        }
    }

    // Рекурсивный DFS с отображением глубины
    public static void dfsRecursiveWithDepth(TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустое");
            return;
        }


        dfsRecursiveWithDepthHelper(root, 0);
        System.out.println();
    }

    private static void dfsRecursiveWithDepthHelper(TreeNode node, int depth) {
        if (node == null) return;

        System.out.println("  ".repeat(depth) + " " + node.val + " (глубина: " + depth + ")");

        for (TreeNode child : node.children) {
            dfsRecursiveWithDepthHelper(child, depth + 1);
        }
    }

    // Итеративный DFS с использованием стека
    public static void dfsIterative(TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустое");
            return;
        }


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
        System.out.println();
    }

    // Итеративный DFS с отображением процесса
    public static void dfsIterativeWithProcess(TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустое");
            return;
        }


        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int step = 1;

        while (!stack.isEmpty()) {
            System.out.print("Шаг " + step + ":");
            for (TreeNode node : stack) {
                System.out.print(node.val + " ");
            }

            TreeNode current = stack.pop();
            System.out.println("извлекаем " + current.val);

            // Добавляем детей в обратном порядке
            for (int i = current.children.size() - 1; i >= 0; i--) {
                stack.push(current.children.get(i));
            }
            step++;
        }
    }

    // DFS с поиском целевого значения
    public static boolean dfsSearch(TreeNode root, int target) {
        if (root == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        System.out.print("Поиск " + target + ": ");

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.val + " ");

            if (current.val == target) {
                System.out.println("НАЙДЕНН");
                return true;
            }

            for (int i = current.children.size() - 1; i >= 0; i--) {
                stack.push(current.children.get(i));
            }
        }

        System.out.println("НЕ НАЙДЕНО");
        return false;
    }

    public static void main(String[] args) {
        // Создаем дерево
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
        node2.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);
        node3.addChild(node7);
        node4.addChild(node8);




        dfsRecursive(root);
        System.out.println();

;
        dfsRecursiveWithDepth(root);

        dfsIterative(root);


        dfsIterativeWithProcess(root);


        dfsSearch(root, 5);
        dfsSearch(root, 9);

        // Тест с пустым деревом

        System.out.println("ТЕСТ С ПУСТЫМ ДРЕВОМ");

        dfsRecursive(null);

        // Тест с одним узлом

        System.out.println("ТЕСТ С ОДНИМ УЗЛОМ");

        TreeNode singleNode = new TreeNode(100);
        dfsIterative(singleNode);
    }
}
