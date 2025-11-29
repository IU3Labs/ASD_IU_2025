package lab2.models;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        int key;
        int value;
        Node prev; // указатель на предыдущий узел
        Node next; // указатель на следующий узел

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> cache; // хеш-таблица для быстрого доступа по ключу
    private Node head; // фиктивный головной узел
    private Node tail; // фиктивный хвостовой узел
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        // создаем фиктивные узлы для упрощения логики
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // перемещаем найденный узел в начало (недавно использованный)
            remove(node);
            addToHead(node);
            return node.value;
        }
        return -1; // ключ не найден
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // обновляем существующий элемент
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            addToHead(node);
        } else {
            // добавляем новый элемент
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            // если превысили емкость, удаляем наименее используемый элемент
            if (cache.size() > capacity) {
                Node lru = tail.prev; // последний элемент (наименее используемый)
                remove(lru);
                cache.remove(lru.key);
            }
        }
    }

    private void addToHead(Node node) {
        // добавляем узел сразу после головного фиктивного узла
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        // удаляем узел из списка
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}