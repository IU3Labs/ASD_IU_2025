import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache - кэш с вытеснением наименее используемых элементов
 * Реализация: двусвязный список + хеш-таблица для доступа O(1)
 */
class LRUCache<K, V> {
    public LRUCache(int capacity) {
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
    private final Map<K, Node> cache;
    private final Node head;
    private final Node tail;

    /**
     * Получение значения по ключу
     * Если ключ существует, перемещаем элемент в начало (как самый новый)
     */
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        relocateToFront(node); // Переименованный вспомогательный метод
        return node.value;
    }

    /**
     * Добавление/обновление значения
     * Если ключ существует - обновляем значение и перемещаем в начало
     * Если ключа нет - создаем новый узел
     * Если кэш полон - удаляем самый старый элемент
     */
    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            attachAtStart(newNode); // Переименованный вспомогательный метод

            if (cache.size() > capacity) {
                Node lastNode = dropOldest(); // Переименованный вспомогательный метод
                cache.remove(lastNode.key);
            }
        } else {
            node.value = value;
            relocateToFront(node); // Переименованный вспомогательный метод
        }
    }

    /**
     * Добавление узла в начало списка (после фиктивной головы)
     */
    private void attachAtStart(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Удаление узла из списка
     */
    private void disconnectNode(Node node) { // Переименованный вспомогательный метод
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Перемещение существующего узла в начало
     */
    private void relocateToFront(Node node) {
        disconnectNode(node);
        attachAtStart(node);
    }

    /**
     * Удаление самого старого узла (перед фиктивным хвостом)
     */
    private Node dropOldest() {
        Node res = tail.prev;
        // Самый старый узел
        disconnectNode(res);
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