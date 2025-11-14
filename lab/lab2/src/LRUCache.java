import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private static class Node { // Создание вложенного класса для элемента
        int key, value;
        Node prev, next;

        Node(int key, int value) { // Конструктор узла
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity; // Макс размер
    private final Map<Integer, Node> cache; // HashMap
    private final Node head, tail;

    public LRUCache(int capacity) { // Конструктор
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1; // Если такого элемента нету
        Node node = cache.get(key); // Берем элемент по ключу
        remove(node); // Убираем этот элемент из списка
        insertToFront(node); // И добавляем его в начала т.к. он теперь является самым новым
        return node.value;
    }

    public void put(int key, int value) { // Добавление нового элемента
        if (cache.containsKey(key)) { // Удаляем старый, если такой ключ есть
            remove(cache.get(key));
        }
        if (cache.size() == capacity) { // Если заполнен, то удаляем самый старый
            remove(tail.prev);
        }
        Node node = new Node(key, value); // Добавляем новый узел в начало
        insertToFront(node);
    }

    private void remove(Node node) { // Удаление
        cache.remove(node.key); // Удаляем этот элемент
        node.prev.next = node.next; // Ставим новые указатели
        node.next.prev = node.prev;
    }

    private void insertToFront(Node node) { // Вставка в начало
        cache.put(node.key, node); // Кладем новый элемент
        node.next = head.next; // Обновляем указатели
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3); // удалится ключ 2
        System.out.println(cache.get(2)); // -1

    }
}
