import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private static class Node<K, V> { // Создание вложенного класса для элемента
        K key;
        V value;
        Node<K, V> prev, next;

        Node(K key, V value) { // Конструктор узла
            this.key = key;
            this.value = value;
        }
    }

    private final long capacity; // Макс размер
    private final Map<K, Node<K, V>> cache; // HashMap
    private final Node<K, V> head, tail;

    public LRUCache(long capacity) { // Конструктор
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) return null; // Если такого элемента нету
        Node<K, V> node = cache.get(key); // Берем элемент по ключу
        remove(node); // Убираем этот элемент из списка
        insertToFront(node); // И добавляем его в начала т.к. он теперь является самым новым
        return node.value;
    }

    public void put(K key, V value) { // Добавление нового элемента
        if (cache.containsKey(key)) { // Удаляем старый, если такой ключ есть
            remove(cache.get(key));
        }
        if (cache.size() == capacity) { // Если заполнен, то удаляем самый старый
            remove(tail.prev);
        }
        Node<K, V> node = new Node<>(key, value); // Добавляем новый узел в начало
        insertToFront(node);
    }

    private void remove(Node<K, V> node) { // Удаление
        cache.remove(node.key); // Удаляем этот элемент
        node.prev.next = node.next; // Ставим новые указатели
        node.next.prev = node.prev;
    }

    private void insertToFront(Node<K, V> node) { // Вставка в начало
        cache.put(node.key, node); // Кладем новый элемент
        node.next = head.next; // Обновляем указатели
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
