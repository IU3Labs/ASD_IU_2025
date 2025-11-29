package lab4;
public class Tree {
    public static class Node {
        Integer data;
        Node left, right;

        public Node(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public Tree() {
        root = null;
    }

    public void insert(Integer data) {
        Node node = new Node(data);

        if (root == null) {
            root = node;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;

            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else {
                return; // узел уже существует
            }
        }

        if (data < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }
}
