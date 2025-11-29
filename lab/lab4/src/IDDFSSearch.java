import java.util.*;

class TreeNode {
    int value;
    List<TreeNode> children;

    public TreeNode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return "Node(" + value + ")";
    }
}

public class IDDFSSearch {

    /**
     * Поиск в глубину с ограничением глубины (Depth-Limited Search)
     *
     * @param node текущий узел
     * @param target цель поиска
     * @param depth текущая глубина
     * @param maxDepth максимальная глубина поиска
     * @return найденный узел или null, если не найден
     */
    private static TreeNode depthLimitedSearch(TreeNode node, int target, int depth, int maxDepth) {
        System.out.println("Посещаем узел: " + node + " на глубине: " + depth);


        if (node.value == target) {
            return node;
        }


        if (depth >= maxDepth) {
            return null;
        }


        for (TreeNode child : node.children) {
            TreeNode result = depthLimitedSearch(child, target, depth + 1, maxDepth);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * Iterative Deepening Depth-First Search
     *
     * @param root корневой узел дерева
     * @param target цель поиска
     * @param maxDepth максимальная глубина поиска
     * @return найденный узел или null, если не найден
     */
    public static TreeNode iterativeDeepeningDFS(TreeNode root, int target, int maxDepth) {
        // Постепенно увеличиваем глубину поиска
        for (int depth = 0; depth <= maxDepth; depth++) {
            System.out.println("\n=== Поиск на глубине: " + depth + " ===");
            TreeNode result = depthLimitedSearch(root, target, 0, depth);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /**
     * Вспомогательный метод для создания демонстрационного дерева
     */
    public static TreeNode createDemoTree() {
        // Создаем дерево:
        //       1
        //     / | \
        //    2  3  4
        //   /|  |  |\
        //  5 6  7  8 9
        //    |     |
        //    10    11

        TreeNode root = new TreeNode(1);

        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);

        root.addChild(node2);
        root.addChild(node3);
        root.addChild(node4);

        node2.addChild(node5);
        node2.addChild(node6);
        node3.addChild(node7);
        node4.addChild(node8);
        node4.addChild(node9);

        node6.addChild(node10);
        node8.addChild(node11);

        return root;
    }

    public static void main(String[] args) {

        TreeNode root = createDemoTree();


        int target = 11;
        int maxDepth = 4;

        System.out.println("Поиск значения " + target + " в дереве с максимальной глубиной " + maxDepth);
        System.out.println("Структура дерева:");
        System.out.println("       1");
        System.out.println("     / | \\");
        System.out.println("    2  3  4");
        System.out.println("   /|  |  |\\");
        System.out.println("  5 6  7  8 9");
        System.out.println("    |     |");
        System.out.println("    10    11");


        TreeNode result = iterativeDeepeningDFS(root, target, maxDepth);


        if (result != null) {
            System.out.println("\n✓ Узел со значением " + target + " найден: " + result);
        } else {
            System.out.println("\n✗ Узел со значением " + target + " не найден в пределах глубины " + maxDepth);
        }


        System.out.println("\n\n--- Поиск несуществующего значения ---");
        int nonExistentTarget = 99;
        TreeNode nonExistentResult = iterativeDeepeningDFS(root, nonExistentTarget, maxDepth);

        if (nonExistentResult != null) {
            System.out.println("Узел со значением " + nonExistentTarget + " найден: " + nonExistentResult);
        } else {
            System.out.println("Узел со значением " + nonExistentTarget + " не найден в пределах глубины " + maxDepth);
        }
    }
}