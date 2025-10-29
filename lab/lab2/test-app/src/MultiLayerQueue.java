/*Группа B, задание 1: Реализовать «Многослойная очередь» (очередь очередей), которая
поддерживает приоритетный режим обработки элементов.
Прокомментировать код.*/

import java.util.*;

public class MultiLayerQueue<T> {
    private final Map<Integer, Queue<T>> priorityQueues;
    private int currentPriority;
    private final int maxPriority;

    public MultiLayerQueue(int maxPriority) {
        this.priorityQueues = new HashMap<>();
        this.maxPriority = maxPriority;
        this.currentPriority = maxPriority;

        for (int i = 0; i <= maxPriority; i++) {
            priorityQueues.put(i, new LinkedList<>());
        }
    }

    /*
     Логика: Добавление элемента с указанием приоритета
     Высший приоритет = 0, низший = maxPriority
     */
    public void add(T element, int priority) {
        if (priority < 0 || priority > maxPriority) {
            throw new IllegalArgumentException("Priority must be between 0 and " + maxPriority);
        }
        priorityQueues.get(priority).add(element);
    }

    /*
     Логика: Извлечение элемента по приоритетному режиму
     Сначала обрабатываем элементы с высшим приоритетом (0)
     Когда очередь с текущим приоритетом пуста, переходим к следующему
     */
    public T poll() {
        while (currentPriority <= maxPriority) {
            Queue<T> currentQueue = priorityQueues.get(currentPriority);
            if (!currentQueue.isEmpty()) {
                return currentQueue.poll();
            }
            currentPriority++;
        }

        currentPriority = 0;
        return null;
    }

    /*
     Логика: Просмотр следующего элемента без извлечения
     */
    public T peek() {
        int tempPriority = currentPriority;
        while (tempPriority <= maxPriority) {
            Queue<T> currentQueue = priorityQueues.get(tempPriority);
            if (!currentQueue.isEmpty()) {
                return currentQueue.peek();
            }
            tempPriority++;
        }
        return null;
    }

    public boolean isEmpty() {
        for (int i = 0; i <= maxPriority; i++) {
            if (!priorityQueues.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        int totalSize = 0;
        for (int i = 0; i <= maxPriority; i++) {
            totalSize += priorityQueues.get(i).size();
        }
        return totalSize;
    }

    public static void main(String[] args) {
        MultiLayerQueue<String> queue = new MultiLayerQueue<>(2);

        queue.add("Низкий приоритет", 2);
        queue.add("Высший приоритет", 0);
        queue.add("Средний приоритет", 1);
        queue.add("Еще высокий", 0);

        System.out.println("Извлечение по приоритетам:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}