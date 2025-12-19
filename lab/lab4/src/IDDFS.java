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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tree building");
        // Построение дерева через консольный ввод
        Node root = BuildTree.buildTree(scanner);

        System.out.print("Input target value: ");
        int target = scanner.nextInt();

        // Поиск с итеративным углублением
        Node result = findNode(root, target);

        if (result != null) {
            System.out.println("Node " + result.value + " was found.");
        } else {
            System.out.println("Node " + target + " was not found.");
        }

        scanner.close();
    }
}
