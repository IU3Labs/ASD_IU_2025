package models;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    // Узел двусвязного списка
    private class Node {
        K key;
        V value;
        Node prev;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node> cache;
    private final Node head; // голова
    private final Node tail; // хвост

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Инициализируем узлы
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }

        // Перемещаем узел в начало (самый недавно использованный)
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node node = cache.get(key);

        if (node == null) {
            // Создаем новый узел
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);

            // Если превысили capacity, удаляем самый старый элемент
            if (cache.size() > capacity) {
                Node tail = popTail();
                cache.remove(tail.key);
            }
        } else {
            // Обновляем значение и перемещаем в начало
            node.value = value;
            moveToHead(node);
        }
    }

    private void addNode(Node node) { // Добавить элемент
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) { // Удалить элемент
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(Node node) { // Передвинуть в начало
        removeNode(node);
        addNode(node);
    }

    private Node popTail() { // Удалить хвост
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    // Вывод содержимого кэша
    public void printCache() {
        Node current = head.next;
        System.out.print("LRU Cache: ");
        while (current != tail) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }
}