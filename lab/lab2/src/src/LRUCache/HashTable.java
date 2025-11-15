package LRUCache;

// Хеш-таблица для быстрого доступа к узлам по ключу

public class HashTable {
    private CacheNode[] table;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new CacheNode[capacity];
    }

    // Простая хеш-функция
    private int hash(int key) {
        return Math.abs(key) % capacity;
    }

    // Находит узел по ключу
    public CacheNode find(int key) {
        int index = hash(key);
        CacheNode node = table[index];

        while (node != null) {
            if (node.key == key) {
                return node;
            }
            node = node.hashNext;
        }
        return null;
    }

    // Добавляет узел в хеш-таблицу
    public void put(CacheNode node) {
        int index = hash(node.key);

        // Добавляем в начало цепочки
        node.hashNext = table[index];
        table[index] = node;
    }

    // Удаляет узел по ключу
    public void remove(int key) {
        int index = hash(key);
        CacheNode current = table[index];
        CacheNode prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    // Удаляем первый элемент цепочки
                    table[index] = current.hashNext;
                } else {
                    // Удаляем из середины цепочки
                    prev.next = current.hashNext;
                }
                current.hashNext = null; // Очищаем ссылку
                return;
            }
            prev = current;
            current = current.hashNext;
        }
    }

    // Очищает хеш-таблицу
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
    }
}
