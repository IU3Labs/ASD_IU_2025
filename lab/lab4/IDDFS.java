public class IDDFS {

    private static boolean dls(Node n, int target, int depth) {
        if (n == null) return false;
        if (depth == 0) return n.value == target;

        return dls(n.left, target, depth - 1) ||
            dls(n.right, target, depth - 1);
    }

    public static boolean iddfs(Node root, int target) {
        int depth = 0;

        while (true) {
            if (dls(root, target, depth)) return true;
            depth++;
        }
    }

    public static void main(String[] args) {
        Tree t = new Tree();
        t.readFromKeyboard();

        System.out.print("Введите значение поиска: ");
        int target = new java.util.Scanner(System.in).nextInt();

        boolean result = iddfs(t.getRoot(), target);
        System.out.println("IDDFS найдено? " + result);
    }
}
