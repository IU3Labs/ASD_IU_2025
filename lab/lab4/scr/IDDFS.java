// 2. Реализовать поиск в дереве (Iterative deepening depth-first search)
import java.util.*;

public class IDDFS {

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

    // Ограниченный поиск в глубину (DLS)
    private static boolean depthLimitedSearch(Node node, int target, int depth, List<Integer> path) {
        path.add(node.value);

        if (node.value == target) {
            return true;
        }

        if (depth == 0) {
            path.remove(path.size() - 1);
            return false;
        }

        // Рекурсивно ищем в дочерних узлах
        for (Node child : node.children) {
            if (depthLimitedSearch(child, target, depth - 1, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    // Основной метод IDDFS
    public static List<Integer> iterativeDeepeningDFS(Node root, int target) {

        for (int depth = 0; depth < 10; depth++) {
            List<Integer> path = new ArrayList<>();
            if (depthLimitedSearch(root, target, depth, path)) {
                return path;
            }
        }
        return new ArrayList<>();
    }

    // все значения узлов
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
            System.out.println("Узел со значением " + targetValue + " не найден в дереве");
            return;
        }

        System.out.println("\nПоиск узла " + targetValue + " с итеративным углублением");

        List<Integer> result = iterativeDeepeningDFS(root, targetValue);
        if (!result.isEmpty()) {
            System.out.println("Путь до узла " + targetValue + ": " + result);
        } else {
            System.out.println("Узел " + targetValue + " не найден");
        }

        scanner.close();
    }
}