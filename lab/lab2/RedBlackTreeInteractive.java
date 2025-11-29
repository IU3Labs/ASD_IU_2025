//Реализовать красно-черное дерево. Прокомментировать логику.
package lab2;

import java.util.*;

/**
 * Красно-черное дерево - самобалансирующееся бинарное дерево поиска
 * Каждый узел имеет цвет (красный или черный) и соблюдает правила:
 * 1. Корень всегда черный
 * 2. Все листья (NIL) черные
 * 3. Красный узел имеет только черных потомков
 * 4. Все пути от узла до листьев содержат одинаковое количество черных узлов
 */
public class RedBlackTreeInteractive {
    public static void main(String[] args) {
        RedBlackTreeInteractive tree = new RedBlackTreeInteractive();
        tree.showMenu();
        scanner.close();
    }
    private static final Scanner scanner = new Scanner(System.in);

    // Цвета узлов
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //узел дерева
    static class Node {
        int key;            // Ключ узла
        Node left, right;   // Левый и правый потомки
        boolean color;      // Цвет узла (RED или BLACK)

        Node(int key) {
            this.key = key;
            this.color = RED; // Новые узлы всегда красные
        }

        @Override
        public String toString() {
            return key + "(" + (color == RED ? "R" : "B") + ")";
        }
    }

    private Node root; // Корень дерева
    /**
     * Проверяет, является ли узел красным
     */
    private boolean isRed(Node node) {
        if (node == null) return false; // NIL узлы считаются черными
        return node.color == RED;
    }

    //левый поворот
    private Node rotateLeft(Node x) {
        System.out.println("Левый поворот вокруг " + x.key);

        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;

        return y;
    }

   //правый поворот
    private Node rotateRight(Node y) {
        System.out.println("Правый поворот вокруг " + y.key);

        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;

        return x;
    }

