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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tree building");
        Node root = BuildTree.buildTree(scanner);

        System.out.print("Input target value: ");
        int target = scanner.nextInt();

        Node result = findNode(root, target);

        if (result != null) {
            System.out.println("Node " + result.value + " was found.");
        } else {
            System.out.println("Node " + target + " was not found.");
        }

        scanner.close();
    }
}
