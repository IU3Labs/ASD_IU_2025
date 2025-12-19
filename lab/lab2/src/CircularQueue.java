//2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
//логику.
import java.util.*;
public class CircularQueue {
    private int[] arr;       // массив для хранения элементов
    private int capacity;    // максимальный размер очереди
    private int start;       // индекс начала очереди
    private int end;         // индекс следующей свободной позиции
    private int size;        // текущее количество элементов

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity]; // создаём массив нужного размера
        this.start = 0;
        this.end = 0;
        this.size = 0;
    }

    public void enqueue(int value) { // Добавление элемента в конец очереди
        arr[end] = value;
        end = (end + 1) % capacity;
        if (size < capacity) size++;
        else start = (start + 1) % capacity;
    }

    public void enqueueToFront(int value) {  // Добавление элемента в начало
        start = (start - 1 + capacity) % capacity;
        arr[start] = value;
        if (size < capacity) size++;
        else end = (end - 1 + capacity) % capacity;
    }


    public void enqueueToMiddle(int value) { // Добавление элемента в середину очереди
        int middle = size / 2;
        end = (end + 1) % capacity;
        if (size < capacity) size++;

        for (int i = size - 2; i >= middle; i--) { // сдвигаем элементы после середины на одну позицию вправо
            int from = (start + i) % capacity;
            int to = (start + i + 1) % capacity;
            arr[to] = arr[from];
        }

        int midIndex = (start + middle) % capacity;
        arr[midIndex] = value;
    }

    public int dequeue() { // Удаление с начала
        int val = arr[start];
        start = (start + 1) % capacity;
        size--;
        return val;
    }

    public int dequeueFromRear() { // Удаление с конца
        end = (end - 1 + capacity) % capacity;
        int val = arr[end];
        size--;
        return val;
    }

    public int dequeueFromMiddle() { // Удаление из середины
        int middle = size / 2;
        int midIndex = (start + middle) % capacity;
        int val = arr[midIndex];

        for (int i = middle; i < size - 1; i++) { // сдвигаем элементы после середины влево
            int from = (start + i + 1) % capacity;
            int to = (start + i) % capacity;
            arr[to] = arr[from];
        }
        end = (end - 1 + capacity) % capacity;
        size--;
        return val;
    }

    public int size() { // текущее количество элементов
        return size;
    }

    public void printForward() { // Печать от начала к концу
        System.out.print("Очередь (от начала к концу): ");
        for (int i = 0; i < size; i++) {
            int idx = (start + i) % capacity; // вычисляем индекс циклично
            System.out.print(arr[idx] + " ");
        }
        System.out.println();
    }

    public void printBackward() { // Печать от конца к началу
        System.out.print("Очередь (от конца к началу): ");
        for (int i = size - 1; i >= 0; i--) {
            int idx = (start + i) % capacity; // индекс циклично
            System.out.print(arr[idx] + " ");
        }
        System.out.println();
    }

    public void printState() {
        System.out.println("start: " + start + "  end: " + end + "  size: " + size);
        System.out.print("Массив: [");
        for (int i = 0; i < capacity; i++) {
            System.out.print(arr[i]);
            if (i < capacity - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация: ");
        CircularQueue q = new CircularQueue(5);

        System.out.println("\n1) Добавление элементов:");
        q.enqueue(7);
        q.enqueue(9);
        q.enqueue(2);
        q.enqueue(4);
        q.printForward();
        q.printBackward();
        q.printState();

        q.enqueueToFront(3);
        System.out.println("\nДобавление '3' в начало");
        q.printForward();
        q.printBackward();
        q.printState();

        q.enqueueToMiddle(6);
        System.out.println("\nДобавление '6' в середину");
        q.printForward();
        q.printBackward();
        q.printState();

        System.out.println("\n2) Удаление с начала: " + q.dequeue());
        q.printForward();
        q.printBackward();
        q.printState();

        System.out.println("\nУдаление с конца: " + q.dequeueFromRear());
        q.printForward();
        q.printBackward();
        q.printState();

        System.out.println("\nУдаление из середины: " + q.dequeueFromMiddle());
        q.printForward();
        q.printBackward();
        q.printState();

        System.out.println("\n3) Проверка цикличности:");
        q.enqueue(10);
        q.enqueue(100);
        q.printForward();
        q.printBackward();
        q.printState();
    }
}