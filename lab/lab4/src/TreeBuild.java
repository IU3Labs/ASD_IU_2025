/* Построение дерева. */
import java.util.*;

class TreeBuild {
    public static final Scanner inputScanner = new Scanner(System.in);

    static class Node {
        int data;
        Node leftChild;
        Node rightChild;

        Node(int data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public static Node buildTree() {
        System.out.print("Введите корневое значение: ");
        int rootData = inputScanner.nextInt();
        Node root = new Node(rootData);

        Queue<Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            Node currentNode = nodeQueue.remove();

            System.out.print("Добавить левого потомка для " + currentNode.data + "? (1-да, 0-нет): ");
            int addLeft = inputScanner.nextInt();
            if (addLeft == 1) {
                System.out.print("Введите значение левого потомка: ");
                int leftData = inputScanner.nextInt();
                currentNode.leftChild = new Node(leftData);
                nodeQueue.add(currentNode.leftChild);
            }

            System.out.print("Добавить правого потомка для " + currentNode.data + "? (1-да, 0-нет): ");
            int addRight = inputScanner.nextInt();
            if (addRight == 1) {
                System.out.print("Введите значение правого потомка: ");
                int rightData = inputScanner.nextInt();
                currentNode.rightChild = new Node(rightData);
                nodeQueue.add(currentNode.rightChild);
            }
        }
        return root;
    }

    public static void finishInput() {
        inputScanner.close();
    }
}