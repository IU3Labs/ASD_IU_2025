
import java.util.Scanner;
import java.util.Stack;

// 3 Реализовать поиск в дереве в глубину двумя способами.

public class Task3 {

    public static boolean recursiveDFS(Tree.Node node, int target) {
        if (node == null) {
            return false;
        }

        if (target == node.data) {
            System.out.println("Элемент найден рекурсивным алгоритмом: " + node.data);
            return true;
        }

        return recursiveDFS(node.left, target) || recursiveDFS(node.right, target);

    }

    public static boolean iterativeDFS(Tree.Node root, int target) {
        if (root == null) {
            return false;
        }

        Stack<Tree.Node> stack = new Stack<>();
        stack.push(root);


        while (!stack.isEmpty()) {
            Tree.Node node = stack.pop();

            if (node.data == target) {
                System.out.println("Элемент найден итеративным алгоритмом: " + node.data);
                return true;
            }

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return false;
    }

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        Tree tree = Utils.createTree(scanner);
        System.out.println("Структура дерева:");
        Utils.printInOrder(tree.root);
        System.out.println();

        int target = Utils.inputTarget(scanner);

        if (!recursiveDFS(tree.root, target)) {
            System.out.println("Элемент не найден ");
        }

        if (!iterativeDFS(tree.root, target)) {
            System.out.println("Элемент не найден ");
        }

    }
}
