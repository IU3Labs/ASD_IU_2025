package models;


public class CircularQueue {
    private int[] data; // массив для хранения элементов
    private int front; // индекс начала очереди
    private int rear; // индекс конца очереди
    private int size; // текущее количество элементов
    private int capacity; // максимальный размер очереди

    public CircularQueue(int capacity) { // создание очереди
        this.capacity = capacity;
        data = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void add(int value) { // добавление элемента
        rear = (rear + 1) % capacity; // сдвиг конца очереди
        data[rear] = value;
        size++;
    }

    public int remove() { // удаление элемента
        int value = data[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public int size() {
        return size;
    } // возвращение текущего количества элементов
}