/**
 * Реализовать красно-черное дерево. Прокомментировать логику.
 *
 */

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * Класс узла красно-черного дерева.
     * Каждый узел содержит данные, ссылки на потомков, родителя и цвет.
     */
    private static class Node {
        int data;
        Node left, right, parent;
        boolean color;

        /**
         * Конструктор узла
         */
        Node(int data) {
            this.data = data;
            this.color = RED; // Новые узлы всегда красные (свойство вставки)
            this.left = this.right = this.parent = null;
        }

        @Override
        public String toString() {
            return data + "(" + (color == RED ? "R" : "B") + ")";
        }
    }

    private Node root;
    private final Node NIL;

    /**
     * Конструктор красно-черного дерева.
     * Инициализирует NIL-узел и устанавливает корень в NIL.
     */
    public RedBlackTree() {
        NIL = new Node(-1);
        NIL.color = BLACK;  // NIL-узлы всегда черные
        root = NIL;
    }

    /**
     * Публичный метод для вставки элемента в дерево.
     * Создает новый узел и выполняет вставку с последующей балансировкой.
     */
    public void insertElement(int data) {
        Node newNode = new Node(data);
        newNode.left = NIL;    // Левый потомок нового узла - NIL
        newNode.right = NIL;   // Правый потомок нового узла - NIL
        insertInBinarySearchTree(newNode);    // Вставка в бинарное дерево поиска
        fixInsert(newNode);    // Балансировка после вставки
    }

    /**
     * Вставка узла в бинарное дерево поиска (без балансировки).
     */
    private void insertInBinarySearchTree(Node newNode) {
        Node parent = NIL;
        Node current = root;

        // Поиск места для вставки
        while (current != NIL) {
            parent = current;
            if (newNode.data < current.data) {
                current = current.left;
            } else if (newNode.data > current.data) {
                current = current.right;
            } else {
                return;
            }
        }

        // Установка родителя для нового узла
        newNode.parent = parent;

        // Прикрепление нового узла к родителю
        if (parent == NIL) {
            root = newNode;
        } else if (newNode.data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    /**
     * Балансировка дерева после вставки красного узла.
     */
    private void fixInsert(Node node) {
        while (node.parent != NIL && node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) {
                // СЛУЧАЙ A: родитель - левый потомок дедушки
                Node uncle = node.parent.parent.right;

                if (uncle.color == RED) {
                    // СЛУЧАЙ 1: дядя красный -> перекрашиваем
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    // СЛУЧАЙ 2: дядя черный
                    if (node == node.parent.right) {
                        // Узел - правый потомок
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // СЛУЧАЙ 3: узел - левый потомок (линейная конфигурация)
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }
            } else {
                // СЛУЧАЙ B: родитель - правый потомок дедушки (симметрично)
                Node uncle = node.parent.parent.left;

                if (uncle.color == RED) {
                    // СЛУЧАЙ 1: дядя красный
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    // СЛУЧАЙ 2: дядя черный
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    // СЛУЧАЙ 3
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            }

            // Прерываем цикл если дошли до корня
            if (node == root) {
                break;
            }
        }
        root.color = BLACK;
    }

    /**
     * Левый поворот вокруг указанного узла(Правый потомок становится родителем)
     */
    private void rotateLeft(Node pivotNode) {
        Node rotator = pivotNode.right;
        pivotNode.right = rotator.left;

        if (rotator.left != NIL) {
            rotator.left.parent = pivotNode;
        }

        rotator.parent = pivotNode.parent;

        if (pivotNode.parent == NIL) {
            root = rotator;
        } else if (pivotNode == pivotNode.parent.left) {
            pivotNode.parent.left = rotator;
        } else {
            pivotNode.parent.right = rotator;
        }

        rotator.left = pivotNode;
        pivotNode.parent = rotator;
    }

    /**
     * Правый поворот вокруг указанного узла(левый потомок становится новым родителем)
     */
    private void rotateRight(Node pivotNode) {
        Node rotator = pivotNode.left;
        pivotNode.left = rotator.right;

        if (rotator.right != NIL) {
            rotator.right.parent = pivotNode;
        }

        rotator.parent = pivotNode.parent;

        if (pivotNode.parent == NIL) {
            root = rotator;
        } else if (pivotNode == pivotNode.parent.left) {
            pivotNode.parent.left = rotator;
        } else {
            pivotNode.parent.right = rotator;
        }

        rotator.right = pivotNode;
        pivotNode.parent = rotator;
    }

    /**
     *Метод для удаления элемента из дерева + балансировка
     */
    public void deleteElement(int data) {
        Node node = findNode(data);
        if (node == NIL) {
            return;
        }
        deleteNode(node);
    }

    /**
     * Поиск узла по значению в дереве.
     */
    private Node findNode(int data) {
        Node current = root;
        while (current != NIL) {
            if (data == current.data) {
                return current;
            } else if (data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return NIL;
    }

    /**
     * Удаление узла из дерева с полной балансировкой.
     * Обрабатывает все три случая удаления.
     * @param nodeToDelete - узел для удаления
     */
    private void deleteNode(Node nodeToDelete) {
        Node nodeToRemove = nodeToDelete;
        Node replacementNode;
        boolean originalColorOfRemovedNode = nodeToRemove.color;

        if (nodeToDelete.left == NIL) {
            // Случай 1: нет левого потомка
            replacementNode = nodeToDelete.right;
            transplant(nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == NIL) {
            // Случай 2: нет правого потомка
            replacementNode = nodeToDelete.left;
            transplant(nodeToDelete, nodeToDelete.left);
        } else {
            // Случай 3: есть оба потомка
            nodeToRemove = findMinimumNode(nodeToDelete.right); // Находим преемника (минимальный в правом поддереве)
            originalColorOfRemovedNode = nodeToRemove.color;
            replacementNode = nodeToRemove.right;

            if (nodeToRemove.parent == nodeToDelete) {
                replacementNode.parent = nodeToRemove;
            } else {
                transplant(nodeToRemove, nodeToRemove.right);   // Заменяем преемника его правым поддеревом
                nodeToRemove.right = nodeToDelete.right;
                nodeToRemove.right.parent = nodeToRemove;
            }
            transplant(nodeToDelete, nodeToRemove);  // Заменяем удаляемый узел преемником
            nodeToRemove.left = nodeToDelete.left;
            nodeToRemove.left.parent = nodeToRemove;
            nodeToRemove.color = nodeToDelete.color;
        }

        // Если удалили черный узел, требуется балансировка
        if (originalColorOfRemovedNode == BLACK) {
            fixDelete(replacementNode);
        }
    }

    /**
     * Замена одного поддерева другим + обновление связей дерева
     * nodeToReplace - удаляемое поддерево (узел, который нужно заменить)
     * newNode - новое поддерево (узел, который займет место удаляемого)
     */
    private void transplant(Node nodeToReplace, Node newNode) {
        // Если заменяемый узел является корнем
        if (nodeToReplace.parent == NIL) {
            root = newNode;
        }
        // Если заменяемый узел является левым потомком своего родителя
        else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        }
        // Если заменяемый узел является правым потомком своего родителя
        else {
            nodeToReplace.parent.right = newNode;
        }

        // Обновляем ссылку на родителя у нового узла
        newNode.parent = nodeToReplace.parent;
    }

    /**
     * Поиск узла с минимальным значением в поддереве.
     */
    private Node findMinimumNode(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    /**
     * Балансировка дерева после удаления черного узла.
     * Восстанавливает свойство черной высоты.
     */
    private void fixDelete(Node x) {
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                // x - левый потомок
                Node w = x.parent.right; // w - брат x

                if (w.color == RED) {
                    // СЛУЧАЙ 1: брат красный
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }

                if (w.left.color == BLACK && w.right.color == BLACK) {
                    // СЛУЧАЙ 2: оба потомка брата черные
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.right.color == BLACK) {
                        // СЛУЧАЙ 3: правый потомок брата черный
                        w.left.color = BLACK;
                        w.color = RED;
                        rotateRight(w);
                        w = x.parent.right;
                    }
                    // СЛУЧАЙ 4: правый потомок брата красный
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                // Симметричный случай: x - правый потомок
                Node w = x.parent.left;

                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }

                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        rotateLeft(w);
                        w = x.parent.left;
                    }

                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    /**
     * Поиск элемента в дереве.
     */
    public boolean contains(int data) {
        return findNode(data) != NIL;
    }

    /**
     * Центрированный обход дерева (левый -> корень -> правый).
     * Выводит элементы в отсортированном порядке.
     */
    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node != NIL) {
            printInOrder(node.left);
            System.out.print(node + " ");
            printInOrder(node.right);
        }
    }

    /**
     * Проверка всех свойств красно-черного дерева.
     * возвращает true если дерево построено правильно, false если дерево построено неправильно
     */
    public boolean isValidRedBlackTree() {
        if (root == NIL) return true; // Пустое дерево валидно
        if (root.color != BLACK) {
            System.out.println("Корень должен быть черным");
            return false;
        }
        return checkRedBlackProperties(root) != -1;
    }

    /**
     * Рекурсивная проверка свойств красно-черного дерева.
     */
    private int checkRedBlackProperties(Node node) {
        if (node == NIL) return 1; // NIL-узлы считаются черными

        //Красный узел не может иметь красных потомков
        if (node.color == RED) {
            if (node.left.color == RED || node.right.color == RED) {
                System.out.println("Нарушение: красный узел " + node.data + " имеет красного потомка");
                return -1;
            }
        }

        // Рекурсивная проверка левого и правого поддеревьев
        int leftBlackHeight = checkRedBlackProperties(node.left);
        int rightBlackHeight = checkRedBlackProperties(node.right);

        // Проверка равенства черных высот
        if (leftBlackHeight == -1 || rightBlackHeight == -1 || leftBlackHeight != rightBlackHeight) {
            System.out.println("Нарушение: неравные черные высоты у узла " + node.data);
            return -1;
        }
        // Возвращаем черную высоту текущего поддерева
        return leftBlackHeight + (node.color == BLACK ? 1 : 0);
    }
    public void printTree() {
        printTree(root, "", true, true);
    }

    /**
     * Рекурсивный метод для графического отображения структуры дерева.
     */
    private void printTree(Node node, String prefix, boolean isLast, boolean isRoot) {
        if (node != NIL) {
            // Выводим текущий узел
            System.out.print(prefix);

            if (isRoot) {
                System.out.print("Корень─── ");
            } else if (isLast) {
                System.out.print("└── ");
                prefix += "    ";
            } else {
                System.out.print("├── ");
                prefix += "│   ";
            }

            String color = node.color == RED ? "R" : "B";
            System.out.println(node.data + "(" + color + ")");

            boolean hasRightChild = node.right != NIL;
            boolean hasLeftChild = node.left != NIL;

            if (hasLeftChild || hasRightChild) {
                if (hasLeftChild) {
                    printTree(node.left, prefix, !hasRightChild, false);
                }
                if (hasRightChild) {
                    printTree(node.right, prefix, true, false);
                }
            }
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int[] values = {7, 3, 18, 10, 22, 8, 11, 26};
        System.out.println("Вставляем элементы: ");
        for (int value : values) {
            System.out.print(value + " ");
            tree.insertElement(value);
        }
        System.out.println();
        System.out.println("Является валидным красно-черным деревом: " + tree.isValidRedBlackTree());
        System.out.println();
        System.out.println("Содержит 10: " + tree.contains(10));
        System.out.println("Содержит 15: " + tree.contains(15));
        System.out.println();
        System.out.println("Обход в порядке возрастания:");
        tree.printInOrder();
        tree.printTree();
        System.out.println("Удаляем 18:");
        tree.deleteElement(18);
        System.out.println("Снова обходим в порядке возрастания:");
        tree.printInOrder();
        tree.printTree();
        System.out.println("Является валидным красно-черным деревом после удаления: " + tree.isValidRedBlackTree());
    }
}
/*
Вставляем элементы: 
7 3 18 10 22 8 11 26 
Является валидным красно-черным деревом: true

Содержит 10: true
Содержит 15: false

Обход в порядке возрастания:
3(B) 7(B) 8(R) 10(B) 11(R) 18(R) 22(B) 26(R) 
Корень─── 7(B)
├── 3(B)
└── 18(R)
    ├── 10(B)
    │   ├── 8(R)
    │   └── 11(R)
    └── 22(B)
        └── 26(R)
Удаляем 18:
Снова обходим в порядке возрастания:
3(B) 7(B) 8(R) 10(B) 11(R) 22(R) 26(B) 
Корень─── 7(B)
├── 3(B)
└── 22(R)
    ├── 10(B)
    │   ├── 8(R)
    │   └── 11(R)
    └── 26(B)
Является валидным красно-черным деревом после удаления: true

 */

