package lab2;

import java.util.*;

class SkipList {
    private Node2 head; // ссылка на высший узел
    private int levels; // количество уровней
    private Random random;

    public SkipList() {
        head = new Node2(Integer.MIN_VALUE, null, null);
        levels = 1;
        random = new Random();
    }

    public boolean search(int value) {
        Node2 current = head;
        while (current != null) {
            while (current.neibour != null && current.neibour.val <= value) {
                current = current.neibour; // идем вперед пока не найдем нужный узел
            }
            if (current.val == value) {
                return true;
            }
            current = current.child; // спускаемся вниз
        }
        return false;
    }

    public void insert(int value) { // поиск места для вставки и сама вставка
        Stack<Node2> stack = new Stack<>();
        Node2 current = head;
        // поиск на каждом уровне
        while (current != null) {
            while (current.neibour != null && current.neibour.val < value) {
                current = current.neibour;
            }
            stack.push(current);
            current = current.child;
        }

        boolean insertUp = true; // поднятие уровня
        Node2 downNode = null; // ссылка на узел на уровне ниже

        while (insertUp && !stack.isEmpty()) { // вставка на уровнях которые мы нашли
            Node2 prev = stack.pop();
            Node2 newNode = new Node2(value, prev.neibour, downNode);
            prev.neibour = newNode;
            downNode = newNode;

            insertUp = (random.nextInt(2) == 0); // 50% вероятность
        }

        // если узел поднимается на новый уровень, создаем его
        if (insertUp) {
            head = new Node2(Integer.MIN_VALUE, null, head);
            head.neibour = new Node2(value, null, downNode);
            levels++;
        }
    }

    // вывод
    public void printSkipList() {
        Node2 current = head;
        int level = levels;
        while (current != null) {
            System.out.print("Уровень " + level + ": ");
            Node2 node = current.neibour;
            while (node != null) {
                System.out.print(node.val + " ");
                node = node.neibour;
            }
            System.out.println();
            current = current.child;
            level--;
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input els: ");
        String[] elements = scanner.nextLine().split(" ");
        for (String element : elements) {
            skipList.insert(Integer.parseInt(element));
        }

        System.out.println("\nSkip List:");
        skipList.printSkipList();

        while (true) {
            System.out.println("1 - Вставка нового");
            System.out.println("2 - Поиск элемента");
            System.out.println("3 - Вывести лист");
            System.out.println("4 = ctrlC");
            System.out.print("Move: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Число для вставки: ");
                    int value = scanner.nextInt();
                    skipList.insert(value);
                    System.out.println("Элемент " + value + " добавлен.");
                    break;
                case 2:
                    System.out.print("Число для поиска: ");
                    int searchValue = scanner.nextInt();
                    if (skipList.search(searchValue)) {
                        System.out.println("Фаунд");
                    } else {
                        System.out.println("Не фаунд");
                    }
                    break;
                case 3:
                    System.out.println("Список: ");
                    skipList.printSkipList();
                    break;
                case 4:
                    System.out.println("туда его");
                    scanner.close();
                    return;
                default:
                    System.out.println("такова тут нет");
            }
        }
        }
        }