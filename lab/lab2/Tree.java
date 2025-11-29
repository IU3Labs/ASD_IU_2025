package lab2;

public class Tree {
    private Node first_node;
    private Node nullNode; // Представитель null-листьев

    // корень
    public Tree() {
        nullNode = new Node(Integer.MIN_VALUE, null);
        nullNode.color = "BLACK";
        first_node = nullNode;
    }

    // добавление нового элемента
    public void add(int value) {
        if (first_node == nullNode) {
            first_node = createNode(value, null);
            first_node.color = "BLACK"; // корень всегда черный
        }
        else{
            recursiveSearchAndInsert(value, first_node);
        }
    }

    // Рекурсивный поиск места и вставка
    private void recursiveSearchAndInsert(int value, Node currentNode) {
        // идем влево
        if (value < currentNode.value) {
            // слева пусто
            if (currentNode.left_child == nullNode) {
                Node newNode = createNode(value, currentNode);
                currentNode.left_child = newNode;
                balace(newNode);
                // слева непусто
            } else {
                recursiveSearchAndInsert(value, currentNode.left_child);
            }
        }
        // идем вправо
        else if (value > currentNode.value) {
            if (currentNode.right_child == nullNode) {
                Node newNode = createNode(value, currentNode);
                currentNode.right_child = newNode;
                balace(newNode);
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

    private void balace(Node node) {
        // яхочупиццы
        while (node.parent != null && "RED".equals(node.parent.color)) {
            if (node.parent == node.parent.parent.left_child) {
                Node uncle = node.parent.parent.right_child;

                // красный дядя
                if (uncle != null && "RED".equals(uncle.color)) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    node.parent.parent.color = "RED";
                    node = node.parent.parent; // ползем вверх
                }
                // черный дядя
                else {
                    // черный дядя и я большой
                    if (node == node.parent.right_child) { // проверка, что я большой
                        node = node.parent;
                        leftRot(node); // левый поворот
                    }
                    // черный дядя и я маленький
                    if (node == node.parent.right_child) {
                        node = node.parent;
                        leftRot(node); 
                    }
                    // дядя черный и узел является левым потомком
                    node.parent.color = "BLACK";
                    node.parent.parent.color = "RED";
                    rightRot(node.parent.parent);
                }
            } else {
                // Симметрично для правого поддерева
                Node uncle = node.parent.parent.left_child;
                if (uncle != null && "RED".equals(uncle.color)) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    node.parent.parent.color = "RED";
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left_child) {
                        node = node.parent;
                        rightRot(node);
                    }
                    node.parent.color = "BLACK";
                    node.parent.parent.color = "RED";
                    leftRot(node.parent.parent);
                }
            }
        }
        first_node.color = "BLACK";
    }

    private void leftRot(Node x) {
        Node y = x.right_child; // берем правого ребенка
        x.right_child = y.left_child; // правый ребенок становится левым внуком правого ребенка

        if (y.left_child != null) {
            y.left_child.parent = x; // правый ребенок теперь родитель
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

    private void rightRot(Node y) {
        Node x = y.left_child;
        y.left_child = x.right_child;

        if (x.right_child != null) {
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
