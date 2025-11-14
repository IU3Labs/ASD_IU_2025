/*
Задание:
    Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
логику.
Комментарий по логике:
    Создаю зацикленный массив(индексы front и rear передвигаются по кругу)
 */



import java.util.Scanner;

public class CyclicQueue {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер циклической очереди: ");
        int capacity = scan.nextInt();
        
        Queue queue = new Queue(capacity);

        for (int i = 0; i < capacity; i++) {
            System.out.print("Введите " + (i + 1) + "-е число: ");
            int value = scan.nextInt();
            queue.enqueue(value);
        }
        //для демонастрации работы очереди
        System.out.println("Удаление элементов");


        while (!queue.isEmpty()) {
            int removed = queue.dequeue();
            System.out.println("Удалили элемент: " + removed);
        }

        scan.close();
    }
}


class Queue {

    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    // Добавление элемента
    public void enqueue(int value) {

        if (isFull()) {
            System.out.println("Очередь переполнена");
            return;
        }

        queue[rear] = value;
        rear = (rear + 1) % capacity;
        size += 1;
    }

    // Удаление элемента
    public int dequeue() {

        if (isEmpty()) {
            System.out.println("Очередь пустая");
            return -1;
        }
        int value = queue[front];
        front = (front + 1) % capacity;
        size -= 1;

        return value;
    }

    // Просмотр первого элемента
    public int peek() {
        if (isEmpty()) {
            System.out.println("Очередь пустая");
            return -1;
        }
        return queue[front];
    }

    // Проверка пустоты
    public boolean isEmpty() {
        return size == 0;
    }

    // Проверка заполненности
    public boolean isFull() {
        return size == capacity;
    }
}
