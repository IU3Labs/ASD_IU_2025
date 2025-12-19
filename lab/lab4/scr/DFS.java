// 3. Реализовать поиск в дереве в глубину двумя способами
import java.util.*;

public class DFS {

    // Узел дерева
    static class Node {
        int value;
        List<Node> children;

        Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        void addChild(Node child) {
            children.add(child);
        }
    }

    // Способ 1: Рекурсивный DFS
    public static List<Integer> dfsRecursive(Node root, int target) {
        List<Integer> path = new ArrayList<>();
        if (dfsRecursiveHelper(root, target, path)) {
            return path;
        }
        return new ArrayList<>();
    }

    private static boolean dfsRecursiveHelper(Node node, int target, List<Integer> path) {
        if (node == null) return false;

        path.add(node.value);

        if (node.value == target) {
            return true;
        }

        // Рекурсивно ищем в дочерних узлах
        for (Node child : node.children) {
            if (dfsRecursiveHelper(child, target, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    // Способ 2: Итеративный DFS
    public static List<Integer> dfsIterative(Node root, int target) {
        if (root == null) return new ArrayList<>();

        Stack<Node> nodeStack = new Stack<>();
        Stack<List<Integer>> pathStack = new Stack<>();

        // Начинаем с корня
        nodeStack.push(root);
        pathStack.push(new ArrayList<>(Arrays.asList(root.value)));

        while (!nodeStack.isEmpty()) {
            Node current = nodeStack.pop();
            List<Integer> currentPath = pathStack.pop();

            if (current.value == target) {
                return currentPath;
            }

            // Добавляем детей в стек (в обратном порядке для сохранения порядка)
            List<Node> children = current.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                Node child = children.get(i);
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(child.value);
                nodeStack.push(child);
                pathStack.push(newPath);
            }
        }

        return new ArrayList<>();
    }

    // Все значения узлов
    public static List<Integer> getAllValues(Node root) {
        List<Integer> values = new ArrayList<>();
        getAllValuesHelper(root, values);
        return values;
    }

    private static void getAllValuesHelper(Node node, List<Integer> values) {
        if (node == null) return;
        values.add(node.value);
        for (Node child : node.children) {
            getAllValuesHelper(child, values);
        }
    }

    // Тестовое дерево
    public static Node createTestTree() {
        Node root = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        root.addChild(node2);
        root.addChild(node3);
        root.addChild(node4);

        node2.addChild(node5);
        node2.addChild(node6);

        node4.addChild(node7);
        node4.addChild(node8);

        return root;
    }

    public static void main(String[] args) {
        Node root = createTestTree();
        Scanner scanner = new Scanner(System.in);

        List<Integer> availableValues = getAllValues(root);
        System.out.println("Доступные узлы для поиска: " + availableValues);

        System.out.print("Введите значение узла для поиска: ");
        int targetValue = scanner.nextInt();

        if (!availableValues.contains(targetValue)) {
            System.out.println("Узел со значением " + targetValue + " не найден в дереве!");
            return;
        }

        System.out.println("\nПоиск узла " + targetValue);

        // Способ 1: Рекурсивный DFS
        System.out.println("\nРекурсивный DFS:");
        List<Integer> recursiveResult = dfsRecursive(root, targetValue);
        if (!recursiveResult.isEmpty()) {
            System.out.println("Путь: " + recursiveResult);
            System.out.println("Длина пути: " + recursiveResult.size());
        } else {
            System.out.println("Узел не найден");
        }

        // Способ 2: Итеративный DFS
        System.out.println("\nИтеративный DFS:");
        List<Integer> iterativeResult = dfsIterative(root, targetValue);
        if (!iterativeResult.isEmpty()) {
            System.out.println("Путь: " + iterativeResult);
            System.out.println("Длина пути: " + iterativeResult.size());
        } else {
            System.out.println("Узел не найден");
        }

        scanner.close();
    }
}
