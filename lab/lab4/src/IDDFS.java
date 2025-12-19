//Реализовать поиск в дереве с итеративным углублением (IDDFS).
import java.util.*;

public class IDDFS {
    public static Node findNode(Node root, int target) {
        //Проверка на наличие узлов в дереве
        if (root == null) {
            return null;
        }

        //Устанавливаем максимальный предел глубины для поиска
        int maxDepth = 100;

        //Постепенно увеличиваем лимит глубины поиска
        for (int depth = 0; depth <= maxDepth; depth++) {
            //Запуск поиска с ограничением по глубине (DLS)
            Node result = dls(root, target, depth);

            //Если узел найден, возвращаем его
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    //Метод поиска с ограничением по глубине
    private static Node dls(Node node, int target, int limit) {
        //Базовый случай и проверка на null
        if (node == null) {
            return null;
        }

        //Заканчиваем поиск если узел найден
        if (node.value == target) {
            return node;
        }

        //Если лимит глубины исчерпан, прекращаем спуск
        if (limit <= 0) {
            return null;
        }

        //Рекурсивный переход к дочерним узлам
        if (node.children != null) {
            for (Node child : node.children) {
                //Вызов поиска для ребенка с уменьшением лимита
                Node result = dls(child, target, limit - 1);

                //Проброс результата наверх по стеку
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
