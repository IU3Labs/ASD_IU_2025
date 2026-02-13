import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearch {

    public static boolean recursiveDFS(Tree.TreeNode node, int targetValue) {
        if (node == null) {
            return false;
        }

        System.out.println("Рекурсивный DFS: проверка узла " + node.value);

        if (node.value == targetValue) {
            System.out.println("Элемент найден рекурсивным алгоритмом: " + node.value);
            return true;
        }

        boolean foundInLeft = recursiveDFS(node.leftChild, targetValue);
        boolean foundInRight = recursiveDFS(node.rightChild, targetValue);

        return foundInLeft || foundInRight;
    }

    public static boolean iterativeDFS(Tree.TreeNode root, int targetValue) {
        if (root == null) {
            return false;
        }

        Stack<Tree.TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            Tree.TreeNode currentNode = nodeStack.pop();

            System.out.println("Итеративный DFS: проверка узла " + currentNode.value);

            if (currentNode.value == targetValue) {
                System.out.println("Элемент найден итеративным алгоритмом: " + currentNode.value);
                return true;
            }

            if (currentNode.rightChild != null) {
                nodeStack.push(currentNode.rightChild);
            }
            if (currentNode.leftChild != null) {
                nodeStack.push(currentNode.leftChild);
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Построение дерева из пользовательского ввода
        Tree tree = TreeHelper.buildTreeFromUserInput(scanner);

        // Отображение структуры дерева
        TreeHelper.displayTreeStructure(tree.getRoot());

        // Запрос целевого значения для поиска
        int target = TreeHelper.getSearchValue(scanner);

        System.out.println("\nРекурсивный поиск в глубину");
        boolean foundRecursive = recursiveDFS(tree.getRoot(), target);

        if (!foundRecursive) {
            System.out.println("Элемент не найден рекурсивным методом");
        }

        System.out.println("\nИтеративный поиск в глубину");
        boolean foundIterative = iterativeDFS(tree.getRoot(), target);

        if (!foundIterative) {
            System.out.println("Элемент не найден итеративным методом");
        }

        scanner.close();
    }
}