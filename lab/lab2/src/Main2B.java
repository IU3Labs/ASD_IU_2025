import java.util.*;

/*
 Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 списка и хеш-таблицы. Прокомментировать код.

 Логика LRU Cache (Least Recently Used — «наименее недавно используемый»):
 - Храним пары (key, value).
 - При запросе get(key):
       если ключ есть — считаем его «только что использованным» и
        переносим соответствующий узел в начало списка;
       если ключа нет — возвращаем -1.
 - При добавлении put(key, value):
      если ключ уже есть — обновляем значение и переносим узел в начало;
      если ключа нет:
           - создаём новый узел, добавляем в начало списка;
           - если кэш переполнился — удаляем узел из конца списка
             (самый «старый» элемент) и убираем его из хеш-таблицы.

 Структуры данных:
 - HashMap<Integer, Node> cache — даёт O(1) доступ по ключу.
 - Двусвязный список Node (prev/next):
      head — самый недавно использованный элемент (MRU);
      tail — самый давно использованный элемент (LRU).
 */

public class Main2B {

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

    private Map<Integer, Node> cache; // Хранит пары ключ -> узел списка
    private Node head;                // Начало списка (самый свежий)
    private Node tail;                // Конец списка (самый старый)
    private int capacity;             // Максимальное количество элементов
    private int size;                 // Текущее количество элементов

    public Main2B(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    // Добавление узла в начало списка
    private void addToFront(Node node) {
        node.prev = null;
        node.next = head;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = node;
        }
        size++;
    }

    // Добавление узла в конец списка
    private void addToEnd(Node node) {
        node.next = null;
        node.prev = tail;

        if (tail != null) {
            tail.next = node;
        }
        tail = node;

        if (head == null) {
            head = node;
        }
        size++;
    }

    // Добавление узла после существующего узла
    private void addAfter(Node existing, Node node) {
        if (existing == null) {
            addToEnd(node);
            return;
        }
        Node nextNode = existing.next;

        existing.next = node;
        node.prev = existing;

        node.next = nextNode;
        if (nextNode != null) {
            nextNode.prev = node;
        } else {
            // вставили в самый конец
            tail = node;
        }
        size++;
    }

    // Удаление узла из списка
    private void removeNode(Node node) {
        if (node == null) return;

        Node prevNode = node.prev;
        Node nextNode = node.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            tail = prevNode;
        }

