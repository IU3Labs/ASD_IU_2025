public class Tree {
    public static class Node {
        Integer data;
        Node left, right;

        Node(Integer data) {
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

            if (node.data < parent.data) {
                current = current.left;
            } else if (node.data > parent.data) {
                current = current.right;
            } else { 
                return; 
            }
        }

        if (node.data < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }
}
