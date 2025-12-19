//А2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать логику.

public class CircularQueue {
    private int[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Добавление элемента в конец очереди
    public boolean enqueue(int value) {
        if (isFull()) return false;

        rear = (rear + 1) % capacity;
        data[rear] = value;
        size++;
        return true;
    }

    // Добавление элемента в начало очереди
    public boolean enqueueFront(int value) {
        if (isFull()) return false;

        front = (front - 1 + capacity) % capacity;
        data[front] = value;
        size++;
        return true;
    }

    // Удаление элемента из начала очереди
    public int dequeue() {
        if (isEmpty()) return -1;

        int value = data[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    // Удаление элемента из конца очереди
    public int dequeueRear() {
        if (isEmpty()) return -1;

        int value = data[rear];
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return value;
    }

    // Подсчет числа элементов
    public int count() {
        return size;
    }

    // Печать очереди в прямом порядке (от начала к концу)
    public void printForward() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }

        System.out.print("Очередь (от начала к концу): ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(data[index] + " ");
        }
        System.out.println();
    }

    //Печать очереди в обратном порядке (от конца к началу)
    public void printReverse() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }

        System.out.print("Очередь (от конца к началу): ");
        for (int i = size - 1; i >= 0; i--) {
            int index = (front + i) % capacity;
            System.out.print(data[index] + " ");
        }
        System.out.println();
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }

    // Проверка на полноту
    public boolean isFull() {
        return size == capacity;
    }

    // Демонстрация работы очереди
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы CircularQueue ===\n");

        CircularQueue queue = new CircularQueue(5);

        System.out.println("1. Добавление элементов в конец очереди:");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.printForward();
        queue.printReverse();
        System.out.println("Количество элементов: " + queue.count());

        System.out.println("\n2. Удаление элемента из начала очереди:");
        System.out.println("Удален: " + queue.dequeue());
        queue.printForward();
        System.out.println("Количество элементов: " + queue.count());

        System.out.println("\n3. Добавление элементов в начало:");
        queue.enqueueFront(5);
        queue.enqueueFront(2);
        queue.printForward();
        queue.printReverse();
        System.out.println("Количество элементов: " + queue.count());

        System.out.println("\n4. Добавление в конец (циклическое заполнение):");
        queue.enqueue(40);
        queue.enqueue(50);
        queue.printForward();
        System.out.println("Количество элементов: " + queue.count());
        System.out.println("Очередь полна? " + queue.isFull());

        System.out.println("\n5. Удаление элемента из конца:");
        System.out.println("Удален: " + queue.dequeueRear());
        queue.printForward();
        System.out.println("Количество элементов: " + queue.count());

        System.out.println("\n6. Удаление всех элементов:");
        while (!queue.isEmpty()) {
            System.out.println("Удален: " + queue.dequeue());
        }
        queue.printForward();
        System.out.println("Количество элементов: " + queue.count());
        System.out.println("Очередь пуста? " + queue.isEmpty());
    }
}