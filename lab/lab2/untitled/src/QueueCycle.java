/*
2 Реализовать «Циклическую очередь» (Circular Queue).
Циклическая очередь — это структура данных, в которой начало и конец связаны "по кругу".
Когда указатель конца достигает конца массива, следующий элемент записывается в начало.
*/

import java.util.Scanner;

public class QueueCycle {

    private int[] queue;
    private int front;       // индекс первого элемента
    private int rear;        // индекс последнего элемента
    private int size;        // текущее количество элементов
    private int capacity;    // максимальное количество элементов

    public QueueCycle(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Добавление элемента
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Очередь заполнена — добавить нельзя");
            return;
        }
        // Если конец достигнут, возвращаемся в начало массива
        rear = (rear + 1) % capacity;
        queue[rear] = value;
        size++;
        System.out.println("Добавлен элемент: " + value);
    }

    // Удаление элемента
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста — удалять нечего");
            return -1;
        }
        int val = queue[front];
        front = (front + 1) % capacity; // сдвиг указателя вперёд по кругу
        size--;
        System.out.println("Удалён элемент: " + val);
        return val;
    }

    // Просмотр первого элемента без удаления
    public int peek() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
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

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        System.out.println("Содержимое очереди:");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println("\n");
    }

    public void fillQueue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов (не больше " + capacity + "): ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Введите элемент " + (i + 1) + ": ");
            enqueue(sc.nextInt());
        }
    }

    // Демонстрация работы
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите размер очереди: ");
        int size = sc.nextInt();

        QueueCycle queue = new QueueCycle(size);
        queue.fillQueue();

        queue.printQueue();
        System.out.println("Первый элемент: " + queue.peek());

        queue.dequeue();
        queue.printQueue();

        System.out.print("Введите число для добавления в очередь: ");
        int newValue = sc.nextInt();
        queue.enqueue(newValue);

        queue.printQueue();
        System.out.println("Текущий первый элемент: " + queue.peek());
    }
}
