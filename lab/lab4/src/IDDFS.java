import java.util.*;

public class IDDFS {

    public static boolean search(Tree tree, int target, int maxDepth) {
        for (int depth = 0; depth <= maxDepth; depth++) {
            if (dfs(tree.root, target, depth)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(Tree.Node node, int target, int depth) {
        if (node == null) return false;

        if (node.data == target) return true;

        if (depth <= 0) return false;

        return dfs(node.left, target, depth - 1) ||
                dfs(node.right, target, depth - 1);
    }

    public static void main(String[] args) {
        Tree tree = InputTree.buildTree();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение для поиска:");
        int target = scanner.nextInt();

        System.out.println("Введите максимальную глубину поиска:");
        int maxDepth = scanner.nextInt();

        if (search(tree, target, maxDepth)) {
            System.out.println(target + " найден!");
        } else {
            System.out.println(target + " не найден.");
        }
    }
}
