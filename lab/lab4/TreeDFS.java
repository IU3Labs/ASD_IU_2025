import java.util.ArrayDeque;

//3 Реализовать поиск в дереве в глубину двумя способами.
public class TreeDFS {

    // Рекурсивный поиск в глубину
    public static TreeNode recursiveSearch(TreeNode root, int target) {
        if (root == null) return null;
        if (root.value == target) {
            return root;
        }
        TreeNode leftResult = recursiveSearch(root.left, target);
        if (leftResult != null) {
            return leftResult;
        }
        return recursiveSearch(root.right, target);
    }

    // Итеративный поиск в глубину с использованием стека
    public static TreeNode iterationSearch(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.value == target) {
                return node;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return null;
    }

    // Дополнительный метод: DFS с возвратом пути
    public static ArrayDeque<Integer> findPath(TreeNode root, int target) {
        ArrayDeque<Integer> path = new ArrayDeque<>();
        boolean found = findPathRecursive(root, target, path);
        return found ? path : new ArrayDeque<>();
    }

    private static boolean findPathRecursive(TreeNode node, int target, ArrayDeque<Integer> path) {
        if (node == null) return false;

        path.addLast(node.value);

        if (node.value == target) {
            return true;
        }

        if (findPathRecursive(node.left, target, path) ||
                findPathRecursive(node.right, target, path)) {
            return true;
        }

        path.removeLast();
        return false;
    }

    // Тестирование
    public static void main(String[] args) {
        System.out.println("=== Тестирование поиска в глубину (DFS) ===\n");

        // Создаем тестовое дерево
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)
                ),
                new TreeNode(3,
                        new TreeNode(6),
                        new TreeNode(7)
                )
        );

        System.out.println("Структура дерева:");
        System.out.println("      1");
        System.out.println("     / \\");
        System.out.println("    2   3");
        System.out.println("   / \\ / \\");
        System.out.println("  4  5 6  7");
        System.out.println();

        // Тест 1 поиск существующих вершин
        System.out.println("Тест 1: Поиск существующих вершин");
        testSearch(root, 5, "5");
        testSearch(root, 3, "3");
        testSearch(root, 1, "1");

        // Тест 2 поиск несуществующих вершин
        System.out.println("\nТест 2: Поиск несуществующих вершин");
        testSearch(root, 8, "8");
        testSearch(root, 0, "0");

        // Тест 3 поиск пути
        System.out.println("\nТест 3: Поиск пути к вершине");
        testPath(root, 5);
        testPath(root, 7);
        testPath(root, 9);

        // Тест 4: Пустое дерево
        System.out.println("\nТест 4: Пустое дерево");
        testSearch(null, 5, "5");
    }

    private static void testSearch(TreeNode root, int target, String targetName) {
        TreeNode recursiveResult = recursiveSearch(root, target);
        TreeNode iterativeResult = iterationSearch(root, target);

        System.out.printf("Поиск вершины %s: ", targetName);
        if (recursiveResult != null) {
            System.out.printf("Найдена (рекурсивно: %d, итеративно: %d)%n",
                    recursiveResult.value, iterativeResult.value);
        } else {
            System.out.println("Не найдена (оба метода вернули null)");
        }
    }

    private static void testPath(TreeNode root, int target) {
        ArrayDeque<Integer> path = findPath(root, target);
        System.out.printf("Путь к вершине %d: %s%n", target, path);
    }
}