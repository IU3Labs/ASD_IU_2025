package org.example.lab2.tasks.a;

import java.util.NoSuchElementException;

/**
 * Кольцевая очередь (циклический буфер).
 * Демонстрирует добавление, удаление и печать.
 */
public class CircularQueue<E> {
    private final Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public CircularQueue(int capacity) {
        buffer = new Object[capacity];
    }

    public void enqueue(E e) {
        if (isFull()) {
            throw new IllegalStateException("Queue full");
        }
        buffer[tail] = e;
        tail = (tail + 1) % buffer.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue empty");
        }
        E val = (E) buffer[head];
        buffer[head] = null;
        head = (head + 1) % buffer.length;
        size--;
        return val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == buffer.length;
    }

    public int size() {
        return size;
    }

    public String printForward() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(buffer[(head + i) % buffer.length]);
            if (i < size - 1) { sb.append(", "); }
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        CircularQueue<Integer> q = new CircularQueue<>(5);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println("Очередь: " + q.printForward());
        q.dequeue();
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        System.out.println("После операций: " + q.printForward());
        System.out.println("Размер: " + q.size());
    }
}