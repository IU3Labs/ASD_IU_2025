import java.util.HashMap;

//2 Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
//списка и хеш-таблицы. Прокомментировать код.


public class LRUCache<K, V> {

//     Узел двусвязного списка, хранящий пару ключ-значение

    private static class ListNode<K, V> {
        K key;
        V value;
        ListNode<K, V> prev;
        ListNode<K, V> next;

        ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private final int maxSize;          // Максимальный размер кэша
    private final HashMap<K, ListNode<K, V>> map; // Для быстрого доступа по ключу
    private ListNode<K, V> first;       // Самый новый элемент
    private ListNode<K, V> last;        // Самый старый элемент
    private int currentSize;            // Текущий размер кэша

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Емкость должна быть положительной");
        }
        this.maxSize = capacity;
        this.map = new HashMap<>();
        this.first = null;
        this.last = null;
        this.currentSize = 0;
    }


    //      Получает значение по ключу и перемещает элемент в начало
    //
    //      key ключ для поиска
    //      значение или null если не найдено

    public V get(K key) {
        ListNode<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }

        // Перемещаем использованный элемент в начало
        promoteToFront(node);
        return node.value;
    }


    //      Добавляет или обновляет элемент в кэше
    //
    //      key ключ элемента
    //      value значение элемента

    public void put(K key, V value) {
        ListNode<K, V> node = map.get(key);

        if (node != null) {
            // Обновляем существующий элемент
            node.value = value;
            promoteToFront(node);
        } else {
            // Создаем новый элемент
            node = new ListNode<>(key, value);
            map.put(key, node);
            addToFront(node);
            currentSize++;

            // Проверяем переполнение
            if (currentSize > maxSize) {
                evictOldest();
            }
        }
    }

//        Удаляет элемент из кэша
//      key ключ для удаления
//     return true если элемент был удален, false если не найден

    public boolean remove(K key) {
        ListNode<K, V> node = map.get(key);
        if (node == null) {
            return false;
        }

        removeNode(node);
        map.remove(key);
        currentSize--;
        return true;
    }

//      Проверяет наличие ключа в кэше

    public boolean contains(K key) {
        return map.containsKey(key);
    }


//      текущее количество элементов

    public int size() {
        return currentSize;
    }

//        Проверяет пуст ли кэш

    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Очищает кэш
     */
    public void clear() {
        map.clear();
        first = null;
        last = null;
        currentSize = 0;
    }

    // внутр методы для работы со списком


//        Перемещает узел в начало списка (делает его самым новым)

    private void promoteToFront(ListNode<K, V> node) {
        if (node == first) {
            return; // Уже в начале
        }

        removeNode(node);
        addToFront(node);
    }


//        Добавляет узел в начало списка

    private void addToFront(ListNode<K, V> node) {
        node.prev = null;
        node.next = first;

        if (first != null) {
            first.prev = node;
        }
        first = node;

        if (last == null) {
            last = node; // Первый элемент становится и последним
        }
    }


//     Удаляет узел из списка
    private void removeNode(ListNode<K, V> node) {
        // Обновляем ссылки соседних узлов
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next; // Удаляем первый элемент
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev; // Удаляем последний элемент
        }

        // Очищаем ссылки удаляемого узла
        node.prev = null;
        node.next = null;
    }


//     Удаляет самый старый элемент из кэша

    private void evictOldest() {
        if (last == null) return;

        map.remove(last.key);
        removeNode(last);
        currentSize--;
    }

    //     Выводит кэш в порядке от нового к старому

    public void display() {
        System.out.print("LRU Cache (новые -> старые): [");
        ListNode<K, V> current = first;
        while (current != null) {
            System.out.print(current);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println("]");
    }


    public void displayReversed() {
        System.out.print("LRU Cache (старые -> новые): [");
        ListNode<K, V> current = last;
        while (current != null) {
            System.out.print(current);
            if (current.prev != null) {
                System.out.print(" ← ");
            }
            current = current.prev;
        }
        System.out.println("]");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LRUCache{size=").append(currentSize)
                .append(", capacity=").append(maxSize)
                .append(", elements=[");

        ListNode<K, V> current = first;
        while (current != null) {
            sb.append(current);
            if (current.next != null) {
                sb.append(" → ");
            }
            current = current.next;
        }
        sb.append("]}");
        return sb.toString();
    }

    //  Демонстрация работы

    public static void main(String[] args) {
        System.out.println("Демонстрация работы LRU Cache");

        // Создаем кэш размером 3
        LRUCache<String, Integer> cache = new LRUCache<>(3);

        System.out.println("1.Добавляем три элемента:");
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        cache.display();

        System.out.println("2. Получаем элемент 'A' (должен переместиться в начало):");
        Integer value = cache.get("A");
        System.out.println("   Получено: " + value);
        cache.display();

        System.out.println("3. Добавляем четвертый элемент (должен вытеснить 'B'):");
        cache.put("D", 4);
        cache.display();

        System.out.println("4. Обновляем значение элемента 'C':");
        cache.put("C", 33);
        cache.display();

        System.out.println("5.Пытаемся получить вытесненный элемент 'B':");
        Integer missing = cache.get("B");
        System.out.println("   Результат: " + missing);

        System.out.println("6.Удаляем элемент 'A':");
        boolean removed = cache.remove("A");
        System.out.println("   Удален: " + removed);
        cache.display();

        System.out.println("7. Проверяем состояние кэша:");
        System.out.println("   Размер: " + cache.size());
        System.out.println("   Пустой: " + cache.isEmpty());
        System.out.println("   Содержит 'C': " + cache.contains("C"));
        System.out.println("   Полное представление: " + cache);

        System.out.println("8.Обратный порядок вывода:");
        cache.displayReversed();

        System.out.println("9. Очищаем кэш:");
        cache.clear();
        System.out.println("   Размер после очистки: " + cache.size());
        cache.display();
    }
}