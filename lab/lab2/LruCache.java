import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private Node<K, V> head; // Самый недавно использованный
    private Node<K, V> tail; // Наименее недавно использованный

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LruCache(int capacity) {
        if (capacity <= 0) {
            System.out.println("Введена неположительная ёмкость");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>(Math.max(16, capacity));
        this.head = null;
        this.tail = null;
    }

    public V get(K key) {
        if (key == null) {
            System.out.println("Ключ не должен иметь нулевое значение");
            return null;
        }

        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }

        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Ключ не должен иметь нулевое значение");
        }
        if (value == null) {
            throw new NullPointerException("Значение не должно быть нулевым");
        }

        Node<K, V> node = cache.get(key);
        if (node != null) {
            // Ключ уже существует, обновляем значение и перемещаем в голову
            node.value = value;
            moveToHead(node);
        } else {
            // Создаем новый узел
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            if (cache.size() > capacity) {
                removeTail();
            }
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }

    private void moveToHead(Node<K, V> node) {
        if (node == head) {
            return; // Узел и есть head
        }

        // Перемещаем узел в head
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node<K, V> node) {
        // Обновляем связи соседних узлов
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // node был головой
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            // node был хвостом
            tail = node.prev;
        }

        // Очищаем связи узла
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node<K, V> node) {
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        // Если список был пуст, обновляем tail
        if (tail == null) {
            tail = head;
        }
    }

    private void removeTail() {
        if (tail == null) {
            return;
        }

        Node<K, V> tailNode = tail;

        // Удаляем из списка
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            // Список стал пустым
            head = null;
        }

        // Удаляем из кэша
        cache.remove(tailNode.key);
    }

    public void printCache() {
        Node<K, V> current = head;
        System.out.print("Cache: ");
        while (current != null) {
            System.out.print("(" + current.key + ":" + current.value + ") ");
            current = current.next;
        }
        System.out.println();
    }

    // Тестирование
    public static void main(String[] args) {
        LruCache<Integer, String> cache = new LruCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.printCache();
    }
}