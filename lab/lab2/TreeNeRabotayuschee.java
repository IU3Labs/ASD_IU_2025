package lab2;

public class TreeNeRabotayuschee {
    private Node first_node;
    private Node nullNode;

    public TreeNeRabotayuschee() {
        nullNode = new Node(Integer.MIN_VALUE, null);
        nullNode.color = "BLACK";
        nullNode.left_child = nullNode;
        nullNode.right_child = nullNode;
        first_node = nullNode;
    }

    public void add(int value) {
        if (first_node == nullNode) {
            first_node = createNode(value, null);
            first_node.color = "BLACK";
        } else {
            recursiveSearchAndInsert(value, first_node);
        }
    }

    private void recursiveSearchAndInsert(int value, Node currentNode) {
        if (value < currentNode.value) {
            if (currentNode.left_child == nullNode) {
                Node newNode = createNode(value, currentNode);
                currentNode.left_child = newNode;
                balanceAfterInsert(newNode);
            } else {
                recursiveSearchAndInsert(value, currentNode.left_child);
            }
        } else if (value > currentNode.value) {
            if (currentNode.right_child == nullNode) {
                Node newNode = createNode(value, currentNode);
                currentNode.right_child = newNode;
                balanceAfterInsert(newNode);
            } else {
                recursiveSearchAndInsert(value, currentNode.right_child);
            }
        }
    }

    private Node createNode(int value, Node parent) {
        Node newNode = new Node(value, parent);
        newNode.left_child = nullNode;
        newNode.right_child = nullNode;
        return newNode;
    }

    private void balanceAfterInsert(Node node) {
        while (node != first_node && node.parent != null && "RED".equals(node.parent.color)) {
            if (node.parent == node.parent.parent.left_child) {
                Node uncle = node.parent.parent.right_child;

                // Случай 1: красный дядя
                if (uncle != nullNode && "RED".equals(uncle.color)) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    node.parent.parent.color = "RED";
                    node = node.parent.parent;
                } else {
                    // Случай 2: чёрный дядя, узел - правый потомок
                    if (node == node.parent.right_child) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    // Случай 3: чёрный дядя, узел - левый потомок
                    node.parent.color = "BLACK";
                    node.parent.parent.color = "RED";
                    rightRotate(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left_child;

                // Случай 1: красный дядя
                if (uncle != nullNode && "RED".equals(uncle.color)) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    node.parent.parent.color = "RED";
                    node = node.parent.parent;
                } else {
                    // Случай 2: чёрный дядя, узел - левый потомок
                    if (node == node.parent.left_child) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    // Случай 3: чёрный дядя, узел - правый потомок
                    node.parent.color = "BLACK";
                    node.parent.parent.color = "RED";
                    leftRotate(node.parent.parent);
                }
            }
        }
        first_node.color = "BLACK";
    }

    private void leftRotate(Node x) {
        Node y = x.right_child;
        x.right_child = y.left_child;

        if (y.left_child != nullNode) {
            y.left_child.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            first_node = y;
        } else if (x == x.parent.left_child) {
            x.parent.left_child = y;
        } else {
            x.parent.right_child = y;
        }

        y.left_child = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left_child;
        y.left_child = x.right_child;

        if (x.right_child != nullNode) {
            x.right_child.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == null) {
            first_node = x;
        } else if (y == y.parent.right_child) {
            y.parent.right_child = x;
        } else {
            y.parent.left_child = x;
        }

        x.right_child = y;
        y.parent = x;
    }

    public Node getRoot() {
        return first_node;
    }
}