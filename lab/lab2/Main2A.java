import java.util.*;

/*
 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
 логику.
 Логика Circular Queue:
Циклическая очередь - это структура данных, которая использует массив фиксированного размера
и два указателя (front и rear), которые зацикливаются при достижении конца массива.
(front указывает на первый элемент в очереди, а rear показывает на следующую свободную позицию для добавления)
Когда указатели доходят до конца массива, они переходят на начало.
 */

public class Main2A {
    private int[] buffer;   // Хранение элементов
    private int front;      // Указатель на начало очереди
    private int rear;       // Указатель на позицию для добавления
    private int size;       // Кол-во элементов
    private int capacity;   // Макс размер очереди

    public Main2A(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Добавление элемента в конец очереди
    public void enqueue(int value) {
        buffer[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
    }

    // Добавление элемента в начало очереди
    public void enqueueToFront(int value) {
        front = (front - 1 + capacity) % capacity;
        buffer[front] = value;
        size++;
    }

    // Добавление элемента в середину очереди
    public void enqueueToMiddle(int value) {
        int middle = size / 2;
        for (int i = size - 1; i >= middle; i--) {
            int currentIndex = (front + i) % capacity;
            int nextIndex = (front + i + 1) % capacity;
            buffer[nextIndex] = buffer[currentIndex];
        }
        int middleIndex = (front + middle) % capacity;
        buffer[middleIndex] = value;
        rear = (rear + 1) % capacity;
        size++;
    }

    // Удаление элемента из начала очереди
    public int dequeue() {
        int value = buffer[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    // Удаление элемента из конца очереди
    public int dequeueFromRear() {
        rear = (rear - 1 + capacity) % capacity;
        int value = buffer[rear];
        size--;
        return value;
    }

    // Удаление элемента из середины очереди
    public int dequeueFromMiddle() {
        int middle = size / 2;
        int middleIndex = (front + middle) % capacity;
        int value = buffer[middleIndex];
        for (int i = middle; i < size - 1; i++) {
            int currentIndex = (front + i + 1) % capacity;
            int prevIndex = (front + i) % capacity;
            buffer[prevIndex] = buffer[currentIndex];
        }
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return value;
    }

    // Кол-во элементов в очереди
    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    // Печать очереди в прямом порядке (от начала к концу)
    public void printForward() {
        System.out.print("Очередь (от начала к концу): ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(buffer[index] + " ");
        }
        System.out.println();
    }

    // Печать очереди в обратном порядке (от конца к началу)
    public void printBackward() {
        System.out.print("Очередь (от конца к началу): ");
        for (int i = size - 1; i >= 0; i--) {
            int index = (front + i) % capacity;
            System.out.print(buffer[index] + " ");
        }
        System.out.println();
    }

    public void printState() {
        System.out.println("Front: " + front + ", Rear: " + rear + ", Size: " + size);
        System.out.print("Массив: [");
        for (int i = 0; i < capacity; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(buffer[i]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация циклической очереди");
        System.out.println("Максимальный размер очереди: 5\n");

        Main2A queue = new Main2A(5);

        System.out.println("1.Добавление элементов:");
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);
        System.out.println("Добавление 100, 200, 300 в конец");
        queue.printForward();
        queue.printState();

        queue.enqueueToFront(50);
        System.out.println("\nДобавление 50 в начало");
        queue.printForward();
        queue.printState();

        queue.enqueueToMiddle(150);
        System.out.println("\nДобавление 150 в середину");
        queue.printForward();
        queue.printState();

        System.out.println("\n2.Печать в разных порядках:");
        queue.printForward();
        queue.printBackward();

        System.out.println("\n3.Кол-во элементов:");
        System.out.println("Количество элементов: " + queue.size());
        System.out.println("Максимальная длина очереди: " + queue.getCapacity());

        System.out.println("\n4.Удаление элементов:");

        System.out.println("Удаление из начала: " + queue.dequeue());
        queue.printForward();
        queue.printState();

        System.out.println("\nУдаление из конца: " + queue.dequeueFromRear());
        queue.printForward();
        queue.printState();

        System.out.println("\nУдаление из середины: " + queue.dequeueFromMiddle());
        queue.printForward();
        queue.printState();

        System.out.println("\n5.Проверка цикличности:");

        queue.printForward();
        queue.printState();

        queue.dequeue();
        queue.dequeue();
        System.out.println("\nПосле удаления двух элементов:");
        queue.printForward();
        queue.printState();

        queue.enqueue(900);
        queue.enqueue(1000);
        System.out.println("\nПосле добавления 900 и 1000:");
        queue.printForward();
        queue.printState();
    }
}

/*
Демонстрация циклической очереди
Максимальный размер очереди: 5

1.Добавление элементов:
Добавление 100, 200, 300 в конец
Очередь (от начала к концу): 100 200 300
Front: 0, Rear: 3, Size: 3
Массив: [100, 200, 300, 0, 0]

Добавление 50 в начало
Очередь (от начала к концу): 50 100 200 300
Front: 4, Rear: 3, Size: 4
Массив: [100, 200, 300, 0, 50]

Добавление 150 в середину
Очередь (от начала к концу): 50 100 150 200 300
Front: 4, Rear: 4, Size: 5
Массив: [100, 150, 200, 300, 50]

2.Печать в разных порядках:
Очередь (от начала к концу): 50 100 150 200 300
Очередь (от конца к началу): 300 200 150 100 50

3.Кол-во элементов:
Количество элементов: 5
Максимальная длина очереди: 5

4.Удаление элементов:
Удаление из начала: 50
Очередь (от начала к концу): 100 150 200 300
Front: 0, Rear: 4, Size: 4
Массив: [100, 150, 200, 300, 50]

Удаление из конца: 300
Очередь (от начала к концу): 100 150 200
Front: 0, Rear: 3, Size: 3
Массив: [100, 150, 200, 300, 50]

Удаление из середины: 150
Очередь (от начала к концу): 100 200
Front: 0, Rear: 2, Size: 2
Массив: [100, 200, 200, 300, 50]

5.Проверка цикличности:
Очередь (от начала к концу): 100 200
Front: 0, Rear: 2, Size: 2
Массив: [100, 200, 200, 300, 50]

После удаления двух элементов:
Очередь (от начала к концу):
Front: 2, Rear: 2, Size: 0
Массив: [100, 200, 200, 300, 50]

После добавления 900 и 1000:
Очередь (от начала к концу): 900 1000
Front: 2, Rear: 4, Size: 2
Массив: [100, 200, 900, 1000, 50]
*/
