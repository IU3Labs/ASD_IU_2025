public class RedBlackTreeInt {

    // Константы для цветов узлов
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * Класс узла красно-черного дерева для int
     */
    private class Node {
        int key;
        Node left, right;
        boolean color;
        int size;

        Node(int key, boolean color, int size) {
            this.key = key;
            this.color = color;
            this.size = size;
        }
    }

    private Node root;  // Корень дерева

    public RedBlackTreeInt() {
        // Инициализация пустого дерева
    }

    // основные операции

    /**
     * Проверка, является ли узел красным
     */
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    /**
     * Возвращает количество узлов в дереве
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    /**
     * Проверка, пусто ли дерево
     */
    public boolean isEmpty() {
        return root == null;
    }

    // основные5 вращ

    /**
     * Левое вращение (left rotation)
     * Используется когда правый ребенок красный, а л - ч
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * Правое вращение (right rotation)
     * Используется когда левый ребенок и его левый ребенок красные
    */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * Смена цветов (color flip)
     * Используется когда оба ребенка красные
     * Меняет цвета родителя и его детей
     */
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    //операц вставки

    /**
     * Вставка элемента в дерево
     * Теперь принимает простой int
     */
    public void insert(int key) {
        root = insert(root, key);
        root.color = BLACK;  // Корень всегда черный
    }

    private Node insert(Node h, int key) {
        // Рекурсивная вставка (как в обычном BST)
        if (h == null) {
            return new Node(key, RED, 1);  // Новые узлы всегда красные
        }

        // Простое сравнение int (не нужен compareTo!)
        if (key < h.key) {
            h.left = insert(h.left, key);
        } else if (key > h.key) {
            h.right = insert(h.right, key);
        } else {
            // Ключ уже существует - можно обновить значение или проигнорировать
            h.key = key;
        }

        // БАЛАНСИРОВКА ДЕРЕВА ПОСЛЕ ВСТАВКИ

        // Случ 1: Правый ребенок красный, левый - черный -> левое вращение
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        // Случ 2: Левый ребенок и его левый ребенок красные -> правое вращение
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        // Случ 3: Оба ребенка красные -> смена цветов
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        // Обновление размера поддерева
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    // операц поиска

    /**
     * Поиск элемента в дереве
     * Теперь возвращает буль нашли или нет
     */
    public boolean contains(int key) {
        return get(key) != null;
    }

    /**
     * Поиск и возвращение узла (для внутреннего использования)
     * Возвращает найденный ключ или null
     */
    private Integer get(int key) {
        Node node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return node.key;  // Нашли
            }
        }
        return null;  // Не нашли
    }


     // Поиск минимального элемента в дереве

    public Integer min() {
        if (root == null) return null;
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }


     // Поиск максимального элемента в дереве

    public Integer max() {
        if (root == null) return null;
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    // удаление


     // Удаление минимального элемента

    public void deleteMin() {
        if (isEmpty()) return;

        // Если оба ребенка корня черные, делаем корень красным
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;

        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }


     // Удаление максимального элемента

    public void deleteMax() {
        if (isEmpty()) return;

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }

        if (h.right == null) return null;

        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }

        h.right = deleteMax(h.right);
        return balance(h);
    }


    // Удаление произвольного элемента

    public void delete(int key) {
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, int key) {
        if (key < h.key) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key == h.key && (h.right == null)) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if (key == h.key) {
                Node x = min(h.right);
                h.key = x.key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    // методы для удаленяи

    // перемещение красного узла влево
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * Перемещение красного узла вправо
     */
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * Балансировка узла
     */
    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    //доп методы

    /**
     * Высота дерева
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Обход в порядке возр (in-order traversal)
     */
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    /**
     * Проверка свойств красно-черного дерева
     */
    public boolean isValid() {
        if (!isBST()) return false;
        if (!isSizeConsistent()) return false;
        if (!is23()) return false;
        if (!isBalanced()) return false;
        return true;
    }

    // Проверка что дерево является BST
    private boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node node, int min, int max) {
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    // Проверка согласованности размеров
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node node) {
        if (node == null) return true;
        if (node.size != size(node.left) + size(node.right) + 1) return false;
        return isSizeConsistent(node.left) && isSizeConsistent(node.right);
    }

    // Проверка что нет двух красных узлов подряд
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node node) {
        if (node == null) return true;
        if (isRed(node.right)) return false;
        if (node != root && isRed(node) && isRed(node.left)) return false;
        return is23(node.left) && is23(node.right);
    }

    // Проверка баланса черных узлов
    private boolean isBalanced() {
        int black = 0;
        Node node = root;
        while (node != null) {
            if (!isRed(node)) black++;
            node = node.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node node, int black) {
        if (node == null) return black == 0;
        if (!isRed(node)) black--;
        return isBalanced(node.left, black) && isBalanced(node.right, black);
    }

    public static void main(String[] args) {
        RedBlackTreeInt tree = new RedBlackTreeInt();

        // Вставка элементов
        int[] elements = {10, 5, 15, 3, 7, 12, 18, 1, 6, 8};
        System.out.println("Вставляем элементы: ");
        for (int elem : elements) {
            System.out.print(elem + " ");
            tree.insert(elem);
        }
        System.out.println();

        System.out.println("Обход дерева (in-order):");
        tree.inOrder();

        System.out.println("\nХарактеристики дерева:");
        System.out.println("Размер дерева: " + tree.size());
        System.out.println("Высота дерева: " + tree.height());
        System.out.println("Минимальный элемент: " + tree.min());
        System.out.println("Максимальный элемент: " + tree.max());
        System.out.println("Содержит 7: " + tree.contains(7));
        System.out.println("Содержит 20: " + tree.contains(20));
        System.out.println("Корректное красно-черное дерево: " + tree.isValid());

        // Удаление элементов
        System.out.println("\nУдаляем 7 и 15:");
        tree.delete(7);
        tree.delete(15);

        System.out.println("Обход дерева после удаления:");
        tree.inOrder();

        System.out.println("\nХарактеристики после удаления:");
        System.out.println("Размер дерева: " + tree.size());
        System.out.println("Содержит 7: " + tree.contains(7));
        System.out.println("Корректное красно-черное дерево: " + tree.isValid());
    }
}