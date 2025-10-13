// Реализовать красно-чёрное дерево, прокомментировать логику

import java.util.Scanner;

public class RedBlackTree<T extends Comparable<T>> { //для удобства сравнения 2 элементов с помощью compareTo

    Scanner inputScanner = new Scanner(System.in);

    // Узел красно-черного дерева
    private static class Node<T> { // узел дерева: "родственные связи" + значение
        T data;
        Node<T> parent;
        Node<T> left;
        Node<T> right;
        boolean isRed; // true == красный, false == черный, определитель цвета

        Node(T data) {
            this.data = data;
            this.isRed = true; // создаем новые узлы красными (правила класно-чёрного дерева)
        }

        public String toString() {
            return "Node{" + "data: " + data + ", colour: " + (isRed ? "red" : "black") + '}';
        }
    }

    private Node<T> root; // корневой узел (всегда чёрный по правилам)
    private Node<T> NIL; // "лист", пустой узел (всегда чёрный по правилам)

    public RedBlackTree() { // первый нулевой элемент, делаем его корнем
        NIL = new Node<>(null);
        NIL.isRed = false; // всегда черный по правилам
        root = NIL;
    }

    // Поиск узла по значению
    // Начинает поиск с корня. Пока у элемента есть ненулевые
    // потомки, сравнивает нужное значение с ними и выбирает,
    // куда идти, пока не найдёт элемент
    public Node<T> search(T data) {
        Node<T> current = root;
        while (current != NIL) {
            int comparison = data.compareTo(current.data);
            if (comparison == 0) {
                return current;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    // Вставка нового узла
    public void insert(T data) {
        Node<T> newNode = new Node<>(data); // новый узел для добавляемого элемента
        newNode.left = NIL; // изначально узел имеет 2 нулевых потомка: левый и правый
        newNode.right = NIL;

        Node<T> current = root;
        Node<T> parent = NIL;

        // Сравниваем с корневым, так определяем, с левым или с
        // правым потомком будем сравнивать дальше, так спускаемся,
        // пока не дойдём до нулевого элемента (туда и будем вставлять новый)
        while (current != NIL) {
            parent = current;
            if (newNode.data.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;

        if (parent == NIL) { // случай вставки нового элемента в пустое дерево
            root = newNode;
        } else if (newNode.data.compareTo(parent.data) < 0) { // выбираем, слева или справа будет новый узел
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        // Исправление красно-чёрного дерева при нарушении его свойств после вставки
        fixInsert(newNode);
    }

    // Вспомогательный метод: Исправление при нарушении
    // свойств красно-чёрного дерева после вставки
    private void fixInsert(Node<T> node) {
        while (node.parent != NIL && node.parent.isRed) { // поднимаемся по дереву, пока есть нарушение
            Node<T> uncle;

            // Определяем, где находится дядя (брат предка)
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right; //предок слева, дядя справа

                // вариант 1: Дядя красный (нарушение: 2 красных узла подряд)
                if (uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true; // делаем дедушку красным
                    node = node.parent.parent; // Перемещаемся выше по дереву
                } else { // вариант 2: Дядя черный
                    // вариант 2.1: node - правый ребенок (2 красных узла подряд)
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node); // меняем предка и новый узел местами, дальше решаем с помощью 2.2
                    }
                    // вариант 2.2: node - левый ребенок
                    node.parent.isRed = false; // перекрашиваем для исправления при
                    node.parent.parent.isRed = true; // правом повороте
                    rightRotate(node.parent.parent); // правый поворот дедушки и нарушение исправлено
                }
            } else {
                uncle = node.parent.parent.left; // рассмотрение тех же ошибок, но дядя слева

                // вариант 1: Дядя красный (симметричный случай)
                if (uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else { // вариант 2: Дядя черный
                    // вариант 2.1: node - левый ребенок
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    // вариант 2.2: node - правый ребенок
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false; // Корень всегда должен быть чёрным
    }


    // Удаление узла
    public void delete(T data) {
        Node<T> nodeToDelete = search(data); // ищем узел, который хотим удалить
        if (nodeToDelete == null) {
            return; // удаляемого узла нет
        }

        Node<T> y = nodeToDelete; // удаляемый узел
        Node<T> x; // узел, который займёт место удалённого (нужен для fixDelete)
        boolean yOriginalColor = y.isRed; // проверка цвета, нарушается ли "чёрная высота"

        // Рассматриваем 3 случая удаления в зависимости от наличия потомков
        if (nodeToDelete.left == NIL) { //нет левого потомка
            x = nodeToDelete.right;
            transplant(nodeToDelete, nodeToDelete.right); //замена узла на его потомка
        }
        else if (nodeToDelete.right == NIL) { // нет правого потомка
            x = nodeToDelete.left;
            transplant(nodeToDelete, nodeToDelete.left); // замена узла на его потомка
        }
        else { // 2 потомка
            y = minimum(nodeToDelete.right); // минимальный узел в поддереве, приемник
            yOriginalColor = y.isRed;
            x = y.right;
            if (y.parent == nodeToDelete) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = nodeToDelete.right;
                y.right.parent = y;
            }
            transplant(nodeToDelete, y); // заменяем удаляемый узел на преемника
            y.left = nodeToDelete.left;
            y.left.parent = y;
            y.isRed = nodeToDelete.isRed;
        }

        if (!yOriginalColor) { //если удалили чёрный узел, нарушена "чёрная высота", исправляем
            fixDelete(x);
        }
    }

    // Исправление нарушения свойств "чёрной высоты" при удалении чёрного узла
    private void fixDelete(Node<T> node) { // node - узел, который занял место удалённого
        while (node != root && !node.isRed) {

            // node - левый ребёнок удалённого узла
            if (node == node.parent.left) {
                Node<T> sibling = node.parent.right; // брат - правый ребёнок удал. узла
                if (sibling.isRed) { // если брат красный: перекрашиваем и делаем левый поворот
                    sibling.isRed = false;
                    node.parent.isRed = true;
                    leftRotate(node.parent);
                    sibling = node.parent.right; // брат гарантированно чёрный теперь
                }
                if (!sibling.left.isRed && !sibling.right.isRed) { // потомки брата чёрные
                    sibling.isRed = true; //делаем брата красным
                    node = node.parent;
                }
                else {
                    if (!sibling.right.isRed) { // левый потомок брата красный: перекрашиваем и делаем правый поворот
                        sibling.left.isRed = false;
                        sibling.isRed = true;
                        rightRotate(sibling);
                        sibling = node.parent.right;
                    }
                    sibling.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    sibling.right.isRed = false; // правый потомок брата чёрный
                    leftRotate(node.parent); // левый поворот
                    node = root;
                }
            } else { // аналогично для случая, когда брат - левый потомок
                Node<T> sibling = node.parent.left;
                if (sibling.isRed) {
                    sibling.isRed = false;
                    node.parent.isRed = true;
                    rightRotate(node.parent);
                    sibling = node.parent.left;
                }
                if (!sibling.right.isRed && !sibling.left.isRed) {
                    sibling.isRed = true;
                    node = node.parent;
                } else {
                    if (!sibling.left.isRed) {
                        sibling.right.isRed = false;
                        sibling.isRed = true;
                        leftRotate(sibling);
                        sibling = node.parent.left;
                    }
                    sibling.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    sibling.left.isRed = false;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.isRed = false; // замещающий узел всегда чёрный
    }



    // Левый поворот вокруг узла х
    private void leftRotate(Node<T> x) {
        Node<T> y = x.right; // правый ребёнок, который будет новым корнем
        x.right = y.left; // левое поддерево y становится правым поддеревом х
        if (y.left != NIL) {
            y.left.parent = x; // х - родитель левого потомка у
        }
        y.parent = x.parent; // переносим родительские связи от х к у
        if (x.parent == NIL) {
            root = y; // если х был корнем, то теперь у корень
        } else if (x == x.parent.left) {
            x.parent.left = y; // если х был левым потомком, переприсваиваем
        } else {
            x.parent.right = y; // если х был правым потомком, переприсваиваем
        }
        y.left = x; // x - левый потомок у
        x.parent = y;
    }

    // Правый поворот вокруг узла у, аналогично левому повороту
    private void rightRotate(Node<T> y) {
        Node<T> x = y.left;
        y.left = x.right;
        if (x.right != NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    // замена одного поддерева на другое (трансплантация)
    private void transplant(Node<T> newTree, Node<T> oldTree) {
        if (newTree.parent == NIL) {
            root = oldTree; //если new был корнем, old становится новым корнем
        } else if (newTree == newTree.parent.left) {
            newTree.parent.left = oldTree; // если new был ребёнком, то заменяем левую ссылку
        } else {
            newTree.parent.right = oldTree; // если new был ребёнком, то заменяем правую ссылку
        }
        oldTree.parent = newTree.parent; // ссылка на нового родителя
    }

    // Поиск минимального узла в поддереве
    private Node<T> minimum(Node<T> node) {
        while (node.left != NIL) { // идём по левой ветке, пока не дойдём до листьев
            node = node.left;
        }
        return node;
    }



    // Функции для обхода дерева (для тестирования) - Inorder обход
    public void printTree() {
        printTreeHelper(root);
    }

    public void printTreeHelper(Node<T> node) {
        if (node != NIL) {
            printTreeHelper(node.left);
            System.out.print(node.data + "(" + (node.isRed ? "Red" : "Black") + ") ");
            printTreeHelper(node.right);
        }
    }

    public static void main(String[] args) {

        RedBlackTree<Integer> tree = new RedBlackTree<>();

        inputTree(tree);
        System.out.println("Tree:");
        tree.printTree();
    }

    static void inputTree(RedBlackTree<Integer> tree) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Какое количество элементов в дереве?");
        int numElem = inputScanner.nextInt();
        System.out.println("Введите по порядку элементы дерева: ");
        int elem;
        for (int i = 0; i < numElem; i++) {
            elem = inputScanner.nextInt();
            tree.insert(elem);
        }
    }
}
