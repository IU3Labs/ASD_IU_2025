//Группа Б Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
//списка и хеш-таблицы. Прокомментировать код.
import java.util.HashMap;

public class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private HashMap<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // Получение элемента
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        // Перемещаем в начало (самый новый)
        moveToHead(node);
        return node.value;
    }

    // Добавление элемента
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Обновляем существующий
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Добавляем новый
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;

            // Проверяем переполнение
            if (size > capacity) {
                Node lru = removeTail();
                cache.remove(lru.key);
                size--;
            }
        }
    }

    // Удаление элемента
    public void remove(int key) {
        if (!cache.containsKey(key)) {
            return;
        }

        Node node = cache.get(key);
        removeNode(node);
        cache.remove(key);
        size--;
    }

    // Подсчет элементов
    public int count() {
        return size;
    }

    // Печать кэша (от новых к старым)
    public void print() {
        Node current = head.next;
        while (current != tail) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        System.out.println("Демонстрация LRU Cache");

        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);

        System.out.println("Добавлены: 1=100, 2=200, 3=300");
        cache.print();
        System.out.println("Количество элементов: " + cache.count());

        System.out.println("\nПолучение элемента 1: " + cache.get(1));
        cache.print();

        System.out.println("\nДобавление 4=400 (должен вытеснить 2):");
        cache.put(4, 400);
        cache.print();

        System.out.println("\nУдаление элемента 3:");
        cache.remove(3);
        cache.print();
        System.out.println("Количество элементов: " + cache.count());
    }
}