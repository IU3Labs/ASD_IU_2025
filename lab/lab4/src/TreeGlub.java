import java.util.ArrayList;
import java.util.List;

public class TreeGlub {

    // Узел дерева
    static class Node {
        int value;
        List<Node> children;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public void addChild(Node child) {
            children.add(child);
        }
    }

    // Рекурсивный
    public static Node recursive(Node node, int target) {
        if (node == null) {
            return null;
        }

        if (node.value == target) {
            return node;
        }

        for (Node child : node.children) {
            Node result = recursive(child, target);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    public static Node iterative(Node root, int target) {
        if (root == null) {
            return null;
        }

        List<Node> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            List<Node> nextLevel = new ArrayList<>();

            for (Node current : currentLevel) {
                if (current.value == target) {
                    return current;
                }

                nextLevel.addAll(current.children);
            }
            currentLevel = nextLevel;
        }

        return null;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node grandChild1 = new Node(5);
        Node grandChild2 = new Node(6);

        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(child3);
        child3.addChild(grandChild1);
        child3.addChild(grandChild2);

        // Рекурсивный поиск
        Node recursiveResult = recursive(root, 6);
        if (recursiveResult != null) {
            System.out.println("Рекурсивный поиск: найден узел с значением " + recursiveResult.value);
        } else {
            System.out.println("Рекурсивный поиск: узел не найден");
        }

        // Итеративный поиск без использования стека
        Node iterativeResult = iterative(root, 6);
        if (iterativeResult != null) {
            System.out.println("Итеративный поиск: найден узел с значением " + iterativeResult.value);
        } else {
            System.out.println("Итеративный поиск: узел не найден");
        }
    }
}
