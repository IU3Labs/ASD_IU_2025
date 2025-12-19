//Реализовать поиск в дереве в ширину двумя способами.
import java.util.*;

public class FirstBFS {
    public static Node findNode(Node root, int target) {
        //Проверка на наличие узлов в дереве
        if (root == null) {
            return null;
        }

        //Очередь используется для реализации FIFO
        Queue<Node> queue = new ArrayDeque<>();
        //Начало обхода
        queue.add(root);

        //Цикл продолжается пока в очереди есть элементы
        while (!queue.isEmpty()) {
            //Извлекаем первый узел из очереди
            Node currentNode = queue.poll();

            //Заканчиваем поиск если узел найден
            if (currentNode.value == target) {
                return currentNode;
            }

            //Добавление дочерних узлов
            if (currentNode.children != null) {
                for (Node child : currentNode.children) {
                    if (child != null) {
                        //Заносим дочерний узел в очередь
                        queue.add(child);
                    }
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tree building");
        // Построение дерева через ввод
        Node root = BuildTree.buildTree(scanner);

        System.out.print("Input target value: ");
        int target = scanner.nextInt();

        // Поиск в ширину
        Node result = findNode(root, target);

        if (result != null) {
            System.out.println("Node " + result.value + " was found.");
        } else {
            System.out.println("Node " + target + " was not found.");
        }

        scanner.close();
    }
}
