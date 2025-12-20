import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearch {

    public static Queue<Tree.TreeNode> initializeQueue(Tree.TreeNode root) {
        Queue<Tree.TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        return queue;
    }

    public static boolean recursiveBFS(Queue<Tree.TreeNode> queue, int targetValue) {
        if (queue.isEmpty()) {
            return false;
        }

        Tree.TreeNode currentNode = queue.poll();

        System.out.println("Рекурсивный BFS: проверка узла " + currentNode.value);

        if (currentNode.value == targetValue) {
            System.out.println("Элемент найден рекурсивным алгоритмом: " + currentNode.value);
            return true;
        }

        if (currentNode.leftChild != null) {
            queue.add(currentNode.leftChild);
        }
        if (currentNode.rightChild != null) {
            queue.add(currentNode.rightChild);
        }

        return recursiveBFS(queue, targetValue);
    }

    public static boolean iterativeBFS(Tree.TreeNode root, int targetValue) {
        if (root == null) {
            return false;
        }

        Queue<Tree.TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Tree.TreeNode currentNode = queue.poll();

            System.out.println("Итеративный BFS: проверка узла " + currentNode.value);

            if (currentNode.value == targetValue) {
                System.out.println("Элемент найден итеративным алгоритмом: " + currentNode.value);
                return true;
            }

            if (currentNode.leftChild != null) {
                queue.add(currentNode.leftChild);
            }
            if (currentNode.rightChild != null) {
                queue.add(currentNode.rightChild);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Tree tree = TreeHelper.buildTreeFromUserInput(scanner);
        TreeHelper.displayTreeStructure(tree.getRoot());
        int target = TreeHelper.getSearchValue(scanner);

        Queue<Tree.TreeNode> queue = initializeQueue(tree.getRoot());

        System.out.println("\nРекурсивный поиск в ширину");
        boolean foundRecursive = recursiveBFS(queue, target);

        if (!foundRecursive) {
            System.out.println("Элемент не найден рекурсивным методом");
        }

        System.out.println("\nИтеративный поиск в ширину");
        boolean foundIterative = iterativeBFS(tree.getRoot(), target);

        if (!foundIterative) {
            System.out.println("Элемент не найден итеративным методом");
        }

        scanner.close();
    }
}