/**
 * Группа А. Задание 3
 * Реализовать поиск в дереве в глубину двумя способами.
 * */
import java.util.*;
public class DFS {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static Node searchRecursive(Node root, int value, ArrayDeque<Node> callStack) {
        if (root == null) return null;

        callStack.push(root);
        System.out.print("Текущий стек рекурсии: ");
        for (Node n : callStack) System.out.print(n + " ");
        System.out.println();

        if (root.value == value) { // если нашли — возвращаем узел, сняв его со стека
            System.out.println("-------------------");
            System.out.println("Элемент найден: " + root);
            callStack.pop(); // снимаем текущий узел перед возвратом
            return root;
        }

        Node foundLeft = searchRecursive(root.left, value, callStack); // Идём в левое поддерево
        if (foundLeft != null) {
            callStack.pop(); // снимаем текущий узел и возвращаем найденное
            return foundLeft;
        }

        Node foundRight = searchRecursive(root.right, value, callStack); // если в левом нет, идем в правое поддерево
        callStack.pop(); // снимаем текущий узел
        return foundRight;
    }

    public static Node searchIterative(Node root, int value) {
        if (root == null) return null;

        ArrayDeque<Node> nodeStack = new ArrayDeque<>();
        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            System.out.print("Текущий стек: ");
            for (Node n : nodeStack) System.out.print(n + " ");
            System.out.println();

            Node current = nodeStack.pop(); // достаём верхний узел
            System.out.println("Извлекаем узел: " + current);

            if (current.value == value) {  // проверяем текущий узел
                System.out.println("-------------------");
                System.out.println("Элемент найден: " + current);
                return current;
            }

            // Сначала добавляем правого потомка, потом левого
            if (current.right != null) {
                nodeStack.push(current.right);
                System.out.println("Добавляем в стек правого потомка: " + current.right);
            }
            if (current.left != null) {
                nodeStack.push(current.left);
                System.out.println("Добавляем в стек левого потомка: " + current.left);
            }
            System.out.println("-------------------");
        }
        return null;
    }

    public static void main(String[] args) {
        Node leaf1 = new Node(4);
        Node leaf2 = new Node(5);
        Node branch1 = new Node(2, leaf1, leaf2);
        Node branch2 = new Node(3);
        Node rootNode = new Node(1, branch1, branch2);

        int targetValue = 5;

        System.out.println("Рекурсивный DFS с пошаговым стеком:");
        Node foundRec = searchRecursive(rootNode, targetValue, new ArrayDeque<>());

        System.out.println("\nИтеративный DFS с пошаговым стеком:");
        Node foundIter = searchIterative(rootNode, targetValue);
    }
}
