/* Реализовать «LRU Cache» — кэш с вытеснением на основе связного списка и хэш-таблицы. Прокомментировать код.*/


import java.util.HashMap;


public class LRUCache<K,V> {

    //Узел, хранящий пару ключ-значение

    private class Node{
        K key;
        V value;
        Node prev; // ссылка на предыдущий узел
        Node next; // ссылка на следующий узел

        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity; // вместимость кэша
    private HashMap<K, Node> hashMap;
    private Node head; // самый недавний используемый элемент
    private Node tail; // самый давний используемый элемент

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    //получение значения по ключу

    public V get(K key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    //запись или перезапись элемента

    public void put(K key, V value){
        Node node = hashMap.get(key);
        if (node != null){
            node.value = value;
            moveToHead(node);
        }
        else{
            Node newNode = new Node(key, value);
            hashMap.put(key, newNode);
            addToStart(newNode);
            if (hashMap.size() > capacity){
                deleteLast();
            }
        }

    }
    // вывод
    public void print() {
        Node temp = head;
        System.out.print("LRUCache: [ ");
        while (temp != null) {
            System.out.print("{ " + temp.key + ":" + temp.value + " }");
            temp = temp.next;
            if (temp != null) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }


    public int size(){
        return hashMap.size();
    }

    private void addToStart(Node node){
        node.prev = null;
        node.next = head;
        if (head != null) {
            head.prev = node;
        }

        if (tail == null){
            tail = node;
        }
        head = node;

    }
    // удалить значение
    private void deleteNode(Node node){
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        else{
            head = node.next;
        }
        if (node.next != null){
            node.next.prev = node.prev;
        }
        else{
            tail = node.prev;
        }
    }
    // удалить крайний элемент
    private void deleteLast() {
        if (tail == null) return;
        hashMap.remove(tail.key);
        deleteNode(tail);
    }
    //перейти к началу
    private void moveToHead(Node node){
        deleteNode(node);
        addToStart(node);
    }


    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(4);
        //Добавим тестовые элементы
        lruCache.put(1, "Интеграл");
        lruCache.put(2, "Диффур");
        lruCache.put(3, "Линал");
        lruCache.put(4, "Ангем");
        lruCache.put(5, "Ряды");

        System.out.println("Размер кэша: " + lruCache.size());

        System.out.println("Получим значению по ключу: " + lruCache.get(2));
        lruCache.print();  // 2,5,4,3
        // Добавим еще 1 элемент
        lruCache.put(6, "джаваскрипт скачать бесплатно");
        lruCache.print();  // 6,2,5,4

    }




}
