package lab2;

import java.util.Scanner;

/**
 * Реализовать красно-черное дерево. Прокомментировать логику.
 */
public class RedBlackTree<T extends Comparable<T>> {
    public static void main(String[] args) {
        // Демонстрация работоспособности дерева и его операций
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Scanner scanner = new Scanner(System.in);

        // Для того чтобы начать удаление элементов,
        // введите строку, не являющуюся числом (или вызовите ошибку scanner любым другим способом)
        while (true) {
            System.out.print("Введите число для добавления в дерево: ");
            try {
                tree.add(scanner.nextInt());
            } catch (Exception _) {
                System.out.println("Переход на удаление элементов");
                scanner.next();
                break;
            }
            System.out.println("Новое дерево:" + tree);
            System.out.println(tree.isValid() ? "Дерево валидно" : "Дерево невалидно");
        }

        while (true) {
            System.out.print("Введите число для удаления из дерева: ");
            tree.remove(scanner.nextInt());
            System.out.println("Новое дерево:" + tree);
            System.out.println(tree.isValid() ? "Дерево валидно" : "Дерево невалидно");
        }
    }

    private Node root;
    private final Node leaf; // Специальный листовой узел

    public RedBlackTree() {
        leaf = new Node(null);
        leaf.isRed = false; // Листья всегда чёрные
        root = leaf;
    }

