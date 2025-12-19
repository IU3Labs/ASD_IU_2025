public class Tree {

    // Внутренний класс узла
    public static class Node {
        Integer data;
        Node left;
        Node right;

        public Node(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public Tree() {
        this.root = null;
    }

    // Вставка нового узла в дерево (рекурсивно)
    public void insert(Integer data) {
        root = insertRecursive(root, data);
    }

    // Вспомогательный метод для рекурсивной вставки
    private Node insertRecursive(Node current, Integer data) {
        if (current == null) {
            return new Node(data);
        }

        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
        }

        return current;
    }
}
