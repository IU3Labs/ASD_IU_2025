// Группа B. Задание 1. Реализовать «Многослойная очередь» (очередь очередей), которая
// поддерживает приоритетный режим обработки элементов. Прокомментировать код.

package MultiLevelQueue;
import CircularQueue.*;

public class MultiLevelQueue extends BaseQueue {
    private CircularQueue[] queues;
    private int levels; // слои
    private int capacityPerLevel; // макс. вместимость каждого слоя

    public MultiLevelQueue(int levels, int capacityPerLevel) {
        super();
        this.levels = levels;
        this.capacityPerLevel = capacityPerLevel;
        this.queues = new CircularQueue[levels];

        // Создаем очереди для каждого уровня приоритета
        for (int i = 0; i < levels; i++) {
            queues[i] = new CircularQueue(capacityPerLevel);
        }
    }

    @Override
    public void enqueue(int value) {
        // Базовый метод - добавляет в очередь с приоритетом 0
        enqueue(value, 0);
    }

    // Добавляет элемент в очередь определенного приоритета
    public void enqueue(int value, int priority) {
        CircularQueue queue = queues[priority];
        if (queue.isFull()) {
            throw new IllegalStateException("Очередь приоритета " + priority + " переполнена");
        }

        queue.enqueue(value);
        size++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Все очереди пусты");
        }

        // Обходим очереди от высшего приоритета к низшему
        for (int i = 0; i < levels; i++) {
            CircularQueue queue = queues[i];
            if (!queue.isEmpty()) {
                int value = queue.dequeue();
                size--;
                return value;
            }
        }

        throw new IllegalStateException("Не удалось извлечь элемент");
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Все очереди пусты");
        }

        // Ищем первый непустой элемент по приоритету
        for (int i = 0; i < levels; i++) {
            CircularQueue queue = queues[i];
            if (!queue.isEmpty()) {
                return queue.peek();
            }
        }

        throw new IllegalStateException("Не удалось найти элемент");
    }

    // Проверяет, заполнена ли очередь определенного приоритета
    public boolean isLevelFull(int priority) {
        return queues[priority].isFull();
    }

    // Возвращает размер очереди определенного приоритета
    public int levelSize(int priority) {
        return queues[priority].size();
    }

    @Override
    public void printQueue() {
        System.out.println("=== Многослойная очередь (всего элементов: " + size + ") ===");
        for (int i = 0; i < levels; i++) {
            System.out.print("Приоритет " + i + " (" + levelSize(i) + "/" + capacityPerLevel + "): ");
            queues[i].printQueue();
        }
    }

    public static void main(String[] args) {
        MultiLevelQueue mlq = new MultiLevelQueue(3, 2);

        // Добавляем элементы с разными приоритетами
        mlq.enqueue(100, 0); // Высокий приоритет
        mlq.enqueue(200, 1); // Средний приоритет  
        mlq.enqueue(300, 2); // Низкий приоритет
        mlq.enqueue(101, 0); // Еще высокий
        mlq.enqueue(201, 1); // Еще средний

        mlq.printQueue();

        System.out.println("\nОбработка по приоритету:");
        while (!mlq.isEmpty()) {
            int value = mlq.dequeue();
            System.out.println("Обработано: " + value);
        }
    }
}