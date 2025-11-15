//2. Реализовать красно-черное дерево. Прокомментировать логику.


/*Красно-черное дерево — это самобалансирующееся двоичное дерево поиска,
в котором каждый узел имеет дополнительный атрибут — цвет (красный или черный).
Эта структура данных гарантирует логарифмическую сложность операций поиска, вставки и удаления — O(log n) в худшем случае.*/

/*4 основных правила для балансировки:
 * 1)Каждый узел имеет цвет - красный или чёрный
 * 2)Корень и листья(NIL узлы) - всегда чёрные
 * 3)Не может быть двух красных узлов подряд
 * 4)Все пути от любого узла до листа содержат одинаковое количество чёрных узлов - Чёрная высота (Без учёта корня)*/
public class RedBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //Внутренний класс для представления узла дерева
    public class Node {
        private T data;
        private Node left;
        private Node right;
        private Node parent;
        private boolean color;

        public Node(T data) {
            this.data = data;
            this.color = RED;  // Новые узлы всегда красные
            this.left = nil;
            this.right = nil;
            this.parent = nil;
        }

        public T getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Node getParent() {
            return parent;
        }

        public boolean getColor() {
            return color;
        }
    }

    private Node root;
    private Node nil;   //nil-узел (представляет NULL)

    //Конструктор красно-черного дерева.
    //Инициализирует nil-узел и корень
    public RedBlackTree() {
        // Создаем NIL-узел (черный лист)
        nil = new Node(null);
        nil.color = BLACK;
        nil.left = null;
        nil.right = null;
        nil.parent = null;

        root = nil;  // Изначально дерево пустое
    }

    public void insert(T data) {
        Node newNode = new Node(data);
        Node parent = nil;
        Node current = root;

        //Стандартная вставка в BinarySearchTree

        //Поиск позиции
        while (current != nil) {
            parent = current;
            if (newNode.data.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;

        //Вставка
        if (parent == nil) {
            root = newNode;         // Дерево было пустым
        } else if (newNode.data.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        //Балансировка дерева

        // Если новый узел — корень, красим его в черный и завершаем
        if (newNode.parent == nil) {
            newNode.color = BLACK;
            return;
        }

        // Если родитель — корень, балансировка не нужна
        if (newNode.parent.parent == nil) {
            return;
        }

        // Исправляем нарушения свойств красно-черного дерева
        fixColorsInsert(newNode);
    }

    //Восстановление свойств красно-черного дерева после вставки.
    //Обрабатывает случаи нарушения правила о двух красных узлах
    private void fixColorsInsert(Node node) {
        //Пока родитель узла красный (нарушение свойства 3)
        while (node.parent.color == RED) {

            //Случай 1: родитель — левый потомок дедушки
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;  //Дядя узла

                //Случай 1.1: дядя красный
                if (uncle.color == RED) {
                    node.parent.color = BLACK;      //Перекрашиваем родителя
                    uncle.color = BLACK;            //Перекрашиваем дядю
                    node.parent.parent.color = RED; //Перекрашиваем дедушку
                    node = node.parent.parent;      //Продолжаем проверку от дедушки
                } else {    //Случай 1.2: дядя чёрный
                    //Случай 1.2.1: узел — правый потомок (преобразуем в случай 1.2.2)
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    // Случай 1.2.2: узел — левый потомок
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rightRotate(node.parent.parent);
                }
            } else {    //Случай 2: родитель — правый потомок дедушки
                Node uncle = node.parent.parent.left;

                //Случай 2.1: дядя красный
                if (uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {    //Случай 2.2: дядя чёрный
                    //Случай 2.2.1: узел — левый потомок
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }

                    //Случай 2.2.2: узел — правый потомок
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    leftRotate(node.parent.parent);
                }
            }
            // Если достигли корня, выходим
            if (node == root) {
                break;
            }
        }
        // Корень всегда должен быть черным (свойство 2)
        root.color = BLACK;
    }

    /*
     Левый поворот вокруг узла x.
     Используется для балансировки дерева
           x                y
          / \              / \
         a   y     =>     x   c
            / \          / \
           b   c        a   b
     */
    //Переменные в коде названы в соответствии с рисунком
    private void leftRotate(Node x) {
        Node y = x.right;    //Сохраняем правого потомка узла 'x', опорного для поворота, - узел 'y'
        x.right = y.left;    //Делаем левого потомка 'y' правым потомком 'x' (правым потомком 'x' стал узел 'b')

        //Если левый потомок 'y' не NIL, обновляем его родителя
        if (y.left != nil) {
            y.left.parent = x;  //Родитель 'b' теперь 'x'
        }

        y.parent = x.parent;    //Связываем родителя 'x' с 'y'

        // Обновляем ссылку от родителя 'x'
        if (x.parent == nil) {
            root = y;               //Если 'x' был корнем, то 'y' становится новым корнем
        } else if (x == x.parent.left) {
            x.parent.left = y;      //'x' был левым потомком. Теперь это 'y'.
        } else {
            x.parent.right = y;     //'x' был правым потомком. Теперь это 'y'.
        }

        y.left = x;     //Делаем 'x' левым потомком 'y'
        x.parent = y;   //Обновляем родителя 'x'
    }

    /*
     Правый поворот вокруг узла y
     Зеркальная операция левому повороту
             y              x
            / \            / \
           x   c   =>     a   y
          / \                / \
         a   b              b   c
     */
    //Переменные в коде названы в соответствии с рисунком
    private void rightRotate(Node y) {
        Node x = y.left;    //Сохраняем левого потомка узла 'y' - узел 'x'
        y.left = x.right;   //Делаем правого потомка 'x' левым потомком 'y' (узел 'b')

        //Если правый потомок 'x' не NIL, обновляем его родителя
        if (x.right != nil) {
            x.right.parent = y;
        }

        x.parent = y.parent;    //Связываем родителя 'y' с 'x'

        //Обновляем ссылку от родителя 'y'
        if (y.parent == nil) {
            root = x;               //Если 'y' был корнем, то 'x' становится новым корнем
        } else if (y == y.parent.right) {
            y.parent.right = x;     //'y' был правым потомком. Теперь это 'x'.
        } else {
            y.parent.left = x;      //'y' был левым потомком. Теперь это 'x'.
        }

        x.right = y;    //Делаем 'y' правым потомком 'x'
        y.parent = x;   //Обновляем родителя 'y'
    }

    public void delete(T data) {
        Node nodeToDelete = search(root, data); //Поиск узла для удаления

        if (nodeToDelete == nil) {
            throw new IllegalStateException("Impossible to delete incorrect data");  //Узел не найден
        }

        deleteNode(nodeToDelete);
    }

    private Node search(Node node, T data) {
        //Базовый случай: достигли NIL или нашли узел
        if (node == nil || data.equals(node.data)) {
            return node;
        }

        //Рекурсивный поиск в левом или правом поддереве
        if (data.compareTo(node.data) < 0) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }

    private void deleteNode(Node node) {
        Node replacement;
        Node toDelete = node;
        boolean originalColor = toDelete.color;

        //Случай 1: нет левого потомка
        if (node.left == nil) {
            replacement = node.right;
            transplant(node, node.right);
        }
        //Случай 2: нет правого потомка
        else if (node.right == nil) {
            replacement = node.left;
            transplant(node, node.left);
        }
        // Случай 3: есть оба потомка
        else {
            /*Удаляемый node: toDelete (два потомка)
            Преемник y: K (минимум справа, левый обычно NIL)
            Ребёнок x: NIL или правый потомок K

          toDelete
           /   \
         ...  toDelete.right
              /      \
minimum(node.right)  ...
              \
          replacement

            — копируем minimum(node.right) в toDelete
            — удаляем узел minimum(node.right), на его место ставим replacement
            — баланс восстанавливаем от replacement*/

            //Находим минимальный узел в правом поддереве (преемник)
            toDelete = minimum(node.right);
            originalColor = toDelete.color;
            replacement = toDelete.right;

            if (toDelete.parent == node) {
                replacement.parent = toDelete;
            } else {
                transplant(toDelete, toDelete.right);
                toDelete.right = node.right;
                toDelete.right.parent = toDelete;
            }

            transplant(node, toDelete);
            toDelete.left = node.left;
            toDelete.left.parent = toDelete;
            toDelete.color = node.color;
        }

        // Если удаленный узел был черным, восстанавливаем свойства
        if (originalColor == BLACK) {
            fixColorsDelete(replacement);
        }
    }

    //Восстановление свойств красно-черного дерева после удаления.
    //Исправляет нарушение правила о количестве черных узлов на путях
    private void fixColorsDelete(Node node) {
        while (node != root && node.color == BLACK) {

            // Случай 1: узел — левый потомок
            if (node == node.parent.left) {
                Node sibling = node.parent.right;  // Брат узла

                //Случай 1.1: брат красный
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    leftRotate(node.parent);
                    sibling = node.parent.right;
                }

                //Случай 1.2: оба потомка брата черные
                if (sibling.left.color == BLACK && sibling.right.color == BLACK) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    //Случай 1.3: правый потомок брата черный
                    if (sibling.right.color == BLACK) {
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rightRotate(sibling);
                        sibling = node.parent.right;
                    }

                    //Случай 1.4: правый потомок брата красный
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            }
            //Случай 2: узел — правый потомок (зеркальные операции)
            else {
                Node sibling = node.parent.left;

                //Случай 2.1: брат красный
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rightRotate(node.parent);
                    sibling = node.parent.left;
                }

                //Случай 2.2: оба потомка брата черные
                if (sibling.right.color == BLACK && sibling.left.color == BLACK) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    //Случай 2.3: левый потомок брата черный
                    if (sibling.left.color == BLACK) {
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        leftRotate(sibling);
                        sibling = node.parent.left;
                    }

                    //Случай 2.4: левый потомок брата красный
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }

        node.color = BLACK;
    }

    //Замена одного поддерева другим.
    //Используется при удалении узла
    private void transplant(Node nodeToReplace, Node replacement) {
        if (nodeToReplace.parent == nil) {
            root = replacement;     //Замена корня
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = replacement; //Замена левого поддерева
        } else {
            nodeToReplace.parent.right = replacement; //Замена правого поддерева
        }
        replacement.parent = nodeToReplace.parent; //Смена родителей
    }

    //Метод находит самый левый узел в поддереве, начиная с переданного узла.
    //Возвращает объект Node с минимальным значением.
    //Используется при удалении узла с двумя потомками, чтобы найти преемника.
    private Node minimum(Node node) {
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

    public boolean contains(T data) {
        return search(root, data) != nil;
    }

    /**
     * Симметричный обход дерева (in-order traversal)
     * Выводит элементы в отсортированном порядке
     */
    public void inorderTraversal() {
        inorderTraversalHelper(root);
        System.out.println();
    }

    /*
     Вспомогательный метод для симметричного обхода
     *node - текущий узел
     */
    private void inorderTraversalHelper(Node node) {
        if (node != nil) {
            inorderTraversalHelper(node.left);
            System.out.print(node.data + " ");
            inorderTraversalHelper(node.right);
        }
    }

    public void printTree() {
        printTreeHelper(root, "", true);
    }

    /*
     Вспомогательный метод для вывода дерева
     *node текущий узел
     *indent - отступ для форматирования
     *isLast - является ли узел последним потомком
     */
    private void printTreeHelper(Node node, String indent, boolean isLast) {
        if (node != nil) {
            System.out.print(indent);
            if (isLast) {
                System.out.print("└── ");
                indent += "    ";
            } else {
                System.out.print("├── ");
                indent += "│   ";
            }

            String color = node.color == RED ? "R" : "B";
            System.out.println(node.data + "(" + color + ")");

            printTreeHelper(node.left, indent, false);
            printTreeHelper(node.right, indent, true);
        }
    }

    public Node getRootNode() {
        return root;
    }

    public Node getNilNode() {
        return nil;
    }
}