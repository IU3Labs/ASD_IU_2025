// Группа B. Задание 2. Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
// списка и хеш-таблицы. Прокомментировать код.

package LRUCache;

/*
 * Храним ограниченное количество элементов. При добавлении нового, если места нет -
 * удаляем самый старый (к которому дольше всего не обращались).
 * Двусвязный список - хранит элементы в порядке использования
 * Хеш-таблица - для быстрого доступа O(1) по ключу
 *
 */

public class LRUCache {
    private final int capacity;
    private int size;
    private DoublyLinkedList list;
    private HashTable hashTable;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.list = new DoublyLinkedList();
        this.hashTable = new HashTable(capacity * 2);
    }

    // Получает значение по ключу, обновляя позицию элемента как наиболее используемого
    public int get(int key) {
        CacheNode node = hashTable.find(key);
        if (node == null) {
            return -1; // Ключ не найден
        }

        // Перемещаем узел в начало списка (как наиболее используемый)
        list.moveToHead(node);
        return node.value;
    }

    // Добавляет или обновляет значение по ключу
    public void put(int key, int value) {
        CacheNode node = hashTable.find(key);

        if (node != null) {
            // Ключ существует - обновляем значение и перемещаем в начало
            node.value = value;
            list.moveToHead(node);
        } else {
            // Новый ключ - создаем узел
            node = new CacheNode(key, value);

            if (size >= capacity) {
                // Кэш полный - удаляем наименее используемый элемент
                evictLRU();
            }

            // Добавляем новый узел
            list.addToHead(node);
            hashTable.put(node);
            size++;
        }
    }

    // Вытесняет наименее используемый элемент (LRU)
    private void evictLRU() {
        CacheNode lruNode = list.removeTail();
        if (lruNode != null) {
            hashTable.remove(lruNode.key);
            size--;
        }
    }

    // Возвращает текущий размер кэша
    public int size() {
        return size;
    }

    // Проверяет, пуст ли кэш
    public boolean isEmpty() {
        return size == 0;
    }

    // Выводит текущее состояние кэша
    public void printCache() {
        System.out.print("LRU Cache: ");
        list.printList();
        System.out.println(" (размер: " + size + "/" + capacity + ")");
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        System.out.println("=== Демонстрация LRU Cache ===");

        // Добавляем элементы
        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        cache.printCache(); // [3=300] → [2=200] → [1=100]

        // Используем элемент 2 - он должен переместиться в начало
        System.out.println("get(2) = " + cache.get(2));
        cache.printCache(); // [2=200] → [3=300] → [1=100]

        // Добавляем новый элемент - должен вытеснить наименее используемый (1)
        cache.put(4, 400);
        cache.printCache(); // [4=400] → [2=200] → [3=300]

        // Используем элемент 3 - перемещается в начало
        System.out.println("get(3) = " + cache.get(3));
        cache.printCache(); // [3=300] → [4=400] → [2=200]

        // Пытаемся получить несуществующий элемент
        System.out.println("get(1) = " + cache.get(1)); // -1

        // Обновляем значение существующего ключа
        cache.put(2, 222);
        cache.printCache(); // [2=222] → [3=300] → [4=400]
    }
}
