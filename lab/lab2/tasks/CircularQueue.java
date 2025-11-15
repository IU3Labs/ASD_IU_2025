package tasks;

public class CircularQueue {
    private int front;
    private int rear;
    private final int[] data;
    private int size;

    public CircularQueue(int capacity) {
        size = 0;
        front = 0;
        rear = -1;
        data = new int[capacity];
    }

    public boolean enqueue(int value) {
        if (size == data.length) {
            System.out.println("Очередь переполнена! Нельзя добавить: " + value);
            return false;
        }

        rear = (rear + 1) % data.length;
        data[rear] = value;
        size++;
        return true;
    }

    public int dequeue() {
        if (size != 0) {
            int value = data[front];

            if (front == rear) {
                front = 0;
                rear = -1;
            } else {
                front = (front + 1) % data.length;
            }

            size--;
            return value;
        } else {
            System.out.println("Очередь пуста! Нечего удалять.");
            return Integer.MIN_VALUE;
        }
    }

    public int peek() {
        if (size != 0) {
            return data[front];
        } else {
            System.out.println("Очередь пуста!");
            return Integer.MIN_VALUE;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void print() {
        if (size == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < size - 1; i++) {
                int index = (front + i) % data.length;
                System.out.print(data[index] + ", ");
            }
            int lastIndex = (front + size - 1) % data.length;
            System.out.print(data[lastIndex] + "]");
            System.out.println();
        }
    }

    public void printReversed() {
        if (size == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < size; i++) {
                int index = (rear - i + data.length) % data.length;
                System.out.print(data[index]);
                if (i < size - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public void printInternalState() {
        System.out.println("Массив: " + java.util.Arrays.toString(data));
        System.out.println("Front: " + front + " -> " + (size > 0 ? data[front] : "empty"));
        System.out.println("Rear: " + rear + " -> " + (size > 0 ? data[rear] : "empty"));
        System.out.println("Size: " + size + "/" + data.length);
        System.out.println("Empty: " + isEmpty() + ", Full: " + isFull());
    }
}