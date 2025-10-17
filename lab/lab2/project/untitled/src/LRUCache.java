/*
Реализовать LRU Cache - кэш свытеснением на основе связанного списка и хэш-таблицы. Прокомментиовать код
 */
import java.util.HashMap;
public class LRUCache<K, V> {
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
    private final int capacity;       // Максимальный размер кэша
    private HashMap<K, Node> map;    // Быстрый доступ к узлам по ключу
    private Node head;              // Голова списка (самый недавно использованный)
    private Node tail;             // Хвост списка (самый давно использованный)
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }
    // Получение значения по ключу
    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            System.out.println("Ключ " + key + " не найден");
            return null;
        }
        // Перемещаем узел в начало списка
        moveToHead(node);
        return node.value;
    }
    // Добавление или обновление элемента
    public void put(K key, V value) {
        Node node = map.get(key);
        if (node != null) {
            // Если ключ уже есть, то обновляем значение и переносим в начало
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addFirst(newNode);
            // Если кэш переполнен, то удаляем последний элемент
            if (map.size() > capacity) {
                removeLast();
            }
        }
    }
    // Добавить узел в начало списка
    private void addFirst(Node node) {
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) { // если список был пуст
            tail = head;
        }
    }
    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next; // если удаляем голову
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev; // если удаляем хвост
        }
    }
    // Переместить существующий узел в начало списка
    private void moveToHead(Node node) {
        removeNode(node);
        addFirst(node);
    }
    // Удалить последний (наименее используемый) элемент
    private void removeLast() {
        if (tail == null) return;
        map.remove(tail.key);
        removeNode(tail);
    }
    public void printCache() {
        Node current = head;
        System.out.print("Кэш: [");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            current = current.next;
            if (current != null) System.out.print(", ");
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCache();
        cache.get(1);
        cache.printCache();
        cache.put(4, "D");
        cache.printCache();
        cache.get(3);
        cache.printCache();
    }
}
/*
Логика:
        cache.printCache()   [3=C, 2=B, 1=A]
        cache.get(1);        используем ключ 1 — теперь он перемещается в начало списка
        cache.printCache();  [1=A, 3=C, 2=B]
        cache.put(4, "D");   добавляем новый элемент - вытесняется самый старый
        cache.printCache();  [4=D, 1=A, 3=C]
        cache.get(3);        аналог с get(1)
        cache.printCache();  [3=C, 4=D, 1=A]
 */