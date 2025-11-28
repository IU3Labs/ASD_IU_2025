package lab2;

//Реализовать красно-черное дерево. Прокомментировать логику.

public class RedBlackTree {

    public static final int RED = 0;
    public static final int BLACK = 1;

    static class Node {
        int data;
        int color;
        Node left;
        Node right;
        Node parent;

        Node(int data) {
            this.data = data;
            this.color = RED;     // новый узел всегда красный
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public static class redBlackTree {

        private Node root;

        public redBlackTree() {
            root = null;
        }

        public Node getRoot() {
            return root;
        }


        public void insertNode(int data) {
            Node node = new Node(data);
            bstInsert(node);
            fixInsert(node);
        }


        private void bstInsert(Node node) {
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


        // Исправление свойств КЧД
        private void fixInsert(Node node) {

            while (node != root) {
                Node parent = node.parent;
                if (parent == null) break;
                if (parent.color != RED) break;

                Node grand = parent.parent;
                if (grand == null) break;

                // Случаи, когда parent — левый ребёнок деда
                if (parent == grand.left) {
                    Node uncle = grand.right;

                    // Случай 1: uncle красный — перекрашиваем вверх
                    if (uncle != null && uncle.color == RED) {
                        parent.color = BLACK;
                        uncle.color = BLACK;
                        grand.color = RED;
                        node = grand; // поднимаемся вверх и повторяем
                    } else {
                        // Случай 2: node — правый ребёнок -> левый поворот parent
                        if (node == parent.right) {
                            node = parent;
                            rotateLeft(node);
                            parent = node.parent;
                            grand = (parent != null) ? parent.parent : null;
                            if (parent == null || grand == null) continue;
                        }
                        // Случай 3: node — левый ребёнок -> правый поворот grand
                        parent.color = BLACK;
                        grand.color = RED;
                        rotateRight(grand);
                    }
                } else {
                    // Зеркальные случаи: parent — правый ребёнок деда
                    Node uncle = grand.left;

                    if (uncle != null && uncle.color == RED) {
                        parent.color = BLACK;
                        uncle.color = BLACK;
                        grand.color = RED;
                        node = grand;
                    } else {
                        if (node == parent.left) {
                            node = parent;
                            rotateRight(node);
                            parent = node.parent;
                            grand = (parent != null) ? parent.parent : null;
                            if (parent == null || grand == null) continue;
                        }
                        parent.color = BLACK;
                        grand.color = RED;
                        rotateLeft(grand);
                    }
                }
            }

            if (root != null) root.color = BLACK;
        }

        // Левый поворот
        private void rotateLeft(Node upperNode) {
            Node lowerNode = upperNode.right;

            upperNode.right = lowerNode.left;
            if (lowerNode.left != null)
                lowerNode.left.parent = upperNode;

            lowerNode.parent = upperNode.parent;

            if (upperNode.parent == null) {
                root = lowerNode;
            } else if (upperNode == upperNode.parent.left) {
                upperNode.parent.left = lowerNode;
            } else {
                upperNode.parent.right = lowerNode;
            }

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

            if (upperNode.parent == null) {
                root = lowerNode;
            } else if (upperNode == upperNode.parent.right) {
                upperNode.parent.right = lowerNode;
            } else {
                upperNode.parent.left = lowerNode;
            }

            lowerNode.right = upperNode;
            upperNode.parent = lowerNode;
        }

        //Вывод
        public void treeOutput(Node node) {
            if (node != null) {
                treeOutput(node.left);
                System.out.print(node.data + " ");
                treeOutput(node.right);
            }
        }
    }


    public static void main(String[] args) {

        redBlackTree tree = new redBlackTree();

        int[] values = {1, 20, 35, 2, 4, 3, 12, 16, 56, 78, 31};
        for (int val : values) {
            tree.insertNode(val);
        }

        System.out.println("Вывод дерева в порядке возрастания:");
        tree.treeOutput(tree.getRoot());
        System.out.println();
    }
}
