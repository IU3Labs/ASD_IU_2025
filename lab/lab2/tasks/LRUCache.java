package tasks;

import java.util.HashMap;

public class LRUCache<K, V> {
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

    private final int capacity;
    private Node head;
    private Node tail;
    private final HashMap<K, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            node = new Node(key, value);
            cache.put(key, node);
            addToHead(node);

            if (cache.size() > capacity) {
                removeTail();
            }
        }
    }

    private void moveToHead(Node node) {
        if (node == head) {
            return;
        }
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node) {
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = node;
        }
    }

    private void removeTail() {
        if (tail != null) {
            cache.remove(tail.key);
            removeNode(tail);
        }
    }

    public void remove(K key) {
        Node node = cache.get(key);
        if (node != null) {
            cache.remove(key);
            removeNode(node);
        }
    }

    public int size() {
        return cache.size();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public void printForward() {
        Node current = head;
        System.out.print("LRU [new->old]: ");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    public void printBackward() {
        Node current = tail;
        System.out.print("LRU [old->new]: ");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            if (current.prev != null) System.out.print(" -> ");
            current = current.prev;
        }
        System.out.println();
    }

    public void printInternalState() {
        System.out.println("Capacity: " + capacity + ", Size: " + size());
        System.out.println("Head: " + (head != null ? head.key : "null"));
        System.out.println("Tail: " + (tail != null ? tail.key : "null"));
    }
}