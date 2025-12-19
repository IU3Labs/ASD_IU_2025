// 4. Реализовать поиск в дереве в ширину двумя способами
import java.util.*;

public class BFS {

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

    // Способ 1: Классический BFS с очередью
    public static List<Integer> bfsClassic(Node root, int target) {
        if (root == null) return new ArrayList<>();

        Queue<Node> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        // Начинаем с корня
        nodeQueue.offer(root);
        pathQueue.offer(new ArrayList<>(Arrays.asList(root.value)));

        while (!nodeQueue.isEmpty()) {
            Node current = nodeQueue.poll();
            List<Integer> currentPath = pathQueue.poll();

            // Проверяем, не нашли ли целевой узел
            if (current.value == target) {
                return currentPath;
            }

            // Добавляем детей в очередь
            for (Node child : current.children) {
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(child.value);
                nodeQueue.offer(child);
                pathQueue.offer(newPath);
            }
        }

        return new ArrayList<>();
    }

    // Способ 2: BFS с уровнями
    public static List<Integer> bfsWithLevels(Node root, int target) {
        if (root == null) return new ArrayList<>();

        Queue<Node> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(new ArrayList<>(Arrays.asList(root.value)));

        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();

            // Обрабатываем все узлы текущего уровня
            for (int i = 0; i < levelSize; i++) {
                Node current = nodeQueue.poll();
                List<Integer> currentPath = pathQueue.poll();

                if (current.value == target) {
                    return currentPath;
                }

                // Добавляем детей для следующего уровня
                for (Node child : current.children) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(child.value);
                    nodeQueue.offer(child);
                    pathQueue.offer(newPath);
                }
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

        // Способ 1: Классический BFS
        System.out.println("\nКлассический BFS:");
        List<Integer> classicResult = bfsClassic(root, targetValue);
        if (!classicResult.isEmpty()) {
            System.out.println("Путь: " + classicResult);
            System.out.println("Длина пути: " + classicResult.size());
        } else {
            System.out.println("Узел не найден");
        }

        // Способ 2: BFS с уровнями
        System.out.println("\nBFS с уровнями:");
        List<Integer> levelResult = bfsWithLevels(root, targetValue);
        if (!levelResult.isEmpty()) {
            System.out.println("Путь: " + levelResult);
            System.out.println("Длина пути: " + levelResult.size());
        } else {
            System.out.println("Узел не найден");
        }

        scanner.close();
    }
}