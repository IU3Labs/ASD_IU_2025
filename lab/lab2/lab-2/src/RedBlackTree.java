// Реализовать красно-черное дерево. Прокомментировать логику.

public class RedBlackTree {

    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

   // Нода
    private class Node {
        int key;
        Node left, right;
        Node parent;
        boolean color;

        Node(int key) {
            this.key = key;
            this.color = RED;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);
        tree.insert(5);

        System.out.println("Дерево после вставки:");
        tree.inorderTraversal();

        // Удаляем несколько элементов
        tree.delete(20);
        tree.delete(5);

        System.out.println("Дерево после удаления 20 и 5:");
        tree.inorderTraversal();
    }

    // Левый поворот вокруг узла x
    private void leftRotate(Node x) {
        Node y = x.right;       // y — правый ребёнок x
        x.right = y.left;       // левое поддерево y становится правым поддеревом x
        if (y.left != null)     // если сосед (поддерево) действительно был(-о)
            y.left.parent = x;  // обновляем родителя перемещённого поддерева
        y.parent = x.parent;    // y займёт место x
        if (x.parent == null)   // если у х не было родителя
            root = y;           // y становится корнем
        else if (x == x.parent.left)    // если х стоял от родителя слева
            x.parent.left = y;
        else                            // иначе
            x.parent.right = y;
        y.left = x;             // x становится левым ребёнком y
        x.parent = y;
    }

    // Правый поворот вокруг узла y
    private void rightRotate(Node y) {
        Node x = y.left;        // x — левый ребёнок y
        y.left = x.right;       // правое поддерево x становится левым поддеревом y
        if (x.right != null)
            x.right.parent = y;
        x.parent = y.parent;    // x займёт место y
        if (y.parent == null)
            root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;
        x.right = y;            // y становится правым ребёнком x
        y.parent = x;
    }

    // Вставка нового узла
    public void insert(int key) {
        Node node = new Node(key); // создаём красный узел
        Node parent = null;        // будущий родитель
        Node current = root;       // начинаем с корня

        while (current != null) {   // весь цикл на сравнение значения вставляемого элемента
            parent = current;         // с элементами древа
            if (key < current.key)
                current = current.left;
            else if (key > current.key)
                current = current.right;
            else
                return; // дубликаты не вставляем
        }

        node.parent = parent;       // привязываем к родителю
        if (parent == null)
            root = node;            // дерево пустое — узел становится корнем
        else if (key < parent.key)
            parent.left = node;
        else
            parent.right = node;

        // Балансировка через сын-отец-дед-дядя (она описана ниже)
        fixInsert(node);
    }

