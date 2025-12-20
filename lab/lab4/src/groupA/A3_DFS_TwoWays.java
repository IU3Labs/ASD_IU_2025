import java.util.ArrayDeque;
import java.util.ArrayList;

public class A3_DFS_TwoWays {

    public static void run(FastScanner fs) throws Exception {
        System.out.print("Введите N (кол-во вершин): ");
        int n = fs.nextInt();

        System.out.print("Введите корень (0..N-1): ");
        int root = readNode(fs, n);

        TreeData tree = new TreeData(n, root);

        System.out.println("Введите " + (n - 1) + " ребер в формате: родитель потомок (0..N-1)");
        for (int i = 0; i < n - 1; i++) {
            int p = readNode(fs, n);
            int c = readNode(fs, n);
            tree.children[p].add(c);
        }

        System.out.print("Введите искомую вершину: ");
        int target = readNode(fs, n);

        System.out.println("1) DFS рекурсивно");
        System.out.println("2) DFS итеративно (стек)");
        System.out.print("Выберите способ (1-2): ");
        int method = fs.nextInt();

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = -1;

        boolean found = (method == 1)
                ? dfsRecursive(tree.children, tree.root, target, parent)
                : dfsIterative(tree.children, tree.root, target, parent);

        if (!found) {
            System.out.println("Вершина не найдена");
            return;
        }

        System.out.println("Найдена. Путь:");
        printPath(tree.root, target, parent);
    }

    private static boolean dfsRecursive(ArrayList<Integer>[] ch, int v, int target, int[] parent) {
        if (v == target) return true;
        for (int to : ch[v]) {
            parent[to] = v;
            if (dfsRecursive(ch, to, target, parent)) return true;
        }
        return false;
    }

    private static boolean dfsIterative(ArrayList<Integer>[] ch, int root, int target, int[] parent) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        st.push(root);

        while (!st.isEmpty()) {
            int v = st.pop();
            if (v == target) return true;

            ArrayList<Integer> kids = ch[v];
            for (int i = kids.size() - 1; i >= 0; i--) {
                int to = kids.get(i);
                parent[to] = v;
                st.push(to);
            }
        }
        return false;
    }

    private static void printPath(int root, int target, int[] parent) {
        ArrayDeque<Integer> path = new ArrayDeque<>();
        int cur = target;
        while (cur != -1) {
            path.push(cur);
            if (cur == root) break;
            cur = parent[cur];
        }
        if (path.isEmpty() || path.peek() != root) {
            System.out.println("Не удалось восстановить путь");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.pop());
            if (!path.isEmpty()) sb.append(" -> ");
        }
        System.out.println(sb);
    }

    private static int readNode(FastScanner fs, int n) throws Exception {
        int x = fs.nextInt();
        if (x < 0 || x >= n) {
            throw new IllegalArgumentException("Вершина должна быть в диапазоне 0.." + (n - 1));
        }
        return x;
    }
}
