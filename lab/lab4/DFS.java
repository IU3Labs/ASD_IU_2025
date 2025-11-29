import java.util.*;

public class DFS {

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
     * Способ 1: Рекурсивный DFS с поиском целевого узла
     */
    public List<Integer> dfsRecursive(TreeNode root, int targetValue) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        boolean found = dfsRecursiveHelper(root, targetValue, result);
        return found ? result : new ArrayList<>();
    }

    /*
     * Вспомогательный рекурсивный метод
     * ищем, и записываем путь, если нашли тру если нет фолс и вернется пустой лист с путем
     */
    private boolean dfsRecursiveHelper(TreeNode node, int targetValue, List<Integer> path) {
        if (node == null) {
            return false;
        }

        // Добавляем текущий узел в путь
        path.add(node.getValue());


        if (node.getValue() == targetValue) {
            return true;
        }


        for (TreeNode neighbor : node.getNeighbors()) {
            if (dfsRecursiveHelper(neighbor, targetValue, path)) {
                return true;
            }
        }

        // Если не нашли - удаляем узел из пути
        path.remove(path.size() - 1);
        return false;
    }

    /*
     * Способ 2: Итеративный DFS с использованием стека
     */
    public List<Integer> dfsIterative(TreeNode root, int targetValue) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<List<Integer>> pathStack = new Stack<>();

        stack.push(root);
        pathStack.push(new ArrayList<>(Arrays.asList(root.getValue())));

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            List<Integer> currentPath = pathStack.pop();

            // Проверяем, не нашли ли целевой узел
            if (current.getValue() == targetValue) {
                return currentPath;
            }

            // Добавляем соседей в обратном порядке
            List<TreeNode> neighbors = current.getNeighbors();
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                TreeNode neighbor = neighbors.get(i);
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbor.getValue());
                stack.push(neighbor);
                pathStack.push(newPath);
            }
        }

        return new ArrayList<>(); // не найден
    }

    /*
     * Получить все значения узлов чтобы выбрать поиск
     */
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

    public static TreeNode DFSDemo() {
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

class DFSDemo {

    public static void main(String[] args) {
        DFS treeDFS = new DFS();
        DFS.TreeNode root = DFS.DFSDemo();
        Scanner scanner = new Scanner(System.in);

        List<Integer> availableValues = treeDFS.getAllValues(root);
        System.out.println("\nДоступные узлы для поиска: " + availableValues);

        System.out.print("\nВведите значение узла для поиска: ");
        int targetValue = scanner.nextInt();

        if (!availableValues.contains(targetValue)) {
            System.out.println("Узел со значением " + targetValue + " не найден в дереве!");
            return;
        }

        System.out.println("\n Поиск узла ");

        // Способ 1: Рекурсивный DFS
        System.out.println("\n Рекурсивный DFS");
        List<Integer> recursiveResult = treeDFS.dfsRecursive(root, targetValue);
        if (!recursiveResult.isEmpty()) {
            System.out.println("Путь до узла " + targetValue + ": " + recursiveResult);
            System.out.println("Длина пути: " + recursiveResult.size());
        } else {
            System.out.println("Узел " + targetValue + " не найден рекурсивным методом");
        }

        // Способ 2: Итеративный DFS
        System.out.println("\n Итеративный DFS");
        List<Integer> iterativeResult = treeDFS.dfsIterative(root, targetValue);
        if (!iterativeResult.isEmpty()) {
            System.out.println("Путь до узла " + targetValue + ": " + iterativeResult);
            System.out.println("Длина пути: " + iterativeResult.size());
        } else {
            System.out.println("Узел " + targetValue + " не найден итеративным методом");
        }
        scanner.close();
    }
}