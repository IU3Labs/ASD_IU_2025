package lab2;

import java.util.HashMap;

public class SecondTaskGroupB {

    /**
     * Реализация LRU Cache.
     * Использует HashMap + двусвязный список.
     * Все операции get() и put() работают за O(1).
     */
    public static class LRUCache {

        // Узел двусвязного списка
        private static class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;                   // максимальный размер кэша
        private final HashMap<Integer, Node> map;     // быстрый доступ по ключу
        private final Node head;                      // фиктивный первый узел
        private final Node tail;                      // фиктивный последний узел

        /**
         * Конструктор LRU Cache.
         */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();

            // Используем фиктивные head и tail для удобного управления списком
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }


        /**
         * Получить значение по ключу.
         * Если ключ найден — переносим этот элемент в начало (самый "свежий").
         */
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1; // нет элемента
            }

            Node node = map.get(key);

            // Перемещаем узел в начало
            remove(node);
            insertToHead(node);

            return node.value;
        }


        /**
         * Добавить или обновить значение в кэше.
         * Если ключ есть — обновляем и перемещаем в начало.
         * Если нет — создаём новый элемент.
         * Если кэш переполнен — удаляем самый старый (хвост списка).
         */
        public void put(int key, int value) {

            // Если элемент уже есть — удаляем старый
            if (map.containsKey(key)) {
                remove(map.get(key));
            }

            Node node = new Node(key, value);
            insertToHead(node);
            map.put(key, node);

            // Если превысили размер — удаляем самый старый
            if (map.size() > capacity) {
                Node last = tail.prev;
                remove(last);
                map.remove(last.key);
            }
        }


        /**
         * Удаление узла из двусвязного списка.
         */
        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }


        /**
         * Вставка узла в начало списка (после head).
         */
        private void insertToHead(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }


        // ====================== TEST ==========================

        public static void main(String[] args) {
            LRUCache cache = new LRUCache(2);

            cache.put(1, 100); // кэш: 1
            cache.put(2, 200); // кэш: 2, 1

            System.out.println(cache.get(1)); // 100 → 1 становится самым "свежим"
            // кэш: 1, 2

            cache.put(3, 300); // вытесняет 2 → кэш: 3, 1

            System.out.println(cache.get(2)); // -1 (удалён)

            cache.put(4, 400); // вытесняет 1 → кэш: 4, 3

            System.out.println(cache.get(1)); // -1
            System.out.println(cache.get(3)); // 300
            System.out.println(cache.get(4)); // 400
        }
    }

}
