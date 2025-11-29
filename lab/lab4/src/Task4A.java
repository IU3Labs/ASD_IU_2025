/* Реализовать поиск в дереве в ширину двумя способами. */
import java.util.*;

public class Task4A {
    public static void main(String[] args) {
        TreeBuild.Node root = TreeBuild.buildTree();

        System.out.print("Введите значение для поиска: ");
        int searchValue = TreeBuild.inputScanner.nextInt();

        boolean foundRecursive = searchBFSRecursive(root, searchValue);
        System.out.println("Результат рекурсивного поиска в ширину: " +
                (foundRecursive ? "значение найдено" : "значение отсутствует"));

        boolean foundIterative = searchBFSIterative(root, searchValue);
        System.out.println("Результат итеративного поиска в ширину: " +
                (foundIterative ? "значение найдено" : "значение отсутствует"));
        TreeBuild.finishInput();
    }

    private static boolean searchBFSRecursive(TreeBuild.Node node, int value) {
        if (node == null) {
            return false;
        }
        Queue<TreeBuild.Node> queue = new LinkedList<>();
        queue.add(node);
        return searchRecursive(queue, value);
    }

    private static boolean searchRecursive(Queue<TreeBuild.Node> queue, int value) {
        if (queue.isEmpty()) {
            return false;
        }
        TreeBuild.Node current = queue.poll();

        if (current.data == value) {
            return true;
        }

        if (current.leftChild != null) {
            queue.add(current.leftChild);
        }
        if (current.rightChild != null) {
            queue.add(current.rightChild);
        }

        return searchRecursive(queue, value);
    }

    private static boolean searchBFSIterative(TreeBuild.Node node, int value) {
        if (node == null) {
            return false;
        }
        Queue<TreeBuild.Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeBuild.Node current = queue.poll();

            if (current.data == value) {
                return true;
            }

            if (current.leftChild != null) {
                queue.add(current.leftChild);
            }
            if (current.rightChild != null) {
                queue.add(current.rightChild);
            }
        }

        return false;
    }
}