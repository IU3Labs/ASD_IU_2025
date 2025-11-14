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
        if (size < capacity) {
            // Если есть место, добавляем обычным способом
            rear = (rear + 1) % capacity;
            data[rear] = value;
            size++;
        } else {
            // Если очередь полная, сдвигаем все элементы
            shiftAndAdd(value);
        }
    }

    // Сдвигает очередь влево и добавляет новый элемент в конец
    private void shiftAndAdd(int value) {
        // Сдвигаем все элементы влево
        for (int i = 0; i < capacity - 1; i++) {
            data[i] = data[i + 1];
        }
        // Последний элемент заменяем новым значением
        data[capacity - 1] = value;

        // Обновляем индексы
        front = 0;
        rear = capacity - 1;
        // size остается тем же (capacity)
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

        System.out.println("=== Демонстрация циклической очереди с перезаписью ===");

        // Заполняем очередь
        for (int i = 1; i <= 4; i++) {
            queue.enqueue(i * 10);
        }
        queue.printQueue();

        // Добавляем 5-й элемент в полную очередь
        System.out.println("\n--- Добавляем 50 в полную очередь ---");
        queue.enqueue(50);
        queue.printQueue();

        // Добавляем еще один элемент
        System.out.println("\n--- Добавляем 60 в полную очередь ---");
        queue.enqueue(60);
        queue.printQueue();

        // Удаляем несколько элементов
        System.out.println("\n--- Удаляем два элемента ---");
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();

        // Добавляем новые элементы
        System.out.println("\n--- Добавляем 70 и 80 ---");
        queue.enqueue(70);
        queue.enqueue(80);
        queue.printQueue();
    }
}