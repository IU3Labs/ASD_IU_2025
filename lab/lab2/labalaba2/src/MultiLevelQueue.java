/*Реализовать «Многослойная очередь» (очередь очередей), которая
поддерживает приоритетный режим обработки элементов.
Прокомментировать код.*/

import java.util.*;

public class MultiLevelQueue<T> {
    private final Map<Integer, Queue<T>> priorityMap;
    private final int totalLevels;

    public MultiLevelQueue(int totalLevels) {
        if (totalLevels <= 0) {
            throw new IllegalArgumentException("Должен быть хотя бы 1 уровень");
        }

        this.totalLevels = totalLevels;
        this.priorityMap = new HashMap<>();

        for (int i = 0; i < totalLevels; i++) {
            priorityMap.put(i, new LinkedList<>());
        }
    }


    public void add(T element, int priorityLevel) { //добавление элемента с указанием приоритета, высший - 0
        if (priorityLevel < 0 || priorityLevel >= totalLevels) {
            throw new IllegalArgumentException("Неверный уровень: " + priorityLevel);
        }
        priorityMap.get(priorityLevel).offer(element);
    }

    public T poll() { //извлечение и удаление эл-та с наивысшим приоритетом
        for (int level = 0; level < totalLevels; level++) {
            Queue<T> currentQueue = priorityMap.get(level);
            if (!currentQueue.isEmpty()) { //если очередь не пуста, извлекаем из нее элемент
                return currentQueue.poll();
            }
        }
        return null; //если все очереди пусты, возвращаем null
    }

    public T peek() { //просматривает эл-т с наивысшим приоритетом(без удаления)
        for (int level = 0; level < totalLevels; level++) {
            Queue<T> queue = priorityMap.get(level);
            if (!queue.isEmpty()) {
                return queue.peek();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    public int size() { //общее кол-во элементов во всех очередях
        int sum = 0;
        for (Queue<T> queue : priorityMap.values()) {
            sum += queue.size();
        }
        return sum;
    }

    public int sizeOfLevel(int priorityLevel) { //кол-во эл-тов в очереди с указанным приоритетом
        if (priorityLevel < 0 || priorityLevel >= totalLevels) {
            throw new IllegalArgumentException("Неверный уровень: " + priorityLevel);
        }
        return priorityMap.get(priorityLevel).size();
    }

    public void display() {
        System.out.println("Многослойная очередь (всего элементов: " + size() + ")");

        for (int i = 0; i < totalLevels; i++) {
            Queue<T> queue = priorityMap.get(i);
            if (!queue.isEmpty()) {
                System.out.println("  Приоритет " + i + ": " + queue + " (элементов: " + sizeOfLevel(i) + ")");
            }
        }

        if (isEmpty()) {
            System.out.println("Все очереди пусты.");
        }
    }

    public static void main(String[] args) {
        MultiLevelQueue<String> queue = new MultiLevelQueue<>(3);
        queue.display(); //Все очереди пусты

        queue.add("Высший приоритет", 0);
        queue.add("Средний", 1);
        queue.add("Низший", 2);
        queue.add("Высший", 0);
        queue.add("Еще средний", 1);

        queue.display();
        //Приоритет 0: [Высший приоритет, Высший] (элементов: 2)
        //Приоритет 1: [Средний, Еще средний] (элементов: 2)
        //Приоритет 2: [Низший] (элементов: 1)


        System.out.println("Размер уровня 0: " + queue.sizeOfLevel(0)); // 2
        System.out.println("Размер уровня 1: " + queue.sizeOfLevel(1)); // 2
        System.out.println("Размер уровня 2: " + queue.sizeOfLevel(2)); // 1

        System.out.println("Первый элемент: " + queue.peek()); //Высший приоритет

        System.out.println("Извлекаем: " + queue.poll()); //Высший приоритет
        System.out.println("Размер уровня 0: " + queue.sizeOfLevel(0)); // 1

        System.out.println("Извлекаем: " + queue.poll()); //Высший
        System.out.println("Размер уровня 0: " + queue.sizeOfLevel(0)); // 0

        System.out.println("Извлекаем: " + queue.poll()); //Средний

        queue.display();
        //Приоритет 1: [Еще средний] (элементов: 1)
        //Приоритет 2: [Низший] (элементов: 1)

    }
}