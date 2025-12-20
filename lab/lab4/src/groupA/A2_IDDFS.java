import java.util.ArrayDeque;
import java.util.ArrayList;

public class A2_IDDFS {

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

        int[] parent = new int[n];
        int foundDepth = -1;

        for (int depth = 0; depth < n; depth++) {
            for (int i = 0; i < n; i++) parent[i] = -1;
            if (dls(tree.children, root, target, depth, parent)) {
                foundDepth = depth;
                break;
            }
        }

        if (foundDepth == -1) {
            System.out.println("Вершина не найдена");
            return;
        }

        System.out.println("Найдена при ограничении глубины: " + foundDepth);
        System.out.println("Путь:");
        printPath(root, target, parent);
    }

    private static boolean dls(ArrayList<Integer>[] ch, int v, int target, int limit, int[] parent) {
        if (v == target) return true;
        if (limit == 0) return false;

        for (int to : ch[v]) {
            parent[to] = v;
            if (dls(ch, to, target, limit - 1, parent)) return true;
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
