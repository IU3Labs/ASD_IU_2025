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
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        root.addChild(n2);
        root.addChild(n3);
        n2.addChild(n4);
        n3.addChild(n5);

        int target = 3;
        Node result = findNode(root, target);

        if (result != null) {
            System.out.println("Node " + result.value + " was found.");
        } else {
            System.out.println("Node " + target + " was not found.");
        }
    }
}
