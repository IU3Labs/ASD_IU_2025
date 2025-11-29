package lab2.models;


import java.util.HashMap;
import java.util.Map;


public class LRUCache<K, V> {
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

    private final int capacity;
    private final Map<K, Node<K, V>> map; // соответствие ключ и ноды
    private final Node<K, V> head; // начало
    private final Node<K, V> tail; // конец

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) { // получить значение
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null; // нет такого ключа
        }
        moveToFront(node); // переводим ноду в начало
        return node.value;
    }

    public void put(K key, V value) { // добавить значение
        Node<K, V> node = map.get(key);
        if (node != null) { // обновляем значение и двигаем в начало
            node.value = value;
            moveToFront(node);
        } else { // иначе создаём новую ноду
            Node<K, V> newNode = new Node<>(key, value);
            map.put(key, newNode);
            addToFront(newNode);
        }
    }

    private void addToFront(Node<K, V> node) { // добавить ноду сразу после начала
        Node<K, V> first = head.next;
        head.next = node;
        node.prev = head;
        node.next = first;
        first.prev = node;
    }

    private void removeNode(Node<K, V> node) { // убрать ноду
        Node<K, V> before = node.prev;
        Node<K, V> after = node.next;
        before.next = after;
        after.prev = before;
    }

    private void moveToFront(Node<K, V> node) { // двигаем ноду в начало
        removeNode(node);
        addToFront(node);
    }

    public int size() { // текущее количество элементов
        return map.size();
    }
}