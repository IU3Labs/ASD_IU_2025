import java.util.*;

public class BFSLevelsRecursive {

    public static boolean bfs(Node root, int target) {
        if (root == null) return false;
        return bfsLevel(List.of(root), target);
    }

    private static boolean bfsLevel(List<Node> level, int target) {
        if (level.isEmpty()) return false;

        List<Node> next = new ArrayList<>();

        for (Node n : level) {
            if (n.value == target) return true;

            if (n.left != null) next.add(n.left);
            if (n.right != null) next.add(n.right);
        }

        return bfsLevel(next, target);
    }

    public static void main(String[] args) {
        Tree t = new Tree();
        t.readFromKeyboard();

        System.out.print("Введите значение поиска: ");
        int target = new java.util.Scanner(System.in).nextInt();

        boolean result = bfs(t.getRoot(), target);
        System.out.println("BFS (recursive levels) найдено? " + result);
    }
}
