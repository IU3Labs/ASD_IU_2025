import java.util.*;

public class MultiLevelQueue<T> {
    private List<Queue<T>> queues;
    private int levels;
    private int totalElements;

    public MultiLevelQueue(int levels) {
        if (levels <= 0) {
            throw new IllegalArgumentException("Number of levels must be positive");
        }

        this.levels = levels;
        this.queues = new ArrayList<>(levels);
        this.totalElements = 0;

        for (int i = 0; i < levels; i++) {
            queues.add(new LinkedList<>());
        }
    }

    public void enqueue(T element, int priority) {
        if (priority < 0 || priority >= levels) {
            throw new IllegalArgumentException("Priority must be between 0 and " + (levels - 1));
        }

        queues.get(priority).offer(element);
        totalElements++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Multi-level queue is empty");
        }

        for (int i = 0; i < levels; i++) {
            Queue<T> queue = queues.get(i);
            if (!queue.isEmpty()) {
                totalElements--;
                return queue.poll();
            }
        }

        throw new IllegalStateException("Queue is empty");
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Multi-level queue is empty");
        }

        for (int i = 0; i < levels; i++) {
            Queue<T> queue = queues.get(i);
            if (!queue.isEmpty()) {
                return queue.peek();
            }
        }

        throw new IllegalStateException("Queue is empty");
    }

    public boolean isEmpty() {
        return totalElements == 0;
    }

    public int size() {
        return totalElements;
    }

    public int size(int priority) {
        if (priority < 0 || priority >= levels) {
            throw new IllegalArgumentException("Priority must be between 0 and " + (levels - 1));
        }
        return queues.get(priority).size();
    }

    public void printQueues() {
        System.out.println("Multi-Level Queue Status:");
        for (int i = 0; i < levels; i++) {
            System.out.println("Priority " + i + " (" + queues.get(i).size() + " elements): " + queues.get(i));
        }
        System.out.println("Total elements: " + totalElements);
    }
}