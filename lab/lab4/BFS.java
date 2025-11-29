import java.util.*;

public class BFS {

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
     *  Классический BFS с очередью, когда находимся на узле ставим соседей в конце этой очереди и тд
     */
    public List<Integer> bfsClassic(TreeNode root, int targetValue) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        queue.offer(root);
        pathQueue.offer(new ArrayList<>(Arrays.asList(root.getValue())));

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            List<Integer> currentPath = pathQueue.poll();

            if (current.getValue() == targetValue) {
                return currentPath;
            }

            for (TreeNode neighbor : current.getNeighbors()) {
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbor.getValue());
                queue.offer(neighbor);
                pathQueue.offer(newPath);
            }
        }

        return new ArrayList<>();
    }

    /*
     * BFS по уровням
     */
    public List<Integer> bfsWithLevels(TreeNode root, int targetValue) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        queue.offer(root);
        pathQueue.offer(new ArrayList<>(Arrays.asList(root.getValue())));

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                List<Integer> currentPath = pathQueue.poll();

                if (current.getValue() == targetValue) {
                    return currentPath;
                }

                for (TreeNode neighbor : current.getNeighbors()) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor.getValue());
                    queue.offer(neighbor);
                    pathQueue.offer(newPath);
                }
            }
        }

        return new ArrayList<>();
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

class BFSDemo {

    public static void main(String[] args) {
        BFS treeBFS = new BFS();
        BFS.TreeNode root = BFS.createTestTree();
        Scanner scanner = new Scanner(System.in);

        List<Integer> availableValues = treeBFS.getAllValues(root);
        System.out.println("\nДоступные узлы для поиска: " + availableValues);

        System.out.print("\nВведите значение узла для поиска: ");
        int targetValue = scanner.nextInt();

        if (!availableValues.contains(targetValue)) {
            System.out.println("Узел со значением " + targetValue + " не найден в дереве!");
            return;
        }

        System.out.println("\n Поиск узла " + targetValue);

        // Способ 1: Классический BFS
        System.out.println("\n Классический BFS");
        List<Integer> classicResult = treeBFS.bfsClassic(root, targetValue);
        if (!classicResult.isEmpty()) {
            System.out.println("Путь до узла " + targetValue + ": " + classicResult);
            System.out.println("Длина пути: " + classicResult.size());
        } else {
            System.out.println("Узел " + targetValue + " не найден классическим методом");
        }

        // Способ 2: BFS с уровнями
        System.out.println("\n BFS с уровнями");
        List<Integer> levelResult = treeBFS.bfsWithLevels(root, targetValue);
        if (!levelResult.isEmpty()) {
            System.out.println("Путь до узла " + targetValue + ": " + levelResult);
            System.out.println("Длина пути: " + levelResult.size());
        } else {
            System.out.println("Узел " + targetValue + " не найден методом с уровнями");
        }

        scanner.close();
    }
}