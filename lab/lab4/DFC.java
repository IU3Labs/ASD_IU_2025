// Реализовать поиск в дереве в глубину двумя способами.
import java.util.ArrayDeque;
public class DFC {
    public static void main(String[] args) {
        // Генерируем тестовое дерево для демонстрации работы алгоритмов
        TreeNode tree = TreeNode.generateTestTree();
        // Проверка итеративного поиска в глубину
        System.out.println(dfsIterative(tree, 11));    // Поиск существующего значения
        System.out.println(dfsIterative(null, 11));    // Поиск в пустом дереве
        System.out.println(dfsIterative(tree, 7));     // Поиск другого существующего значения
        // Проверка рекурсивного поиска в глубину
        System.out.println(dfsRecursive(tree, 11));    // Поиск существующего значения
        System.out.println(dfsRecursive(null, 11));    // Поиск в пустом дереве
        System.out.println(dfsRecursive(tree, 7));     // Поиск другого существующего значения
    }
    /*
     * Итеративный поиск в глубину с использованием стека
     * Преимущества: избегает переполнения стека вызовов, явный контроль над процессом
     *
     * @param root - корень дерева для поиска
     * @param target - целевое значение для поиска
     * @return узел с целевым значением или null если не найден
     */
    private static TreeNode dfsIterative(TreeNode root, int target) {
        // Проверка на пустое дерево
        if (root == null) return null;
        // Используем двустороннюю очередь как стек
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root); // Начинаем с корневого узла
        // Пока в стеке есть узлы для обработки
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(); // Извлекаем узел из стека (LIFO)
            // Проверяем, является ли текущий узел целевым
            if (node.val == target) return node;
            /*
             * Добавляем потомков в стек: сначала правый, затем левый
             * Это обеспечит порядок обхода: левый потомок будет обработан первым
             * (так как стек LIFO - последний зашел, первый вышел)
             */
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return null; // Целевое значение не найдено
    }
    /*
     * Рекурсивный поиск в глубину
     * Преимущества: простой и лаконичный код, естественная реализация для деревьев
     * Недостатки: возможное переполнение стека вызовов для больших деревьев
     *
     * @param root - корень дерева для поиска
     * @param target - целевое значение для поиска
     * @return узел с целевым значением или null если не найден
     */
    private static TreeNode dfsRecursive(TreeNode root, int target) {
        // Базовый случай: достигнут конец ветки или пустое дерево
        if (root == null) return null;
        // Проверяем текущий узел
        if (root.val == target) return root;
        // Рекурсивно ищем в левом поддереве
        TreeNode leftResult = dfsRecursive(root.left, target);
        // Если нашли в левом поддереве - сразу возвращаем результат
        if (leftResult != null) return leftResult;
        // Если в левом поддереве не нашли, ищем в правом поддереве
        TreeNode rightResult = dfsRecursive(root.right, target);
        return rightResult;
    }
}