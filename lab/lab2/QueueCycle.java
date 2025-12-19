import java.util.Scanner;

public class QueueCycle {

    private int[] buffer;
    private int readPtr;     // начало (front)
    private int writePtr;    // конец (rear)
    private int count;       // размер (size)
    private int maxCapacity; // вместимость

    public QueueCycle(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        buffer = new int[maxCapacity];
        readPtr = 0;
        writePtr = -1;
        count = 0;
    }

    public void enqueue(int data) {
        if (isFull()) {
            // если очередь полная то вытесняем старый элемент
            // забываем самый старый элемент (сдвигаем указатель чтения)
            readPtr = (readPtr + 1) % maxCapacity;
            count--;
            System.out.println("Очередь полна — вытесняем старейший элемент");
        }

        // сдвиг указателя вперед по кругу
        writePtr = (writePtr + 1) % maxCapacity;
        buffer[writePtr] = data;
        count++;
        System.out.println("добавлен элемент: " + data);
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("удалять нечего");
            return -1;
        }
        int item = buffer[readPtr];
        readPtr = (readPtr + 1) % maxCapacity; // сдвиг указателя вперёд по кругу
        count--;
        System.out.println("удалён элемент: " + item);
        return item;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("очередь пуста");
            return -1;
        }
        return buffer[readPtr];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == maxCapacity;
    }

    public void showBuffer() {
        if (isEmpty()) {
            System.out.println("очередь пуста");
            return;
        }
        System.out.print("очередь: ");
        for (int i = 0; i < count; i++) {
            int idx = (readPtr + i) % maxCapacity;
            System.out.print(buffer[idx] + " ");
        }
        System.out.println("\n");
    }

    public void manualFill() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ввод количества элементов (не больше " + maxCapacity + "): ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("ввод элемента " + (i + 1) + ": ");
            enqueue(scanner.nextInt());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("размер очереди: ");
        int size = scanner.nextInt();

        QueueCycle ringBuffer = new QueueCycle(size);
        ringBuffer.manualFill();
        ringBuffer.showBuffer();

        System.out.println("удаляем два элемента...");
        ringBuffer.dequeue();
        ringBuffer.dequeue();
        ringBuffer.showBuffer();

        // добавляем новые элементы и мы должны попасть в начало массива
        System.out.println("добавляем новые элементы:");
        ringBuffer.enqueue(99);
        ringBuffer.enqueue(100);
        ringBuffer.showBuffer();

        // проверяем вытеснение
        while (true) {
            System.out.print("число для добавления (для выхода ввести -1): ");
            int val = scanner.nextInt();
            if (val == -1) break;
            ringBuffer.enqueue(val);
            ringBuffer.showBuffer();
        }
    }
}