import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 * списка и хеш-таблицы. Прокомментировать код.
 */
public class LruCache<K, V> {
    public static void main(String[] args) {
        // Демонстрация работоспособности кэша и всех его операций
        LruCache<String, Integer> lruCache = new LruCache<>(3);
        lruCache.putElement("A", 100);
        System.out.println(lruCache);
        lruCache.putElement("B", 150);
        System.out.println(lruCache);
        lruCache.putElement("С", 300);
        System.out.println(lruCache);
        lruCache.putElement("B", 100);
        System.out.println(lruCache);
        lruCache.putElement("A", 400);
        System.out.println(lruCache);
        lruCache.putElement("D", 500);
        System.out.println(lruCache);
        System.out.println("Изменяем вместимость на 2: ");
        lruCache.setCapacity(2);
        System.out.println(lruCache);
        System.out.println(lruCache.size());
        System.out.println(lruCache.getForwardList());
        System.out.println(lruCache.getBackwardList());

    }

    private final HashMap<K, Node> map;
    private Node first;
    private Node last;

    private int capacity;
    private int size;

    public LruCache(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Вместимость должна быть положительной ");
        this.capacity = capacity;
        size = 0;
        map = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        Node entry = first;

        while (entry != null) {
            s.append("[").append(entry.key.toString()).append(", ").append(entry.value.toString()).append("]");
            if (entry != last) s.append(", ");
            entry = entry.next;
        }

        s.append("]");
        return s.toString();
    }

    /**
     * Метод для получения значения по ключу
     */
    public V getValue(K key) {
        Node entry = map.get(key);
        if (entry == null) return null;
        nodeToHead(entry);
        return entry.value;
    }

    /**
     * Метод для добавления или обновления кэша по ключу
     */
        public void putElement(K key, V value) {
            if (key == null) {
                throw new IllegalArgumentException("Ключ не существует");
            }

            Node current = map.get(key);
            if (current != null) {
                current.value = value;
                nodeToHead(current);
                return;
            }

            current = new Node(key, value);
            current.next = first;
            if (first != null) {
                first.prev = current;
            } else {
                last = current;
            }
            first = current;
            map.put(key, current);
            size++;
            if (size > capacity) {
                removeLast();
            }
        }

    /**
     * Метод для изменения максимальной вместимости кэша
     */
    public void setCapacity(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Вместимость должна быть положительной ");
        this.capacity = capacity;
        while (size > capacity) {
            removeLast();
        }
    }

    /**
     * Метод для перемещения кэша в начало списка при обращении к нему
     */
    private void nodeToHead(Node current) {
        if (current != first) {
            current.prev.next = current.next;

            if (current != last) {
                current.next.prev = current.prev;
            } else last = current.prev;

            current.prev = null;
            current.next = first;
            first.prev = current;
            first = current;
        }
    }

    /**
     * Метод для удаления последнего кэша при переполнении
     */
    private void removeLast() {
        if (last == first) first = null;
        map.remove(last.key);
        Node lastNode = last;
        last = lastNode.prev;
        lastNode.prev = null;
        if (last != null) last.next = null;
        size--;
    }

    /**
     * Возвращает текущее количество элементов в кэше
     */
    public int size() {
        System.out.println("Текущее количество элементов в массиве: ");
        return size;
    }

    /**
     * Получить список элементов в прямом порядке (от новых к старым)
     */
    public List<String> getForwardList() {
        List<String> result = new ArrayList<>();
        Node current = first;
        while (current != null) {
            result.add("[" + current.key + ", " + current.value + "]");
            current = current.next;
        }
        System.out.println("Вывод в прямом порядке(от новых к старым): ");
        return result;
    }

    /**
     * Получить список элементов в обратном порядке (от старых к новым)
     */
    public List<String> getBackwardList() {
        List<String> result = new ArrayList<>();
        Node current = last;
        while (current != null) {
            result.add("[" + current.key + ", " + current.value + "]");
            current = current.prev;
        }
        System.out.println("Вывод в обратном порядке(от старых к новым): ");
        return result;
    }


    /**
     * Внутренний класс узла двусвязного списка
     */
    class Node {
        K key;
        V value;

        Node prev;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
/*
Вывод программы:
[[A, 100]]
[[B, 150], [A, 100]]
[[С, 300], [B, 150], [A, 100]]
[[B, 100], [С, 300], [A, 100]]
[[A, 400], [B, 100], [С, 300]]
[[D, 500], [A, 400], [B, 100]]
Изменяем вместимость на 2:
[[D, 500], [A, 400]]
Текущее количество элементов в массиве:
2
Вывод в прямом порядке(от новых к старым):
[[D, 500], [A, 400]]
Вывод в обратном порядке(от старых к новым):
[[A, 400], [D, 500]]
 */
