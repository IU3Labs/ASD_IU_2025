import java.util.*;

/**
 * Многослойная очередь - очередь очередей с приоритетным режимом обработки.
 * Каждый приоритет имеет свою очередь элементов.
 * Элементы с более высоким приоритетом обрабатываются первыми.
 */
public class MultiLayerQueue<T> {
    private TreeMap<Integer, Queue<T>> layers;
    private int totalElements;

    public MultiLayerQueue() {
        // TreeMap автоматически сортирует по приоритетам
        this.layers = new TreeMap<>(Collections.reverseOrder());
        this.totalElements = 0;
    }

    /**
     * Добавление элемента с указанным приоритетом
     */
    public void enqueue(T element, int priority) {
        // Если для этого приоритета очереди еще нет, создаем новую
        layers.putIfAbsent(priority, new LinkedList<>());
        layers.get(priority).add(element);
        totalElements++;
    }

    /**
     * Извлечение элемента с наивысшим приоритетом
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }

        // Получаем очередь с наивысшим приоритетом
        int highestPriority = layers.firstKey();
        Queue<T> queue = layers.get(highestPriority);

        T element = queue.poll();
        totalElements--;

        // Если очередь приоритета стала пуста, удаляем ее
        if (queue.isEmpty()) {
            layers.remove(highestPriority);
        }

        return element;
    }

    /**
     * Просмотр элемента с наивысшим приоритетом без удаления
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        int highestPriority = layers.firstKey();
        return layers.get(highestPriority).peek();
    }

    public boolean isEmpty() {
        return totalElements == 0;
    }

    public int size() {
        return totalElements;
    }

    /**
     * Печать структуры очереди с отображением приоритетов
     */
    public void print() {
        System.out.println("\n=== Многослойная очередь (Priority Queue) ===");
        for (Integer priority : layers.keySet()) {
            System.out.print("Приоритет " + priority + ": ");
            System.out.println(layers.get(priority));
        }
    }

    public static void main(String[] args) {
        MultiLayerQueue<String> queue = new MultiLayerQueue<>();

        System.out.println("--- Добавление элементов с разными приоритетами ---");
        queue.enqueue("Задача A", 1);
        queue.enqueue("Задача B", 3);
        queue.enqueue("Задача C", 2);
        queue.enqueue("Задача D", 3);
        queue.enqueue("Задача E", 1);

        queue.print();

        System.out.println("\n--- Извлечение элементов по приоритетам ---");
        while (!queue.isEmpty()) {
            System.out.println("Обработка: " + queue.dequeue());
        }

        System.out.println("\nОчередь пуста: " + queue.isEmpty());
    }
}
