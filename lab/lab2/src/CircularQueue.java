public class CircularQueue {
    private int[] array;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean enqueue(int value) {
        if (isFull()) return false;
        array[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    public Integer dequeue() {
        if (isEmpty()) return null;
        int result = array[head];
        head = (head + 1) % capacity;
        size--;
        return result;
    }

    public Integer peek() {
        if (isEmpty()) return null;
        return array[head];
    }
}

