/* Реализовать поиск в дереве в глубину двумя способами. */
import java.util.*;

public class Task3A {
    public static void main(String[] args) {
        TreeBuild.Node root = TreeBuild.buildTree();

        System.out.print("Введите значение для поиска: ");
        int searchValue = TreeBuild.inputScanner.nextInt();

        boolean foundRecursive = searchRecursive(root, searchValue);
        System.out.println("Результат рекурсивного поиска: " +
                (foundRecursive ? "значение найдено" : "значение отсутствует"));

        boolean foundIterative = searchIterative(root, searchValue);
        System.out.println("Результат итеративного поиска: " +
                (foundIterative ? "значение найдено" : "значение отсутствует"));
        TreeBuild.finishInput();
    }

    private static boolean searchRecursive(TreeBuild.Node node, int value) {
        if (node == null) {
            return false;
        }

        if (node.data == value) {
            return true;
        }

        boolean foundLeft = searchRecursive(node.leftChild, value);
        if (foundLeft) {
            return true;
        }

        return searchRecursive(node.rightChild, value);
    }

    private static boolean searchIterative(TreeBuild.Node root, int value) {
        if (root == null) {
            return false;
        }

        Deque<TreeBuild.Node> nodeStack = new ArrayDeque<>();
        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            TreeBuild.Node current = nodeStack.pop();

            if (current.data == value) {
                return true;
            }

            if (current.rightChild != null) {
                nodeStack.push(current.rightChild);
            }

            if (current.leftChild != null) {
                nodeStack.push(current.leftChild);
            }
        }

        return false;
    }
}