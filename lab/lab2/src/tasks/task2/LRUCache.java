package tasks.task2;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Узел двусвязного списка
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head; // Фиктивный головной узел
    private final Node tail; // Фиктивный хвостовой узел
    private int size; // Количество созданных узлов кеша

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Инициализируем фиктивные узлы
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    //Получаем значение по ключу
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        moveToHead(node); // Обновляем порядок использования
        return node.value;
    }

    //Добавление нового узла кэша
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }

        if (cache.size() >= capacity) {
            removeLRU();
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        addToHead(newNode);
        size++;
    }

    // Добавляем узел сразу после головного
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Удаляем узел из списка
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Перемещаем узел в начало списка
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // Удаляем наименее используемый элемент (перед хвостом)
    private void removeLRU() {
        Node lru = tail.prev;
        removeNode(lru);
        cache.remove(lru.key);
    }

    public int getCashSize(){
        return size;
    }
}
