//Реализовать поиск в дереве в глубину двумя способами.
import java.util.*;

public class RecursiveDFS {
    public static Node findNode(Node node, int target) {
        //Проверка на наличие узлов в дереве
        if (node == null) {
            return null;
        }

        //Условие поиска
        if (node.value == target) {
            return node;
        }

        //Рекурсивный шаг
        if (node.children != null) {
            //Перебор дочерних узлов
            for (Node child : node.children) {
                //Вызов рекурсии происходит для каждого ребенка
                Node result = findNode(child, target);

                //Рекурсивная функция возвращает result если цель найдена
                if (result != null) {
                    return result;
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
