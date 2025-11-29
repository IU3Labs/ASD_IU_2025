// Реализовать поиск в дереве в ширину двумя способами.
import java.util.ArrayDeque;
public class BFS {
    public static void main(String[] args) {
        // Генерируем тестовое дерево для демонстрации работы алгоритмов
        TreeNode tree = TreeNode.generateTestTree();
        // Проверка поиска в ширину (итеративный BFS)
        System.out.println(findNodeBFS(tree, 11));    // Поиск несуществующего значения
        System.out.println(findNodeBFS(null, 11));    // Поиск в пустом дереве
        System.out.println(findNodeBFS(tree, 7));     // Поиск существующего значения
        // Проверка поиска по уровням (рекурсивный BFS)
        System.out.println(findNodeLevelOrder(tree, 11)); // Поиск несуществующего значения
        System.out.println(findNodeLevelOrder(null, 11)); // Поиск в пустом дереве
        System.out.println(findNodeLevelOrder(tree, 7));  // Поиск существующего значения
    }
    /*
     * Итеративный поиск в ширину (BFS) с использованием очереди
     * Обходит дерево уровень за уровнем, начиная с корня
     * Преимущества: находит кратчайший путь, не переполняет стек вызовов
     * @param root - корень дерева для поиска
     * @param target - целевое значение для поиска
     * @return узел с целевым значением или null если не найден
     */
    private static TreeNode findNodeBFS(TreeNode root, int target) {
        // Проверка на пустое дерево
        if (root == null) return null;
        // Используем очередь для хранения узлов текущего уровня (FIFO)
        ArrayDeque<TreeNode> nodesQueue = new ArrayDeque<>();
        nodesQueue.add(root); // Начинаем с корневого узла
        // Пока в очереди есть узлы для обработки
        while (!nodesQueue.isEmpty()) {
            TreeNode current = nodesQueue.poll(); // Извлекаем узел из начала очереди
            // Проверяем, является ли текущий узел целевым
            if (current.val == target) return current;
            /*
             * Добавляем потомков в конец очереди: сначала левый, затем правый
             * Это обеспечит обход уровня слева направо
             */
            if (current.left != null) nodesQueue.add(current.left);
            if (current.right != null) nodesQueue.add(current.right);
        }
        return null; // Целевое значение не найдено
    }
    /*
     * Рекурсивный поиск по уровням (Level Order Search)
     * Альтернативная реализация BFS с использованием рекурсии
     * Обходит дерево уровень за уровнем рекурсивно
     * @param root - корень дерева для поиска
     * @param target - целевое значение для поиска
     * @return узел с целевым значением или null если не найден
     */
    private static TreeNode findNodeLevelOrder(TreeNode root, int target) {
        if (root == null) return null;
        // Создаем очередь для первого уровня (только корень)
        ArrayDeque<TreeNode> currentLevel = new ArrayDeque<>();
        currentLevel.add(root);
        // Запускаем рекурсивный поиск по уровням
        return searchLevel(currentLevel, target);
    }
    /*
     * Рекурсивный метод для поиска на текущем уровне и перехода к следующему
     * @param level - очередь узлов текущего уровня
     * @param target - целевое значение для поиска
     * @return узел с целевым значением или null если не найден
     */
    private static TreeNode searchLevel(ArrayDeque<TreeNode> level, int target) {
        // Базовый случай: достигнут конец дерева
        if (level.isEmpty()) return null;
        // Создаем очередь для следующего уровня
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>();
        // Обрабатываем все узлы текущего уровня
        for (TreeNode node : level) {
            // Проверяем текущий узел
            if (node.val == target) return node;
            // Добавляем потомков в очередь следующего уровня
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }
        // Рекурсивно переходим к следующему уровню
        return searchLevel(nextLevel, target);
    }
}