/* Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
логику. */

//Элементы хранятся в массиве фиксированного размера, а указатели начала и конца "зацикливаются" при достижении границ массива.

import java.util.Scanner;


public class CircularQueue {

    private int[] queue; // массив где хранятся элементы
    private int first; // индекс 1 элемента
    private int last; // индекс последнего элемента
    private int capacity; // максимальная вместимость
    private int size; // кол-во элементов в очереди

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        queue = new int[capacity];
        this.first = 0;
        this.last = -1;
    }

    // добавление элемента
    public void add(int newElement) {
        if (isCapacityReached()) {
            first = (first + 1) % capacity; // сдвиг старта
        } else {
            size++;
        }

        last = (last + 1) % capacity;
        queue[last] = newElement;

        if (size == 1) {
            first = last;
        }
    }

    //удаление элемента
    public void delete() {
        if (isQueueEmpty()) {
            throw new IllegalStateException("Нечего удалить");
        }
        first = (first + 1) % capacity;
        size--;

        if (isQueueEmpty()) {
            first = 0;
            last = -1;
        }
    }

    // размер
    public int getSize() {
        return size;
    }
    // вывод очереди
    public void print() {
        if (isQueueEmpty()) {
            System.out.println("Пустая очередь");
        }

        System.out.println("Циклическая очередь: ");
        for (int i = 0; i < size; i++) {
            int index = (first + i) % capacity;
            System.out.println(queue[index] + " ");
        }
    }

    // ручной ввод
    public void inputQueue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество элементов, число не большее максимальной вместимости =  " + capacity);
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            System.out.println("Введите элемент: ");
            add(scanner.nextInt());
        }
    }

    // ручной ввод вместимости
    public static int inputCapacity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите максимальную вместимость очереди: ");
        int capacity = scanner.nextInt();
        return capacity;
    }

    private boolean isCapacityReached() {
        return size == capacity;
    }

    private boolean isQueueEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        int capacity = inputCapacity();
        CircularQueue circularQueue = new CircularQueue(capacity);

        circularQueue.inputQueue();
        circularQueue.print();

        System.out.println("Добавим элемент");
        circularQueue.add(5);
        circularQueue.print();

        System.out.println("Удалим элемент");
        circularQueue.delete();

        circularQueue.print();

        System.out.println("Размер массива: " + circularQueue.getSize());
    }
}