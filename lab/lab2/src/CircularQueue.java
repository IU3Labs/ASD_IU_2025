import java.util.Optional;
import java.util.OptionalInt;

public class CircularQueue {
    private int[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.rear = -1;
        this.data = new int[capacity];
        this.front = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean enqueue(int elem) { // Добавляем элемент в очередь
        if (isFull()) return false; // Если очередь полная
        if (front == -1) front = 0; // Если неполная, то ставим front в начало
        rear = (rear + 1) % capacity; // Дальше двигаем rear
        data[rear] = elem; // Кладем новый элемент в очередь
        size++; // Увеличиваем счетчик элементов в очереди
        return true;
    }

    public OptionalInt dequeue() { // Выход из очереди
        if (isEmpty()) { // Если очередь пуста возвращаем пустой OptionalInt
            System.out.println("Очередь пуста");
            return OptionalInt.empty();
        }
        int elem = data[front]; // Берем элемент из начала очереди
        if (front == rear) { // Проверяем не единственный ли он элемент, если да, то очищаем очередь
            front = -1;
            rear = -1;
        } else // Если он не единственный, то дальше двигаем front
            front = (front + 1) % capacity;
        size--; // Уменьшаем счетчик элементов в очереди
        return OptionalInt.of(elem); // Возвращаем удаленный элемент
    }

    public void display() { // Вывод
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        System.out.println("Элементы: ");
        int i = front; // Вывод начинается с первого элемента
        do {
            System.out.println(data[i] + " ");
            i = (i + 1) % capacity; // Двигаем i дальше по очереди, пока не будет в rear
        }
        while (i != (rear + 1) % capacity);
        System.out.println();
    }

    // Проверка

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.display();

        queue.dequeue();
        queue.dequeue();
        queue.display();

        queue.enqueue(6);
        queue.enqueue(7);
        queue.display();
    }
}

