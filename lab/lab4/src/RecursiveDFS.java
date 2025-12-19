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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tree building");
        // Вызов метода построения дерева
        Node root = BuildTree.buildTree(scanner);

        System.out.print("Input target value for search: ");
        int target = scanner.nextInt();

        // Поиск целевого узла рекурсивным способом
        Node result = findNode(root, target);

        if (result != null) {
            System.out.println("Node " + result.value + " was found.");
        } else {
            System.out.println("Node " + target + " was not found.");
        }

        scanner.close();
    }
}