    // Балансировка после вставки
    private void fixInsert(Node node) {
        while (node.parent != null && node.parent.color == RED) { // пока родитель красный
            Node parent = node.parent;            // отец
            Node grandparent = parent.parent;    // дед
            Node uncle;                           // дядя

            if (parent == grandparent.left) {    // отец — левый ребёнок деда
                uncle = grandparent.right;       // дядя — правый ребёнок деда

                if (uncle != null && uncle.color == RED) { // Случай 1: дядя красный
                    parent.color = BLACK;      // перекрашиваем родителя
                    uncle.color = BLACK;       // перекрашиваем дядю
                    grandparent.color = RED;   // деда в красный
                    node = grandparent;        // поднимаемся наверх (перекрашивать с начала будем)
                } else {                       // дяди нет или он черный
                    if (node == parent.right) {     // Случай 2: внутренняя форма
                        node = parent;              // Смещаемся на отца
                        leftRotate(node);           // левый поворот относительно отца (сын свапается с отцом --> линия)
                    }
                    // Случай 3: внешняя форма
                    parent.color = BLACK;
                    grandparent.color = RED;
                    rightRotate(grandparent); // проворачиваем покрашенную структуру
                }
            } else { // отец правый ребёнок деда (зеркально)
                uncle = grandparent.left;
                if (uncle != null && uncle.color == RED) { // Случай 1
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.left) { // Случай 2
                        node = parent;
                        rightRotate(node);
                    }
                    // Случай 3: внешняя форма
                    parent.color = BLACK;
                    grandparent.color = RED;
                    leftRotate(grandparent);
                }
            }
        }
        root.color = BLACK; // Проверка (корень всегда черный)
    }

    // Поиск минимального узла в поддереве
    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Замена одного поддерева на другое (для удаления)
    private void transplant(Node u, Node v) {
        if (u.parent == null)
            root = v;               // если u — корень, v становится корнем
        else if (u == u.parent.left)
            u.parent.left = v;      // если u был левым ребёнком
        else
            u.parent.right = v;     // если u был правым ребёнком
        if (v != null)
            v.parent = u.parent;    // обновляем родителя v
    }

    // Удаление узла по значению (ключу)
    public void delete(int key) {
        Node z = root;  // начинаем поиск с корня

        // Поиск узла с заданным значением
        while (z != null) {          // пока не дойдём до конца дерева
            if (key == z.key)        // если нашли узел с таким значением
                break;
            else if (key < z.key)    // если ключ меньше текущего идём влево
                z = z.left;
            else
                z = z.right;
        }

        if (z == null)
            return;         // если узел не найден

        Node y = z;                 // y — узел, который реально будет удалён из дерева
        boolean origColor = y.color; // сохраняем исходный цвет y
        Node x;                     // x — узел, который займёт место y после удаления

        if (z.left == null) {
            x = z.right;             // x будет правым ребёнком
            transplant(z, z.right);  // заменяем z на его правого ребёнка
        }
        else if (z.right == null) {
            x = z.left;
            transplant(z, z.left);
        }
        else {
            y = minimum(z.right);    // ищем минимальный элемент в правом поддереве (преемник z)
            origColor = y.color;
            x = y.right;             // x правый ребёнок преемника

            if (y.parent == z) {     // если преемник правый ребёнок z
                if (x != null)
                    x.parent = y;    // обновляем родителя для x если он существует
            } else {
                transplant(y, y.right);  // заменяем y его правым ребенком
                y.right = z.right;       // правый ребёнок y теперь становится тем, что был у z
                y.right.parent = y;      // обновляем родителя у правого поддерева
            }

            transplant(z, y);           // заменяем z на y (теперь y на месте удаленного узла)
            y.left = z.left;            // левое поддерево z теперь принадлежит y
            y.left.parent = y;          // обновляем родителя для левого поддерева
            y.color = z.color;          // сохраняем исходный цвет удалённого узла
        }

        if (origColor == BLACK) {       // балансируем только если удалён черный узел (чтобы не менялась черная высота)
            fixDelete(x);               // начинаем с x
        }
    }

    // Балансировка после удаления
    private void fixDelete(Node x) {
        while (x != root && (x == null || x.color == BLACK)) {
            Node parent = x.parent;
            Node sibling;               // брат текущего узла
            if (x == parent.left) {     // x левый ребенок
                sibling = parent.right;

                if (sibling.color == RED) { // Случай 1: брат красный --> черный (через поворот и перекраску)
                    sibling.color = BLACK;
                    parent.color = RED;
                    leftRotate(parent);
                    sibling = parent.right;
                }

                if ((sibling.left == null || sibling.left.color == BLACK) &&
                        (sibling.right == null || sibling.right.color == BLACK)) { // Случай 2: брат и его дети черные
                    sibling.color = RED;  // перекрашиваем брата в красный и двигаем наверх
                    x = parent;
                } else {
                    if (sibling.right == null || sibling.right.color == BLACK) { // Случай 3: брат и его правый ребенок черные, левый красный
                        if (sibling.left != null) sibling.left.color = BLACK;
                        sibling.color = RED;
                        rightRotate(sibling); // правый поворот вокруг брата --> Случай 4
                        sibling = parent.right;
                    }
                    // Случай 4: брат черный, его правый ребенок красный --> левый поворот вокруг отца
                    sibling.color = parent.color;
                    parent.color = BLACK;
                    if (sibling.right != null) sibling.right.color = BLACK;
                    leftRotate(parent);
                    x = root;
                }
            } else { // x правый ребёнок (зеркально)
                sibling = parent.left;

                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    parent.color = RED;
                    rightRotate(parent);
                    sibling = parent.left;
                }

                if ((sibling.left == null || sibling.left.color == BLACK) &&
                        (sibling.right == null || sibling.right.color == BLACK)) {
                    sibling.color = RED;
                    x = parent;
                } else {
                    if (sibling.left == null || sibling.left.color == BLACK) {
                        if (sibling.right != null) sibling.right.color = BLACK;
                        sibling.color = RED;
                        leftRotate(sibling);
                        sibling = parent.left;
                    }
                    sibling.color = parent.color;
                    parent.color = BLACK;
                    if (sibling.left != null) sibling.left.color = BLACK;
                    rightRotate(parent);
                    x = root;
                }
            }
        }
        if (x != null) x.color = BLACK;
    }

    // обход дерева
    public void inorderTraversal() {
        inorderHelper(root);
        System.out.println();
    }

    //  вывод
    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.key + (node.color == RED ? "(R) " : "(B) "));
            inorderHelper(node.right);
        }
    }
}