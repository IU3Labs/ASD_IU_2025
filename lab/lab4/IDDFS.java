// Реализовать поиск в дереве (Iterative deepening depth-first search)
public class IDDFS {
    public static void main(String[] args) {
        // Генерируем тестовое дерево для демонстрации работы алгоритма
        TreeNode tree = TreeNode.generateTestTree();
        // Тестирование поиска различных значений в дереве
        System.out.println(iddfs(tree, 11)); // Поиск существующего значения
        System.out.println(iddfs(null, 11)); // Поиск в пустом дереве
        System.out.println(iddfs(tree, 7));  // Поиск другого существующего значения
        System.out.println(iddfs(tree, 4));  // Поиск возможного значения
    }
    /*
     * Реализация поиска в глубину с итеративным углублением (IDDFS)
     * Алгоритм сочетает преимущества поиска в глубину и ширину:
     * - Экономия памяти как у DFS
     * - Нахождение кратчайшего пути как у BFS
     *блаблабла чтобы add сработал
     *
     * @param root - корень дерева для поиска
     * @param target - целевое значение для поиска
     * @return узел с целевым значением или null если не найден
     */
    private static TreeNode iddfs(TreeNode root, int target) {
        // Проверка на пустое дерево
        if (root == null) return null;
        // Определяем максимальную глубину дерева для ограничения поиска
        int maxDepth = getMaxDepth(root);
        TreeNode result;
        /*
         * Последовательно увеличиваем глубину поиска от 0 до максимальной
         * На каждой итерации выполняем поиск в глубину с ограничением глубины
         */
        for (int depth = 0; depth <= maxDepth; depth++) {
            result = depthLimitedSearch(root, target, depth);
            if (result != null) {
                return result; // Возвращаем найденный узел
            }
        }
        return null; // Целевое значение не найдено во всем дереве
    }
    /*
     * Рекурсивный поиск в глубину с ограничением глубины
     *
     * @param node - текущий узел для проверки
     * @param target - целевое значение для поиска
     * @param depth - оставшаяся глубина для поиска
     * @return узел с целевым значением или null если не найден на данной глубине
     */
    private static TreeNode depthLimitedSearch(TreeNode node, int target, int depth) {
        // Базовый случай: достигнут конец ветки
        if (node == null) return null;
        // Проверяем, совпадает ли значение текущего узла с целевым
        if (node.val == target) return node;
        // Базовый случай: достигнут предел глубины - дальше не ищем
        if (depth == 0) return null;
        // Рекурсивно ищем в левом поддереве с уменьшенной глубиной
        TreeNode leftResult = depthLimitedSearch(node.left, target, depth - 1);
        if (leftResult != null) return leftResult;
        // Если в левом поддереве не найдено, ищем в правом поддереве
        TreeNode rightResult = depthLimitedSearch(node.right, target, depth - 1);
        return rightResult;
    }
    /*
     * Вспомогательный метод для определения максимальной глубины дерева
     * Исп. для ограничения цикла IDDFS и предотвращения бесконечного поиска
     * @param node - корень дерева для вычисления глубины
     * @return максимальная глубина дерева
     */
    private static int getMaxDepth(TreeNode node) {
        if (node == null) return 0;
        // Рекурсивно вычисляем глубину как максимум из глубин поддеревьев + 1
        return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
    }
}