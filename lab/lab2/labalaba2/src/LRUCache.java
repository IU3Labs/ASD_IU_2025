/*2 Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
списка и хеш-таблицы. Прокомментировать код.*/

import java.util.HashMap;
import java.util.Map;

//узел двусвязного списка
class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {

    private final int capacity; //максимальная ёмкость кэша
    private final Map<Integer, Node> cache; //хеш-таблица
    private final Node head; //"головной" узел (начало списка)
    private final Node tail; //"хвостовой" узел (конец списка)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    private void addToHead(Node node) { //добавления узла в начало списка
        node.prev = head; //узел ссылается на head
        node.next = head.next; //узел ссылается на бывший первый элемент
        head.next.prev = node; //бывший первый элемент теперь ссылается на новый узел
        head.next = node; //head ссылается на новый узел
    }

    private void removeNode(Node node) { //удаление узла из списка
        node.prev.next = node.next; //предыдущий узел ссылается на следующий
        node.next.prev = node.prev; //следующий узел ссылается на предыдущий
    }

    private void moveToHead(Node node) { //перемещение узла в начало списка
        removeNode(node);
        addToHead(node);
    }

    private Node popTail() { //удаление наименее использованного узла
        Node lastNode = tail.prev; //хвостовой узел — это последний реальный узел
        removeNode(lastNode); //удаляем его из списка
        return lastNode; //возвращаем для удаления из мапы
    }


    public int get(int key) { //получение значения по ключу
        Node node = cache.get(key); //ищем узел в мапе

        if (node == null) {
            return -1;
        }
        moveToHead(node);//ключ найден и мы перемещаем узел в начало(он будет недавно использованным)

        return node.value;
    }

    public void put(int key, int value) { //вставка нового эл-та
        Node node = cache.get(key); //проверяем, существует ли ключ

        if (node == null) { //если нет, то создаем новый узел
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            if (cache.size() > capacity) { //проверка на переполнение
                Node tailNode = popTail();
                cache.remove(tailNode.key);
            }
        } else { //если есть то обновляем значение
            node.value = value;
            moveToHead(node);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); //1 (1 становится "свежим")

        cache.put(3, 3); //вытесняет ключ 2 (он самый старый)
        System.out.println(cache.get(2)); //-1 (ключ 2 удалён)

        cache.put(4, 4); //вытесняет ключ 1
        System.out.println(cache.get(1)); //вывод: -1
        System.out.println(cache.get(3)); //вывод: 3
        System.out.println(cache.get(4)); //вывод: 4
    }
}