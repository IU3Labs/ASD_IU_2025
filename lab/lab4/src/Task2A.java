/* Реализовать поиск в дереве (Iterative deepening depth-first search) */
import java.util.*;

public class Task2A {

    public static void main(String[] args) {
        TreeBuild.Node root = TreeBuild.buildTree();

        System.out.print("Введите значение для поиска: ");
        int searchValue = TreeBuild.inputScanner.nextInt();

        boolean found = searchIDDFS(root, searchValue);
        System.out.println("Результат поиска Iterative deepening depth-first search: " +
                (found ? "значение найдено" : "значение отсутствует"));
        TreeBuild.finishInput();
    }

    private static boolean searchIDDFS(TreeBuild.Node root, int value) {
        if (root == null) {
            return false;
        }
        int depth = 0;
        while (true) {
            System.out.println("Поиск на глубине: " + depth);
            boolean found = depthLimitedSearch(root, value, depth);
            if (found) {
                System.out.println("Найдено на глубине " + depth);
                return true;
            }
            depth++;
        }
    }

    private static boolean depthLimitedSearch(TreeBuild.Node node, int value, int depth) {
        if (node == null) {
            return false;
        }

        if (node.data == value) {
            return true;
        }

        if (depth == 0) {
            return false;
        }

        boolean foundLeft = depthLimitedSearch(node.leftChild, value, depth - 1);
        if (foundLeft) {
            return true;
        }

        boolean foundRight = depthLimitedSearch(node.rightChild, value, depth - 1);
        return foundRight;
    }
}