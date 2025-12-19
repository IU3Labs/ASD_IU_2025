import java.util.Scanner;

public class QueueCycle {

    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueCycle(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int value) {
        if (isFull()) {
            // если очередь полная то вытесняем старый элемент
            front = (front + 1) % capacity; // забываем самый старый элемент
            size--; // уменьшаем размер, чтобы далее увеличить его
            System.out.println("Очередь полна — вытесняем старейший элемент");
        }

        rear = (rear + 1) % capacity;
        queue[rear] = value;
        size++;
        System.out.println("добавлен элемент: " + value);
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("удалять нечего");
            return -1;
        }
        int val = queue[front];
        front = (front + 1) % capacity; // сдвиг указателя вперёд по кругу
        size--;
        System.out.println("удалён элемент: " + val);
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("очередь пуста");
            return -1;
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("очередь пуста");
            return;
        }
        System.out.println("очередь:");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println("\n");
    }

    public void fillQueue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ввод количества элементов (не больше " + capacity + "): ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("ввод элемента " + (i + 1) + ": ");
            enqueue(sc.nextInt());
        }
    }

    // демо
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("размер очереди: ");
        int size = sc.nextInt();

        QueueCycle queue = new QueueCycle(size);

        // заполняем очередь
        queue.fillQueue();
        queue.printQueue();

        // удаляем элементы чтобы освободить место
        System.out.println("удаляем два элемента...");
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();

        // добавляем новые элементы и мы должны попасть в начало массива
        System.out.println("добавляем новые элементы:");
        queue.enqueue(99);
        queue.enqueue(100);
        queue.printQueue();

        System.out.println("первый элемент: " + queue.peek());

        // проверяем вытеснение
        while (true) {
            System.out.print("число для добавления (для выхода ввести -1): ");
            int newValue = sc.nextInt();

            // Проверка на выход
            if (newValue == -1) {
                System.out.println("выход из программы");
                break;
            }

            queue.enqueue(newValue);
            queue.printQueue();

            System.out.println("первый элемент: " + queue.peek());
        }

    }
}
