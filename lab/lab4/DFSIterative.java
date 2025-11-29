import java.util.Stack;

public class DFSIterative {

    public static boolean dfs(Node root, int target) {
        if (root == null) return false;

        Stack<Node> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node n = st.pop();

            if (n.value == target) return true;

            if (n.right != null) st.push(n.right);
            if (n.left != null) st.push(n.left);
        }

        return false;
    }

    public static void main(String[] args) {
        Tree t = new Tree();
        t.readFromKeyboard();

        System.out.print("Введите значение поиска: ");
        int target = new java.util.Scanner(System.in).nextInt();

        boolean result = dfs(t.getRoot(), target);
        System.out.println("DFS (iterative) найдено? " + result);
    }
}
