import java.util.ArrayDeque;

//4 Реализовать поиск в дереве в ширину двумя способами.
public class TreeBFS {

    // Итеративный поиск в ширину с использованием очереди
    public static TreeNode iterationSearch(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            if (node.value == target) {
                return node;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return null;
    }

    // Рекурсивный поиск в ширину (имитация с помощью очереди)
    public static TreeNode recursiveSearch(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        return recursiveHelper(queue, target);
    }

    private static TreeNode recursiveHelper(ArrayDeque<TreeNode> queue, int target) {
        if (queue.isEmpty()) return null;
        TreeNode node = queue.poll();
        if (node.value == target) {
            return node;
        }
        if (node.left != null) {
            queue.offer(node.left);
        }
        if (node.right != null) {
            queue.offer(node.right);
        }
        return recursiveHelper(queue, target);
    }

    // Дополнительный метод: BFS с поиском уровня вершины
    public static int findLevel(TreeNode root, int target) {
        if (root == null) return -1;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.value == target) {
                    return level;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return -1;
    }

    // Дополнительный метод: BFS с выводом уровней
    public static void printLevels(TreeNode root) {
        if (root == null) return;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;

        System.out.println("Обход дерева по уровням:");
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.printf("Уровень %d: ", level);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.value + " ");

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
            level++;
        }
    }

    // Тестирование
    public static void main(String[] args) {
        System.out.println("=== Тестирование поиска в ширину (BFS) ===\n");

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
        System.out.println("    2  3");
        System.out.println("   / \\ / \\");
        System.out.println("  4   5 6  7");
        System.out.println();

        // Вывод обхода по уровням
        printLevels(root);
        System.out.println();

        // Тест 1: Поиск существующих вершин
        System.out.println("Тест 1: Поиск существующих вершин");
        testSearch(root, 5, "5");
        testSearch(root, 3, "3");
        testSearch(root, 1, "1");

        // Тест 2: Поиск несуществующих вершин
        System.out.println("\nТест 2: Поиск несуществующих вершин");
        testSearch(root, 8, "8");
        testSearch(root, 0, "0");

        // Тест 3: Поиск уровня вершины
        System.out.println("\nТест 3: Поиск уровня вершины");
        testLevel(root, 4);
        testLevel(root, 3);
        testLevel(root, 5);
        testLevel(root, 8);

        // Тест 4: Пустое дерево
        System.out.println("\nТест 4: Пустое дерево");
        testSearch(null, 5, "5");
    }

    private static void testSearch(TreeNode root, int target, String targetName) {
        TreeNode iterativeResult = iterationSearch(root, target);
        TreeNode recursiveResult = recursiveSearch(root, target);

        System.out.printf("Поиск вершины %s: ", targetName);
        if (iterativeResult != null) {
            System.out.printf("Найдена (итеративно: %d, рекурсивно: %d)%n",
                    iterativeResult.value, recursiveResult.value);
        } else {
            System.out.println("Не найдена (оба метода вернули null)");
        }
    }

    private static void testLevel(TreeNode root, int target) {
        int level = findLevel(root, target);
        if (level != -1) {
            System.out.printf("Вершина %d находится на уровне %d%n", target, level);
        } else {
            System.out.printf("Вершина %d не найдена в дереве%n", target);
        }
    }
}