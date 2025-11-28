/**
 * Циклическая очередь - эффективная реализация очереди с использованием массива
 * Логика: используем массив фиксированного размера и два указателя (front и rear)
 * Когда указатели достигают конца массива, они "переходят" в начало (циклический буфер)
 */
class CircularQueue {
    private int[] queue;
    private int front;  // Указатель на начало очереди
    private int rear;   // Указатель на конец очереди
    private int size;   // Текущее количество элементов
    private int capacity; // Максимальная вместимость

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Добавление элемента в конец очереди
     * Если очередь заполнена, выбрасываем исключение
     */
    public void enqueue(int item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        rear = (rear + 1) % capacity; // Циклическое перемещение
        queue[rear] = item;
        size++;
    }

    /**
     * Удаление элемента из начала очереди
     * Возвращает удаленный элемент
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        int item = queue[front];
        front = (front + 1) % capacity; // Циклическое перемещение
        size--;
        return item;
    }

    /**
     * Просмотр элемента в начале очереди без удаления
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
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

    /**
     * Печать очереди в прямом порядке
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("Queue: ");
        int count = 0;
        int index = front;

        while (count < size) {
            System.out.print(queue[index] + " ");
            index = (index + 1) % capacity;
            count++;
        }
        System.out.println();
    }

    /**
     * Печать внутреннего состояния для отладки
     */
    public void printInternalState() {
        System.out.println("Internal state - Front: " + front + ", Rear: " + rear + ", Size: " + size);
        System.out.print("Array: [");
        for (int i = 0; i < capacity; i++) {
            System.out.print(queue[i]);
            if (i < capacity - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

/**
 * Демонстрационный класс для тестирования CircularQueue
 */
class CircularQueueDemo {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ CIRCULAR QUEUE ===\n");

        // Создаем очередь вместимостью 5 элементов
        CircularQueue queue = new CircularQueue(5);
        System.out.println("1. Создана очередь вместимостью 5 элементов");
        queue.printInternalState();

        // Тест 1: Добавление элементов
        System.out.println("\n2. Добавляем элементы 10, 20, 30, 40:");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.printQueue();
        queue.printInternalState();

        // Тест 2: Просмотр первого элемента
        System.out.println("\n3. Просмотр первого элемента (peek): " + queue.peek());

        // Тест 3: Удаление элементов
        System.out.println("\n4. Удаляем два элемента из начала:");
        System.out.println("Удален: " + queue.dequeue());
        System.out.println("Удален: " + queue.dequeue());
        queue.printQueue();
        queue.printInternalState();

        // Тест 4: Добавление с циклическим поведением
        System.out.println("\n5. Добавляем элементы 50, 60, 70 (демонстрация цикличности):");
        queue.enqueue(50);
        queue.enqueue(60);
        queue.enqueue(70);
        queue.printQueue();
        queue.printInternalState();
        System.out.println("Заметка: rear перешел в начало массива!");

        // Тест 5: Попытка добавления в полную очередь
        System.out.println("\n6. Попытка добавить элемент в полную очередь:");
        try {
            queue.enqueue(80);
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест 6: Удаление всех элементов
        System.out.println("\n7. Удаляем все элементы по одному:");
        while (!queue.isEmpty()) {
            System.out.println("Удален: " + queue.dequeue());
            queue.printInternalState();
        }

        // Тест 7: Попытка удаления из пустой очереди
        System.out.println("\n8. Попытка удаления из пустой очереди:");
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест 8: Расширенная демонстрация циклического поведения
        System.out.println("\n9. Расширенная демонстрация циклического поведения:");
        CircularQueue demoQueue = new CircularQueue(4);

        // Серия операций enqueue/dequeue
        demoQueue.enqueue(1);
        demoQueue.enqueue(2);
        demoQueue.enqueue(3);
        System.out.println("После добавления 1, 2, 3:");
        demoQueue.printQueue();
        demoQueue.printInternalState();

        demoQueue.dequeue();
        demoQueue.dequeue();
        System.out.println("После удаления двух элементов:");
        demoQueue.printQueue();
        demoQueue.printInternalState();

        demoQueue.enqueue(4);
        demoQueue.enqueue(5);
        demoQueue.enqueue(6);
        System.out.println("После добавления 4, 5, 6:");
        demoQueue.printQueue();
        demoQueue.printInternalState();
        System.out.println("Видно как указатели движутся по кругу!");

        // Тест 9: Проверка методов isEmpty и isFull
        System.out.println("\n10. Проверка состояний очереди:");
        System.out.println("Очередь пуста: " + demoQueue.isEmpty());
        System.out.println("Очередь полна: " + demoQueue.isFull());
        System.out.println("Размер очереди: " + demoQueue.size());

        // Тест 10: Производительность
        System.out.println("\n11. Тестирование производительности:");
        testPerformance();

        System.out.println("\n=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===");
    }

    /**
     * Тестирование производительности циклической очереди
     */
    private static void testPerformance() {
        final int OPERATIONS = 1000000;
        CircularQueue perfQueue = new CircularQueue(OPERATIONS + 1);

        long startTime, endTime;

        // Тест добавления
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            perfQueue.enqueue(i);
        }
        endTime = System.nanoTime();
        System.out.println("Добавление " + OPERATIONS + " элементов: " +
                (endTime - startTime) / 1000000 + " ms");

        // Тест удаления
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            perfQueue.dequeue();
        }
        endTime = System.nanoTime();
        System.out.println("Удаление " + OPERATIONS + " элементов: " +
                (endTime - startTime) / 1000000 + " ms");

        // Тест смешанных операций
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS / 2; i++) {
            perfQueue.enqueue(i);
            if (i % 2 == 0) {
                perfQueue.dequeue();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Смешанные операции (" + OPERATIONS + "): " +
                (endTime - startTime) / 1000000 + " ms");
    }
}