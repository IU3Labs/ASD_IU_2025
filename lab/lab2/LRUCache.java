/**
 * Группа B. Задание 2.
 * Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 * списка и хеш-таблицы. Прокомментировать код.
 **/
import java.util.*;
public class LRUCache {

    private class Node { // узел двусвязного списка
        int key;     // ключ
        int value;   // значение
        Node prev, next; // ссылки на соседние узлы

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity; // максимальный размер кэша
    private int size;           // текущее количество элементов
    private Node head, tail;    // голова = самый недавно использованный, хвост = самый старый
    private final HashMap<Integer, Node> map; // быстрый доступ по ключу

    public LRUCache(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity > 0");
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int get(int key) { // Получение значения по ключу
        Node n = map.get(key);
        if (n == null) {
            System.out.println("Ключ " + key + " отсутствует");
            return Integer.MIN_VALUE; // нет такого ключа
        }
        moveToHead(n);
        System.out.println("Получено " + key + " -> " + n.value);
        return n.value;
    }

    public void put(int key, int value) {  // Добавление/обновление ключа
        Node n = map.get(key);
        if (n != null) {
            n.value = value;   // обновляем значение
            moveToHead(n);     // перемещаем к голове
            System.out.println("Обновлён ключ " + key);
        } else {
            Node newNode = new Node(key, value); // создаём новый узел
            addToHead(newNode);                  // добавляем в голову
            map.put(key, newNode);
            size++;
            System.out.println("Добавлен ключ " + key);
            if (size > capacity) {              // если переполнение
                Node removed = removeTail();    // удаляем LRU
                map.remove(removed.key);
                size--;
                System.out.println("Удалён LRU ключ " + removed.key);
            }
        }
    }

    public void addToMiddle(int key, int value) { // Добавление элемента в середину
        if (map.containsKey(key)) {
            put(key, value); // если ключ есть — обновляем
            return;
        }
        Node newNode = new Node(key, value);
        int midIndex = size / 2; // находим середину
        Node current = head;
        for (int i = 0; i < midIndex - 1; i++) {
            current = current.next;
        }
        if (current != null) {
            newNode.next = current.next;
            newNode.prev = current;

            if (current.next != null) {
                current.next.prev = newNode;
            }
            current.next = newNode;

            if (newNode.next == null) {
                tail = newNode;
            }
        } else { // Если список пустой, новый узел становится и головой, и хвостом
            head = newNode;
            tail = newNode;
        }
        map.put(key, newNode);
        size++;
        if (size > capacity) {   // проверка на переполнение
            Node removed = removeTail();
            map.remove(removed.key);
            size--;
        }
        System.out.println("Добавлен ключ " + key + " в середину");
    }

    private void moveToHead(Node n) { // Переместить узел в голову
        if (n == head) return;
        removeNode(n);
        addToHead(n);
    }

    private void addToHead(Node n) { // Добавление узла в голову
        n.prev = null;
        n.next = head;
        if (head != null) {
            head.prev = n;
        }
        head = n;
        if (tail == null) {
            tail = n;
        }
    }

    private void removeNode(Node n) { // Удаление узла из списка
        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next;
        }
        if (n.next != null) {
            n.next.prev = n.prev;
        } else {
            tail = n.prev;
        }
        n.prev = n.next = null;
    }

    private Node removeTail() { // Удаление хвоста
        Node oldTail = tail;
        if (oldTail != null) removeNode(oldTail);
        return oldTail;
    }

    public void printCache() {  // Печать кэша MRU -> LRU
        System.out.print("Кэш: ");
        Node cur = head;
        while (cur != null) {
            System.out.print("[" + cur.key + ":" + cur.value + "] ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация: ");
        LRUCache cache = new LRUCache(4);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        cache.printCache();

        cache.get(2);
        cache.printCache();

        cache.addToMiddle(5, 50);
        cache.printCache();

        cache.put(4, 40);
        cache.printCache();

        cache.put(6, 60);
        cache.printCache();
    }
}
/**
 * Демонстрация:
 *
 * Добавлен ключ 1
 * Добавлен ключ 2
 * Добавлен ключ 3
 * Кэш: [3:30] [2:20] [1:10]
 * Получено 2 -> 20
 * Кэш: [2:20] [3:30] [1:10]
 * Добавлен ключ 5 в середину
 * Кэш: [2:20] [5:50] [3:30] [1:10]
 * Добавлен ключ 4
 * Удалён LRU ключ 1
 * Кэш: [4:40] [2:20] [5:50] [3:30]
 * Добавлен ключ 6
 * Удалён LRU ключ 3
 * Кэш: [6:60] [4:40] [2:20] [5:50]
 */
