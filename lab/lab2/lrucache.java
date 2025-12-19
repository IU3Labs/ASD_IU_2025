import java.util.*;
// Реализовать «LRU Cache» — кэш с вытеснением на основе связанного списка и хеш-таблицы. Прокомментировать код.
public class lrucache {
    private final int capacity;
    private Map<Integer, Integer> cache; //хэш-таблица для хранения ключа значения
    private LinkedHashMap<Integer, Long> accessOrder; // хэш-таблица для отслеживания времени последнего доступа

    public lrucache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        accessOrder = new LinkedHashMap<>();
    }

    public int get(int key) { //возвращает значение по ключу и обновляет время последнего допступа
        if (cache.containsKey(key)) {
            accessOrder.put(key, System.nanoTime());
            return cache.get(key);
        }
        return -1;
    }

    //метод, который добавляет элемент в кэш, если кэш переполнен, то удаляет для самого старого элемента
    public void put(int key, int value) {
        if (cache.size() >= capacity) {
            evictLeastRecentlyUsed();
        }
        cache.put(key, value);
        accessOrder.put(key, System.nanoTime());
    }

    private void evictLeastRecentlyUsed() {
        long oldest = Long.MAX_VALUE;
        int oldestKey = -1;
        for (Map.Entry<Integer, Long> entry : accessOrder.entrySet()) {
            if (entry.getValue() < oldest) {
                oldest = entry.getValue();
                oldestKey = entry.getKey();
            }
        }
        cache.remove(oldestKey);
        accessOrder.remove(oldestKey);
    }

    public static void main(String[] args) {
        lrucache cache = new lrucache(3);

        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);

        System.out.println(cache.get(1));  // 100

        cache.put(4, 400);

        System.out.println(cache.get(2));
    }
}
