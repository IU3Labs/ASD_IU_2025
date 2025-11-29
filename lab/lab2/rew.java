package lab2;

import java.util.*;

class SkipList {
    static class Node {
        int value; // значение узла
        Node next; // ссылка на след узел того же уровня
        Node down; // ссылка на соотв узел на уровне ниже

        public Node(int value, Node next, Node down) {
            this.value = value;
            this.next = next;
            this.down = down;
        }
    }

    private Node head; // ссылка на голову самого верхнего уровня
    private int levels; // колво уровней
    private Random random; // рандом

    public SkipList() { // пустой с одним уровнем
        head = new Node(Integer.MIN_VALUE, null, null);
        levels = 1;
        random = new Random();
    }

    public boolean search(int value) {
        Node current = head; // начинаем с узла самого верхнего уровня
        while (current != null) { // пока не достигнем самого нижнего уровня
            while (current.next != null && current.next.value <= value) {
                current = current.next; // идем вперед пока не найдем нужный узел
            }
            if (current.value == value) {
                return true;
            }
            current = current.down; // спускаемся вниз
        }
        return false;
    }

    public void insert(int value) { // поиск места для вставки и сама вставка
        Stack<Node> stack = new Stack<>();
        Node current = head;
        // поиск на каждом уровне
        while (current != null) {
            while (current.next != null && current.next.value < value) {
                current = current.next;
            }
            stack.push(current);
            current = current.down;
        }

        boolean insertUp = true; // поднятие уровня
        Node downNode = null; // ссылка на узел на уровне ниже

        while (insertUp && !stack.isEmpty()) { // вставка на уровнях которые мы нашли
            Node prev = stack.pop();
            Node newNode = new Node(value, prev.next, downNode);
            prev.next = newNode;
            downNode = newNode;

            insertUp = (random.nextInt(2) == 0); // 50% вероятность
        }

        // если узел поднимается на новый уровень, создаем его
        if (insertUp) {
            head = new Node(Integer.MIN_VALUE, null, head);
            head.next = new Node(value, null, downNode);
            levels++;
        }
    }

    // вывод
    public void printSkipList() {
        Node current = head;
        int level = levels;
        while (current != null) {
            System.out.print("Уровень " + level + ": ");
            Node node = current.next;
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next;
            }
            System.out.println();
            current = current.down;
            level--;
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите элементы для Skip List:");
        String[] elements = scanner.nextLine().split(" ");
        for (String element : elements) {
            skipList.insert(Integer.parseInt(element));
        }

        System.out.println("\nSkip List:");
        skipList.printSkipList();

        while (true) {
            System.out.println("1 - Вставить элемент");
            System.out.println("2 - Поиск элемента");
            System.out.println("3 - Вывести Skip List");
            System.out.println("4 - Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Введите число для вставки: ");
                    int value = scanner.nextInt();
                    skipList.insert(value);
                    System.out.println("Элемент " + value + " добавлен.");
                    break;
                case 2:
                    System.out.print("Введите число для поиска: ");
                    int searchValue = scanner.nextInt();
                    if (skipList.search(searchValue)) {
                        System.out.println("Элемент найден!");
                    } else {
                        System.out.println("Элемент не найден.");
                    }
                    break;
                case 3:
                    System.out.println("Skip List:");
                    skipList.printSkipList();
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        }
        }