import java.util.*;

enum Color {
    RED, BLACK
}

class RBNode {
    int data;
    Color color;
    RBNode left, right, parent;

    public RBNode(int data) {
        this.data = data;
        this.color = Color.RED;
        this.left = this.right = this.parent = null;
    }
}

public class RedBlackTree {
    private RBNode root;
    private final RBNode nil; // узел-лист

    public RedBlackTree() {
        nil = new RBNode(0);
        nil.color = Color.BLACK;
        root = nil;
    }

    // 1. ДОБАВЛЕНИЕ ЭЛЕМЕНТА
    public void insert(int key) {
        RBNode node = new RBNode(key);
        node.left = nil;
        node.right = nil;

        RBNode parent = null;
        RBNode current = root;

        // Поиск места для вставки
        while (current != nil) {
            parent = current;
            if (node.data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        node.parent = parent;

        if (parent == null) {
            root = node;
        } else if (node.data < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        // Если это корень - красим в черный
        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        // Если дед не существует - выходим
        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Балансировка после вставки
    private void fixInsert(RBNode node) {
        while (node.parent != null && node.parent.color == Color.RED) {
            RBNode parent = node.parent;
            RBNode grandparent = parent.parent;

            if (parent == grandparent.left) {
                RBNode uncle = grandparent.right;

                if (uncle.color == Color.RED) {
                    // Случай 1: Дядя красный
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        // Случай 2: Треугольная форма
                        node = parent;
                        leftRotate(node);
                    }
                    // Случай 3: Линейная форма
                    parent.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    rightRotate(grandparent);
                }
            } else {
                // Симметричный случай
                RBNode uncle = grandparent.left;

                if (uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        // Случай 2: Треугольная форма
                        node = parent;
                        rightRotate(node);
                    }
                    // Случай 3: Линейная форма
                    parent.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    leftRotate(grandparent);
                }
            }

            if (node == root) break;
        }

        root.color = Color.BLACK;
    }

    // Левый поворот
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;

        if (y.left != nil) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    // Правый поворот
    private void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;

        if (x.right != nil) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    // 2. УДАЛЕНИЕ ЭЛЕМЕНТА
    public void delete(int key) {
        deleteNodeHelper(root, key);
    }

    private void deleteNodeHelper(RBNode node, int key) {
        RBNode z = nil;

        // Поиск удаляемого узла
        while (node != nil) {
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == nil) {
            return; // элемент не найден
        }

        RBNode y = z;
        Color yOriginalColor = y.color;
        RBNode x;

        if (z.left == nil) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;

            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.BLACK) {
            fixDelete(x);
        }
    }

    // Балансировка после удаления
    private void fixDelete(RBNode x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                RBNode sibling = x.parent.right;

                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    sibling = x.parent.right;
                }

                if (sibling.left.color == Color.BLACK && sibling.right.color == Color.BLACK) {
                    sibling.color = Color.RED;
                    x = x.parent;
                } else {
                    if (sibling.right.color == Color.BLACK) {
                        sibling.left.color = Color.BLACK;
                        sibling.color = Color.RED;
                        rightRotate(sibling);
                        sibling = x.parent.right;
                    }

                    sibling.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    sibling.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                // Симметричный случай
                RBNode sibling = x.parent.left;

                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    sibling = x.parent.left;
                }

                if (sibling.right.color == Color.BLACK && sibling.left.color == Color.BLACK) {
                    sibling.color = Color.RED;
                    x = x.parent;
                } else {
                    if (sibling.left.color == Color.BLACK) {
                        sibling.right.color = Color.BLACK;
                        sibling.color = Color.RED;
                        leftRotate(sibling);
                        sibling = x.parent.left;
                    }

                    sibling.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    sibling.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }

        x.color = Color.BLACK;
    }

    // Вспомогательные методы для удаления
    private void rbTransplant(RBNode u, RBNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private RBNode minimum(RBNode node) {
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

    // 3. ПОДСЧЕТ ЧИСЛА ЭЛЕМЕНТОВ
    public int count() {
        return countNodes(root);
    }

    private int countNodes(RBNode node) {
        if (node == nil) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 4. ПЕЧАТЬ ДЕРЕВА
    public void printInOrder() {
        System.out.print("In-order: ");
        printInOrder(root);
        System.out.println();
    }

    public void printPreOrder() {
        System.out.print("Pre-order: ");
        printPreOrder(root);
        System.out.println();
    }

    public void printVisual() {
        System.out.println("Визуальное представление:");
        printVisual(root, "", true);
    }

    private void printInOrder(RBNode node) {
        if (node != nil) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + (node.color == Color.RED ? "R" : "B") + ") ");
            printInOrder(node.right);
        }
    }

    private void printPreOrder(RBNode node) {
        if (node != nil) {
            System.out.print(node.data + "(" + (node.color == Color.RED ? "R" : "B") + ") ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    private void printVisual(RBNode node, String indent, boolean last) {
        if (node != nil) {
            System.out.print(indent);
            if (last) {
                System.out.print("└── ");
                indent += "    ";
            } else {
                System.out.print("├── ");
                indent += "│   ";
            }

            String color = node.color == Color.RED ? "R" : "B";
            System.out.println(node.data + "(" + color + ")");

            printVisual(node.left, indent, false);
            printVisual(node.right, indent, true);
        }
    }

    // Поиск элемента
    public boolean contains(int key) {
        return search(key) != nil;
    }

    private RBNode search(int key) {
        RBNode current = root;
        while (current != nil) {
            if (key == current.data) {
                return current;
            } else if (key < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return nil;
    }

    // ДЕМОНСТРАЦИЯ
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // 1. ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ
        System.out.println("=== ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ ===");
        int[] elements = {50, 30, 70, 20, 40, 60, 80, 10, 25};
        for (int elem : elements) {
            tree.insert(elem);
            System.out.println("Добавлен: " + elem);
        }

        tree.printVisual();
        System.out.println("Всего элементов: " + tree.count());

        // 2. ПЕЧАТЬ В РАЗНЫХ ПОРЯДКАХ
        System.out.println("\n=== ОБХОД ДЕРЕВА ===");
        tree.printInOrder();
        tree.printPreOrder();

        // 3. ПОИСК ЭЛЕМЕНТОВ
        System.out.println("\n=== ПОИСК ЭЛЕМЕНТОВ ===");
        System.out.println("Содержит 30: " + tree.contains(30));
        System.out.println("Содержит 45: " + tree.contains(45));

        // 4. УДАЛЕНИЕ ЭЛЕМЕНТОВ
        System.out.println("\n=== УДАЛЕНИЕ ЭЛЕМЕНТОВ ===");
        tree.delete(25);
        System.out.println("Удален 25");

        tree.delete(30);
        System.out.println("Удален 30");

        tree.delete(50);
        System.out.println("Удален 50");

        tree.printVisual();
        System.out.println("Элементов после удаления: " + tree.count());

        // 5. ФИНАЛЬНАЯ ПРОВЕРКА
        System.out.println("\n=== ФИНАЛЬНАЯ ПРОВЕРКА ===");
        tree.printInOrder();
        System.out.println("Сбалансированное дерево содержит: " + tree.count() + " элементов");
    }
}