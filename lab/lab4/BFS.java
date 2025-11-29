/**
 * Группа А. Задание 4
 * Реализовать поиск в дереве в ширину двумя способами.
 * */
import java.util.*;
public class BFS {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static void bfsQueueStep(Node root) {
        if (root == null) return; // пустое дерево

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root); // кладём корень в очередь

        List<Integer> result = new ArrayList<>();
        System.out.println("Итеративный BFS:");

        int line = 1;
        while (!queue.isEmpty()) {
            System.out.println("Очередь " + line + ": " + queueValues(queue));
            Node current = queue.poll(); // достаём голову очереди
            result.add(current.value); // добавляем значение в результат
            System.out.println("Обрабатываем узел: " + current.value);

            // добавляем в очередь: сначала левый, потом правый
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);

            line++;
        }
        System.out.println("Результат обхода: " + result + "\n");
    }

    private static List<Integer> queueValues(Queue<Node> queue) { // возвращает список значений из очереди
        List<Integer> values = new ArrayList<>();
        for (Node node : queue) values.add(node.value);
        return values;
    }

    // Рекурсивный BFS по уровням с пошаговым выводом
    public static void bfsByLevelsStep(Node root) {
        if (root == null) return;

        List<Node> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        List<Integer> result = new ArrayList<>();
        System.out.println("Рекурсивный BFS:");
        bfsLevel(currentLevel, result, 1);

        System.out.println("Результат обхода: " + result);
    }

    private static void bfsLevel(List<Node> currentLevel, List<Integer> result, int level) {
        // печатает узлы уровня, собирает следующий уровень и вызывает себя
        if (currentLevel.isEmpty()) return;

        List<Node> nextLevel = new ArrayList<>();
        System.out.println("Уровень " + level + ": " + nodeValues(currentLevel));

        for (Node node : currentLevel) { // обрабатывает все узлы текущего уровня и формируем список для следующего уровня
            result.add(node.value);
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }
        bfsLevel(nextLevel, result, level + 1);
    }

    private static List<Integer> nodeValues(List<Node> nodes) {
        List<Integer> values = new ArrayList<>();
        for (Node node : nodes) values.add(node.value);
        return values;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        bfsQueueStep(root);
        bfsByLevelsStep(root);
    }
}
