//Реализовать поиск в дереве в ширину двумя способами.
import java.util.*;

public class SecondBFS {
    public static Node findNode(Node root, int target) {
        //Проверка на наличие узлов в дереве
        if (root == null) {
            return null;
        }

        //Очередь для текущего уровня
        Queue<Node> currentLevel = new ArrayDeque<>();
        //Начало обхода
        currentLevel.add(root);

        //Цикл продолжается пока на текущем уровне есть элементы
        while (!currentLevel.isEmpty()) {
            //Очередь для накопления следующего уровня
            Queue<Node> nextLevel = new ArrayDeque<>();

            //Обход всех узлов текущего уровня
            while (!currentLevel.isEmpty()) {
                //Извлекаем узел из текущей очереди
                Node currentNode = currentLevel.poll();

                //Заканчиваем поиск если узел найден
                if (currentNode.value == target) {
                    return currentNode;
                }

                //Добавление дочерних узлов
                if (currentNode.children != null) {
                    for (Node child : currentNode.children) {
                        if (child != null) {
                            //Заносим дочерний узел в очередь следующего уровня
                            nextLevel.add(child);
                        }
                    }
                }
            }
            //Переход к следующему уровню
            currentLevel = nextLevel;
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
