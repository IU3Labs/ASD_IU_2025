import java.util.*;

public class BFSLevels {

    public static boolean bfs(Node root, int target) {
        if (root == null) return false;

        List<Node> current = new ArrayList<>();
        current.add(root);

        while (!current.isEmpty()) {
            List<Node> next = new ArrayList<>();

            for (Node n : current) {
                if (n.value == target) return true;

                if (n.left != null) next.add(n.left);
                if (n.right != null) next.add(n.right);
            }

            current = next;
        }

        return false;
    }

    public static void main(String[] args) {
        Tree t = new Tree();
        t.readFromKeyboard();

        System.out.print("Введите значение поиска: ");
        int target = new java.util.Scanner(System.in).nextInt();

        boolean result = bfs(t.getRoot(), target);
        System.out.println("BFS (levels) найдено? " + result);
    }
}