        node.prev = null;
        node.next = null;
        size--;
    }

    // Переместить узел в начало
    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }

    // Удалить последний узел LRU и вернуть его
    private Node removeLast() {
        if (tail == null) return null;
        Node removed = tail;
        removeNode(removed);
        return removed;
    }


    // Получение значения по ключу
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            System.out.println("Ключ " + key + " не найден в кэше");
            return -1;
        }
        moveToFront(node);
        System.out.println("Получен ключ " + key + " со значением " + node.value);
        return node.value;
    }

    // Добавление/обновление значения по ключу
    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            node.value = value;
            moveToFront(node);
            System.out.println("Обновлено значение для ключа " + key + " на " + value);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToFront(newNode);
            System.out.println("Добавлен ключ " + key + " со значением " + value + " в кэш");

            if (size > capacity) {
                Node lru = removeLast();
                if (lru != null) {
                    cache.remove(lru.key);
                    System.out.println("Удалён LRU элемент с ключом " + lru.key);
                }
            }
        }
    }

    // 1. Добавление в конец
    public void putToEndDemo(int key, int value) {
        if (cache.containsKey(key)) {
            System.out.println("Ключ " + key + " уже есть, пропуск putToEndDemo");
            return;
        }
        Node node = new Node(key, value);
        cache.put(key, node);
        addToEnd(node);
        System.out.println("Добавлен в конец ключ " + key + " со значением " + value);

        if (size > capacity) {
            Node lru = removeLast();
            if (lru != null) {
                cache.remove(lru.key);
                System.out.println("Удалён LRU элемент с ключом " + lru.key);
            }
        }
    }

    // 1. Добавление «после ключа» (середина)
    public void putAfterKeyDemo(int existingKey, int newKey, int value) {
        Node existing = cache.get(existingKey);
        Node node = new Node(newKey, value);
        cache.put(newKey, node);
        addAfter(existing, node);
        System.out.println("Добавлен ключ " + newKey + " после ключа " + existingKey);

        if (size > capacity) {
            Node lru = removeLast();
            if (lru != null) {
                cache.remove(lru.key);
                System.out.println("Удалён LRU элемент с ключом " + lru.key);
            }
        }
    }

    // 2. Удаление элемента по ключу
    public void remove(int key) {
        Node node = cache.get(key);
        if (node == null) {
            System.out.println("Элемент с ключом " + key + " не найден для удаления");
            return;
        }
        removeNode(node);
        cache.remove(key);
        System.out.println("Удалён элемент с ключом " + key);
    }

    // 3. Подсчёт числа элементов
    public int size() {
        return size;
    }

    // 4. Печать в прямом порядке (от head к tail)
    public void printForward() {
        System.out.print("Кэш (от свежего к старому): ");
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.key + ":" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }

    // 4. Печать в обратном порядке (от tail к head)
    public void printBackward() {
        System.out.print("Кэш (от старого к свежему): ");
        Node current = tail;
        while (current != null) {
            System.out.print("[" + current.key + ":" + current.value + "] ");
            current = current.prev;
        }
        System.out.println();
    }

    // Печать текущей ёмкости и размера
    public void printInfo() {
        System.out.println("Размер кэша: " + size + ", ёмкость: " + capacity);
    }

    // Демонстрация работы LRU Cache
    public static void main(String[] args) {
        System.out.println("Демонстрация LRU Cache (на основе списка и хеш-таблицы)\n");

        Main2B cache = new Main2B(3);

        System.out.println("1.Добавление элементов (put):");
        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        cache.printForward();
        cache.printBackward();
        cache.printInfo();

        System.out.println("\n2.Доступ к элементу get(2) (становится самым «свежим»):");
        cache.get(2);
        cache.printForward();
        cache.printInfo();

        System.out.println("\n3.Добавление нового элемента put(4, 400) — вытеснение LRU:");
        cache.put(4, 400);
        cache.printForward();
        cache.printBackward();
        cache.printInfo();

        System.out.println("\n4.Добавление в конец и «после ключа»:");
        cache.putToEndDemo(5, 500);
        cache.putAfterKeyDemo(4, 6, 600);
        cache.printForward();
        cache.printBackward();
        cache.printInfo();

        System.out.println("\n5.Удаление по ключу:");
        cache.remove(2);
        cache.printForward();
        cache.printInfo();

        System.out.println("\n6.Повторный get(10) — отсутствующий ключ:");
        cache.get(10);
        cache.printForward();
        cache.printInfo();
    }
}

/*
Демонстрация LRU Cache (на основе списка и хеш-таблицы)

1.Добавление элементов (put):
Добавлен ключ 1 со значением 100 в кэш
Добавлен ключ 2 со значением 200 в кэш
Добавлен ключ 3 со значением 300 в кэш
Кэш (от свежего к старому): [3:300] [2:200] [1:100]
Кэш (от старого к свежему): [1:100] [2:200] [3:300]
Размер кэша: 3, ёмкость: 3

2.Доступ к элементу get(2) (становится самым «свежим»):
Получен ключ 2 со значением 200
Кэш (от свежего к старому): [2:200] [3:300] [1:100]
Размер кэша: 3, ёмкость: 3

3.Добавление нового элемента put(4, 400) — вытеснение LRU:
Добавлен ключ 4 со значением 400 в кэш
Удалён LRU элемент с ключом 1
Кэш (от свежего к старому): [4:400] [2:200] [3:300]
Кэш (от старого к свежему): [3:300] [2:200] [4:400]
Размер кэша: 3, ёмкость: 3

4.Добавление в конец и «после ключа»:
Добавлен в конец ключ 5 со значением 500
Удалён LRU элемент с ключом 5
Добавлен ключ 6 после ключа 4
Удалён LRU элемент с ключом 3
Кэш (от свежего к старому): [4:400] [6:600] [2:200]
Кэш (от старого к свежему): [2:200] [6:600] [4:400]
Размер кэша: 3, ёмкость: 3

5.Удаление по ключу:
Удалён элемент с ключом 2
Кэш (от свежего к старому): [4:400] [6:600]
Размер кэша: 2, ёмкость: 3

6.Повторный get(10) — отсутствующий ключ:
Ключ 10 не найден в кэше
Кэш (от свежего к старому): [4:400] [6:600]
Размер кэша: 2, ёмкость: 3
*/

