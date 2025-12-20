import java.util.*;

/**
 * Задача 2 (Группа A): Итеративное углубление в глубину (IDDFS)
 * Поиск в дереве с ограничением глубины, постепенно увеличивая лимит.
 * Комбинирует преимущества DFS (мало памяти) и BFS (находит кратчайший путь).
 */
public class IterativeDeepeningDFS {
    static class TreeNode {
        int value;
        List<TreeNode> children;

        TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            this.children.add(child);
        }
    }

    static boolean iddfs(TreeNode root, int target) {
        if (root == null) return false;

        for (int depth = 0; depth <= Integer.MAX_VALUE; depth++) {
            System.out.println("Поиск с максимальной глубиной: " + depth);

            if (dfsWithDepthLimit(root, target, depth)) {
                System.out.println("Найдено значение " + target + " на глубине " + depth);
                return true;
            }
        }

        System.out.println("Значение " + target + " не найдено в дереве");
        return false;
    }

    static boolean dfsWithDepthLimit(TreeNode node, int target, int depthLimit) {
        if (node == null) return false;
        if (node.value == target) return true;
        if (depthLimit <= 0) return false;

        for (TreeNode child : node.children) {
            if (dfsWithDepthLimit(child, target, depthLimit - 1)) {
                return true;
            }
        }

        return false;
    }

    static List<Integer> iddfsWithPath(TreeNode root, int target) {
        if (root == null) return new ArrayList<>();

        for (int depth = 0; depth <= Integer.MAX_VALUE; depth++) {
            List<Integer> path = new ArrayList<>();
            if (dfsPathWithDepthLimit(root, target, depth, path)) {
                path.add(0, root.value);
                return path;
            }
        }

        return new ArrayList<>();
    }

    static boolean dfsPathWithDepthLimit(TreeNode node, int target,
                                         int depthLimit, List<Integer> path) {
        if (node == null) return false;
        if (node.value == target) {
            path.add(target);
            return true;
        }
        if (depthLimit <= 0) return false;

        for (TreeNode child : node.children) {
            if (dfsPathWithDepthLimit(child, target, depthLimit - 1, path)) {
                path.add(0, node.value);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
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


        System.out.println("=== Поиск узла со значением 8 ===");
        iddfs(root, 8);

        System.out.println("\n=== Поиск узла со значением 6 ===");
        iddfs(root, 6);

        System.out.println("\n=== Путь до узла 8 ===");
        List<Integer> path = iddfsWithPath(root, 8);
        System.out.println("Путь: " + path);
    }
}