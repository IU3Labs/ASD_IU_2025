package tasks.task1;

import tasks.helper.TreeNode;


public class RecursiveDFS {

    //Рекурсивный поиск узла по значению
    public static TreeNode search(TreeNode root, int target) {
        // Базовый случай: узел не существует
        if (root == null) return null;

        // Если нашли нужный узел, возвращаем его
        if (root.value == target) return root;

        // Рекурсивно ищем в каждом дочернем узле
        for (TreeNode child : root.children) {
            TreeNode result = search(child, target);
            // Если нашли в одном из поддеревьев, возвращаем результат
            if (result != null) return result;
        }

        // Не нашли в этом поддереве
        return null;
    }
}
