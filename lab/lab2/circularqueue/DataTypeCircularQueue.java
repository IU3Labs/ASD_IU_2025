/* Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
логику. */

package circularqueue;

public class DataTypeCircularQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public DataTypeCircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue (int element) {
        if (isFull()) {
            System.out.println("Очередь заполнена!");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = element;
        size++;
        System.out.println("Добавлен элемент: " + element);

    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста!");
            return -1;
        }
        int removed = queue[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("Удалён элемпент: " + removed);
        return removed;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Очередь пуста!");
            return -1;
        }
        return queue[front];
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста!");
            return;
        }
        System.out.println("Содержимое очереди: ");
        for (int i = 0; i < size; i++) {
            System.out.println(queue[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}
