// 2 Реализовать поиск в дереве (Iterative deepening depth-first search)

import java.util.Scanner;

public class IDDFS  {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        Tree tree = Utils.createTree(scanner);

        int target = Utils.inputTarget(scanner);

        boolean found = search(tree.root, target);
        if (!found) {
            System.out.println("Элемент не найден");
        }
    }

    public static boolean search(Tree.Node root, int target) {
        if (root == null) return false;

        int limit = 0;

        while (true) {
            System.out.println("Глубина = " + limit);

            boolean found = dfs(root, target, limit);
            if (found) {
                System.out.println("элемент найден на глубине: " + limit);
                return true;
            }
            limit += 1;
        }
    }

    public static boolean dfs(Tree.Node node, int target, int limit) {
        if (node == null) return false;

        if (node.data == target) {
            System.out.println("найден: " + node.data);
            return true;
        }

        if (limit == 0) return false;

        return dfs(node.left, target, limit - 1) || dfs(node.right, target, limit - 1);
    }
}