import java.util.ArrayDeque;
import java.util.ArrayList;

public class A4_BFS_TwoWays {

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

        System.out.println("1) BFS итеративно (очередь)");
        System.out.println("2) BFS рекурсивно (через очередь)");
        System.out.print("Выберите способ (1-2): ");
        int method = fs.nextInt();

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = -1;

        boolean found;
        if (method == 1) {
            found = bfsIterative(tree.children, tree.root, target, parent);
        } else {
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(tree.root);
            found = bfsRecursive(tree.children, q, target, parent);
        }

        if (!found) {
            System.out.println("Вершина не найдена");
            return;
        }

        System.out.println("Найдена. Путь:");
        printPath(tree.root, target, parent);
    }

    private static boolean bfsIterative(ArrayList<Integer>[] ch, int root, int target, int[] parent) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == target) return true;
            for (int to : ch[v]) {
                parent[to] = v;
                q.add(to);
            }
        }
        return false;
    }

    private static boolean bfsRecursive(ArrayList<Integer>[] ch, ArrayDeque<Integer> q, int target, int[] parent) {
        if (q.isEmpty()) return false;
        int v = q.poll();
        if (v == target) return true;

        for (int to : ch[v]) {
            parent[to] = v;
            q.add(to);
        }
        return bfsRecursive(ch, q, target, parent);
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
