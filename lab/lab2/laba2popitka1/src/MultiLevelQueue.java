import java.util.*;

public class MultiLevelQueue<T> {
    private final List<Queue<T>> queues; // Список очередей по приоритетам (0 — самый высокий)
    private final int levels;

    public MultiLevelQueue(int levels) {
        if (levels <= 0) throw new IllegalArgumentException("Levels must be > 0");
        this.levels = levels;
        this.queues = new ArrayList<>(levels);
        for (int i = 0; i < levels; i++) {
            queues.add(new LinkedList<>()); // каждая очередь — обычный LinkedList
        }
    }

    /** Добавление элемента с приоритетом */
    public void enqueue(T item, int priority) {
        if (priority < 0 || priority >= levels) throw new IllegalArgumentException("Invalid priority");
        queues.get(priority).add(item);
    }

    /** Удаление всегда из самой высокой непустой очереди */
    public T dequeue() {
        for (Queue<T> queue : queues) {
            if (!queue.isEmpty()) {
                return queue.poll();
            }
        }
        return null; // очередь пуста
    }

    public int size() {
        return queues.stream().mapToInt(Queue::size).sum();
    }

    public static void main(String[] args) {
        MultiLevelQueue<String> q = new MultiLevelQueue<>(3);
        q.enqueue("Low", 2);
        q.enqueue("High", 0);
        q.enqueue("Medium", 1);
        q.enqueue("Highest", 0);

        System.out.println(q.dequeue()); // Высший (приоритет 0)
        System.out.println(q.dequeue());
    }
}