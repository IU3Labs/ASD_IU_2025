/*Группа B, задание 2: Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
        списка и хеш-таблицы. Прокомментировать код.*/

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    /*
     Логика: Используем двусвязный список для отслеживания порядка использования
     и HashMap для быстрого доступа к элементам
     */
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final Node<K, V> head; // Фиктивная голова (самый новый)
    private final Node<K, V> tail; // Фиктивный хвост (самый старый)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    /*
     Логика: При получении элемента перемещаем его в начало списка (как самый новый)
     */
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }

        moveToHead(node);
        return node.value;
    }

    /*
     Логика: При добавлении:
     1. Если ключ существует - обновляем значение и перемещаем в начало
     2. Если нет - создаем новый узел
     3. Если превышен capacity - удаляем самый старый элемент (из хвоста)
     */
    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);

        if (node == null) {
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            if (cache.size() > capacity) {
                Node<K, V> tailNode = removeTail();
                cache.remove(tailNode.key);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /*
     Логика: Добавление узла в начало списка
     */
    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /*
     Логика: Удаление узла из списка
     */
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /*
     Логика: Перемещение узла в начало
     */
    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    /*
     Логика: Удаление самого старого узла
     */
    private Node<K, V> removeTail() {
        Node<K, V> node = tail.prev;
        removeNode(node);
        return node;
    }

    public void printCache() {
        Node<K, V> current = head.next;
        System.out.print("LRU Cache: ");
        while (current != tail) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCache();

        cache.get(1);
        cache.printCache();

        cache.put(4, "D");
        cache.printCache();
    }
}