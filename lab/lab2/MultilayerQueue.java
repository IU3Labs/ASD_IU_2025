package lab2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//Многослойная очередь: хранит несколько очередей, каждая — свой приоритет


public class MultilayerQueue<T> {

    //Внутренний список очередей. Индекс списка = приоритет

    private final List<ArrayDeque<T>> priorityQueues;


    //Конструктор. param layersCount количество уровней приоритетов
    public MultilayerQueue(int layersCount) {
        if (layersCount <= 0) {
            throw new IllegalArgumentException("Количество слоев должно быть положительным");
        }
        this.priorityQueues = new ArrayList<>();
        for (int i = 0; i < layersCount; i++) {
            priorityQueues.add(new ArrayDeque<>());
        }
    }


    //Добавить элемент в очередь определенного приоритета


    public void enqueue(int priority, T element) {
        if (priority < 0 || priority >= priorityQueues.size()) {
            throw new IllegalArgumentException("Некорректный приоритет");
        }
        priorityQueues.get(priority).addLast(element);
    }


    //Извлечь элемент с учетом приоритета
    public T dequeue() {
        for (ArrayDeque<T> queue : priorityQueues) {
            if (!queue.isEmpty()) {
                return queue.pollFirst();
            }
        }
        return null; // Если все пусты
    }

    //Проверяет, пуста ли многослойная очередь

    public boolean isEmpty() {
        for (ArrayDeque<T> queue : priorityQueues) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }


    //Возвращает общее количество элементов во всех слоях

    public int size() {
        int size = 0;
        for (ArrayDeque<T> queue : priorityQueues) {
            size += queue.size();
        }
        return size;
    }

    //Тестирование многослойной очереди

    public static void main(String[] args) {
        MultilayerQueue<String> queue = new MultilayerQueue<>(3);

        queue.enqueue(2, "low-1");
        queue.enqueue(2, "low-2");
        queue.enqueue(1, "medium-1");
        queue.enqueue(0, "high-1");
        queue.enqueue(1, "medium-2");
        queue.enqueue(0, "high-2");

        System.out.println("Извлечение в порядке приоритета:");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
