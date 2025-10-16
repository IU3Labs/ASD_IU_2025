import java.util.*;

class Student {
    private Long id;
    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

public class StructursStudent {

    public static void main(String[] args) {
        MyArrayList<Student> arrayList = new MyArrayList<>();
        MyLinkedList<Student> linkedList = new MyLinkedList<>();
        MyHashSet<Student> hashSet = new MyHashSet<>();
        MyHashMap<Long, Student> hashMap = new MyHashMap<>();

        initializeStructures(arrayList, linkedList, hashSet, hashMap);

        measureArrayListPerformance(arrayList);
        measureLinkedListPerformance(linkedList);
        measureHashSetPerformance(hashSet);
        measureHashMapPerformance(hashMap);
    }

    private static void initializeStructures(MyArrayList<Student> arrayList,
                                             MyLinkedList<Student> linkedList,
                                             MyHashSet<Student> hashSet,
                                             MyHashMap<Long, Student> hashMap) {
        for (long i = 0; i < 5000000; i++) {
            Student student = new Student(i, "Student " + i);
            arrayList.addLast(student);
            linkedList.addLast(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }
    }

    private static void measureArrayListPerformance(MyArrayList<Student> list) {
        System.out.println("=== MyArrayList Performance Test (время в наносекундах) ===");

        // Добавление в конец
        Student newEndStudent = new Student(5000000L, "New End Student");
        long startTime = System.nanoTime();
        list.addLast(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

        // Добавление в начало
        Student newStartStudent = new Student(5000001L, "New Start Student");
        startTime = System.nanoTime();
        list.addFirst(newStartStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

        // Удаление последнего элемента
        startTime = System.nanoTime();
        Student removedLast = list.remove(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        // Удаление первого элемента
        startTime = System.nanoTime();
        Student removedFirst = list.remove(0);
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        // Взятие центрального элемента
        int middleIndex = list.size() / 2;
        startTime = System.nanoTime();
        Student middleStudent = list.get(middleIndex);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        // Взятие последнего элемента
        startTime = System.nanoTime();
        Student lastStudent = list.get(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + list.size() + "\n");
    }

    private static void measureLinkedListPerformance(MyLinkedList<Student> list) {
        System.out.println("=== MyLinkedList Performance Test (время в наносекундах) ===");

        // Добавление в конец
        Student newEndStudent = new Student(5000000L, "New End Student");
        long startTime = System.nanoTime();
        list.addLast(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

        // Добавление в начало
        Student newStartStudent = new Student(5000001L, "New Start Student");
        startTime = System.nanoTime();
        list.addFirst(newStartStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

        // Удаление последнего элемента
        startTime = System.nanoTime();
        Student removedLast = list.remove(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        // Удаление первого элемента
        startTime = System.nanoTime();
        Student removedFirst = list.remove(0);
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        // Взятие центрального элемента
        int middleIndex = list.size() / 2;
        startTime = System.nanoTime();
        Student middleStudent = list.get(middleIndex);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        // Взятие последнего элемента
        startTime = System.nanoTime();
        Student lastStudent = list.get(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + list.size() + "\n");
    }

    private static void measureHashSetPerformance(MyHashSet<Student> set) {
        System.out.println("=== MyHashSet Performance Test (время в наносекундах) ===");

        // Добавление несуществующего элемента
        Student newStudent = new Student(5000000L, "New Student");
        long startTime = System.nanoTime();
        boolean added = set.add(newStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление несуществующего элемента: " + (endTime - startTime) + " нс");

        // Добавление элемента
        Student anotherStudent = new Student(5000001L, "Another Student");
        startTime = System.nanoTime();
        boolean added2 = set.add(anotherStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление другого элемента: " + (endTime - startTime) + " нс");

        // Удаление последнего добавленного элемента
        startTime = System.nanoTime();
        boolean removedLast = set.remove(anotherStudent);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего добавленного элемента: " + (endTime - startTime) + " нс");

        // Удаление первого добавленного элемента
        Student firstStudent = new Student(0L, "Student 0");
        startTime = System.nanoTime();
        boolean removedFirst = set.remove(firstStudent);
        endTime = System.nanoTime();
        System.out.println("Удаление первого добавленного элемента: " + (endTime - startTime) + " нс");

        // Проверка наличия центрального элемента
        Student middleStudent = new Student(2500000L, "Student 5000000");
        startTime = System.nanoTime();
        boolean containsMiddle = set.contains(middleStudent);
        endTime = System.nanoTime();
        System.out.println("Проверка центрального элемента: " + (endTime - startTime) + " нс");

        // Проверка наличия последнего добавленного элемента
        startTime = System.nanoTime();
        boolean containsLast = set.contains(newStudent);
        endTime = System.nanoTime();
        System.out.println("Проверка последнего добавленного элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + set.size() + "\n");
    }

    private static void measureHashMapPerformance(MyHashMap<Long, Student> map) {
        System.out.println("=== MyHashMap Performance Test (время в наносекундах) ===");

        // Добавление несуществующего элемента в конец (по новому ключу)
        Student newStudent = new Student(5000000L, "New Student");
        long startTime = System.nanoTime();
        map.put(5000000L, newStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление несуществующего элемента: " + (endTime - startTime) + " нс");

        // Добавление элемента
        Student anotherStudent = new Student(5000001L, "Another Student");
        startTime = System.nanoTime();
        map.put(5000001L, anotherStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление другого элемента: " + (endTime - startTime) + " нс");

        // Удаление последнего добавленного элемента
        startTime = System.nanoTime();
        Student removedLast = map.remove(5000001L);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего добавленного элемента: " + (endTime - startTime) + " нс");

        // Удаление первого добавленного элемента
        startTime = System.nanoTime();
        Student removedFirst = map.remove(0L);
        endTime = System.nanoTime();
        System.out.println("Удаление первого добавленного элемента: " + (endTime - startTime) + " нс");

        // Взятие центрального элемента
        startTime = System.nanoTime();
        Student middleStudent = map.get(2500000L);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        // Взятие последнего добавленного элемента
        startTime = System.nanoTime();
        Student lastStudent = map.get(5000000L);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего добавленного элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + map.size() + "\n");
    }


}

class Node<T> {
    Node<T> prev;
    Node<T> next;
    T data;

    Node(T data) {
        this.data = data;
    }
}

class MyArrayList<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 5000000;

    public MyArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    public void addLast(T element) {
        if (size == data.length) {
            ensureCapacity();
        }
        data[size++] = element;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == data.length) {
            ensureCapacity();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

    public void addMiddle(T element) {
        int middle = size / 2;
        add(middle, element);
    }

    public void addFirst(T element) {
        add(0, element);
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[--size] = null;
        return removedElement;
    }

    private void ensureCapacity() {
        int newCapacity = data.length * 2;
        T[] newData = (T[]) new Object[newCapacity];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public void printArrayList() {
        for (T element: data) {
            System.out.println(element);
        }
    }

    public void printArrayListReversed() {
        int sizeArray = size();
        for (int i = sizeArray - 1; i >= 0; i--) {
            System.out.println(data[i]);
        }
    }
}

class MyLinkedList<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void addLast(T element) {
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void add( int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node newNode = new Node(element);
        if (index == 0) {
            if (head == null){
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
    }

    public void addMiddle(T element) {
        int middle = size / 2;
        add(middle, element);
    }

    public void addFirst(T element) {
        add(0, element);
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node removedNode;

        if (index == 0) {
            removedNode = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (index == size -1) {
            removedNode = tail;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        }
        else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            removedNode = current;
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return removedNode.data;
    }

    public void printLinkedList() {
        Node current = head;
        for (int i = 0; i < size; i++) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void printLinkedListReversed() {
        Node current = tail;
        for (int i = 0; i < size; i++) {
            System.out.println(current);
            current = current.prev;
        }
    }
}

class MyHashSet<T> {
    private Entry<T>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 1048576;
    private static final float LOAD_FACTOR = 0.75f;

    public MyHashSet() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashSet(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    // Внутренний класс для элементов цепочки
    private static class Entry<T> {
        T data;
        Entry<T> next;

        Entry(T data) {
            this.data = data;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        int hash = element.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<T> current = table[index];
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean add(T element) {
        int hash = element.hashCode();
        int index = Math.abs(hash) % table.length;

        // Проверяем, нет ли уже такого элемента
        Entry<T> current = table[index];
        while (current != null) {
            if (current.data.equals(element)) {
                return false; // Элемент уже существует
            }
            current = current.next;
        }

        // Добавляем новый элемент в начало цепочки
        Entry<T> newEntry = new Entry<>(element);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;

        // Проверяем необходимость расширения
        if ((float) size / table.length > LOAD_FACTOR) {
            resizeTable();
        }

        return true;
    }

    public boolean remove(T element) {
        int hash = element.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<T> current = table[index];
        Entry<T> previous = null;

        while (current != null) {
            if (current.data.equals(element)) {
                if (previous == null) {
                    // Удаляем первый элемент цепочки
                    table[index] = current.next;
                } else {
                    // Удаляем из середины/конца цепочки
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Entry<T>[] newTable = new Entry[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Entry<T> current = table[i];
            while (current != null) {
                Entry<T> next = current.next;

                int newHash = current.data.hashCode();
                int newIndex = Math.abs(newHash) % newCapacity;

                // Вставляем в начало цепочки новой таблицы
                current.next = newTable[newIndex];
                newTable[newIndex] = current;

                current = next;
            }
        }

        table = newTable;
    }
}

class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    static class Entry<K, V> {
        K K;
        V V;
        Entry<K, V> next;

        Entry(K K, V V) {
            this.K = K;
            this.V = V;
        }
    }

    public V get(K K) {
        int hash = K.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.K.equals(K)) {
                return current.V;
            }
            current = current.next;
        }
        return null;
    }

    public void put(K K, V V) {
        int hash = K.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<K, V> newEntry = new Entry<>(K, V);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            newEntry.next = table[index];
            table[index] = newEntry;
        }
        size++;

        if ((float)size / table.length > LOAD_FACTOR) {
            resizeTable();
        }
    }

    public V remove(K K) {
        int hash = K.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<K, V> current = table[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.K.equals(K)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.V;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                Entry<K, V> next = current.next;
                int newIndex = Math.abs(current.K.hashCode()) % newCapacity;

                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    current.next = null;
                } else {
                    current.next = newTable[newIndex];
                    newTable[newIndex] = current;
                }
                current = next;
            }
        }

        table = newTable;
    }
}
//Результаты для 5.000.000 строк данных:
//
//        === MyArrayList Performance Test (время в наносекундах) ===
//Добавление в конец: 106174000 нс
//Добавление в начало: 138141200 нс
//Удаление последнего элемента: 30700 нс
//Удаление первого элемента: 135670900 нс
//Взятие центрального элемента: 8700 нс
//Взятие последнего элемента: 1300 нс
//Итоговый размер: 5000000
//
//        === MyLinkedList Performance Test (время в наносекундах) ===
//Добавление в конец: 2600 нс
//Добавление в начало: 9000 нс
//Удаление последнего элемента: 6800 нс
//Удаление первого элемента: 1300 нс
//Взятие центрального элемента: 16182400 нс
//Взятие последнего элемента: 32560700 нс
//Итоговый размер: 5000000
//
//        === MyHashSet Performance Test (время в наносекундах) ===
//Добавление несуществующего элемента: 4700 нс
//Добавление другого элемента: 1400 нс
//Удаление последнего добавленного элемента: 71800 нс
//Удаление первого добавленного элемента: 5200 нс
//Проверка центрального элемента: 8800 нс
//Проверка последнего добавленного элемента: 1700 нс
//Итоговый размер: 5000000
//
//        === MyHashMap Performance Test (время в наносекундах) ===
//Добавление несуществующего элемента: 3500 нс
//Добавление другого элемента: 800 нс
//Удаление последнего добавленного элемента: 73300 нс
//Удаление первого добавленного элемента: 23500 нс
//Взятие центрального элемента: 8000 нс
//Взятие последнего добавленного элемента: 2900 нс
//Итоговый размер: 5000000