    /**
     * Метод для добавления элемента в дерево
     */
    public void add(T data) {
        Node newNode = new Node(data);
        newNode.left = leaf;
        newNode.right = leaf;

        Node parent = null;
        Node current = root;

        // Спускаемся по дереву, пока не дойдём до листа
        while (current != leaf) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
                System.out.print("-> left ");
            } else if (data.compareTo(current.data) > 0) {
                current = current.right;
                System.out.print("-> right ");
            } else {
                // Элемент уже существует
                System.out.println("Элемент существует");
                return;
            }
        }

        System.out.println();
        newNode.parent = parent;

        // Привязываем новый элемент к родителю или делаем его корнем
        if (parent == null) {
            root = newNode;
        } else if (data.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        fixInsert(newNode);
    }

    /**
     * Балансировка дерева после вставки
     */
    private void fixInsert(Node node) {
        while (node.parent != null && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                // Родитель - левый потомок дедушки
                Node uncle = node.parent.parent.right;

                if (uncle.isRed) {
                    // Случай 1: дядя красный - перекрашиваем
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    // Случай 2: дядя чёрный
                    if (node == node.parent.right) {
                        // Узел - правый потомок
                        node = node.parent;
                        rotateLeft(node);
                    }

                    // Случай 3: узел - левый потомок
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateRight(node.parent.parent);
                }
            } else {
                // Родитель - правый потомок дедушки (симметрично)
                Node uncle = node.parent.parent.left;

                if (uncle.isRed) {
                    // Случай 1: дядя красный
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    // Случай 2: дядя чёрный
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }

                    // Случай 3
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateLeft(node.parent.parent);
                }
            }

            if (node == root) break;
        }

        root.isRed = false; // Корень всегда чёрный
    }

    /**
     * Левый поворот (правый потомок становится родителем)
     */
    private void rotateLeft(Node pivot) {
        System.out.println("Rotating left " + pivot.data.toString() + "...");

        Node rotator = pivot.right;
        pivot.right = rotator.left;

        if (rotator.left != leaf) {
            rotator.left.parent = pivot;
        }

        rotator.parent = pivot.parent;

        if (pivot.parent == null) {
            root = rotator;
        } else if (pivot == pivot.parent.left) {
            pivot.parent.left = rotator;
        } else {
            pivot.parent.right = rotator;
        }

        rotator.left = pivot;
        pivot.parent = rotator;
    }

    /**
     * Правый поворот (левый потомок становится родителем)
     */
    private void rotateRight(Node pivot) {
        System.out.println("Rotating right " + pivot.data.toString() + "...");

        Node rotator = pivot.left;
        pivot.left = rotator.right;

        if (rotator.right != leaf) {
            rotator.right.parent = pivot;
        }

        rotator.parent = pivot.parent;

        if (pivot.parent == null) {
            root = rotator;
        } else if (pivot == pivot.parent.right) {
            pivot.parent.right = rotator;
        } else {
            pivot.parent.left = rotator;
        }

        rotator.right = pivot;
        pivot.parent = rotator;
    }

    /**
     * Удаление элемента из дерева
     */
    public void remove(T data) {
        Node node = findNode(data);
        if (node == leaf) return;
        deleteNode(node);
    }

    /**
     * Поиск узла по значению
     */
    private Node findNode(T data) {
        Node current = root;

        while (current != leaf) {
            int cmp = data.compareTo(current.data);
            if (cmp == 0) {
                return current;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return leaf;
    }

    /**
     * Удаление узла из дерева
     */
    private void deleteNode(Node z) {
        Node y = z;
        Node x;
        boolean yOriginalColor = y.isRed;

        if (z.left == leaf) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == leaf) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.isRed;
            x = y.right;

            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.isRed = z.isRed;
        }

        if (!yOriginalColor) {
            fixDelete(x);
        }
    }

    /**
     * Замена одного поддерева другим
     */
    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }

        v.parent = u.parent;
    }

    /**
     * Поиск минимального элемента в поддереве
     */
    private Node minimum(Node node) {
        while (node.left != leaf) node = node.left;
        return node;
    }

    /**
     * Балансировка после удаления
     */
    private void fixDelete(Node x) {
        while (x != root && !x.isRed) {
            if (x == x.parent.left) {
                Node w = x.parent.right;

                if (w.isRed) {
                    // Случай 1: брат красный
                    w.isRed = false;
                    x.parent.isRed = true;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }

                if (!w.left.isRed && !w.right.isRed) {
                    // Случай 2: оба потомка брата чёрные
                    w.isRed = true;
                    x = x.parent;
                } else {
                    if (!w.right.isRed) {
                        // Случай 3: правый потомок брата чёрный
                        w.left.isRed = false;
                        w.isRed = true;
                        rotateRight(w);
                        w = x.parent.right;
                    }

                    // Случай 4: правый потомок брата красный
                    w.isRed = x.parent.isRed;
                    x.parent.isRed = false;
                    w.right.isRed = false;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                // Симметричный случай
                Node w = x.parent.left;

                if (w.isRed) {
                    w.isRed = false;
                    x.parent.isRed = true;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }

                if (!w.right.isRed && !w.left.isRed) {
                    w.isRed = true;
                    x = x.parent;
                } else {
                    if (!w.left.isRed) {
                        w.right.isRed = false;
                        w.isRed = true;
                        rotateLeft(w);
                        w = x.parent.left;
                    }

                    w.isRed = x.parent.isRed;
                    x.parent.isRed = false;
                    w.left.isRed = false;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }

        x.isRed = false;
    }

    /**
     * Вывод всех элементов дерева в порядке возрастания
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString();
    }

    /**
     * Центрированный обход дерева
     */
    private void inOrderTraversal(Node node, StringBuilder sb) {
        if (node != leaf) {
            inOrderTraversal(node.left, sb);
            if (!sb.isEmpty()) sb.append(" ");
            sb.append(node);
            inOrderTraversal(node.right, sb);
        }
    }

    /**
     * Проверка свойств красно-чёрного дерева (для тестирования)
     */
    public boolean isValid() {
        if (root.isRed) return false; // Свойство 2
        return checkBlackHeight(root) != -1;
    }

    private int checkBlackHeight(Node node) {
        if (node == leaf) return 1;

        int leftBlackHeight = checkBlackHeight(node.left);
        int rightBlackHeight = checkBlackHeight(node.right);

        if (leftBlackHeight == -1 || rightBlackHeight == -1 ||
                leftBlackHeight != rightBlackHeight) {
            return -1; // Нарушение свойства 5
        }

        if (node.isRed && (node.left.isRed || node.right.isRed)) {
            return -1; // Нарушение свойства 4
        }

        return leftBlackHeight + (node.isRed ? 0 : 1);
    }

    /**
     * Внутренний класс, представляющий узел дерева
     */
    private class Node {
        T data;
        Node left, right, parent;
        boolean isRed;

        Node(T data) {
            this.data = data;
            this.isRed = true;
            this.left = this.right = this.parent = null;
        }

        @Override
        public String toString() {
            return data.toString() + (isRed ? "(R)" : "(B)");
        }
    }
}