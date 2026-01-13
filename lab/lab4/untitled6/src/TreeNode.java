import java.util.*;

public class TreeNode {
    int value;
    List<TreeNode> children;

    public TreeNode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    // Создаем дерево, где явно видна разница между BFS и DFS
    public static TreeNode createTestTree() {
        /*

        Структура дерева:
                   1
               /   |   \
              2    3    4
            /  \   |   /  \
           5    6  7  8    9
          / \      |
         10 11    12
             |
            7

        */

        // Создаем узлы
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7_short = new TreeNode(7);  // Узел 7 на коротком пути
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        TreeNode node13_long = new TreeNode(7);  // Узел 7 на длинном пути

        // Строим дерево
        node1.addChild(node2);
        node1.addChild(node3);
        node1.addChild(node4);

        node2.addChild(node5);
        node2.addChild(node6);

        node3.addChild(node7_short);
        node3.addChild(node12);

        node4.addChild(node8);
        node4.addChild(node9);

        node5.addChild(node10);
        node5.addChild(node11);

        node11.addChild(node13_long);

        return node1;
    }

    // Метод для демонстрации порядка обхода BFS
    public static List<Integer> bfsTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.value);
            queue.addAll(current.children);
        }

        return result;
    }

    // Метод для демонстрации порядка обхода DFS (рекурсивный)
    public static List<Integer> dfsTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfsTraversalHelper(root, result);
        return result;
    }

    private static void dfsTraversalHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;

        result.add(node.value);
        for (TreeNode child : node.children) {
            dfsTraversalHelper(child, result);
        }
    }

    // Метод для получения всех значений в дереве
    public static List<Integer> getAllValues(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        getAllValuesHelper(root, values);
        return values;
    }

    private static void getAllValuesHelper(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.value);
        for (TreeNode child : node.children) {
            getAllValuesHelper(child, values);
        }
    }

    @Override
    public String toString() {
        return "Node(" + value + ")";
    }
}