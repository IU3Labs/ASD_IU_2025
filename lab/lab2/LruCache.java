package lab2;

import java.util.HashMap;

/**
 * Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 * списка и хеш-таблицы. Прокомментировать код.
 */
public class LruCache<K, V> {
    public static void main(String[] args) {
        // Демонстрация работоспособности кэша и всех его операций
        LruCache<String, Integer> cache = new LruCache<>(3);
        cache.put("Alpha", 100);
        System.out.println(cache);
        cache.put("Beta", 150);
        System.out.println(cache);
        cache.put("Gamma", 300);
        System.out.println(cache);
        cache.get("Beta");
        System.out.println(cache);
        cache.put("Alpha", 400);
        System.out.println(cache);
        cache.put("Delta", 500);
        System.out.println(cache);
        cache.setCapacity(2);
        System.out.println(cache);
    }

    private final HashMap<K, Node> map;
    private Node first;
    private Node last;

    private int capacity;
    private int size;

    public LruCache(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
        this.capacity = capacity;
        size = 0;
        map = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        Node entry = first;

        while (entry != null) {
            s.append("[").append(entry.key.toString()).append(", ").append(entry.value.toString()).append("]");
            if (entry != last) s.append(", ");
            entry = entry.next;
        }

        s.append("]");
        return s.toString();
    }

    /**
     * Метод для получения кэшированного значения по ключу
     */
    public V get(K key) {
        Node entry = map.get(key);
        if (entry == null) return null;
        mainstream(entry);
        return entry.value;
    }

    /**
     * Метод для добавления или обновления кэша по ключу
     */
    public void put(K key, V value) {
        Node entry = map.get(key);

        if (entry != null) {
            entry.value = value;
            mainstream(entry);
            return;
        }

        entry = new Node(key, value);
        entry.next = first;
        if (first != null) first.prev = entry;
        else last = entry;
        first = entry;
        map.put(key, entry);

        size++;
        if (size > capacity) removeLast();
    }

    /**
     * Метод для изменения максимальной вместимости кэша
     */
    public void setCapacity(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
        this.capacity = capacity;
        while (size > capacity) removeLast();
    }

    /**
     * Метод для перемещения кэша в начало списка при обращении к нему
     */
    private void mainstream(Node entry) {
        if (entry != first) {
            entry.prev.next = entry.next;

            if (entry != last) {
                entry.next.prev = entry.prev;
            } else last = entry.prev;

            entry.prev = null;
            entry.next = first;
            first.prev = entry;
            first = entry;
        }
    }

    /**
     * Метод для удаления последнего кэша при переполнении
     */
    private void removeLast() {
        if (last == first) first = null;
        map.remove(last.key);
        Node lastNode = last;
        last = lastNode.prev;
        lastNode.prev = null;
        if (last != null) last.next = null;
        size--;
    }

    /**
     * Внутренний класс элемента связного списка
     */
    class Node {
        K key;
        V value;

        Node prev;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}