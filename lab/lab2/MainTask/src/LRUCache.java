import java.util.*;

/**
 * LRU Cache - кэш с вытеснением на основе связанного списка и хеш-таблицы.
 * Автоматически удаляет наименее недавно использованный элемент,
 * когда кэш переполняется.
 *
 * Используется LinkedHashMap для отслеживания порядка доступа,
 * что позволяет определить LRU элемент.
 */
public class LRUCache<K, V> {
    private final int capacity;
    // LinkedHashMap с accessOrder = true отслеживает порядок доступа
    private LinkedHashMap<K, V> cache;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity должен быть > 0");
        }
        this.capacity = capacity;

        // LinkedHashMap с переопределением removeEldestEntry для автоматического вытеснения
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                // Удаляем элемент, если размер превышает capacity
                return size() > capacity;
            }
        };
    }

    /**
     * Получение значения из кэша. Обновляет порядок доступа.
     */
    public V get(K key) {
        return cache.get(key);
    }

    /**
     * Добавление или обновление значения в кэше
     */
    public void put(K key, V value) {
        cache.put(key, value);
    }

    /**
     * Удаление элемента из кэша
     */
    public void remove(K key) {
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Печать содержимого кэша в порядке доступа
     */
    public void print() {
        System.out.println("\n=== LRU Cache (Capacity: " + capacity + ", Size: " + size() + ") ===");
        System.out.println("Порядок доступа (слева - самый старый, справа - самый новый):");
        for (Map.Entry<K, V> entry : cache.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);

        System.out.println("--- Добавление элементов в кэш ---");
        cache.put("user1", 100);
        cache.put("user2", 200);
        cache.put("user3", 300);
        cache.print();

        System.out.println("\n--- Доступ к user1 (обновляет его позицию) ---");
        cache.get("user1");
        cache.print();

        System.out.println("\n--- Добавление user4 (вытесняет LRU элемент user2) ---");
        cache.put("user4", 400);
        cache.print();

        System.out.println("\n--- Добавление user5 (вытесняет LRU элемент user3) ---");
        cache.put("user5", 500);
        cache.print();

        System.out.println("\n--- Доступ к user1 и user4 ---");
        cache.get("user1");
        cache.get("user4");
        cache.print();

        System.out.println("\n--- Добавление user6 (вытесняет LRU элемент user5) ---");
        cache.put("user6", 600);
        cache.print();
    }
}
