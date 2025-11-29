import java.util.*;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node> map;
    private final Node head;
    private final Node tail;

    private class Node {
        K key; V value;
        Node prev, next;
        Node(K key, V value) { this.key = key; this.value = value; }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        if (!map.containsKey(key)) return null;
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (map.size() == capacity) {
            remove(head.next);
        }
        Node newNode = new Node(key, value);
        add(newNode);
    }

    private void add(Node node) {
        map.put(node.key, node);
        Node prevNode = tail.prev;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = tail;
        tail.prev = node;
    }

    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println(cache.get(1));
        cache.put(3, "C");

        System.out.println("Get 2: " + cache.get(2));
        System.out.println("Get 1: " + cache.get(1));
        System.out.println("Get 3: " + cache.get(3));
    }
}
