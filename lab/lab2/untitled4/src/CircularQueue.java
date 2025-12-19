//Группа А Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
//логику.
public class CircularQueue {
    private int[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        buffer = new int[capacity];
        head = 0;
        tail = -1;
        size = 0;
    }

    // Добавление элемента в очередь
    public void enqueue(int value) {
        if (isFull()) {
            // Удаляем самый старый элемент
            head = (head + 1) % capacity;
            size--;
        }

        tail = (tail + 1) % capacity;
        buffer[tail] = value;
        size++;
    }

    // Удаление элемента из очереди
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        int value = buffer[head];
        head = (head + 1) % capacity;
        size--;
        return value;
    }

    // Получение первого элемента
    public int getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        return buffer[head];
    }

    // Получение последнего элемента
    public int getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        return buffer[tail];
    }

    // Подсчет элементов
    public int count() {
        return size;
    }

    // Печать содержимого буфера
    public void print() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        System.out.print("Содержимое очереди: ");
        for (int i = 0; i < size; i++) {
            int index = (head + i) % capacity;
            System.out.print(buffer[index] + " ");
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(3);

        System.out.println("Демонстрация CircularQueue");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Добавлены: 1, 2, 3");
        queue.print();
        System.out.println("Количество элементов: " + queue.count());
        System.out.println("Первый элемент: " + queue.getFirst());
        System.out.println("Последний элемент: " + queue.getLast());

        System.out.println("\nДобавление 4 (очередь заполнена, удаляется 1):");
        queue.enqueue(4);
        queue.print();
        System.out.println("Первый элемент: " + queue.getFirst());

        System.out.println("\nУдаление элемента: " + queue.dequeue());
        queue.print();
        System.out.println("Количество элементов: " + queue.count());
    }
}