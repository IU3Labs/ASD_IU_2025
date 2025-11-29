package lab2.models;

public class CircularQueue {
    private int[] data;
    private int front; // указатель на начало очереди
    private int rear;  // указатель на конец очереди
    private int size;  // текущее количество элементов
    private int capacity; // максимальная вместимость

    public CircularQueue(int k) {
        capacity = k;
        data = new int[capacity];
        front = 0;
        rear = -1; // -1 означает, что очередь пуста
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        // циклическое движение - когда доходим до конца, возвращаемся в начало
        rear = (rear + 1) % capacity;
        data[rear] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        // двигаем указатель начала, тоже с циклическим переходом
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[front];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return data[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}