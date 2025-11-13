/* Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
логику. */

import java.util.*;

public class CircularQueue {

    private int[] buffer;
    private int headIndex;
    private int tailIndex;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity + 1; // один элемент резервируем для отличия пустой и полной очереди
        buffer = new int[this.capacity];
        headIndex = 0;
        tailIndex = 0;
    }

    // Проверка, пустая ли очередь
    public boolean isEmpty() {
        return headIndex == tailIndex;
    }

    // Проверка, полная ли очередь
    public boolean isFull() {
        return (tailIndex + 1) % capacity == headIndex;
    }

    // Добавление элемента в очередь
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Очередь переполнена! Невозможно добавить: " + value);
            return;
        }
        buffer[tailIndex] = value;
        tailIndex = (tailIndex + 1) % capacity;
        System.out.println("Добавлен элемент: " + value);
    }

    // Удаление элемента из очереди
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста! Нечего удалять.");
            return -1;
        }
        int removed = buffer[headIndex];
        headIndex = (headIndex + 1) % capacity;
        System.out.println("Удалён элемент: " + removed);
        return removed;
    }

    // Просмотр первого элемента без удаления
    public int peekFront() {
        if (isEmpty()) {
            System.out.println("Очередь пуста!");
            return -1;
        }
        return buffer[headIndex];
    }

    // Печать элементов очереди в порядке очереди
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста!");
            return;
        }
        System.out.print("Очередь: ");
        int i = headIndex;
        while (i != tailIndex) {
            System.out.print(buffer[i] + " ");
            i = (i + 1) % capacity;
        }
        System.out.println();
    }
}