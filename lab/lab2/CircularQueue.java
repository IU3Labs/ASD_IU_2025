//2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
//логику.
public class CircularQueue {
    private int[] data;
    private int front;    //Указатель на начало
    private int rear;     //указатель на конец (следующая свободная позиция)
    private int size;     //Текущее количество элементов
    private int capacity; //Максимальный размер

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Добавление в конец очереди
    public boolean enqueue(int value) {
        if (isFull()) {
            System.out.println("Очередь переполнена");
            return false;
        }

        data[rear] = value;
        rear = (rear + 1) % capacity; // Циклическое перемещение
        size++;
        return true;
    }

    // Удаление из начала очереди
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста!");
            return Integer.MIN_VALUE;
        }

        int value = data[front];
        front = (front + 1) % capacity; // Циклическое перемещение
        size--;
        return value;
    }

    // получение первого элемента
    public int peek() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return Integer.MIN_VALUE;
        }
        return data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    // Печать в прямом порядке (от начала к концу)
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

    // Печать в обратном порядке (от конца к началу)
    public void printBackward() {
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

    // Демонстрация работы
    public static void main(String[] args) {
        System.out.println("Демонстрация работы");

        CircularQueue queue = new CircularQueue(5);

        // Добавляем элементы
        System.out.println("1. Добавляем элементы: 10, 20, 30, 40");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        queue.printForward();
        System.out.println("Размер: " + queue.size());
        System.out.println("Первый элемент: " + queue.peek());

        // Удаляем элементы
        System.out.println("2. Удаляем два элемента:");
        System.out.println("Удален: " + queue.dequeue());
        System.out.println("Удален: " + queue.dequeue());

        queue.printForward();
        System.out.println("Размер: " + queue.size());

        // Добавляем еще (демонстрация цикличности)
        System.out.println("3. Добавляем еще: 50, 60, 70");
        queue.enqueue(50);
        queue.enqueue(60);
        queue.enqueue(70);

        queue.printForward();
        System.out.println("Размер: " + queue.size());

        // Печатаем в обратном порядке
        System.out.println("4. Печать в обратном порядке:");
        queue.printBackward();

        // Пытаемся добавить в полную очередь
        System.out.println("5. Пытаемся добавить в полную очередь:");
        queue.enqueue(80);

        // Удаляем все элементы
        System.out.println("6. Удаляем все элементы:");
        while (!queue.isEmpty()) {
            System.out.println("Удален: " + queue.dequeue());
        }

        queue.printForward();
        System.out.println("Очередь пуста: " + queue.isEmpty());
    }
}