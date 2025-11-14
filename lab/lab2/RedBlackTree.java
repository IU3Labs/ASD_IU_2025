public static final int RED = 0;
public static final int BLACK = 1;


static class Node {
    int data;
    int color;
    Node left;
    Node right;
    Node parent;


    Node(int data, int color) {
        this.data = data;
        this.color = color;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}


public static class RedBlackTree {
    private Node root;


    public RedBlackTree() {
        root = null;
    }


    public void insertNode(int data) {
        Node newNode = new Node(data, RED);
        rebalanceTree(newNode);
        treeRotate(newNode);
    }

    private void rebalanceTree(Node node) {
        Node current = root;
        Node parent = null;


        while (current != null) {
            parent = current;
            if (node.data < current.data)
                current = current.left;
            else
                current = current.right;
        }

        node.parent = parent;


        if (parent == null) {
            root = node;
        } else if (node.data < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }


    private void treeRotate(Node node) {

        while (node != root && node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) {

                Node uncle = node.parent.parent.right;

                if (uncle != null && uncle.color == RED) {
                    // 1 Случай, когда дядя тоже красный
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    // 2 и 3 случай, когда дядя чёрный или отсутствует
                    if (node == node.parent.right) {
                        // 2 случай -> левый поворот
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // 3 случай -> правый поворот
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }
            } else {

                Node uncle = node.parent.parent.left;

                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }

                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    // Левый поворот
    private void rotateLeft(Node upperNode) {
        Node lowerNode = upperNode.right;
        upperNode.right = lowerNode.left;
        if (lowerNode.left != null)
            lowerNode.left.parent = upperNode;
        lowerNode.parent = upperNode.parent;
        if (upperNode.parent == null)
            root = lowerNode;
        else if (upperNode == upperNode.parent.left)
            upperNode.parent.left = lowerNode;
        else
            upperNode.parent.right = lowerNode;
        lowerNode.left = upperNode;
        upperNode.parent = lowerNode;
    }

    // Правый поворот
    private void rotateRight(Node upperNode) {
        Node lowerNode = upperNode.left;
        upperNode.left = lowerNode.right;
        if (lowerNode.right != null)
            lowerNode.right.parent = upperNode;
        lowerNode.parent = upperNode.parent;
        if (upperNode.parent == null)
            root = lowerNode;
        else if (upperNode == upperNode.parent.right)
            upperNode.parent.right = lowerNode;
        else
            upperNode.parent.left = lowerNode;
        lowerNode.right = upperNode;
        upperNode.parent = lowerNode;
    }


    public void treeOutput(Node node) {
        if (node != null) {
            treeOutput(node.left);
            System.out.print(node.data + " ");
            treeOutput(node.right);
        }
    }

}

void main() {
    RedBlackTree tree = new RedBlackTree();


    int[] values = {10, 20, 30, 15, 25, 5, 1, 24, 53, 19};
    for (int val : values) {
        tree.insertNode(val);
    }


    System.out.println("Вывод дерева в порядке возрастания: ");
    tree.treeOutput(tree.root);
    System.out.println();
}


