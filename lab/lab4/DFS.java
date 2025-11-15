// 3 Реализовать поиск в дереве в глубину двумя способами.
import java.util.Scanner;
import java.util.Stack;

public class DFS {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        Tree tree = Utils.createTree(scanner);
        int target = Utils.inputTarget(scanner);

        if (!recursiveDFS(tree.root, target)) {
            System.out.println("element not found");
        };

        if (!iterativeDFS(tree.root, target)) {
            System.out.println("element not found");
        };

    }

    public static boolean recursiveDFS(Tree.Node node, int target) {
        if (node == null) { return false; }

        if (target == node.data) {
            System.out.println("Found: " + node.data);
            return true;
        }

        return recursiveDFS(node.left, target) || 
           recursiveDFS(node.right, target);

    }

    public static boolean iterativeDFS(Tree.Node root, int target) {
        if (root == null) { return false; }

        Stack<Tree.Node> stack = new Stack<>();
        stack.push(root);


        while(!stack.isEmpty()) {
            Tree.Node node = stack.pop();

            if (node.data == target) {
                System.out.println("Found: " + node.data);
                return true;
            }

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return false;
    }
}
