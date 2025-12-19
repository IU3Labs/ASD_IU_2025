import java.util.Scanner;
import java.util.Stack;
//2 Реализовать поиск в дереве (Iterative deepening depth-first search)
public class Task2 {

    // Элемент стека: узел + глубина
    private static class Item {
        Tree.Node node;
        int depth;

        Item(Tree.Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // Реализация IDDFS
    public static boolean search(Tree.Node root, int target) {
        if (root == null) {
            return false;
        }

        int maxDepth = getMaxDepth(root);

        for (int limit = 0; limit <= maxDepth; limit++) {
            if (dfsLimited(root, target, limit)) {
                System.out.println("Элемент " + target + " найден на глубине " + limit);
                return true;
            }
        }
        return false;
    }

    // DFS с ограничением глубины
    private static boolean dfsLimited(Tree.Node root, int target, int limit) {
        Stack<Item> dfsStack = new Stack<>();
        dfsStack.push(new Item(root, 0));

        while (!dfsStack.isEmpty()) {
            Item current = dfsStack.pop();
            Tree.Node node = current.node;

            if (node.data == target) {
                return true;
            }


            if (current.depth < limit) {
                if (node.right != null) {
                    dfsStack.push(new Item(node.right, current.depth + 1));
                }
                if (node.left != null) {
                    dfsStack.push(new Item(node.left, current.depth + 1));
                }
            }
        }
        return false;
    }

    // Подсчёт глубины дерева
    private static int getMaxDepth(Tree.Node root) {
        Stack<Item> stack = new Stack<>();
        stack.push(new Item(root, 0));
        int maxDepth = 0;

        while (!stack.isEmpty()) {
            Item current = stack.pop();
            maxDepth = Math.max(maxDepth, current.depth);

            if (current.node.right != null) {
                stack.push(new Item(current.node.right, current.depth + 1));
            }
            if (current.node.left != null) {
                stack.push(new Item(current.node.left, current.depth + 1));
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree tree = Utils.createTree(scanner);

        System.out.println("Структура дерева:");
        Utils.printInOrder(tree.root);
        System.out.println();

        int target = Utils.inputTarget(scanner);

        boolean foundTarget = search(tree.root, target);
        if (!foundTarget) {
            System.out.println("Элемент не найден");
        }
    }
}
