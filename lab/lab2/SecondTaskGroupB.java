package lab2;

import java.util.HashMap;

/*
 2 Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 списка и хеш-таблицы. Прокомментировать код.
 */

public class SecondTaskGroupB<A, B> {

    // Внутренний класс для элемента кэша
    private class Node {
        A key;
        B value;
        Node next;
        Node prev;

        Node(A key, B value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity; // Максимальный размер кэша
    private Node first; // Самый недавно использованный элемент
    private Node last;  // Самый давно использованный элемент
    private final HashMap<A, Node> cache; // Хранилище для быстрого доступа


    public SecondTaskGroupB(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.first = null;
        this.last = null;
    }


    //получаем значение по ключу и перемещает элемент в начало как недавно использованный
    public B get(A key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        // Перемещаем использованный элемент в начало
        moveToFront(node);
        return node.value;
    }

    //добавляем элементы в кэш
    public void put(A key, B value) {
        Node node = cache.get(key);

        if (node != null) {
            // Обновляем существующий элемент
            node.value = value;
            moveToFront(node);
        } else {
            // Создаем новый элемент
            node = new Node(key, value);
            cache.put(key, node);
            addToFront(node);

            // Если превышен размер кэша, удаляем последний элемент
            if (cache.size() > capacity) {
                removeLast();
            }
        }
    }

    //перемещение элемента в начало списка
    private void moveToFront(Node node) {
        if (node == first) {
            return; // Уже в начале
        }
        removeNode(node);
        addToFront(node);
    }

    //удаление элемента из списка
    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

   //Добавление элемента в начало списка
    private void addToFront(Node node) {
        node.next = first;
        node.prev = null;

        if (first != null) {
            first.prev = node;
        }
        first = node;

        // Если список был пуст, элемент становится и первым и последним
        if (last == null) {
            last = node;
        }
    }


    private void removeLast() {
        if (last != null) {
            cache.remove(last.key);
            removeNode(last);
        }
    }

    //Удаление элемента по ключу
    public void remove(A key) {
        Node node = cache.get(key);
        if (node != null) {
            cache.remove(key);
            removeNode(node);
        }
    }

    //текущий размер кэша
    public int size() {
        return cache.size();
    }

    //проверка кэша на пустоту
    public boolean isEmpty() {
        return cache.isEmpty();
    }


    public void print() {
        Node current = first;
        System.out.print("LRU Cache [new -> old]: ");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }


    public void printReversed() {
        Node current = last;
        System.out.print("LRU Cache [old -> new]: ");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            if (current.prev != null) {
                System.out.print(" -> ");
            }
            current = current.prev;
        }
        System.out.println();
    }
    public static void main(String[] args) {

        SecondTaskGroupB<Integer, Integer> cache = new SecondTaskGroupB<>(2);

        System.out.println("put(1,100)");
        cache.put(1, 100);
        cache.print();

        System.out.println("put(2,200)");
        cache.put(2, 200);
        cache.print();

        System.out.println("get(1) -> " + cache.get(1));
        cache.print();

        System.out.println("put(3,300) вытеснит 2");
        cache.put(3, 300);
        cache.print();

        System.out.println("get(2) -> " + cache.get(2));
        System.out.println("put(4,400) вытеснит 1");
        cache.put(4, 400);
        cache.print();

        System.out.println("get(1) -> " + cache.get(1));
        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("get(4) -> " + cache.get(4));
        cache.printReversed();
    }
}