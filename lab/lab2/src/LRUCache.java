/**
 * LRU Cache реализация с нуля с использованием двусвязного списка и хеш-таблицы
 */
public class LRUCache<K, V> {

    /**
     * Узел двусвязного списка
     */
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

    /**
     * Простая хеш-таблица с цепочками для разрешения коллизий
     */
    private class HashTable {
        private class Entry {
            K key;
            Node node;
            Entry next;

            Entry(K key, Node node) {
                this.key = key;
                this.node = node;
            }
        }

        private Entry[] table;
        private final int capacity;
        private int size;

        @SuppressWarnings("unchecked")
        HashTable(int capacity) {
            this.capacity = capacity;
            this.table =  (Entry[]) new Object[capacity];
            this.size = 0;
        }

        private int hash(K key) {
            return Math.abs(key.hashCode()) % capacity;
        }

        void put(K key, Node node) {
            int index = hash(key);
            Entry current = table[index];

            // Проверяем, существует ли уже ключ
            while (current != null) {
                if (current.key.equals(key)) {
                    current.node = node;
                    return;
                }
                current = current.next;
            }

            // Добавляем новую запись
            Entry newEntry = new Entry(key, node);
            newEntry.next = table[index];
            table[index] = newEntry;
            size++;
        }

        Node get(K key) {
            int index = hash(key);
            Entry current = table[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    return current.node;
                }
                current = current.next;
            }
            return null;
        }

        Node remove(K key) {
            int index = hash(key);
            Entry current = table[index];
            Entry prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    if (prev == null) {
                        table[index] = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                    return current.node;
                }
                prev = current;
                current = current.next;
            }
            return null;
        }

        boolean containsKey(K key) {
            return get(key) != null;
        }

        int size() {
            return size;
        }
    }

    private final int capacity;
    private HashTable table;
    private Node head;
    private Node tail;
    private int size;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Емкость должна быть положительной");
        }

        this.capacity = capacity;
        this.table = new HashTable(capacity * 2); // Увеличиваем для уменьшения коллизий
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Получение значения по ключу с обновлением позиции в кэше
     */
    public V get(K key) {
        Node node = table.get(key);
        if (node == null) {
            return null;
        }

        // Перемещаем узел в начало (самый новый)
        moveToHead(node);
        return node.value;
    }

    /**
     * Добавление или обновление элемента в кэше
     */
    public void put(K key, V value) {
        Node node = table.get(key);

        if (node == null) {
            // Создаем новый узел
            node = new Node(key, value);
            table.put(key, node);
            addNode(node);
            size++;

            // Проверяем превышение емкости
            if (size > capacity) {
                // Удаляем самый старый элемент (хвост)
                Node tailNode = popTail();
                table.remove(tailNode.key);
                size--;
            }
        } else {
            // Обновляем существующий узел
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * Удаление элемента из кэша
     */
    public void remove(K key) {
        Node node = table.remove(key);
        if (node != null) {
            removeNode(node);
            size--;
        }
    }

    public boolean containsKey(K key) {
        return table.containsKey(key);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        table = new HashTable(capacity * 2);
        head = null;
        tail = null;
        size = 0;
    }

    // Вспомогательные методы для работы с двусвязным списком

    private void addNode(Node node) {
        node.prev = null;
        node.next = head;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = node;
        }
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
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node res = tail;
        removeNode(res);
        return res;
    }


}
