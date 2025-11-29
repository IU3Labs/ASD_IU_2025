public class DFSRecursive {

    public static boolean dfs(Node n, int target) {
        if (n == null) return false;
        if (n.value == target) return true;

        return dfs(n.left, target) || dfs(n.right, target);
    }

    public static void main(String[] args) {
        Tree t = new Tree();
        t.readFromKeyboard();

        System.out.print("Введите значение поиска: ");
        int target = new java.util.Scanner(System.in).nextInt();

        boolean result = dfs(t.getRoot(), target);
        System.out.println("DFS (recursive) найдено? " + result);
    }
}
