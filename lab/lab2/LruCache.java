// Реализовать LRU Cashe, прокомментировать логику

import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V> {

    private final int capacity;  // Максимальная вместимость кэша
    private final Map<K, Node<K, V>> cache; // Хеш-таблица для быстрого доступа к узлам по ключу
    private Node<K, V> head; // Указатель на "голову" двусвязного списка (самый недавно использованный элемент)
    private Node<K, V> tail; // Указатель на "хвост" двусвязного списка (наименее недавно использованный элемент)

    // Внутренний класс для представления узла в двусвязном списке
    private static class Node<K, V> {
        K key;    // Ключ элемента
        V value;  // Значение элемента
        Node<K, V> prev; // Указатель на предыдущий узел
        Node<K, V> next; // Указатель на следующий узел

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.head = null;
        this.tail = null;
    }

    // Получение значения из кэша по ключу
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null; // Ключ отсутствует в кэше
        }

        moveToHead(node); // Перемещаем узел в "голову" списка, т.к. он был использован
        return node.value;
    }

    // Добавление элемента в кэш
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            // Ключ уже существует, обновляем значение и перемещаем в "голову"
            Node<K, V> node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Ключа нет в кэше, добавляем новый узел
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            if (cache.size() > capacity) {
                // Кэш переполнен, удаляем наименее недавно использованный элемент (из "хвоста")
                Node<K, V> tailNode = removeTail();
                cache.remove(tailNode.key);
            }
        }
    }

    // Перемещение узла в "голову" списка
    private void moveToHead(Node<K, V> node) {
        if (node == head) {
            return; // Узел уже в "голове"
        }

        // Удаляем узел из текущей позиции
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (node == tail) {
            tail = node.prev; // Если удаляем "хвост", обновляем указатель tail
        }

        // Добавляем узел в "голову"
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = head; // Если список был пуст, обновляем tail
        }
    }

    // Добавление нового узла в "голову" списка
    private void addToHead(Node<K, V> node) {
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }

        head = node;

        if (tail == null) {
            tail = head; // Если список был пуст, обновляем tail
        }
    }

    // Удаление узла из "хвоста" списка
    private Node<K, V> removeTail() {
        if (tail == null) {
            return null; // Список пуст
        }

        Node<K, V> tailNode = tail;
        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            // Список стал пустым
            head = null;
        }
        return tailNode;
    }

    // вывод содержимого кэша
    public void printCache() {
        Node<K, V> current = head;
        System.out.print("Cache: ");
        while (current != null) {
            System.out.print("(" + current.key + ":" + current.value + ") ");
            current = current.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LruCache<Integer, String> cache = new LruCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.printCache();
    }
}
