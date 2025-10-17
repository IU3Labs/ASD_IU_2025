/*
2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
логику.
 */
import java.util.Scanner;
public class CircularQueue {
    private int[] queue;    // массив для хранения элементов очереди
    private int front;      // индекс начала очереди
    private int rear;       // индекс конца очереди
    private int size;       // текущий размер
    private int capacity;   // максимальный размер очереди
    // Конструктор
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    // Добавление элемента в очередь
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Очередь переполнена, добавить нельзя");
            return;
        }
        // Сдвиг по кругу
        rear = (rear + 1) % capacity;
        queue[rear] = value;
        size++;
        System.out.println("Добавлен элемент: " + value);
    }
    // Удаление элемента из очереди
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста, удалить нечего");
            return -1;
        }
        int value = queue[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("Удален элемент: " + value);
        return value;
    }
    // Получение элемента без удаления
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
        System.out.println("Содержимое очереди: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.println(queue[index] + " ");
        }
        System.out.println();
    }
    public void fillQueue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов (до " + capacity + "): ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Введите элемент " + (i + 1) + ": ");
            enqueue(sc.nextInt());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер очереди: ");
        int size = sc.nextInt();
        CircularQueue queue = new CircularQueue(size);
        queue.fillQueue();
        queue.printQueue();
        System.out.println("Первый элемент: " + queue.peek());
        queue.dequeue();
        queue.printQueue();
        System.out.println("Введите число, которое необходимо добавить в очередь:");
        int value = sc.nextInt();
        queue.enqueue(value);
        queue.printQueue();
        System.out.println("Текущий первый элемент: " + queue.peek());
    }
}

