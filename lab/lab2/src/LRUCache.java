//Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
//списка и хеш-таблицы. Прокомментировать код.

import java.util.HashMap;

public class LRUCache {

    // Класс для узла двусвязного списка
    private static class Node {
        int key;
        int value;
        Node prev;  // Ссылка на предыдущий узел
        Node next;  // Ссылка на следующий узел

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> cache;  // Хеш-таблица для быстрого доступа
    private Node head;  // Голова списка (самый новый элемент)
    private Node tail;  // Хвост списка (самый старый элемент)
    private int capacity;  // Максимальная вместимость кэша
    private int size;  // Текущий размер кэша


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    // Добавление элемента в начало (самый новый)
    public void put(int key, int value) {
        // Если ключ уже существует, обновляем значение
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);  // Делаем его самым новым
            System.out.println("Обновлен элемент [" + key + "=" + value + "]");
            return;
        }

        // Создаем новый узел
        Node newNode = new Node(key, value);

        // Если кэш пуст
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Добавляем в начало
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        // Добавляем в хеш-таблицу
        cache.put(key, newNode);
        size++;
        System.out.println("Добавлен элемент [" + key + "=" + value + "]");

        // Если превысили вместимость, удаляем самый старый
        if (size > capacity) {
            removeLRU();
        }
    }


    // Добавление элемента в конец (самый старый)
    public void putToEnd(int key, int value) {
        put(key, value);  // Сначала добавляем как самый новый
        // Затем перемещаем в конец, делая его самым старым
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            moveToTail(node);
            System.out.println("Элемент [" + key + "=" + value + "] добавлен в конец");
        }
    }


    // Удаление элемента по ключу
    public boolean remove(int key) {
        if (!cache.containsKey(key)) {
            System.out.println("Элемент с ключом " + key + " не найден");
            return false;
        }

        Node node = cache.get(key);

        // Удаляем из списка
        removeFromList(node);

        // Удаляем из хеш-таблицы
        cache.remove(key);
        size--;

        System.out.println("Удален элемент с ключом " + key);
        return true;
    }


    // Удаление последнего элемента (самого старого)
    public boolean removeLast() {
        if (tail == null) {
            System.out.println("Кэш пуст");
            return false;
        }

        return remove(tail.key);
    }


    // Получение элемента по ключу
    public int get(int key) {
        if (!cache.containsKey(key)) {
            System.out.println("Элемент с ключом " + key + " не найден");
            return -1;
        }

        Node node = cache.get(key);
        moveToHead(node);  // Делаем элемент самым новым
        System.out.println("Получен элемент [" + key + "=" + node.value + "]");
        return node.value;
    }


    // Подсчет числа элементов в кэше
    public int count() {
        return size;
    }


    // Печать кэша в прямом порядке (от нового к старому)
    public void printForward() {
        if (size == 0) {
            System.out.println("Кэш пуст");
            return;
        }

        System.out.print("LRU Cache (новые → старые): ");
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }


    // Печать кэша в обратном порядке (от старого к новому)
    public void printReverse() {
        if (size == 0) {
            System.out.println("Кэш пуст");
            return;
        }

        System.out.print("LRU Cache (старые → новые): ");
        Node current = tail;
        while (current != null) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.prev;
        }
        System.out.println();
    }

    // Проверка наличия элемента по ключу

    public boolean contains(int key) {
        return cache.containsKey(key);
    }


    // Очистка кэша
    public void clear() {
        cache.clear();
        head = null;
        tail = null;
        size = 0;
        System.out.println("Кэш очищен");
    }


    // Перемещение узла в начало (делает его самым новым)
    private void moveToHead(Node node) {
        if (node == head) return;

        removeFromList(node);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }


    //Перемещение узла в конец (делает его самым старым)
    private void moveToTail(Node node) {
        if (node == tail) return;

        removeFromList(node);

        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
        }
    }


    // Удаление узла из списка
    private void removeFromList(Node node) {
        // Обновляем предыдущий узел
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // Если это голова
            head = node.next;
        }

        // Обновляем следующий узел
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            // Если это хвост
            tail = node.prev;
        }

        // Очищаем ссылки
        node.prev = null;
        node.next = null;
    }


    // Удаление самого старого элемента (LRU)
    private void removeLRU() {
        if (tail == null) return;

        System.out.println("Вытеснен LRU элемент [" + tail.key + "=" + tail.value + "]");
        remove(tail.key);
    }


    // Демонстрация работы LRU Cache
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы LRU Cache ===\n");

        // Создаем кэш вместимостью 3 элемента
        LRUCache cache = new LRUCache(3);

        System.out.println("1. Добавление элементов:");
        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        cache.printForward();
        System.out.println("Количество элементов: " + cache.count());

        System.out.println("\n2. Доступ к элементу (делает его новым):");
        cache.get(1);  // Делаем элемент 1 самым новым
        cache.printForward();

        System.out.println("\n3. Добавление при переполнении:");
        cache.put(4, 400);  // Должен вытеснить элемент 2 (самый старый)
        cache.printForward();
        cache.printReverse();

        System.out.println("\n4. Добавление существующего элемента:");
        cache.put(3, 350);  // Обновляем значение и делаем его новым
        cache.printForward();

        System.out.println("\n5. Добавление элемента в конец:");
        cache.putToEnd(5, 500);  // Добавляем и сразу делаем старым
        cache.printForward();
        System.out.println("Количество элементов: " + cache.count());

        System.out.println("\n6. Удаление элементов:");
        cache.remove(3);  // Удаляем по ключу
        cache.printForward();
        cache.removeLast();  // Удаляем самый старый
        cache.printForward();
        System.out.println("Количество элементов: " + cache.count());

        System.out.println("\n7. Добавление новых элементов:");
        cache.put(6, 600);
        cache.put(7, 700);
        cache.put(8, 800);
        cache.printForward();
        cache.printReverse();

        System.out.println("\n8. Проверка наличия элементов:");
        System.out.println("Содержит ключ 6? " + cache.contains(6));
        System.out.println("Содержит ключ 3? " + cache.contains(3));

        System.out.println("\n9. Очистка кэша:");
        cache.clear();
        cache.printForward();
        System.out.println("Количество элементов: " + cache.count());

        System.out.println("\n10. Добавление после очистки:");
        cache.put(10, 1000);
        cache.put(20, 2000);
        cache.printForward();
        cache.printReverse();
    }
}