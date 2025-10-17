// Группа А. Задание 2. Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать логику.

package CircularQueue;

public class CircularQueue extends BaseQueue {
    private int[] data; // массив фиксированного размера для хранения элементов
    private int front; // индекс начала очереди
    private int rear; // индекс конца очереди
    private int capacity; // максимальная вместимость очереди

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Добавляет элемент в конец очереди
    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Очередь переполнена");
        }

        rear = (rear + 1) % capacity; // циклическое перемещение
        data[rear] = value;
        size++;
    }

    // Удаляет и возвращает элемент из начала очереди
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }

        int value = data[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    // Возвращает элемент из начала очереди без удаления
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        return data[front];
    }

    // пустая очередь или нет
    public boolean isEmpty() {
        return size == 0;
    }

    // полная очередь или нет
    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    // Выводит содержимое очереди
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }

        System.out.print("Очередь: [");
        int count = 0;
        int index = front;

        while (count < size) {
            System.out.print(data[index]);
            if (count < size - 1) {
                System.out.print(" ← ");
            }
            index = (index + 1) % capacity;
            count++;
        }
        System.out.println("] (размер: " + size + "/" + capacity + ")");
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(4);

        System.out.println("=== Демонстрация циклической очереди ===");

        // Заполняем очередь
        for (int i = 1; i <= 4; i++) {
            queue.enqueue(i * 10);
        }
        queue.printQueue();

        // Освобождаем место в начале
        System.out.println("\n--- Освобождаем начало ---");
        queue.dequeue(); // 10
        queue.dequeue(); // 20
        queue.printQueue();

        // Добавляем новые элементы
        System.out.println("\n--- Добавляем в 'освободившееся' начало ---");
        queue.enqueue(50);
        queue.enqueue(60);
        queue.printQueue();

        System.out.println("rear теперь меньше front: " + (queue.rear < queue.front));
    }
}