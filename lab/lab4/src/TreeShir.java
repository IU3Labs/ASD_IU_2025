import java.util.ArrayList;
import java.util.List;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

public class TreeShir {
    Node root;

    void printIterative() {
        if (root == null) return;

        List<Node> currentLevel = new ArrayList<>();
        List<Node> nextLevel = new ArrayList<>();

        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            for (Node node : currentLevel) {
                System.out.print(node.data + " ");

                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
    }

    void printRecursive() {
        int height = getHeight(root);
        for (int i = 1; i <= height; i++) {
            printCurrentLevel(root, i);
            System.out.println();
        }
    }

    int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    void printCurrentLevel(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            printCurrentLevel(node.left, level - 1);
            printCurrentLevel(node.right, level - 1);
        }
    }

    public static void main(String[] args) {
        TreeShir tree = new TreeShir();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Поиск в ширину (итеративный метод):");
        tree.printIterative();

        System.out.println("Поиск в ширину (рекурсивный метод):");
        tree.printRecursive();
    }
}
