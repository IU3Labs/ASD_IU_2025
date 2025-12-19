//Реализовать поиск в дереве в глубину двумя способами.
import java.util.*;

public class IterativeDFS {
    public static Node findNode(Node root, int target) {
        //Проверка на наличие узлов в дереве
        if (root == null) {
            return null;
        }

        //Используем stack для реализации LIFO
        Stack<Node> stack = new Stack<>();
        //Начало обхода, помещаем корень в стек
        stack.push(root);

        //Цикл работает пока стек не окажется пустым
        while (!stack.isEmpty()) {
            //Проверяем "верхний" элемент стека
            Node currentNode = stack.pop();

            //Условие поиска
            if (currentNode.value == target) {
                return currentNode;
            }

            //Добавление дочерних узлов в стэк для дальнейшего обхода
            if (currentNode.children != null) {
                //Обработка списка происходит в обратном порядке
                for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                    Node child = currentNode.children.get(i);

                    //Если узел пустой, то не добавляем
                    if (child != null) {
                        stack.push(child);
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
