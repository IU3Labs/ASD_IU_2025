import java.util.HashMap;
import java.util.Map;
/**
 * LRU (Least Recently Used) Cache - кэш с вытеснением наименее используемых элементов
 * Реализация: двусвязный список + хеш-таблица для доступа O(1)
 */
class LRUCache<K, V> {
    public  LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(null, null);
        this.tail = new Node(null, null);

        head.next = tail;
        tail.prev = head;
    }

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
    private final  Map<K, Node> cache;
    private final Node head;
    private final Node tail;

    /**
     * Получение значения по ключу
     * Если ключ существует, перемещаем элемент в начало (как самый новый)
     */

    public V get(K key) {
        Node node = cache.get(key);
        if (node==null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * Добавление/обновление значения
     * Если ключ существует - обновляем значение и перемещаем в начало
     * Если ключа нет - создаем новый узел
     * Если кэш полон - удаляем самый старый элемент
     */

    public void put(K key, V value){
        Node node = cache.get(key);

        if(node == null) {
            Node newNode = new Node(key, value);
            cache.put(key,newNode);
            addToHead(newNode);

            if (cache.size() > capacity) {
                Node tail = removeTail();
                cache.remove(tail.key);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
    /**
     * Добавление узла в начало списка (после фиктивной головы)
     */
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Удаление узла из списка
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    /**
     * Перемещение существующего узла в начало
     */
    private void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }
    /**
     * Удаление самого старого узла (перед фиктивным хвостом)
     */
    private Node removeTail() {
        Node res = tail.prev; // Самый старый узел
        removeNode(res);
        return res;
    }

    /**
     * Печать текущего состояния кэша (для демонстрации)
     */
    public void printCache() {
        System.out.print("LRU Cache (newest -> oldest): ");
        Node current = head.next;
        while (current != tail) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }
}