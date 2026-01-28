import java.util.*;

// Класс узла дерева
class DNode {
    int value;
    DNode left, right;

    public DNode(int val) {
        value = val;
        left = null;
        right = null;
    }
}

public class Task3 {

    // 1. Рекурсивный DFS
    public static void RecursiveDFS(DNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " "); // Посещение узла
        RecursiveDFS(node.left);  // Рекурсия влево
        RecursiveDFS(node.right); // Рекурсия вправо
    }

    // 2. Итеративный DFS
    public static void IterativeDFS(DNode root) {
        if (root == null) {
            return;
        }

        Stack<DNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            DNode current = stack.pop();
            System.out.print(current.value + " ");

            // Добавляем сначала правый, потом левый, чтобы левый обрабатывался первым
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public static void main(String[] args) {
        DNode root = new DNode(1);
        root.left = new DNode(2);
        root.right = new DNode(3);
        root.left.left = new DNode(4);
        root.left.right = new DNode(5);

        System.out.println("Recursive DFS:");
        RecursiveDFS(root); // Вывод: 1 2 4 5 3
        System.out.println("\nIterative DFS:");
        IterativeDFS(root); // Вывод: 1 2 4 5 3
    }
}