package lab2.b;

import java.util.HashMap;

/**
 2 Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 списка и хеш-таблицы. Прокомментировать код.
 */
public class LRUCache<K, V> {

    // Внутренний класс для элемента кэша
    private class Node {
        K key;
        V value;
        Node next;
        Node prev;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity; // Максимальный размер кэша
    private Node first; // Самый недавно использованный элемент
    private Node last;  // Самый давно использованный элемент
    private final HashMap<K, Node> cache; // Хранилище для быстрого доступа

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.first = null;
        this.last = null;
    }

    /**
     * Получение значения по ключу
     * перемещает элемент в начало как недавно использованный
     */
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        // Перемещаем использованный элемент в начало
        moveToFront(node);
        return node.value;
    }

    /**
     * Добавление элемента в кэш
     */
    public void put(K key, V value) {
        Node node = cache.get(key);

        if (node != null) {
            // Обновляем существующий элемент
            node.value = value;
            moveToFront(node);
        } else {
            // Создаем новый элемент
            node = new Node(key, value);
            cache.put(key, node);
            addToFront(node);

            // Если превышен размер кэша, удаляем последний элемент
            if (cache.size() > capacity) {
                removeLast();
            }
        }
    }

    /**
     * Перемещение элемента в начало списка
     */
    private void moveToFront(Node node) {
        if (node == first) {
            return; // Уже в начале
        }
        removeNode(node);
        addToFront(node);
    }

    /**
     * Удаление элемента из списка
     */
    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next; // Удаляем первый элемент
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev; // Удаляем последний элемент
        }

        node.prev = null;
        node.next = null;
    }

    /**
     * Добавление элемента в начало списка
     */
    private void addToFront(Node node) {
        node.next = first;
        node.prev = null;

        if (first != null) {
            first.prev = node;
        }
        first = node;

        // Если список был пуст, элемент становится и первым и последним
        if (last == null) {
            last = node;
        }
    }


    private void removeLast() {
        if (last != null) {
            cache.remove(last.key);
            removeNode(last);
        }
    }

    /**
     * Удаление элемента по ключу
     */
    public void remove(K key) {
        Node node = cache.get(key);
        if (node != null) {
            cache.remove(key);
            removeNode(node);
        }
    }

    /**
     * Текущий размер кэша
     */
    public int size() {
        return cache.size();
    }

    /**
     * Проверка пустоты кэша
     */
    public boolean isEmpty() {
        return cache.isEmpty();
    }


    public void print() {
        Node current = first;
        System.out.print("LRU Cache [new -> old]: ");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }


    public void printReversed() {
        Node current = last;
        System.out.print("LRU Cache [old -> new]: ");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            if (current.prev != null) {
                System.out.print(" -> ");
            }
            current = current.prev;
        }
        System.out.println();
    }
}
