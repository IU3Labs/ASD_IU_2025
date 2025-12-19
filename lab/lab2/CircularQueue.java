import java.util.*;
//2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
//логику.
    public class CircularQueue {
    private int[] queue;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = rear = -1;
        size = 0;
    }

    public void enqueue(int value) {
        if (size == capacity) {
            System.out.println("Queue is full");
            return;
        }

        if (rear == capacity - 1) {
            rear = -1;
        }
        queue[++rear] = value;
        if (front == -1) {
            front = 0;
        }
        size++;
    }


    public int dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }

        int value = queue[front];
        front++;
        if (front == capacity) {
            front = 0;
        }
        size--;
        return value;
    }


    public int front() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        return queue[front];
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue.dequeue());
        queue.enqueue(6);
        System.out.println(queue.dequeue());
        queue.enqueue(7);
        System.out.println(queue.front());
    }
}
