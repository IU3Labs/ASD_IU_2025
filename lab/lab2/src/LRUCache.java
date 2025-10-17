//Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
//списка и хеш-таблицы. Прокомментировать код.

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class Node {
        int key;
        int data;
        Node prev;
        Node next;

        Node(int newKey, int newValue) {
            key = newKey;
            data = newValue;
        }
    }

    private final Map<Integer, Node> cache;
    private final int lenth;
    private Node first;
    private Node last;

    public LRUCache(int newLenth) {
        this.lenth = newLenth;
        cache = new HashMap<>();
    }

    public String get(int key) {
        Node buffer = cache.get(key);
        if (buffer == null) {
            return "Ключ не найден";
        }

        // перемещаем узел в начало
        if (buffer != first) {
            delete(buffer);
            addFirst(buffer);
        }

        return "Данные: " + buffer.data;
    }

    public void put(int key, int value) {
        Node buffer = cache.get(key);

        if (buffer != null) {
            buffer.data = value;
            if (buffer != first) {
                delete(buffer);
                addFirst(buffer);
            }
            return;
        }

        if (cache.size() >= lenth) {
            removeLast();
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        addFirst(newNode);

        // если это первый элемент
        if (last == null) {
            last = newNode;
        }
    }

    private void delete(Node buffer) {
        // убираем узел из соседей
        if (buffer.prev != null) {
            buffer.prev.next = buffer.next;
        }
        if (buffer.next != null) {
            buffer.next.prev = buffer.prev;
        }

        // обновляем указатели
        if (buffer == first) {
            first = buffer.next;
        }
        if (buffer == last) {
            last = buffer.prev;
        }

        buffer.prev = null;
        buffer.next = null;
    }

    private void addFirst(Node buffer) {
        if (first != null) {
            first.prev = buffer;
            buffer.next = first;
        }

        first = buffer;

        if (last == null) {
            last = buffer;
        }
    }

    private void removeLast() {
        if (last == null) return;

        // удаляем последний узел из кэша
        cache.remove(last.key);

        // перемещаем указатель на предыдущий узел
        Node newLast = last.prev;
        if (newLast != null) {
            newLast.next = null;
        }
        last = newLast;

        if (last == null) {
            first = null;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        //тестируем, будут ли находиться потерянные ключи и будут ли они обновляться
        cache.put(6, 4);
        System.out.println(cache.get(6));

        cache.put(5, 3);
        System.out.println(cache.get(2));
    }
}

//Данные: 4
//Ключ не найден
