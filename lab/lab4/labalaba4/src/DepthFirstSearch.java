import java.util.*;

public class DepthFirstSearch {

    public static List<Integer> dfsRecursive(TreeNode root, int target) {
        List<Integer> path = new ArrayList<>();
        if (dfsRecursiveHelper(root, target, path)) {
            return path;
        } else {
            return new ArrayList<>(); //не найдено
        }
    }

    private static boolean dfsRecursiveHelper(TreeNode node, int target, List<Integer> path) {
        if (node == null) {
            return false;
        }

        //добавляем текущий узел в путь
        path.add(node.val);

        // проверка нашли ли?
        if (node.val == target) {
            return true;
        }

        //рекурсивно ищем в детях
        for (TreeNode child : node.children) {
            if (dfsRecursiveHelper(child, target, path)) {
                return true; // нашли, не удаляем из path
            }
        }

        // target не в этом поддереве, следовательно, удаляем узел из пути
        path.remove(path.size() - 1);
        return false;
    }

    public static List<Integer> dfsIterative(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<List<Integer>> pathStack = new Stack<>();

        //начинаем с корня
        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(root.val);
        nodeStack.push(root);
        pathStack.push(initialPath);

        if (root.val == target) {
            return initialPath;
        }

        while (!nodeStack.isEmpty()) {
            TreeNode current = nodeStack.pop();
            List<Integer> currentPath = pathStack.pop();

            // обходим детей (в обратном порядке, чтобы сохранить "слева направо")
            for (int i = current.children.size() - 1; i >= 0; i--) {
                TreeNode child = current.children.get(i);
                List<Integer> newPath = new ArrayList<>(currentPath); // копируем путь
                newPath.add(child.val);

                if (child.val == target) {
                    return newPath; // нашли!
                }

                nodeStack.push(child);
                pathStack.push(newPath);
            }
        }

        //не найдено
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // Дерево:
        //        1
        //      / | \
        //     2  3  4
        //    /|     |\
        //   5 6     7 8
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        root.addChild(n2);
        root.addChild(n3);
        root.addChild(n4);
        n2.addChild(n5);
        n2.addChild(n6);
        n4.addChild(n7);
        n4.addChild(n8);

        int target = 7;

        List<Integer> recPath = DepthFirstSearch.dfsRecursive(root, target);
        List<Integer> iterPath = DepthFirstSearch.dfsIterative(root, target);

        System.out.println("Цель: " + target);
        System.out.println("Рекурсивный путь: " + recPath);
        System.out.println("Итеративный путь: " + iterPath);

        if (recPath.equals(iterPath)) {
            System.out.println("Пути совпадают!");
        } else {
            System.out.println("Пути не совпадают!");
        }

        //тест: элемент отсутствует
        System.out.println("\nПоиск несуществующего элемента (99):");
        System.out.println("Рекурсивный: " + DepthFirstSearch.dfsRecursive(root, 99));
        System.out.println("Итеративный: " + DepthFirstSearch.dfsIterative(root, 99));
    }

}