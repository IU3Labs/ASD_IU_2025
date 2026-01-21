import java.util.*;

public class MultiLayerQueue {

    private final List<ArrayDeque<Integer>> layers; // Список очередей
    private final int levels; // количество уровней

    public MultiLayerQueue(int levels) {
        this.levels = levels;
        layers = new ArrayList<>(levels);
        for (int i = 0; i < levels; i++) {
            layers.add(new ArrayDeque<>());
        }
    }

    public void enqueue(int value, int priority) { // Добавление элемента в очередь с указанным приоритетом
        if (priority >= levels) { // если priority больше количества уровней, ставим в самый низкий
            priority = levels - 1;
        }
        layers.get(priority).addLast(value); // добавляем в конец очереди уровня
    }

    public int dequeue() { // Извлечение элемента в приоритетном режиме, от высокого приоритета к низкому
        for (int p = 0; p < levels; p++) {
            ArrayDeque<Integer> q = layers.get(p);
            if (!q.isEmpty()) {
                return q.removeFirst();
            }
        }
        System.out.println("All layers are empty - nothing to extract");
        return Integer.MIN_VALUE;
    }

    private int lastRoundRobin = 0;
    public int roundRobinDequeue() { // Извлечение по кругу
        for (int i = 0; i < levels; i++) {
            int idx = (lastRoundRobin + i) % levels; // текущий уровень по кругу
            ArrayDeque<Integer> q = layers.get(idx);
            if (!q.isEmpty()) {
                lastRoundRobin = (idx + 1) % levels; // запоминаем следующий уровень
                return q.removeFirst();
            }
        }
        System.out.println("RoundRobin: all layers are empty");
        return Integer.MIN_VALUE;
    }

    public boolean isEmpty() { // Проверка: пусты ли все очереди
        for (ArrayDeque<Integer> q : layers) {
            if (!q.isEmpty()) return false;
        }
        return true;
    }

    public int fetchFullSize() { // Переименованный вспомогательный метод (количество элементов во всех слоях)
        int s = 0;
        for (ArrayDeque<Integer> q : layers) s += q.size();
        return s;
    }

    public void showStatus() { // Переименованный вспомогательный метод
        for (int p = 0; p < levels; p++) {
            System.out.print("Level " + p + " (priority " + (levels - p - 1) + "): ");
            ArrayDeque<Integer> q = layers.get(p);
            if (q.isEmpty()) {
                System.out.println("[empty]");
            } else {
                System.out.println(q);
            }
        }
        System.out.println("Total size: " + fetchFullSize());
    }

    public static void main(String[] args) {
        System.out.println("Demo: ");
        System.out.println();

        MultiLayerQueue mlq = new MultiLayerQueue(3); // 3 уровня приоритетов

        System.out.println("1) Adding elements to different levels:");
        mlq.enqueue(11, 0); // высокий приоритет
        mlq.enqueue(12, 0);
        mlq.enqueue(13, 0);
        mlq.enqueue(21, 1); // средний приоритет
        mlq.enqueue(22, 1);
        mlq.enqueue(23, 1);
        mlq.enqueue(31, 2); // низкий приоритет
        mlq.enqueue(32, 2);
        mlq.enqueue(33, 2);
        mlq.showStatus();
        System.out.println();

        System.out.println("2) Extracting in priority mode:");
        System.out.println("dequeue() -> " + mlq.dequeue());
        System.out.println("dequeue() -> " + mlq.dequeue());
        System.out.println("dequeue() -> " + mlq.dequeue());
        mlq.showStatus();
        System.out.println();

        System.out.println("3) Adding more elements");
        mlq.enqueue(34, 2);
        mlq.enqueue(35, 2);
        mlq.showStatus();
        System.out.println();

        System.out.println("4) Demonstrating round-robin mode:");
        System.out.println("roundRobinDequeue() -> " + mlq.roundRobinDequeue());
        System.out.println("roundRobinDequeue() -> " + mlq.roundRobinDequeue());
        System.out.println("roundRobinDequeue() -> " + mlq.roundRobinDequeue());
        mlq.showStatus();
        System.out.println();

        System.out.println("5) Extracting all priority-based until the end:");
        while (!mlq.isEmpty()) {
            System.out.println("dequeue() -> " + mlq.dequeue());
        }
        mlq.showStatus();
    }
}