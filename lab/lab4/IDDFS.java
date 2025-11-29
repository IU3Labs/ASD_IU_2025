import java.util.*;

public class IDDFS {

    public static class TreeNode {
        private final int value;
        private final List<TreeNode> neighbors;

        public TreeNode(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public int getValue() {
            return value;
        }

        public List<TreeNode> getNeighbors() {
            return neighbors;
        }

        public void addNeighbor(TreeNode neighbor) {
            neighbors.add(neighbor);
        }
    }

    /*
     * Поиск с итеративным углублением (IDDFS)
     */
    public List<Integer> iddfs(TreeNode root, int targetValue) {
        if (root == null) {
            return new ArrayList<>();
        }

        // Постепенно увеличиваем глубину поиска
        int depth = 0;
        while (true) {
            List<Integer> result = new ArrayList<>();
            boolean found = depthLimitedSearch(root, targetValue, depth, result);

            if (found) {
                return result;
            }

            // Если не нашли на этой глубине, увеличиваем глубину
            depth++;

            // Защита от бесконечного цикла для деревьев без целевого узла
            if (depth > 10) {
                return new ArrayList<>();
            }
        }
    }

    /*
     * Ограниченный поиск в глубину (DLS)
     */
    private boolean depthLimitedSearch(TreeNode node, int targetValue, int depth, List<Integer> path) {
        if (node == null) {
            return false;
        }

        // Добавляем текущий узел в путь
        path.add(node.getValue());

        if (node.getValue() == targetValue) {
            return true;
        }

        if (depth <= 0) {
            path.remove(path.size() - 1);
            return false;
        }

        // Рекурсивно ищем в соседях с уменьшенной глубиной
        for (TreeNode neighbor : node.getNeighbors()) {
            if (depthLimitedSearch(neighbor, targetValue, depth - 1, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public List<Integer> getAllValues(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) {
            return values;
        }
        getAllValuesHelper(root, values);
        return values;
    }

    private void getAllValuesHelper(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        values.add(node.getValue());
        for (TreeNode neighbor : node.getNeighbors()) {
            getAllValuesHelper(neighbor, values);
        }
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

        root.addNeighbor(node2);
        root.addNeighbor(node3);
        root.addNeighbor(node4);

        node2.addNeighbor(node5);
        node2.addNeighbor(node6);

        node4.addNeighbor(node7);
        node4.addNeighbor(node8);

        return root;
    }
}

class IDDFSDemo {

    public static void main(String[] args) {
        IDDFS treeIDDFS = new IDDFS();
        IDDFS.TreeNode root = IDDFS.createTestTree();
        Scanner scanner = new Scanner(System.in);

        List<Integer> availableValues = treeIDDFS.getAllValues(root);
        System.out.println("\nДоступные узлы для поиска: " + availableValues);

        System.out.print("\nВведите значение узла для поиска: ");
        int targetValue = scanner.nextInt();

        if (!availableValues.contains(targetValue)) {
            System.out.println("Узел со значением " + targetValue + " не найден в дереве!");
            return;
        }

        System.out.println("\n Поиск узла " + targetValue + " с итеративным углублением");

        List<Integer> iddfsResult = treeIDDFS.iddfs(root, targetValue);
        if (!iddfsResult.isEmpty()) {
            System.out.println("Путь до узла " + targetValue + ": " + iddfsResult);
            System.out.println("Длина пути: " + iddfsResult.size());
        } else {
            System.out.println("Узел " + targetValue + " не найден");
        }

        scanner.close();
    }
}