    //смена цветов
    private void flipColors(Node node) {
        System.out.println("Смена цветов для " + node.key);

        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    //ключ в дерево
    public void insert(int key) {
        System.out.println("\nВставка ключа: " + key);
        root = insert(root, key);
        root.color = BLACK; // Корень всегда черный
        System.out.println("Ключ " + key + " успешно вставлен");
    }

    //рекурсивная вставка
    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key); // Создаем новый красный узел
        }

        // Обычная вставка в BST
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            System.out.println("Ключ " + key + " уже существует");
            return node; // Ключ уже существует
        }

        // Балансировка дерева
        return balance(node);
    }

    //балансировка узла
    private Node balance(Node node) {
        // Case 1: Правый ребенок красный, левый - черный -> левый поворот
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        // Case 2: Левый ребенок и левый внук красные -> правый поворот
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        // Case 3: Оба ребенка красные -> смена цветов
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    //поиск ключа
    public boolean contains(int key) {
        return contains(root, key);
    }

    private boolean contains(Node node, int key) {
        if (node == null) return false;

        if (key < node.key) {
            return contains(node.left, key);
        } else if (key > node.key) {
            return contains(node.right, key);
        } else {
            return true;
        }
    }

    //удаление ключа из дерева
    public void delete(int key) {
        System.out.println("\nУдаление ключа: " + key);
        if (!contains(key)) {
            System.out.println("Ключ " + key + " не найден");
            return;
        }

        // Временное окрашивание корня в красный для упрощения логики удаления
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);
        if (root != null) {
            root.color = BLACK;
        }
        System.out.println("Ключ " + key + " удален");
    }

    private Node delete(Node node, int key) {
        if (key < node.key) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (key == node.key && node.right == null) {
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (key == node.key) {
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, key);
            }
        }
        return balance(node);
    }

    //помощь при удалении
    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return null;

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return balance(node);
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    //обход дерева в порядке in-order (сортированный вывод)
    public void inOrder() {
        System.out.print("\nIn-order обход: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node + " ");
            inOrder(node.right);
        }
    }

    //обход дерева в порядке pre-order
    public void preOrder() {
        System.out.print("Pre-order обход: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //в консоли
    public void printTree() {
        System.out.println("\nСтруктура дерева:");
        if (root == null) {
            System.out.println("Дерево пусто");
            return;
        }
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node);
            String newPrefix = prefix + (isTail ? "    " : "│   ");
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printTree(node.left, newPrefix, node.right == null);
                }
                if (node.right != null) {
                    printTree(node.right, newPrefix, true);
                }
            }
        }
    }

    //высота дерева
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //валидность дерева
    public boolean isValid() {
        if (root == null) return true;
        if (isRed(root)) {
            System.out.println("Нарушение: корень должен быть черным");
            return false;
        }
        return isValid(root) != -1;
    }

    private int isValid(Node node) {
        if (node == null) return 1; // NIL узлы считаются черными

        // Проверка: красный узел не может иметь красных детей
        if (isRed(node)) {
            if (isRed(node.left) || isRed(node.right)) {
                System.out.println("Нарушение: красный узел " + node.key + " имеет красного ребенка");
                return -1;
            }
        }

        int leftBlackHeight = isValid(node.left);
        int rightBlackHeight = isValid(node.right);

        if (leftBlackHeight == -1 || rightBlackHeight == -1) {
            return -1;
        }

        // Проверка: черные высоты должны быть равны
        if (leftBlackHeight != rightBlackHeight) {
            System.out.println("Нарушение: разные черные высоты у узла " + node.key);
            return -1;
        }

        return leftBlackHeight + (isRed(node) ? 0 : 1);
    }

    /**
     * Интерактивное меню для работы с деревом
     */
    public void showMenu() {
        System.out.println("\nИНТЕРАКТИВНОЕ УПРАВЛЕНИЕ КРАСНО-ЧЕРНЫМ ДЕРЕВОМ");

        while (true) {
            System.out.println("\n--- ГЛАВНОЕ МЕНЮ ---");
            System.out.println("1) Вставить ключ");
            System.out.println("2) Удалить ключ");
            System.out.println("3) Найти ключ");
            System.out.println("4) Показать дерево");
            System.out.println("5) In-order обход");
            System.out.println("6) Pre-order обход");
            System.out.println("7) Высота дерева");
            System.out.println("8) Проверить валидность");
            System.out.println("9) Автоматическое тестирование");
            System.out.println("0) Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    insertKey();
                    break;
                case "2":
                    deleteKey();
                    break;
                case "3":
                    searchKey();
                    break;
                case "4":
                    printTree();
                    break;
                case "5":
                    inOrder();
                    break;
                case "6":
                    preOrder();
                    break;
                case "7":
                    System.out.println("Высота дерева: " + height());
                    break;
                case "8":
                    System.out.println(isValid() ? "Дерево валидно" : "Дерево невалидно");
                    break;
                case "9":
                    autoTest();
                    break;
                case "0":
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private void insertKey() {
        System.out.print("Введите ключ для вставки: ");
        try {
            int key = Integer.parseInt(scanner.nextLine());
            insert(key);
        } catch (NumberFormatException e) {
            System.out.println("Введите целое число!");
        }
    }

    private void deleteKey() {
        System.out.print("Введите ключ для удаления: ");
        try {
            int key = Integer.parseInt(scanner.nextLine());
            delete(key);
        } catch (NumberFormatException e) {
            System.out.println("Введите целое число!");
        }
    }

    private void searchKey() {
        System.out.print("Введите ключ для поиска: ");
        try {
            int key = Integer.parseInt(scanner.nextLine());
            boolean found = contains(key);
            System.out.println(found ? "Ключ найден" : "Ключ не найден");
        } catch (NumberFormatException e) {
            System.out.println("Введите целое число!");
        }
    }

    /**
     * Автоматическое тестирование с демонстрацией балансировки
     */
    private void autoTest() {
        System.out.println("\nАВТОМАТИЧЕСКОЕ ТЕСТИРОВАНИЕ");

        // Тест 1: Последовательная вставка
        System.out.println("\nТест 1: Вставка последовательных чисел");
        int[] testData = {10, 5, 15, 3, 7, 12, 18, 1, 9, 20};

        for (int key : testData) {
            insert(key);
            printTree();
        }

        // Тест 2: Поиск
        System.out.println("\nТест 2: Поиск ключей");
        System.out.println("Поиск 7: " + (contains(7) ? "найден" : "не найден"));
        System.out.println("Поиск 25: " + (contains(25) ? "найден" : "не найден"));

        // Тест 3: Удаление
        System.out.println("\nТест 3: Удаление ключей");
        delete(7);
        delete(15);
        printTree();

        // Тест 4: Валидация
        System.out.println("\nТест 4: Проверка валидности");
        System.out.println(isValid() ? "Дерево валидно" : "Дерево невалидно");

        System.out.println("\nАвтотестирование завершено!");
    }



}