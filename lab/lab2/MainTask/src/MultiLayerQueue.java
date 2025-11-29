import java.util.*;

public class MultiLayerQueue<T> {
    private final TreeMap<Integer, Queue<T>> levels = new TreeMap<>();

    public void add(int priority, T element) {
        levels.computeIfAbsent(priority, k -> new LinkedList<>()).add(element);
    }

    public T poll() {
        if (levels.isEmpty()) return null;

        Map.Entry<Integer, Queue<T>> entry = levels.firstEntry();
        Integer priority = entry.getKey();
        Queue<T> queue = entry.getValue();

        T element = queue.poll();

        if (queue.isEmpty()) {
            levels.remove(priority);
        }

        return element;
    }

    public static void main(String[] args) {
        MultiLayerQueue<String> mq = new MultiLayerQueue<>();
        mq.add(2, "Нормальный");
        mq.add(1, "Важный");
        mq.add(10, "Неважный");
        mq.add(1, "Очень важный");

        System.out.println(mq.poll());
        System.out.println(mq.poll());
        System.out.println(mq.poll());
        System.out.println(mq.poll());
    }
}
