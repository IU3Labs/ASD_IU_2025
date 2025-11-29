/*
Задание:
    Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
    списка и хеш-таблицы. Прокомментировать код.
КОММЕНТАРИИ БУДУТ К КЛАССУ, Т.К. В main ВСЕ "ПРОКОММЕНТИРОВАННО" ЧЕРЕЗ print
 */

import java.util.HashMap;
import java.util.Scanner;

public class LruCache {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер кеша: ");
        int capacity = scan.nextInt();

        Cache cache = new Cache(capacity);

        System.out.print("Введите количество операций: ");
        int count = scan.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Введите тип операции (1-put, 2-get): ");
            int op = scan.nextInt();

            if (op == 1) {
                System.out.print("Введите key: ");
                int key = scan.nextInt();

                System.out.print("Введите value: ");
                int value = scan.nextInt();

                cache.put(key, value);

            } else if (op == 2) {
                System.out.print("Введите key: ");
                int key = scan.nextInt();

                int result = cache.get(key);
                System.out.println("Результат get: " + result);
            }
        }
        cache.printCache();
        scan.close();
    }
}


class Cache {                               //само создание класса

    class Node {                            // через узлы реализация двусвязного списка
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {          //конструктор узла
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;

    public Cache(int capacity) {            //конструктор кэша
        this.capacity = capacity;
        map = new HashMap<>(capacity);



        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    /*
    Метод get: если ключа нет, то просто вернем -1. А если ключ есть,
    то мы берем узел, удаляем из списка, записываем новым в конец и возвращаем значение
     */
    public int get(int key) {           
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        removeNode(node);
        addToEnd(node);

        return node.value;
    }
    /*
    Проверяем есть ли ключ, если ключ есть, то меняем значение, удаляем узел из списка  и убираем его в конец.
    Если ключа нет, то проверяем заполненность кеша. если заполнен, то удаляем элемент, стоящий после головного узла.
    Создаем новый узел в конце списка.
     */
    public void put(int key, int value) {

        if (map.containsKey(key)) {

            Node node = map.get(key);
            node.value = value;

            removeNode(node);
            addToEnd(node);

        } else {

            if (map.size() == capacity) {
                Node old = head.next;
                removeNode(old);
                map.remove(old.key);
            }

            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToEnd(newNode);
        }
    }
    // Обычное удаление узла из двустороннего списка
    private void removeNode(Node node) {
        Node left = node.prev;
        Node right = node.next;

        left.next = right;
        right.prev = left;
    }
    //Добавление узла в конец списка
    private void addToEnd(Node node) {

        Node beforeTail = tail.prev;

        beforeTail.next = node;
        node.prev = beforeTail;

        node.next = tail;
        tail.prev = node;
    }
    public void printCache() {
        Node current = head.next;

        if (current == tail) {
            System.out.println("Кеш пуст.");
            return;
        }

        while (current != tail) {
            System.out.println("key = " + current.key + ", value = " + current.value);
            current = current.next;
        }
    }
}
