package groupa;

public class CircularQueue {
    private int[] data;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int k) {
        data = new int[k];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        rear = (rear + 1) % data.length;
        data[rear] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % data.length;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[front];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return data[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(3);

        System.out.println("Добавляем 1, 2, 3:");
        System.out.println("enQueue(1): " + queue.enQueue(1));
        System.out.println("enQueue(2): " + queue.enQueue(2));
        System.out.println("enQueue(3): " + queue.enQueue(3));

        System.out.println("\nПытаемся добавить 4 в заполненную очередь:");
        System.out.println("enQueue(4): " + queue.enQueue(4) + " (должен быть false)");

        System.out.println("\nСостояние очереди:");
        System.out.println("isFull(): " + queue.isFull());
        System.out.println("Rear(): " + queue.Rear());
        System.out.println("Front(): " + queue.Front());

        System.out.println("\nУдаляем один элемент и добавляем 4:");
        System.out.println("deQueue(): " + queue.deQueue());
        System.out.println("enQueue(4): " + queue.enQueue(4));

        System.out.println("\nФинальное состояние:");
        System.out.println("Rear(): " + queue.Rear());
        System.out.println("Front(): " + queue.Front());
        System.out.println("isFull(): " + queue.isFull());
    }
}